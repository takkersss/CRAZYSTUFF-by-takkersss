package fr.takkers.crst.item.custom;

import com.google.common.collect.ImmutableMap;
import fr.takkers.crst.item.client.OrbitalSanderRenderer;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.apache.logging.log4j.core.jmx.Server;
import org.jetbrains.annotations.Nullable;
import software.bernie.example.client.renderer.item.PistolRenderer;
import software.bernie.example.registry.SoundRegistry;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.ClientUtils;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

import static fr.takkers.crst.item.ModItems.ORBITAL_SANDER;

public class OrbitalSander extends Item implements GeoItem {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final RawAnimation POPUP_ANIM = RawAnimation.begin().thenPlay("animation.ponceuse.idle");

    protected static final Map<Block, Block> STRIPPABLES = (new ImmutableMap.Builder<Block, Block>())
            .put(Blocks.OAK_WOOD, Blocks.STRIPPED_OAK_WOOD).put(Blocks.OAK_LOG, Blocks.STRIPPED_OAK_LOG).put(Blocks.DARK_OAK_WOOD, Blocks.STRIPPED_DARK_OAK_WOOD)
            .put(Blocks.DARK_OAK_LOG, Blocks.STRIPPED_DARK_OAK_LOG).put(Blocks.ACACIA_WOOD, Blocks.STRIPPED_ACACIA_WOOD).put(Blocks.ACACIA_LOG, Blocks.STRIPPED_ACACIA_LOG)
            .put(Blocks.CHERRY_WOOD, Blocks.STRIPPED_CHERRY_WOOD).put(Blocks.CHERRY_LOG, Blocks.STRIPPED_CHERRY_LOG).put(Blocks.BIRCH_WOOD, Blocks.STRIPPED_BIRCH_WOOD)
            .put(Blocks.BIRCH_LOG, Blocks.STRIPPED_BIRCH_LOG).put(Blocks.JUNGLE_WOOD, Blocks.STRIPPED_JUNGLE_WOOD).put(Blocks.JUNGLE_LOG, Blocks.STRIPPED_JUNGLE_LOG)
            .put(Blocks.SPRUCE_WOOD, Blocks.STRIPPED_SPRUCE_WOOD).put(Blocks.SPRUCE_LOG, Blocks.STRIPPED_SPRUCE_LOG).put(Blocks.WARPED_STEM, Blocks.STRIPPED_WARPED_STEM)
            .put(Blocks.WARPED_HYPHAE, Blocks.STRIPPED_WARPED_HYPHAE).put(Blocks.CRIMSON_STEM, Blocks.STRIPPED_CRIMSON_STEM).put(Blocks.CRIMSON_HYPHAE, Blocks.STRIPPED_CRIMSON_HYPHAE)
            .put(Blocks.MANGROVE_WOOD, Blocks.STRIPPED_MANGROVE_WOOD).put(Blocks.MANGROVE_LOG, Blocks.STRIPPED_MANGROVE_LOG).put(Blocks.BAMBOO_BLOCK, Blocks.STRIPPED_BAMBOO_BLOCK).build();

    protected static final Map<Block, Block> POLISHABLES = (new ImmutableMap.Builder<Block, Block>())
            // DIORITE / GRANITE / ANDESITE
            .put(Blocks.DIORITE, Blocks.POLISHED_DIORITE).put(Blocks.GRANITE, Blocks.POLISHED_GRANITE).put(Blocks.ANDESITE, Blocks.POLISHED_ANDESITE)
            .put(Blocks.DIORITE_SLAB, Blocks.POLISHED_DIORITE_SLAB).put(Blocks.GRANITE_SLAB, Blocks.POLISHED_GRANITE_SLAB).put(Blocks.ANDESITE_SLAB, Blocks.POLISHED_ANDESITE_SLAB)
            .put(Blocks.DIORITE_STAIRS, Blocks.POLISHED_DIORITE_STAIRS).put(Blocks.GRANITE_STAIRS, Blocks.POLISHED_GRANITE_STAIRS).put(Blocks.ANDESITE_STAIRS, Blocks.POLISHED_ANDESITE_STAIRS)
            // BLACKSTONE
            .put(Blocks.BLACKSTONE, Blocks.POLISHED_BLACKSTONE).put(Blocks.BLACKSTONE_SLAB, Blocks.POLISHED_BLACKSTONE_SLAB).put(Blocks.BLACKSTONE_STAIRS, Blocks.POLISHED_BLACKSTONE_STAIRS)
            .put(Blocks.BLACKSTONE_WALL, Blocks.POLISHED_BLACKSTONE_WALL)
            // DEEPSLATE
            .put(Blocks.DEEPSLATE, Blocks.POLISHED_DEEPSLATE)
            // COBBLE
            .put(Blocks.COBBLESTONE, Blocks.STONE)
            // SANDSTONE
            .put(Blocks.SANDSTONE, Blocks.CUT_SANDSTONE).put(Blocks.SANDSTONE_SLAB, Blocks.SMOOTH_SANDSTONE_SLAB).put(Blocks.SANDSTONE_STAIRS, Blocks.SMOOTH_SANDSTONE_STAIRS)
            .put(Blocks.RED_SANDSTONE, Blocks.CUT_RED_SANDSTONE).put(Blocks.RED_SANDSTONE_SLAB, Blocks.SMOOTH_RED_SANDSTONE_SLAB).put(Blocks.RED_SANDSTONE_STAIRS, Blocks.SMOOTH_RED_SANDSTONE_STAIRS)
            // QUARTZ
            .put(Blocks.QUARTZ_BLOCK, Blocks.SMOOTH_QUARTZ).put(Blocks.QUARTZ_SLAB, Blocks.SMOOTH_QUARTZ_SLAB).put(Blocks.QUARTZ_STAIRS, Blocks.SMOOTH_QUARTZ_STAIRS)
            .put(Blocks.BASALT, Blocks.SMOOTH_BASALT)
            // DIRT
            .put(Blocks.DIRT, Blocks.DIRT_PATH)
            .put(Blocks.GRASS_BLOCK, Blocks.DIRT_PATH)
            .put(Blocks.COARSE_DIRT, Blocks.DIRT_PATH)
            .put(Blocks.ROOTED_DIRT, Blocks.DIRT_PATH)
            .build();

