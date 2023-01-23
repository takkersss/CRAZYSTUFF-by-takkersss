package fr.takkers.crst.enchantment;

import fr.takkers.crst.CRST;
import fr.takkers.crst.enchantment.custom.LightningStrikeEnchantment;
import fr.takkers.crst.enchantment.custom.TNTStrikeEnchantment;
import fr.takkers.crst.enchantment.custom.WeaknessEnchantment;
import fr.takkers.crst.item.ModItems;
import fr.takkers.crst.util.ModCreativeTab;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS =
            DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, CRST.MODID);



    public static RegistryObject<Enchantment> LIGHTNING_STRIKE =
            ENCHANTMENTS.register("lightning_strike",
                    () -> new LightningStrikeEnchantment(Enchantment.Rarity.RARE,
                            EnchantmentCategory.WEAPON, EquipmentSlot.MAINHAND));

    public static RegistryObject<Enchantment> TNT_STRIKE =
            ENCHANTMENTS.register("tnt_strike",
                    () -> new TNTStrikeEnchantment(Enchantment.Rarity.RARE,
                            EnchantmentCategory.WEAPON, EquipmentSlot.MAINHAND));

    public static RegistryObject<Enchantment> POWERLESS_ENEMY =
            ENCHANTMENTS.register("powerless_enemy",
                    () -> new WeaknessEnchantment(Enchantment.Rarity.UNCOMMON,
                            EnchantmentCategory.WEAPON, EquipmentSlot.MAINHAND));




    public static void register(IEventBus eventBus) {
        ENCHANTMENTS.register(eventBus);
    }
}