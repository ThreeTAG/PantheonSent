package net.threetag.pantheonsent.tags;

import net.minecraft.core.Registry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.threetag.pantheonsent.PantheonSent;

public class PSBannerPatternTags {

    public static final TagKey<BannerPattern> PATTERN_CRESCENT = create("pattern_item/crescent");

    private static TagKey<BannerPattern> create(String string) {
        return TagKey.create(Registry.BANNER_PATTERN_REGISTRY, PantheonSent.id(string));
    }

}
