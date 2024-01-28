package net.threetag.pantheonsent.data.forge;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.tags.PoiTypeTags;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.threetag.pantheonsent.PantheonSent;
import net.threetag.pantheonsent.entity.PSPoiTypes;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class PSPoiTypeTagsProvider extends IntrinsicHolderTagsProvider<PoiType> {

    public PSPoiTypeTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, Registries.POINT_OF_INTEREST_TYPE, completableFuture, poiType -> BuiltInRegistries.POINT_OF_INTEREST_TYPE.getResourceKey(poiType).orElse(null), PantheonSent.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(PoiTypeTags.ACQUIRABLE_JOB_SITE).add(PSPoiTypes.ARCHEOLOGIST.get());
    }

    @Override
    public String getName() {
        return "PantheonSent " + super.getName();
    }
}
