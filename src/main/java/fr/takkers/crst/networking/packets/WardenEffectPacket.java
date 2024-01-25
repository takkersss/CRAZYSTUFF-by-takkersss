package fr.takkers.crst.networking.packets;

import fr.takkers.crst.util.ModRayTrace;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;

import java.util.List;
import java.util.function.Supplier;

public class WardenEffectPacket {
    public WardenEffectPacket() {

    }

    public WardenEffectPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE SERVER!
            ServerPlayer player = context.getSender();
            ServerLevel level = player.serverLevel();

            ModRayTrace rayTrace = new ModRayTrace();
            LivingEntity rayTraceResult = rayTrace.getEntityInCrosshair(15, player, level);
            Mob mob = (Mob)rayTraceResult;

            if(rayTraceResult != null){
                Vec3 vec3 = player.position().add(0.0D, (double)1.6F, 0.0D);
                Vec3 vec31 = mob.getEyePosition().subtract(vec3);
                Vec3 vec32 = vec31.normalize();

                // Partciles
                for(int i = 1; i < Mth.floor(vec31.length()) + 7; ++i) {
                    Vec3 vec33 = vec3.add(vec32.scale((double)i));
                    level.sendParticles(ParticleTypes.SONIC_BOOM, vec33.x, vec33.y, vec33.z, 1, 0.0D, 0.0D, 0.0D, 0.0D);
                }

                player.playSound(SoundEvents.WARDEN_SONIC_BOOM, 3.0F, 1.0F);

                // Fracasser et pousser le mob
                mob.hurt(level.damageSources().sonicBoom(player), 5.0F);
                double d1 = 0.5D * (1.0D - mob.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
                double d0 = 2.5D * (1.0D - mob.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
                mob.push(vec32.x() * d0, vec32.y() * d1, vec32.z() * d0);

                // Endommager et casser l'item
                player.getItemBySlot(EquipmentSlot.CHEST).hurtAndBreak(1, player, (p_150845_) -> {
                    p_150845_.broadcastBreakEvent(player.getItemBySlot(EquipmentSlot.CHEST).getEquipmentSlot());
                });
            }

        });
        return true;
    }

}
