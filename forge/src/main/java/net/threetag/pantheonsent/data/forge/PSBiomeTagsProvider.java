package net.threetag.pantheonsent.data.forge;

import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.threetag.pantheonsent.PantheonSent;
import org.jetbrains.annotations.Nullable;

public class PSBiomeTagsProvider extends BiomeTagsProvider {

    public static final TagKey<Biome> HAS_KHONSHU_TEMPLE = create("has_structure/khonshu_temple");
    public static final TagKey<Biome> HAS_DIG_SITE_DESERT = create("has_structure/dig_site_desert");

    public PSBiomeTagsProvider(DataGenerator arg, @Nullable ExistingFileHelper existingFileHelper) {
        super(arg, PantheonSent.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        this.tag(HAS_KHONSHU_TEMPLE).addTag(BiomeTags.HAS_DESERT_PYRAMID);
        this.tag(HAS_DIG_SITE_DESERT).addTag(BiomeTags.HAS_DESERT_PYRAMID);
    }

    private static TagKey<Biome> create(String name) {
        return TagKey.create(Registry.BIOME_REGISTRY, PantheonSent.id(name));
    }

    @Override
    public String getName() {
        return "PantheonSent " + super.getName();
    }
}
