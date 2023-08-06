package fr.takkers.crst.util;

import fr.takkers.crst.CRST;
import fr.takkers.crst.item.ModItems;
import net.minecraft.Util;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;

import java.util.EnumMap;
import java.util.function.Supplier;

public enum ModArmorMaterials implements ArmorMaterial {
    FROST("frost", 17, Util.make(new EnumMap<>(ArmorItem.Type.class), (p_266655_) -> {
        p_266655_.put(ArmorItem.Type.BOOTS, 1);
        p_266655_.put(ArmorItem.Type.LEGGINGS, 2);
        p_266655_.put(ArmorItem.Type.CHESTPLATE, 3);
        p_266655_.put(ArmorItem.Type.HELMET, 1);
    }), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> {
        return Ingredient.of(Tags.Items.STRING);
    }),
    SHADOW("shadow", 35, Util.make(new EnumMap<>(ArmorItem.Type.class), (p_266655_) -> {
        p_266655_.put(ArmorItem.Type.BOOTS, 2);
        p_266655_.put(ArmorItem.Type.LEGGINGS, 6);
        p_266655_.put(ArmorItem.Type.CHESTPLATE, 8);
        p_266655_.put(ArmorItem.Type.HELMET, 2);
    }), 14, SoundEvents.ARMOR_EQUIP_NETHERITE, 1.8F, 0.3F, () -> {
        return Ingredient.of(ModItems.SHADOWWALKER_SCALES.get());
    }),
    PWD_SHADOW("pwd_shadow", 70, Util.make(new EnumMap<>(ArmorItem.Type.class), (p_266655_) -> {
        p_266655_.put(ArmorItem.Type.BOOTS, 2);
        p_266655_.put(ArmorItem.Type.LEGGINGS, 6);
        p_266655_.put(ArmorItem.Type.CHESTPLATE, 12);
        p_266655_.put(ArmorItem.Type.HELMET, 2);
    }), 14, SoundEvents.ARMOR_EQUIP_GOLD, 2.5F, 0.3F, () -> {
        return Ingredient.of(ModItems.SHADOWWALKER_SCALES.get());
    });

 /*
     FROST("frost", 17, new int[]{1, 2, 3, 1}, 15, SoundEvents.ARMOR_EQUIP_LEATHER,
            0.0F, 0.0F, () -> Ingredient.of(Tags.Items.STRING)),
    SHADOW("shadowwalker_scales", 17, new int[]{2, 6, 9, 2}, 15, SoundEvents.ARMOR_EQUIP_NETHERITE,
            0.0F, 0.0F, () -> Ingredient.of(ModItems.SHADOWWALKER_SCALES.get()))
  */

    public static final StringRepresentable.EnumCodec<ArmorMaterials> CODEC = StringRepresentable.fromEnum(ArmorMaterials::values);
    private static final EnumMap<ArmorItem.Type, Integer> HEALTH_FUNCTION_FOR_TYPE = Util.make(new EnumMap<>(ArmorItem.Type.class), (p_266653_) -> {
        p_266653_.put(ArmorItem.Type.BOOTS, 13);
        p_266653_.put(ArmorItem.Type.LEGGINGS, 15);
        p_266653_.put(ArmorItem.Type.CHESTPLATE, 16);
        p_266653_.put(ArmorItem.Type.HELMET, 11);
    });
    private final String name;
    private final int durabilityMultiplier;
    private final EnumMap<ArmorItem.Type, Integer> protectionFunctionForType;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    private ModArmorMaterials(String p_268171_, int p_268303_, EnumMap<ArmorItem.Type, Integer> p_267941_, int p_268086_, SoundEvent p_268145_, float p_268058_, float p_268180_, Supplier<Ingredient> p_268256_) {
        this.name = p_268171_;
        this.durabilityMultiplier = p_268303_;
        this.protectionFunctionForType = p_267941_;
        this.enchantmentValue = p_268086_;
        this.sound = p_268145_;
        this.toughness = p_268058_;
        this.knockbackResistance = p_268180_;
        this.repairIngredient = new LazyLoadedValue<>(p_268256_);
    }

    public int getDurabilityForType(ArmorItem.Type p_266745_) {
        return HEALTH_FUNCTION_FOR_TYPE.get(p_266745_) * this.durabilityMultiplier;
    }

    public int getDefenseForType(ArmorItem.Type p_266752_) {
        return this.protectionFunctionForType.get(p_266752_);
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public SoundEvent getEquipSound() {
        return this.sound;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    public String getName() {
        return this.name;
        //return CRST.MODID + ":" + this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }

    public String getSerializedName() {
        return this.name;
    }
}