package fr.takkers.crst.block.entity;

import fr.takkers.crst.CRST;
import fr.takkers.crst.block.ModBlocks;
import fr.takkers.crst.block.entity.custom.ArtefactExtractorBlockEntity;
import fr.takkers.crst.block.entity.custom.EconomyControlOfficeBlockEntity;
import fr.takkers.crst.block.entity.custom.PwdKLNGGnomonEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, CRST.MODID);

    public static final RegistryObject<BlockEntityType<ArtefactExtractorBlockEntity>> ARTEFACT_EXTRACTOR_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("artefact_extractor_block_entity", () ->
                    BlockEntityType.Builder.of(ArtefactExtractorBlockEntity::new,
                            ModBlocks.ARTEFACT_EXTRACTOR.get()).build(null));

    public static final RegistryObject<BlockEntityType<EconomyControlOfficeBlockEntity>> ECONOMY_CONTROL_OFFICE_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("economy_control_office_block_entity", () ->
                    BlockEntityType.Builder.of(EconomyControlOfficeBlockEntity::new,
                            ModBlocks.ECONOMY_CONTROL_OFFICE.get()).build(null));

    public static final RegistryObject<BlockEntityType<PwdKLNGGnomonEntity>> PWD_KLNG_GNOMON_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("pwd_klng_gnomon_block_entity", () ->
                    BlockEntityType.Builder.of(PwdKLNGGnomonEntity::new,
                            ModBlocks.PWD_KLNG_GNOMON.get()).build(null));


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
