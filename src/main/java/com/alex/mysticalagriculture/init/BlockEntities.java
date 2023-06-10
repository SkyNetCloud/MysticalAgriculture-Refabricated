package com.alex.mysticalagriculture.init;

import com.alex.mysticalagriculture.blockentities.*;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class BlockEntities {
    public static BlockEntityType<EssenceFurnaceBlockEntity.Inferium> INFERIUM_FURNACE;
    public static BlockEntityType<EssenceFurnaceBlockEntity.Prudentium> PRUDENTIUM_FURNACE;
    public static BlockEntityType<EssenceFurnaceBlockEntity.Tertium> TERTIUM_FURNACE;
    public static BlockEntityType<EssenceFurnaceBlockEntity.Imperium> IMPERIUM_FURNACE;
    public static BlockEntityType<EssenceFurnaceBlockEntity.Supremium> SUPREMIUM_FURNACE;
    public static BlockEntityType<EssenceFurnaceBlockEntity.AwakenedSupremium> AWAKENED_SUPREMIUM_FURNACE;
    public static BlockEntityType<InfusionPedestalBlockEntity> INFUSION_PEDESTAL;
    public static BlockEntityType<InfusionAltarBlockEntity> INFUSION_ALTAR;
    public static BlockEntityType<AwakeningPedestalBlockEntity> AWAKENING_PEDESTAL;
    public static BlockEntityType<AwakeningAltarBlockEntity> AWAKENING_ALTAR;
    public static BlockEntityType<EssenceVesselBlockEntity> ESSENCE_VESSEL;
    public static BlockEntityType<TinkeringTableBlockEntity> TINKERING_TABLE;
    public static BlockEntityType<ReprocessorBlockEntity.Basic> BASIC_REPROCESSOR;
    public static BlockEntityType<ReprocessorBlockEntity.Inferium> INFERIUM_REPROCESSOR;
    public static BlockEntityType<ReprocessorBlockEntity.Prudentium> PRUDENTIUM_REPROCESSOR;
    public static BlockEntityType<ReprocessorBlockEntity.Tertium> TERTIUM_REPROCESSOR;
    public static BlockEntityType<ReprocessorBlockEntity.Imperium> IMPERIUM_REPROCESSOR;
    public static BlockEntityType<ReprocessorBlockEntity.Supremium> SUPREMIUM_REPROCESSOR;
    public static BlockEntityType<ReprocessorBlockEntity.AwakenedSupremium> AWAKENED_SUPREMIUM_REPROCESSOR;
    public static BlockEntityType<SoulExtractorBlockEntity> SOUL_EXTRACTOR;
    public static BlockEntityType<HarvesterBlockEntity> HARVESTER;

    public static void registerBlockEntities() {
        INFERIUM_FURNACE = Registry.register(Registry.BLOCK_ENTITY_TYPE, "mysticalagriculture:inferium_furnace", BlockEntityType.Builder.of(EssenceFurnaceBlockEntity.Inferium::new, Blocks.INFERIUM_FURNACE).build(null));
        PRUDENTIUM_FURNACE = Registry.register(Registry.BLOCK_ENTITY_TYPE, "mysticalagriculture:prudentium_furnace", BlockEntityType.Builder.of(EssenceFurnaceBlockEntity.Prudentium::new, Blocks.PRUDENTIUM_FURNACE).build(null));
        TERTIUM_FURNACE = Registry.register(Registry.BLOCK_ENTITY_TYPE, "mysticalagriculture:tertium_furnace", BlockEntityType.Builder.of(EssenceFurnaceBlockEntity.Tertium::new, Blocks.TERTIUM_FURNACE).build(null));
        IMPERIUM_FURNACE = Registry.register(Registry.BLOCK_ENTITY_TYPE, "mysticalagriculture:imperium_furnace", BlockEntityType.Builder.of(EssenceFurnaceBlockEntity.Imperium::new, Blocks.IMPERIUM_FURNACE).build(null));
        SUPREMIUM_FURNACE = Registry.register(Registry.BLOCK_ENTITY_TYPE, "mysticalagriculture:supremium_furnace", BlockEntityType.Builder.of(EssenceFurnaceBlockEntity.Supremium::new, Blocks.SUPREMIUM_FURNACE).build(null));
        AWAKENED_SUPREMIUM_FURNACE = Registry.register(Registry.BLOCK_ENTITY_TYPE, "mysticalagriculture:awakened_supremium_furnace", BlockEntityType.Builder.of(EssenceFurnaceBlockEntity.AwakenedSupremium::new, Blocks.AWAKENED_SUPREMIUM_FURNACE).build(null));
        INFUSION_PEDESTAL = Registry.register(Registry.BLOCK_ENTITY_TYPE, "mysticalagriculture:infusion_pedestal", BlockEntityType.Builder.of(InfusionPedestalBlockEntity::new, Blocks.INFUSION_PEDESTAL).build(null));
        INFUSION_ALTAR = Registry.register(Registry.BLOCK_ENTITY_TYPE, "mysticalagriculture:infusion_altar_block", BlockEntityType.Builder.of(InfusionAltarBlockEntity::new, Blocks.INFUSION_ALTAR).build(null));
        AWAKENING_PEDESTAL = Registry.register(Registry.BLOCK_ENTITY_TYPE, "mysticalagriculture:awakening_pedestal", BlockEntityType.Builder.of(AwakeningPedestalBlockEntity::new, Blocks.AWAKENING_PEDESTAL).build(null));
        AWAKENING_ALTAR = Registry.register(Registry.BLOCK_ENTITY_TYPE, "mysticalagriculture:awakening_altar_block", BlockEntityType.Builder.of(AwakeningAltarBlockEntity::new, Blocks.AWAKENING_ALTAR).build(null));
        ESSENCE_VESSEL = Registry.register(Registry.BLOCK_ENTITY_TYPE, "mysticalagriculture:essence_vessel", BlockEntityType.Builder.of(EssenceVesselBlockEntity::new, Blocks.ESSENCE_VESSEL).build(null));
        TINKERING_TABLE = Registry.register(Registry.BLOCK_ENTITY_TYPE, "mysticalagriculture:tinkering_table", BlockEntityType.Builder.of(TinkeringTableBlockEntity::new, Blocks.TINKERING_TABLE).build(null));
        BASIC_REPROCESSOR = Registry.register(Registry.BLOCK_ENTITY_TYPE, "mysticalagriculture:basic_reprocessor", BlockEntityType.Builder.of(ReprocessorBlockEntity.Basic::new, Blocks.BASIC_REPROCESSOR).build(null));
        INFERIUM_REPROCESSOR = Registry.register(Registry.BLOCK_ENTITY_TYPE, "mysticalagriculture:inferium_reprocessor", BlockEntityType.Builder.of(ReprocessorBlockEntity.Inferium::new, Blocks.INFERIUM_REPROCESSOR).build(null));
        PRUDENTIUM_REPROCESSOR = Registry.register(Registry.BLOCK_ENTITY_TYPE, "mysticalagriculture:prudentium_reprocessor", BlockEntityType.Builder.of(ReprocessorBlockEntity.Prudentium::new, Blocks.PRUDENTIUM_REPROCESSOR).build(null));
        TERTIUM_REPROCESSOR = Registry.register(Registry.BLOCK_ENTITY_TYPE, "mysticalagriculture:tertium_reprocessor", BlockEntityType.Builder.of(ReprocessorBlockEntity.Tertium::new, Blocks.TERTIUM_REPROCESSOR).build(null));
        IMPERIUM_REPROCESSOR = Registry.register(Registry.BLOCK_ENTITY_TYPE, "mysticalagriculture:imperium_reprocessor", BlockEntityType.Builder.of(ReprocessorBlockEntity.Imperium::new, Blocks.IMPERIUM_REPROCESSOR).build(null));
        SUPREMIUM_REPROCESSOR = Registry.register(Registry.BLOCK_ENTITY_TYPE, "mysticalagriculture:supremium_reprocessor", BlockEntityType.Builder.of(ReprocessorBlockEntity.Supremium::new, Blocks.SUPREMIUM_REPROCESSOR).build(null));
        AWAKENED_SUPREMIUM_REPROCESSOR = Registry.register(Registry.BLOCK_ENTITY_TYPE, "mysticalagriculture:awakened_supremium_processor", BlockEntityType.Builder.of(ReprocessorBlockEntity.AwakenedSupremium::new, Blocks.AWAKENED_SUPREMIUM_REPROCESSOR).build(null));
        SOUL_EXTRACTOR = Registry.register(Registry.BLOCK_ENTITY_TYPE, "mysticalagriculture:soul_extractor", BlockEntityType.Builder.of(SoulExtractorBlockEntity::new, Blocks.SOUL_EXTRACTOR).build(null));
        HARVESTER = Registry.register(Registry.BLOCK_ENTITY_TYPE, "mysticalagriculture:harvester", BlockEntityType.Builder.of(HarvesterBlockEntity::new, Blocks.HARVESTER).build(null));
    }
}
