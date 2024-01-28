package net.threetag.pantheonsent.data.forge;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.threetag.pantheonsent.PantheonSent;
import net.threetag.pantheonsent.block.PSBlocks;
import net.threetag.pantheonsent.tags.PSBlockTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class PSBlockTagsProvider extends IntrinsicHolderTagsProvider<Block> {

    public PSBlockTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, Registries.BLOCK, completableFuture, block -> block.builtInRegistryHolder().key(), PantheonSent.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(PSBlocks.GILDED_SANDSTONE.get(), PSBlocks.GILDED_SANDSTONE_PILLAR.get(), PSBlocks.BROKEN_KHONSHU_USHABTI.get(), PSBlocks.KHONSHU_USHABTI.get(), PSBlocks.SANDSTONE_TOTEM_HOLDER.get(), PSBlocks.LUNAR_STONE.get());
        this.tag(BlockTags.NEEDS_STONE_TOOL).add(PSBlocks.LUNAR_STONE.get());
        this.tag(BlockTags.NEEDS_IRON_TOOL).add(PSBlocks.GILDED_SANDSTONE.get(), PSBlocks.GILDED_SANDSTONE_PILLAR.get(), PSBlocks.LUNAR_STONE.get());
        this.tag(BlockTags.MINEABLE_WITH_AXE).add(PSBlocks.ARCHEOLOGY_TABLE.get());
        this.tag(BlockTags.GUARDED_BY_PIGLINS).add(PSBlocks.GILDED_SANDSTONE.get(), PSBlocks.GILDED_SANDSTONE_PILLAR.get());

        this.tag(PSBlockTags.CRESCENT_DART_DESTROYABLE).addOptionalTag(Tags.Blocks.GLASS.location()).addOptionalTag(Tags.Blocks.GLASS_PANES.location()).addOptionalTag(new ResourceLocation("c:glass_blocks")).addOptionalTag(new ResourceLocation("c:glass_panes"));
    }

    @Override
    public String getName() {
        return "PantheonSent " + super.getName();
    }
}
