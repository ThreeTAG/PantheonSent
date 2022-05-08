package net.threetag.pantheonsent.data.forge;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.threetag.pantheonsent.PantheonSent;
import net.threetag.pantheonsent.block.PSBlocks;
import org.jetbrains.annotations.Nullable;

public class PSBlockTagsProvider extends BlockTagsProvider {

    public PSBlockTagsProvider(DataGenerator arg, @Nullable ExistingFileHelper existingFileHelper) {
        super(arg, PantheonSent.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(PSBlocks.SANDSTONE_TOTEM_HOLDER.get(), PSBlocks.LUNAR_STONE.get());
        this.tag(BlockTags.NEEDS_STONE_TOOL).add(PSBlocks.LUNAR_STONE.get());
        this.tag(BlockTags.MINEABLE_WITH_SHOVEL).add(PSBlocks.MYSTERIOUS_DIRT.get(), PSBlocks.MYSTERIOUS_GRAVEL.get(), PSBlocks.MYSTERIOUS_SAND.get());
    }

    @Override
    public String getName() {
        return "PantheonSent " + super.getName();
    }
}
