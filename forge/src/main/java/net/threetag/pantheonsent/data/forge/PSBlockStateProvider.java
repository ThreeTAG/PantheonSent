package net.threetag.pantheonsent.data.forge;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.threetag.pantheonsent.PantheonSent;
import net.threetag.pantheonsent.block.BrushableBlock;
import net.threetag.pantheonsent.block.LunarStoneBlock;
import net.threetag.pantheonsent.block.PSBlocks;
import net.threetag.pantheonsent.block.TotemHolderBlock;

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
        this.horizontalBlock(PSBlocks.KHONSHU_USHABTI.get(), models().getExistingFile(PantheonSent.id("block/khonshu_ushabti")));
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

        // Brushable Blocks
        for (int i = 0; i <= 3; i++) {
            models().withExistingParent("brushable_" + i, "block/cube_all").element().from(0, 0, 0).to(16, BrushableBlock.getHeightPerStage(i), 16).textureAll("#all").end();
        }
        brushableBlock(PSBlocks.MYSTERIOUS_DIRT);
        brushableBlock(PSBlocks.MYSTERIOUS_GRAVEL);
        brushableBlock(PSBlocks.MYSTERIOUS_SAND);
    }

    public void brushableBlock(RegistrySupplier<Block> block) {
        var builder = this.getVariantBuilder(block.get());

        for (int i = 0; i <= 3; i++) {
            var model = models().withExistingParent(block.getId().getPath() + "_" + i, PantheonSent.id("block/brushable_" + i)).texture("all", PantheonSent.id("block/" + block.getId().getPath()));
            builder.partialState().with(BrushableBlock.STAGE, i).modelForState().modelFile(model).addModel();
        }
    }

    @Override
    public String getName() {
        return "PantheonSent " + super.getName();
    }
}
