package fr.takkers.crst.entity.custom;

import fr.takkers.crst.block.ModBlocks;
import fr.takkers.crst.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class ShadowWalkerEntity extends Monster implements IAnimatable {
    private AnimationFactory factory = GeckoLibUtil.createFactory(this);

    public ShadowWalkerEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }


    protected void registerGoals() {
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.04D, true));
        this.goalSelector.addGoal(2, new FloatGoal(this));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.targetSelector.addGoal(6, new NearestAttackableTargetGoal<>(this, Zombie.class, true));
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(8, (new HurtByTargetGoal(this)).setAlertOthers());
    }

    public static AttributeSupplier setAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 50.0D)
                .add(Attributes.ATTACK_DAMAGE, 4.0f)
                .add(Attributes.ATTACK_SPEED, 2.0f)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1)
                .add(Attributes.ATTACK_KNOCKBACK, 1)
                .add(Attributes.ARMOR_TOUGHNESS, 1)
                .add(Attributes.MOVEMENT_SPEED, 0.35f).build();
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.shadowwalker.walk", () -> true));
            return PlayState.CONTINUE;
        }

        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.shadowwalker.idle", () -> true));
        return PlayState.CONTINUE;
    }

    private PlayState attackPredicate(AnimationEvent event) {
        if(this.swinging && event.getController().getAnimationState().equals(AnimationState.Stopped)) {
            event.getController().markNeedsReload();
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.shadowwalker.attack", () -> false));
            this.swinging = false;
        }

        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller",
                0, this::predicate));
        data.addAnimationController(new AnimationController(this, "attackController",
                0, this::attackPredicate));

    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }



 /*
    protected void populateDefaultEquipmentSlots(DifficultyInstance pDifficulty) {
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(ModItems.SHADOWWALKER_SWORD.get()));
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        this.populateDefaultEquipmentSlots(pDifficulty);
        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }

 */

    public boolean canHoldItem(ItemStack pStack) {
        return false;
    }

    protected void dropCustomDeathLoot(DamageSource pSource, int pLooting, boolean pRecentlyHit) {
        super.dropCustomDeathLoot(pSource, pLooting, pRecentlyHit);
        Entity entity = pSource.getEntity();
        if (entity instanceof Creeper) {
            Creeper creeper = (Creeper)entity;
            if (creeper.canDropMobsSkull()) {
                ItemStack itemstack = this.getSkull();
                if (!itemstack.isEmpty()) {
                    creeper.increaseDroppedSkulls();
                    this.spawnAtLocation(itemstack);
                }
            }
        }

    }

    protected ItemStack getSkull() {
        return new ItemStack(ModBlocks.SHADOWWALKER_HEAD.get());
    }

    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.SKELETON_STEP, 0.15F, 1.0F);
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.WITHER_SKELETON_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.WITHER_SKELETON_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.IRON_GOLEM_DEATH;
    }

    protected float getSoundVolume() {
        return 0.2F;
    }
}
