package fr.takkers.crst.block;

import fr.takkers.crst.CRST;
import fr.takkers.crst.block.custom.*;
import fr.takkers.crst.item.ModItems;
import fr.takkers.crst.util.ModCreativeTab;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;


public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, CRST.MODID);


/*
    public static final RegistryObject<Block> SOLAR_FURNACE = registerBlock("solar_furnace",
            () -> new SolarFurnace(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(7f).requiresCorrectToolForDrops()), ModCreativeTab.CRST_CREATIVE_TAB);

 */

    public static final RegistryObject<Block> SHADOWWALKER_HEAD = registerBlock("shadowwalker_head",
            () -> new ShadowWalkerHead(BlockBehaviour.Properties.of(Material.DECORATION).strength(1f)), "tooltip.crst.shadowwalker_head");

    public static final RegistryObject<Block> ECONOMY_CONTROL_OFFICE = registerBlock("economy_control_office",
            () -> new EconomyControlOffice(BlockBehaviour.Properties.of(Material.WOOD).strength(3.5f).sound(SoundType.WOOD)), "tooltip.crst.economy_control_office");

    public static final RegistryObject<Block> ARTEFACT_EXTRACTOR = registerBlock("artefact_extractor",
            () -> new ArtefactExtractor(BlockBehaviour.Properties.of(Material.METAL).strength(3.5f).sound(SoundType.METAL)));

    //Center Stone
    public static final RegistryObject<Block> CENTER_STONE = registerBlock("center_stone", ()-> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final RegistryObject<Block> CENTER_STONE_SLAB = registerBlock("center_stone_slab", ()-> new SlabBlock(BlockBehaviour.Properties.copy(CENTER_STONE.get())));
    public static final RegistryObject<Block> CENTER_STONE_STAIRS = registerBlock("center_stone_stairs", ()-> new StairBlock(CENTER_STONE.get().defaultBlockState(), BlockBehaviour.Properties.copy(CENTER_STONE.get())));
    public static final RegistryObject<Block> CENTER_STONE_WALL = registerBlock("center_stone_wall", ()-> new WallBlock(BlockBehaviour.Properties.copy(CENTER_STONE.get())));

    public static final RegistryObject<Block> DENSE_VEGETATION = registerBlock("dense_vegetation", ()-> new DenseVegetation(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().strength(0.35f).sound(SoundType.GRASS)));

    public static final RegistryObject<Block> SHEET_METAL = registerBlock("sheet_metal", ()-> new SheetMetal(BlockBehaviour.Properties.of(Material.METAL).strength(4f, 15f).requiresCorrectToolForDrops().sound(SoundType.METAL)));

    public static final RegistryObject<Block> CHRISTMAS_BALL = registerBlock("christmas_ball", ()-> new ChristmasBall(BlockBehaviour.Properties.of(Material.GLASS).strength(0.5f).sound(SoundType.GLASS).noOcclusion()
            .lightLevel((pState) -> {
                return pState.getValue(ChristmasBall.LIT) ? 0 : 4 + 3 * pState.getValue(ChristmasBall.BALL);
            })
    ));
    
    //register tooltip
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, String tooltipKey) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tooltipKey);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, String tooltipKey) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()) {
            @Override
            public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
                if (Screen.hasShiftDown()) {
                    pTooltip.add(Component.translatable(tooltipKey));
                } else {
                    pTooltip.add(Component.translatable("tooltip.crst.shift"));
                }
            }
        });
    }


    //register Block
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}