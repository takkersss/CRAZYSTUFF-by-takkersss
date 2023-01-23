package fr.takkers.crst.event;

import fr.takkers.crst.CRST;
import fr.takkers.crst.effect.ModEffects;
import fr.takkers.crst.enchantment.ModEnchantments;
import fr.takkers.crst.item.ModItems;
import fr.takkers.crst.particle.ModParticles;
import fr.takkers.crst.sound.ModSounds;
import fr.takkers.crst.villager.ModVillagers;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

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

    @SubscribeEvent
    public static void onRightClickedTotem(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();


        if(player.getMainHandItem().getItem() == ModItems.UNUSUAL_TOTEM.get()){
            if (!player.hasEffect(ModEffects.CROUCH_TELEPORTATION_EFFECT.get())){

                    Minecraft.getInstance().gameRenderer.displayItemActivation(new ItemStack(ModItems.UNUSUAL_TOTEM.get(), 1));
                    Minecraft.getInstance().particleEngine.createTrackingEmitter(player, ModParticles.UNUSUAL_TOTEM_PARTICLES.get(), 30);
                    player.getLevel().playLocalSound(player.getX(), player.getY(), player.getZ(), ModSounds.USED_UNUSUAL_TOTEM.get(), player.getSoundSource(), 0.5F, 1.0F, false);
                    player.addEffect(new MobEffectInstance(ModEffects.CROUCH_TELEPORTATION_EFFECT.get(), 4000, 0));
                    player.getMainHandItem().shrink(1);

            }

        }

    }

    @SubscribeEvent
    public static void onRightClickedLevitationWand(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();

        if(!event.getLevel().isClientSide()){
                if(player.getMainHandItem().getItem() == ModItems.LEVITATION_WAND.get() && player.isCrouching()) {

                    player.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 10, 10, true, false));

                }else if(player.getMainHandItem().getItem() == ModItems.LEVITATION_WAND.get() && !player.isCrouching()){

                }

        }
    }

}