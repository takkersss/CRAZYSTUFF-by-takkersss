package fr.takkers.crst.particle;

import fr.takkers.crst.CRST;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
        DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, CRST.MODID);

    public static final RegistryObject<SimpleParticleType> GLOWSTONE_PARTICLES =
            PARTICLE_TYPES.register("glowstone_particles", () -> new SimpleParticleType(true));

    public static final RegistryObject<SimpleParticleType> UNUSUAL_TOTEM_PARTICLES =
            PARTICLE_TYPES.register("unusual_totem_particles", () -> new SimpleParticleType(true));

    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }
}