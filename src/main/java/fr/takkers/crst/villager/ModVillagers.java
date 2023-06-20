package fr.takkers.crst.villager;

import com.google.common.collect.ImmutableSet;
import fr.takkers.crst.CRST;
import fr.takkers.crst.block.ModBlocks;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.EventBus;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.lang.reflect.InvocationTargetException;

public class ModVillagers {
    public static final DeferredRegister<PoiType> POI_TYPES
            = DeferredRegister.create(ForgeRegistries.POI_TYPES, CRST.MODID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS
            = DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, CRST.MODID);


    public static final RegistryObject<PoiType> ECONOMY_GOVERNOR_POI = POI_TYPES.register("economy_governor_poi",
        ()->new PoiType(ImmutableSet.copyOf(ModBlocks.ECONOMY_CONTROL_OFFICE.get().getStateDefinition().getPossibleStates()), 1, 1));

    public static final RegistryObject<VillagerProfession> ECONOMY_GOVERNOR = VILLAGER_PROFESSIONS.register("economy_governor",
        () -> new VillagerProfession("economy_governor", x -> x.get() == ECONOMY_GOVERNOR_POI.get()
                , x -> x.get() == ECONOMY_GOVERNOR_POI.get()
                , ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_CARTOGRAPHER));


    /*
    public static void registerPOIs() {
        try {
            ObfuscationReflectionHelper.findMethod(PoiType.class,
                    "registerBlockStates", PoiType.class).invoke(null, ECONOMY_GOVERNOR_POI.get());
        } catch(InvocationTargetException | IllegalAccessException exception) {
            exception.printStackTrace();
        }
    }
    */


    public static void register(IEventBus eventBus) {

        POI_TYPES.register(eventBus);
        VILLAGER_PROFESSIONS.register(eventBus);
    }
}
