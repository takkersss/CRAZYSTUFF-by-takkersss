package fr.takkers.crst.item;

import fr.takkers.crst.CRST;
import fr.takkers.crst.block.ModBlocks;
import fr.takkers.crst.entity.ModEntityTypes;
import fr.takkers.crst.item.custom.*;
import fr.takkers.crst.util.ModArmorMaterials;
import fr.takkers.crst.util.ModCreativeTab;
import fr.takkers.crst.util.ModMaterialTiers;
import net.minecraft.client.particle.TotemParticle;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.GlazedTerracottaBlock;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CRST.MODID);

    public static final RegistryObject<Item> RED_WAND = ITEMS.register("red_wand", ()-> new RedWand(new Item.Properties().durability(128)));
    public static final RegistryObject<Item> LEVITATION_WAND = ITEMS.register("levitation_wand", ()-> new LevitationWand(new Item.Properties().durability(2033)));
    //public static final RegistryObject<Item> LEVITATION_BALL = ITEMS.register("levitation_ball", ()-> new LevitationBallItem((new Item.Properties()).stacksTo(16)));
    public static final RegistryObject<Item> SHADOWWALKER_SWORD = ITEMS.register("shadowwalker_sword", ()-> new ShadowWalkerSword(ModMaterialTiers.SHADOW_WALKER_TIER, 6, -2.2f, new Item.Properties()));
    public static final RegistryObject<Item> SHADOWWALKER_SPAWN_EGG = ITEMS.register("shadowwalker_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.SHADOW_WALKER,0x0F1012, 0xD4EB0E,
                    new Item.Properties()));
    public static final RegistryObject<Item> TRIANGULAR_ARTEFACT = ITEMS.register("triangular_artefact", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DIAMOND_TIP = ITEMS.register("diamond_tip", ()-> new Item(new Item.Properties().durability(5)) {
        @Override
        public boolean isEnchantable(ItemStack pStack) {
            return false;
        }
    });

    public static final RegistryObject<Item> OIL_FLASK = ITEMS.register("oil_flask", ()-> new Item(new Item.Properties().craftRemainder(Items.GLASS_BOTTLE)));
    public static final RegistryObject<Item> MORTAR = ITEMS.register("mortar", ()-> new Mortar(new Item.Properties()));
    public static final RegistryObject<Item> CENTER_STONE_BAR = ITEMS.register("center_stone_bar", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SNOW_SHOES = ITEMS.register("snow_shoes", ()-> new SnowShoes(ModArmorMaterials.FROST, ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final RegistryObject<Item> UNUSUAL_TOTEM = ITEMS.register("unusual_totem", ()-> new UnusualTotem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> SHADOWWALKER_SCALES = ITEMS.register("shadowwalker_scales", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PWD_KLNG_GNOMON_ITEM = ITEMS.register("pwd_klng_gnomon", ()-> new PwdKLNGGnomonItem(ModBlocks.PWD_KLNG_GNOMON.get(), new Item.Properties())); // same name as the block (BlockItem here)
    public static final RegistryObject<Item> ARTEFACT_BALL = ITEMS.register("artefact_ball", () -> new Item(new Item.Properties().stacksTo(1)));


    public static final RegistryObject<Item> SHADOWWALKER_HELMET = ITEMS.register("shadowwalker_helmet", ()-> new ShadowWalkerArmorItem(ModArmorMaterials.SHADOW, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> SHADOWWALKER_CHESTPLATE = ITEMS.register("shadowwalker_chestplate", ()-> new ShadowWalkerArmorItem(ModArmorMaterials.SHADOW, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> PWD_SHADOWWALKER_CHESTPLATE = ITEMS.register("pwd_shadowwalker_chestplate", ()-> new PwdShadowWalkerArmorItem(ModArmorMaterials.PWD_SHADOW, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> SHADOWWALKER_LEGGINGS = ITEMS.register("shadowwalker_leggings", ()-> new ShadowWalkerArmorItem(ModArmorMaterials.SHADOW, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> SHADOWWALKER_BOOTS = ITEMS.register("shadowwalker_boots", ()-> new ShadowWalkerArmorItem(ModArmorMaterials.SHADOW, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
