package net.threetag.pantheonsent.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.threetag.pantheonsent.PantheonSent;

public class PSBlockTags {

    public static final TagKey<Block> CRESCENT_DART_DESTROYABLE = create("crescent_dart_destroyable");

    private static TagKey<Block> create(String string) {
        return TagKey.create(Registries.BLOCK, PantheonSent.id(string));
    }

}
