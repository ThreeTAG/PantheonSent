package net.threetag.pantheonsent.data.forge;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BannerPatternTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.threetag.pantheonsent.PantheonSent;
import net.threetag.pantheonsent.block.PSBannerPatterns;
import net.threetag.pantheonsent.tags.PSBannerPatternTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class PSBannerPatternTagsProvider extends BannerPatternTagsProvider {

    public PSBannerPatternTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, completableFuture, PantheonSent.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.getOrCreateRawBuilder(PSBannerPatternTags.PATTERN_CRESCENT).addElement(PSBannerPatterns.CRESCENT.getId());
    }

    @Override
    public String getName() {
        return "PantheonSent " + super.getName();
    }
}
