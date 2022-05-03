package net.threetag.pantheonsent.data.forge;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.threetag.pantheonsent.PantheonSent;
import net.threetag.pantheonsent.block.LunarStoneBlock;
import net.threetag.pantheonsent.block.PSBlocks;
import net.threetag.pantheonsent.block.TotemHolderBlock;

public class PSBlockStateProvider extends BlockStateProvider {

    public PSBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, PantheonSent.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
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
    }

    @Override
    public String getName() {
        return "PantheonSent " + super.getName();
    }
}