    public OrbitalSander(Properties pProperties) {
        super(pProperties);
        SingletonGeoAnimatable.registerSyncedAnimatable(this);
    }

    // GECKOLIB

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private OrbitalSanderRenderer renderer = null;

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if (this.renderer == null)
                    this.renderer = new OrbitalSanderRenderer();

                return this.renderer;
            }
        });
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "popup_controller", 0, state -> PlayState.STOP)
                .triggerableAnim("ponceuse_active", POPUP_ANIM));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    // INTERACT

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        boolean eventOk = false;
        Block bToPlace = null;

        Player p = pContext.getPlayer();
        Level l = p.level();
        ItemStack stack = p.getItemInHand(pContext.getHand());

        if (!l.isClientSide()) {
            if (stack.getItem() == ORBITAL_SANDER.get()) {

                boolean isPolishMode;
                if(p.getItemInHand(pContext.getHand()).getTag() == null) {
                    isPolishMode = true;
                    setPolishMode(stack, isPolishMode);
                }else {isPolishMode = stack.getTag().getBoolean("isPolishMode");}

                Block b = l.getBlockState(pContext.getClickedPos()).getBlock();

                if(isPolishMode){
                    if (POLISHABLES.containsKey(b)) {
                        bToPlace = POLISHABLES.get(b);
                    } else if (STRIPPABLES.containsKey(b)) {
                        bToPlace = STRIPPABLES.get(b);
                    }
                }else{
                    for (Map.Entry<Block, Block> entry : STRIPPABLES.entrySet()) {
                        if (Objects.equals(b, entry.getValue())) {
                            bToPlace = entry.getKey();
                            break;
                        }
                    }
                    for (Map.Entry<Block, Block> entry : POLISHABLES.entrySet()) {
                        if (Objects.equals(b, entry.getValue())) {
                            bToPlace = entry.getKey();
                            break;
                        }
                    }
                }
                if(bToPlace != null){eventOk = true;}
            }
        }
        if(eventOk && stack.getItem() == ORBITAL_SANDER.get()){
            l.setBlockAndUpdate(pContext.getClickedPos(), bToPlace.defaultBlockState());
            if (l instanceof ServerLevel serverLevel){
                triggerAnim(p, GeoItem.getOrAssignId(stack, serverLevel), "popup_controller", "ponceuse_active");
            }
            l.playSound(null, pContext.getClickedPos(), SoundEvents.COPPER_PLACE, SoundSource.BLOCKS, 1f, 1f);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level l, Player p, InteractionHand hand) {
        ItemStack stack = p.getItemInHand(hand);
        if(!l.isClientSide()){
            if(p.isCrouching()){
                boolean isPolishMode;
                if(stack.getTag() == null) {
                    isPolishMode = true;
                    setPolishMode(stack, false);
                }else{
                    isPolishMode = stack.getTag().getBoolean("isPolishMode");
                    setPolishMode(stack, !isPolishMode);
                }

                if(!isPolishMode){
                    p.sendSystemMessage(Component.literal("Sander sets to POLISH mode."));
                }else p.sendSystemMessage(Component.literal("Sander sets to UNpolish mode."));
            }
        }
        return super.use(l,p,hand);
    }

    public static void setPolishMode(ItemStack pCrossbowStack, boolean isPolishMode) {
        CompoundTag compoundtag = pCrossbowStack.getOrCreateTag();
        compoundtag.putBoolean("isPolishMode", isPolishMode);
    }

    // HOVER TEXT
    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> tooltip, TooltipFlag pIsAdvanced) {
        if(Screen.hasShiftDown()){
            tooltip.add(Component.translatable("tooltip.crst.orbital_sander").withStyle(ChatFormatting.AQUA));
        }else {
            tooltip.add(Component.translatable("tooltip.crst.shift").withStyle(ChatFormatting.YELLOW));
        }
        super.appendHoverText(pStack, pLevel, tooltip, pIsAdvanced);
    }
}
