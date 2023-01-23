package fr.takkers.crst.entity;

import fr.takkers.crst.CRST;
import fr.takkers.crst.entity.custom.ShadowWalkerEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, CRST.MODID);

    public static final RegistryObject<EntityType<ShadowWalkerEntity>> SHADOW_WALKER =
            ENTITY_TYPES.register("shadowwalker",
                    () -> EntityType.Builder.of(ShadowWalkerEntity::new, MobCategory.MONSTER)
                            .sized(0.6f, 1.99f)
                            .build(new ResourceLocation(CRST.MODID, "shadowwalker").toString()));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
