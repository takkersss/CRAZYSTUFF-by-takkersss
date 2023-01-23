package fr.takkers.crst.painting;

import fr.takkers.crst.CRST;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPaintings {
    public static final DeferredRegister<PaintingVariant> PAINTING_VARIANTS =
            DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, CRST.MODID);


    public static final RegistryObject<PaintingVariant> BOHEMIAN_GROVE1 = PAINTING_VARIANTS.register("bohemian_grove1", () -> new PaintingVariant(48, 32));
    public static final RegistryObject<PaintingVariant> BOHEMIAN_GROVE2 = PAINTING_VARIANTS.register("bohemian_grove2", () -> new PaintingVariant(64, 48));
    public static final RegistryObject<PaintingVariant> BOHEMIAN_GROVE4 = PAINTING_VARIANTS.register("bohemian_grove4", () -> new PaintingVariant(64, 32));
    public static final RegistryObject<PaintingVariant> ECONOMY_GOVERNOR = PAINTING_VARIANTS.register("economy_governor_p", () -> new PaintingVariant(32, 32));
    //public static final RegistryObject<PaintingVariant> ECONOMY_GOVERNOR2 = PAINTING_VARIANTS.register("economy_governor_p2", () -> new PaintingVariant(16, 16));

    public static void register(IEventBus eventBus) {
        PAINTING_VARIANTS.register(eventBus);
    }
}
