package net.threetag.pantheonsent.data.forge;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.threetag.palladium.item.CurioTrinketRegistry;
import net.threetag.pantheonsent.PantheonSent;
import net.threetag.pantheonsent.item.PSItems;
import org.jetbrains.annotations.Nullable;

public class PSItemTagsProvider extends ItemTagsProvider {

    public PSItemTagsProvider(DataGenerator dataGenerator, BlockTagsProvider blockTagsProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGenerator, blockTagsProvider, PantheonSent.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        this.tag(ItemTags.PIGLIN_LOVED).add(PSItems.GILDED_SANDSTONE.get(), PSItems.GILDED_SANDSTONE_PILLAR.get(), PSItems.ANCIENT_GOLD_SHARD.get());
        this.tag(CurioTrinketRegistry.NECKLACE.getFabric()).add(PSItems.EYE_OF_HORUS.get());
        this.tag(CurioTrinketRegistry.NECKLACE.getForge()).add(PSItems.EYE_OF_HORUS.get());
    }

    @Override
    public String getName() {
        return "PantheonSent " + super.getName();
    }
}
