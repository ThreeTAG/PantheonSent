package net.threetag.pantheonsent.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.threetag.pantheonsent.PantheonSent;

public class PSBannerPatternTags {

    public static final TagKey<BannerPattern> PATTERN_CRESCENT = create("pattern_item/crescent");

    private static TagKey<BannerPattern> create(String string) {
        return TagKey.create(Registries.BANNER_PATTERN, PantheonSent.id(string));
    }

}
