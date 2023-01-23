package fr.takkers.crst.sound;

import fr.takkers.crst.CRST;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, CRST.MODID);

    public static final RegistryObject<SoundEvent> USED_UNUSUAL_TOTEM = registerSoundEvent("used_unusual_totem");

    /*
    public static final ForgeSoundType CITRINE_LAMP_SOUNDS = new ForgeSoundType(1f, 1f,
            ModSounds.CITRINE_LAMP_BREAK, ModSounds.CITRINE_LAMP_STEP, ModSounds.CITRINE_LAMP_PLACE,
            ModSounds.CITRINE_LAMP_HIT, ModSounds.CITRINE_LAMP_FALL);
    */

    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENTS.register(name, () -> new SoundEvent(new ResourceLocation(CRST.MODID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}