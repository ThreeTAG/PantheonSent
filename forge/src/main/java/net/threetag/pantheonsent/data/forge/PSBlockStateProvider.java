package net.threetag.pantheonsent.data.forge;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.threetag.pantheonsent.PantheonSent;
import net.threetag.pantheonsent.block.*;

public class PSBlockStateProvider extends BlockStateProvider {

    public PSBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, PantheonSent.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        this.getVariantBuilder(PSBlocks.GILDED_SANDSTONE.get()).partialState().modelForState().modelFile(models().cubeColumn("gilded_sandstone", PantheonSent.id("block/gilded_sandstone"), new ResourceLocation("block/gold_block"))).addModel();
        this.axisBlock((RotatedPillarBlock) PSBlocks.GILDED_SANDSTONE_PILLAR.get(), PantheonSent.id("block/gilded_sandstone_pillar"), PantheonSent.id("block/gilded_sandstone_pillar_top"));
        this.getVariantBuilder(PSBlocks.ARCHEOLOGY_TABLE.get())
                .partialState().modelForState().modelFile(models().withExistingParent("archeology_table", new ResourceLocation("block/cube"))
                        .texture("particle", PantheonSent.id("block/archeology_table_front"))
                        .texture("north", PantheonSent.id("block/archeology_table_front"))
                        .texture("south", PantheonSent.id("block/archeology_table_side"))
                        .texture("east", PantheonSent.id("block/archeology_table_side"))
                        .texture("west", PantheonSent.id("block/archeology_table_front"))
                        .texture("up", PantheonSent.id("block/archeology_table_top"))
                        .texture("down", new ResourceLocation("block/jungle_planks"))
                ).addModel();
        this.horizontalBlock(PSBlocks.BROKEN_KHONSHU_USHABTI.get(), models().getExistingFile(PantheonSent.id("block/broken_khonshu_ushabti")));
        this.horizontalBlock(PSBlocks.KHONSHU_USHABTI.get(), blockState -> {
            if (blockState.getValue(UshabtiBlock.USED)) {
                return models().withExistingParent("used_khonshu_ushabti", PantheonSent.id("block/khonshu_ushabti")).texture("0", PantheonSent.id("block/used_khonshu_ushabti"));
            } else {
                return models().getExistingFile(PantheonSent.id("block/khonshu_ushabti"));
            }
        });
        this.getVariantBuilder(PSBlocks.SANDSTONE_TOTEM_HOLDER.get())
                .partialState().with(TotemHolderBlock.ACTIVE, false).modelForState().modelFile(models().withExistingParent("sandstone_totem_holder", "chiseled_sandstone").texture("side", PantheonSent.id("block/sandstone_totem_holder"))).addModel()
                .partialState().with(TotemHolderBlock.ACTIVE, true).modelForState().modelFile(models().withExistingParent("sandstone_totem_holder_active", "chiseled_sandstone").texture("side", PantheonSent.id("block/sandstone_totem_holder_active"))).addModel();
        this.getVariantBuilder(PSBlocks.LUNAR_STONE.get())
                .partialState().with(LunarStoneBlock.PHASE, 0).modelForState().modelFile(models().withExistingParent("lunar_stone_0", "block/cube_all").texture("all", PantheonSent.id("block/lunar_stone_0"))).addModel()
                .partialState().with(LunarStoneBlock.PHASE, 1).modelForState().modelFile(models().withExistingParent("lunar_stone_1", "block/cube_all").texture("all", PantheonSent.id("block/lunar_stone_1"))).addModel()
                .partialState().with(LunarStoneBlock.PHASE, 2).modelForState().modelFile(models().withExistingParent("lunar_stone_2", "block/cube_all").texture("all", PantheonSent.id("block/lunar_stone_2"))).addModel()
                .partialState().with(LunarStoneBlock.PHASE, 3).modelForState().modelFile(models().withExistingParent("lunar_stone_3", "block/cube_all").texture("all", PantheonSent.id("block/lunar_stone_3"))).addModel()
                .partialState().with(LunarStoneBlock.PHASE, 4).modelForState().modelFile(models().withExistingParent("lunar_stone_4", "block/cube_all").texture("all", PantheonSent.id("block/lunar_stone_4"))).addModel()
                .partialState().with(LunarStoneBlock.PHASE, 5).modelForState().modelFile(models().withExistingParent("lunar_stone_5", "block/cube_all").texture("all", PantheonSent.id("block/lunar_stone_5"))).addModel()
                .partialState().with(LunarStoneBlock.PHASE, 6).modelForState().modelFile(models().withExistingParent("lunar_stone_6", "block/cube_all").texture("all", PantheonSent.id("block/lunar_stone_6"))).addModel()
                .partialState().with(LunarStoneBlock.PHASE, 7).modelForState().modelFile(models().withExistingParent("lunar_stone_7", "block/cube_all").texture("all", PantheonSent.id("block/lunar_stone_7"))).addModel();

        this.getVariantBuilder(PSBlocks.SUSPICIOUS_SAND.get())
                .partialState().with(SuspiciousSandBlock.DUSTED, 0).modelForState().modelFile(models().withExistingParent("suspicious_sand_0", "block/cube_all").texture("all", PantheonSent.id("block/suspicious_sand_0"))).addModel()
                .partialState().with(SuspiciousSandBlock.DUSTED, 1).modelForState().modelFile(models().withExistingParent("suspicious_sand_1", "block/cube_all").texture("all", PantheonSent.id("block/suspicious_sand_1"))).addModel()
                .partialState().with(SuspiciousSandBlock.DUSTED, 2).modelForState().modelFile(models().withExistingParent("suspicious_sand_2", "block/cube_all").texture("all", PantheonSent.id("block/suspicious_sand_2"))).addModel()
                .partialState().with(SuspiciousSandBlock.DUSTED, 3).modelForState().modelFile(models().withExistingParent("suspicious_sand_3", "block/cube_all").texture("all", PantheonSent.id("block/suspicious_sand_3"))).addModel();
    }

    @Override
    public String getName() {
        return "PantheonSent " + super.getName();
    }
}
