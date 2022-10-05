package net.threetag.pantheonsent.data.forge;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BannerPatternTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.threetag.pantheonsent.PantheonSent;
import net.threetag.pantheonsent.block.PSBannerPatterns;
import net.threetag.pantheonsent.tags.PSBannerPatternTags;
import org.jetbrains.annotations.Nullable;

public class PSBannerPatternTagsProvider extends BannerPatternTagsProvider {

    public PSBannerPatternTagsProvider(DataGenerator arg, @Nullable ExistingFileHelper existingFileHelper) {
        super(arg, PantheonSent.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        this.tag(PSBannerPatternTags.PATTERN_CRESCENT).add(PSBannerPatterns.CRESCENT.get());
    }

    @Override
    public String getName() {
        return "PantheonSent " + super.getName();
    }
}
