package net.threetag.pantheonsent.data.forge;

import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.threetag.pantheonsent.PantheonSent;

public class PSBiomeTagsProvider extends TagsProvider<Biome> {

    public static final TagKey<Biome> HAS_KHONSHU_TEMPLE = create("has_structure/khonshu_temple");

    public PSBiomeTagsProvider(DataGenerator arg, ExistingFileHelper existingFileHelper) {
        super(arg, BuiltinRegistries.BIOME, PantheonSent.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
//        this.tag(HAS_KHONSHU_TEMPLE).addTag(BiomeTags.HAS_DESERT_PYRAMID);
    }

    private static TagKey<Biome> create(String name) {
        return TagKey.create(Registry.BIOME_REGISTRY, PantheonSent.id(name));
    }

    @Override
    public String getName() {
        return "PantheonSent Biome Tags";
    }
}
