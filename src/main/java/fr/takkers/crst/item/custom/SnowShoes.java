package fr.takkers.crst.item.custom;

import com.google.common.collect.ImmutableMap;
import fr.takkers.crst.entity.client.armor.SnowShoesRenderer;
import fr.takkers.crst.util.ModArmorMaterials;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.example.client.renderer.armor.GeckoArmorRenderer;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class SnowShoes extends ArmorItem implements GeoItem {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    /*
    private static final Map<ArmorMaterial, MobEffectInstance> MATERIAL_TO_EFFECT_MAP =
            (new ImmutableMap.Builder<ArmorMaterial, MobEffectInstance>())
                    .put(ModArmorMaterials.FROST, new MobEffectInstance(MobEffects.LUCK, 200, 1)).build();

     */

    public SnowShoes(ArmorMaterial material, Type slot, Properties settings) {
        super(material, slot, settings);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private GeoArmorRenderer<?> renderer;

            @Override
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
                if (this.renderer == null)
                    this.renderer = new SnowShoesRenderer();

                this.renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);

                return this.renderer;
            }
        });
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController(this, "controller",
                0, this::predicate));
    }

    private PlayState predicate(AnimationState animationState) {
            animationState.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
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

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> tooltip, TooltipFlag pIsAdvanced) {
        if(Screen.hasShiftDown()){
            tooltip.add(Component.translatable("tooltip.crst.snow_shoes").withStyle(ChatFormatting.AQUA));
        }else {
            tooltip.add(Component.translatable("tooltip.crst.shift").withStyle(ChatFormatting.YELLOW));
        }
        super.appendHoverText(pStack, pLevel, tooltip, pIsAdvanced);
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