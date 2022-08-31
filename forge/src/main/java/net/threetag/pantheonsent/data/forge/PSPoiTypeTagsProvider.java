package net.threetag.pantheonsent.data.forge;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.PoiTypeTagsProvider;
import net.minecraft.tags.PoiTypeTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.threetag.pantheonsent.PantheonSent;
import net.threetag.pantheonsent.entity.PSPoiTypes;
import org.jetbrains.annotations.Nullable;

public class PSPoiTypeTagsProvider extends PoiTypeTagsProvider {

    public PSPoiTypeTagsProvider(DataGenerator arg, @Nullable ExistingFileHelper existingFileHelper) {
        super(arg, PantheonSent.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        this.tag(PoiTypeTags.ACQUIRABLE_JOB_SITE).add(PSPoiTypes.ARCHEOLOGIST.get());
    }

    @Override
    public String getName() {
        return "PantheonSent " + super.getName();
    }
}
