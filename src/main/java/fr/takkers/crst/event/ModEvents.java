package fr.takkers.crst.event;

import com.google.common.collect.ImmutableMap;
import fr.takkers.crst.CRST;
import fr.takkers.crst.block.ModBlocks;
import fr.takkers.crst.effect.ModEffects;
import fr.takkers.crst.enchantment.ModEnchantments;
import fr.takkers.crst.item.ModItems;
import fr.takkers.crst.networking.ModMessages;
import fr.takkers.crst.networking.packets.WardenEffectPacket;
import fr.takkers.crst.particle.ModParticles;
import fr.takkers.crst.sound.ModSounds;
import fr.takkers.crst.util.ModRayTrace;
import fr.takkers.crst.villager.ModVillagers;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.util.Unit;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.behavior.warden.SonicBoom;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ShulkerBullet;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DragonEggBlock;
import net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.NoteBlockEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Map;

import static fr.takkers.crst.item.ModItems.*;

@Mod.EventBusSubscriber(modid = CRST.MODID)
public class ModEvents {

    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        if(event.getType() == ModVillagers.ECONOMY_GOVERNOR.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            ItemStack shadowWalkerSword = new ItemStack(ModItems.SHADOWWALKER_SWORD.get(), 1);
            ItemStack luminousStrikeBook = EnchantedBookItem.createForEnchantment(new EnchantmentInstance(ModEnchantments.LIGHTNING_STRIKE.get(),1));


            trades.get(1).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.GOLD_INGOT, 35),
                    luminousStrikeBook,10,12,0.1F));
            trades.get(2).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 20),
                    shadowWalkerSword,2,12,0.05F));

        }
    }

    @SubscribeEvent // Méthode côté client
    public static void onRightClickedTotem(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();

        if(event.getLevel().isClientSide()){
            if(player.getMainHandItem().getItem() == ModItems.UNUSUAL_TOTEM.get()){
                if (!player.hasEffect(ModEffects.CROUCH_TELEPORTATION_EFFECT.get())){

                    Minecraft.getInstance().gameRenderer.displayItemActivation(new ItemStack(ModItems.UNUSUAL_TOTEM.get(), 1));
                    Minecraft.getInstance().particleEngine.createTrackingEmitter(player, ModParticles.UNUSUAL_TOTEM_PARTICLES.get(), 30);
                    //player.getLevel().playLocalSound(player.getX(), player.getY(), player.getZ(), ModSounds.USED_UNUSUAL_TOTEM.get(), player.getSoundSource(), 0.5F, 1.0F, false);
                    player.playSound(ModSounds.USED_UNUSUAL_TOTEM.get(), 0.4F, 1.0F);
                    player.addEffect(new MobEffectInstance(ModEffects.CROUCH_TELEPORTATION_EFFECT.get(), 10000, 0));
                    player.getMainHandItem().shrink(1);
                }
            }
        }
    }

    @SubscribeEvent // Méthode BOTH SIDE
    public static void onRightClickedArmorEntity(PlayerInteractEvent.EntityInteract event) {
        if(!(event.getEntity() instanceof Player)) return;
        Player player = event.getEntity();
        ItemStack armorChestplate = player.getItemBySlot(EquipmentSlot.CHEST);
        if(!event.getLevel().isClientSide()){
            if(armorChestplate.getItem() != PWD_SHADOWWALKER_CHESTPLATE.get()){
                return;
            }else{
                ServerPlayer sPlayer = (ServerPlayer) player;

                if(event.getHand() == InteractionHand.MAIN_HAND && sPlayer.getMainHandItem().isEmpty()){
                    ServerLevel level = (ServerLevel) event.getLevel();

                    ModRayTrace rayTrace = new ModRayTrace();
                    LivingEntity rayTraceResult = rayTrace.getEntityInCrosshair(15, sPlayer, level);
                    Mob mob = (Mob)rayTraceResult;

                    if(rayTraceResult != null){
                        Vec3 vec3 = sPlayer.position().add(0.0D, (double)1.6F, 0.0D);
                        Vec3 vec31 = mob.getEyePosition().subtract(vec3);
                        Vec3 vec32 = vec31.normalize();

                        // Particles
                        for(int i = 1; i < Mth.floor(vec31.length()) + 7; ++i) {
                            Vec3 vec33 = vec3.add(vec32.scale((double)i));
                            level.sendParticles(ParticleTypes.SONIC_BOOM, vec33.x, vec33.y, vec33.z, 1, 0.0D, 0.0D, 0.0D, 0.0D);
                        }

                        level.playSound(null, sPlayer.getOnPos(), SoundEvents.WARDEN_SONIC_BOOM, SoundSource.PLAYERS, 0.7F, 1.0F);

                        // Fracasser et pousser le mob
                        mob.hurt(level.damageSources().sonicBoom(sPlayer), 5.0F);
                        double d1 = 0.5D * (1.0D - mob.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
                        double d0 = 2.5D * (1.0D - mob.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
                        mob.push(vec32.x() * d0, vec32.y() * d1, vec32.z() * d0);

                        // Endommager et casser l'item
                        player.getItemBySlot(EquipmentSlot.CHEST).hurtAndBreak(1, sPlayer, (p_150845_) -> {
                            p_150845_.broadcastBreakEvent(player.getItemBySlot(EquipmentSlot.CHEST).getEquipmentSlot());
                        });
                    }
                }
            }
        }
    }

    @SubscribeEvent // Client side
    public static void onRightClickedArmorHand(PlayerInteractEvent.RightClickEmpty event) {
        if(!(event.getEntity() instanceof Player)) return;
        Player player = event.getEntity();
        ItemStack armorChestplate = player.getItemBySlot(EquipmentSlot.CHEST);
        if(event.getLevel().isClientSide()){
            if(armorChestplate.getItem() != PWD_SHADOWWALKER_CHESTPLATE.get()){
                return;
            }else{
                if(event.getHand() == InteractionHand.MAIN_HAND){
                    ModMessages.sendToServer(new WardenEffectPacket());
                }
            }
        }
    }

    @SubscribeEvent
    public static void onRightClickedLevitationWand(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();
        Level level = event.getLevel();
        Item mainHandItem = player.getMainHandItem().getItem();
        Item leftHandItem = player.getOffhandItem().getItem();

        if(!level.isClientSide()){
                if((mainHandItem == ModItems.LEVITATION_WAND.get() || leftHandItem == ModItems.LEVITATION_WAND.get())&& player.isCrouching() && !player.hasEffect(ModEffects.CROUCH_TELEPORTATION_EFFECT.get())){

                    // LEVITATION
                    player.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 10, 6, true, false));

                }else if(!player.isCrouching() && (mainHandItem == ModItems.LEVITATION_WAND.get() || leftHandItem == ModItems.LEVITATION_WAND.get())) {

                    // ATTAQUE
                    List<Monster> monsters = level.getEntitiesOfClass(Monster.class, (new AABB(player.blockPosition())).inflate(8.0D, 6.0D, 8.0D));
                    Monster nearestMonster = level.getNearestEntity(monsters, TargetingConditions.DEFAULT, player, 10, 10, 10);
                    if (nearestMonster != null) {
                        level.addFreshEntity(new ShulkerBullet(level, player, nearestMonster, player.getDirection().getAxis()));
                        if (!player.isCreative()) {
                            if (mainHandItem == ModItems.LEVITATION_WAND.get()) {
                                player.getCooldowns().addCooldown(mainHandItem, 5);
                                player.getMainHandItem().hurtAndBreak(1, player, (p_150845_) -> {
                                    p_150845_.broadcastBreakEvent(event.getHand());
                                });
                            } else if (leftHandItem == ModItems.LEVITATION_WAND.get()) {
                                player.getCooldowns().addCooldown(leftHandItem, 5);
                                player.getOffhandItem().hurtAndBreak(1, player, (p_150845_) -> {
                                    p_150845_.broadcastBreakEvent(event.getHand());
                                });
                            }
                        }

                    }
                }
                    /*
                    for(int i=0; i < monsters.size(); i++){
                        level.addFreshEntity(new ShulkerBullet(level, player, monsters.get(i), player.getDirection().getAxis()));
                    }
                    */
        }
    }

    /*
    public static void spawnGlowstoneParticles(Player p) {
        BlockPos blockPos = p.getOnPos();

        double max = 0.2d;
        double min = 0.05d;
        double range = max - min + 0.1;
        double diameter = (Math.random() * range) + min;
        System.out.println("\u001B[34m" + diameter + "\u001B[0m" );

        for(int i = 0; i < 360; i++) {
            if(i % 20 == 0) {
                p.level().addParticle(ModParticles.GLOWSTONE_PARTICLES.get(),
                        blockPos.getX() + 0.5d, blockPos.getY() + 1, blockPos.getZ() + 0.5d,
                        Math.cos(i) * diameter, 0.10d, Math.sin(i) * diameter);
            }
        }
    }
     */

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        if (event.getEntity() instanceof Player player && !player.level().isClientSide()) { // Fonctionne que sur les joueurs

            // Récupère les emplacements d'armure
            ItemStack armorHelmet = player.getItemBySlot(EquipmentSlot.HEAD);
            ItemStack armorChestplate = player.getItemBySlot(EquipmentSlot.CHEST);
            ItemStack armorLeggings = player.getItemBySlot(EquipmentSlot.LEGS);
            ItemStack armorBoots = player.getItemBySlot(EquipmentSlot.FEET);

            if(event.getSource().is(DamageTypeTags.IS_EXPLOSION)) {
                float damages = event.getAmount();
                float reduceFactor = 0.78f;
                if (armorHelmet.getItem() == SHADOWWALKER_HELMET.get()) {
                    damages *= reduceFactor;
                }
                if (armorChestplate.getItem() == SHADOWWALKER_CHESTPLATE.get()) {
                    damages *= reduceFactor;
                }
                if (armorLeggings.getItem() == SHADOWWALKER_LEGGINGS.get()) {
                    damages *= reduceFactor;
                }
                if (armorBoots.getItem() == SHADOWWALKER_BOOTS.get()) {
                    damages *= reduceFactor;
                }

                event.setAmount(damages);
            }
        }
    }
}