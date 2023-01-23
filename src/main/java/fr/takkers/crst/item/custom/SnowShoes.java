package fr.takkers.crst.item.custom;

import com.google.common.collect.ImmutableMap;
import fr.takkers.crst.util.ModArmorMaterials;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.item.GeoArmorItem;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.Map;

public class SnowShoes extends GeoArmorItem implements IAnimatable {

    public AnimationFactory factory = GeckoLibUtil.createFactory(this);

    private static final Map<ArmorMaterial, MobEffectInstance> MATERIAL_TO_EFFECT_MAP =
            (new ImmutableMap.Builder<ArmorMaterial, MobEffectInstance>())
                    .put(ModArmorMaterials.FROST, new MobEffectInstance(MobEffects.LUCK, 200, 1)).build();

    public SnowShoes(ArmorMaterial material, EquipmentSlot slot, Properties settings) {
        super(material, slot, settings);
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<SnowShoes>(this, "controller",
                20, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    private <P extends IAnimatable> PlayState predicate(AnimationEvent<P> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", new ILoopType() {
            @Override
            public boolean isRepeatingAfterEnd() {
                return true;
            }
        }));
        return PlayState.CONTINUE;
    }

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        if(!world.isClientSide()) {
                BlockPos blockPosUnderPlayer = player.getOnPos();
                int blockX = blockPosUnderPlayer.getX();
                int blockY = blockPosUnderPlayer.getY();
                int blockZ = blockPosUnderPlayer.getZ();

                Direction playerDir = player.getMotionDirection();
                Block blockUnderPlayer = world.getBlockState(blockPosUnderPlayer).getBlock();
                BlockState blockstateUnderPlayer = world.getBlockState(blockPosUnderPlayer);
                //System.out.println("\u001B[34m" + "blockPosUnderPlayer = " + blockPosUnderPlayer + "\u001B[0m");
                //System.out.println("\u001B[34m" + "blockUnderPlayer = " + blockUnderPlayer + "\u001B[0m");
                //System.out.println("\u001B[34m" + "playerDir = " + playerDir + "\u001B[0m");

                if(blockUnderPlayer == Blocks.POWDER_SNOW) {
                    world.setBlockAndUpdate(blockPosUnderPlayer, Blocks.SNOW_BLOCK.defaultBlockState());

                }

                if(playerDir == Direction.EAST) {
                    BlockPos blockPosFace = new BlockPos(blockX + 1, blockY , blockZ);
                    Block blockFace = world.getBlockState(blockPosFace).getBlock();
                    if(blockFace == Blocks.POWDER_SNOW) {
                        world.setBlockAndUpdate(blockPosFace, Blocks.SNOW_BLOCK.defaultBlockState());
                    }
                }

                if(playerDir == Direction.NORTH) {
                    BlockPos blockPosFace = new BlockPos(blockX, blockY , blockZ - 1);
                    Block blockFace = world.getBlockState(blockPosFace).getBlock();
                    if(blockFace == Blocks.POWDER_SNOW) {
                        world.setBlockAndUpdate(blockPosFace, Blocks.SNOW_BLOCK.defaultBlockState());
                    }
                }

                if(playerDir == Direction.WEST) {
                    BlockPos blockPosFace = new BlockPos(blockX -1 , blockY , blockZ);
                    Block blockFace = world.getBlockState(blockPosFace).getBlock();
                    if(blockFace == Blocks.POWDER_SNOW) {
                        world.setBlockAndUpdate(blockPosFace, Blocks.SNOW_BLOCK.defaultBlockState());
                    }
                }

                if(playerDir == Direction.SOUTH) {
                    BlockPos blockPosFace = new BlockPos(blockX , blockY , blockZ + 1);
                    Block blockFace = world.getBlockState(blockPosFace).getBlock();
                    if(blockFace == Blocks.POWDER_SNOW) {
                        world.setBlockAndUpdate(blockPosFace, Blocks.SNOW_BLOCK.defaultBlockState());
                    }
                }


    }
    }

/*
    private void evaluateArmorEffects(Player player) {
        for (Map.Entry<ArmorMaterial, MobEffectInstance> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {
            ArmorMaterial mapArmorMaterial = entry.getKey();
            MobEffectInstance mapStatusEffect = entry.getValue();

            if(hasCorrectArmorOn(mapArmorMaterial, player)) {
                addStatusEffectForMaterial(player, mapArmorMaterial, mapStatusEffect);
            }
        }
    }

 */
/*
    private void addStatusEffectForMaterial(Player player, ArmorMaterial mapArmorMaterial, MobEffectInstance mapStatusEffect) {
        boolean hasPlayerEffect = player.hasEffect(mapStatusEffect.getEffect());

        if(hasCorrectArmorOn(mapArmorMaterial, player) && !hasPlayerEffect) {
            player.addEffect(new MobEffectInstance(mapStatusEffect.getEffect(),
                    mapStatusEffect.getDuration(), mapStatusEffect.getAmplifier()));

            //if(new Random().nextFloat() > 0.6f) { // 40% of damaging the armor! Possibly!
            //    player.getInventory().hurtArmor(DamageSource.MAGIC, 1f, new int[]{0, 1, 2, 3});
            //}
        }
    }

 */

    private boolean hasFullSuitOfArmorOn(Player player) {
        ItemStack boots = player.getInventory().getArmor(0);
        ItemStack leggings = player.getInventory().getArmor(1);
        ItemStack breastplate = player.getInventory().getArmor(2);
        ItemStack helmet = player.getInventory().getArmor(3);

        return !helmet.isEmpty() && !breastplate.isEmpty()
                && !leggings.isEmpty() && !boots.isEmpty();
    }
/*
    private boolean hasCorrectArmorOn(ArmorMaterial material, Player player) {
        for (ItemStack armorStack: player.getInventory().armor) {
            if(!(armorStack.getItem() instanceof ArmorItem)) {
                return false;
            }
        }

        ArmorItem boots = ((ArmorItem)player.getInventory().getArmor(0).getItem());
        ArmorItem leggings = ((ArmorItem)player.getInventory().getArmor(1).getItem());
        ArmorItem breastplate = ((ArmorItem)player.getInventory().getArmor(2).getItem());
        ArmorItem helmet = ((ArmorItem)player.getInventory().getArmor(3).getItem());

        return helmet.getMaterial() == material && breastplate.getMaterial() == material &&
                leggings.getMaterial() == material && boots.getMaterial() == material;
    }

 */


    @Override
    public boolean isEnchantable(ItemStack pStack) {
        return true;
    }



}