package net.threetag.pantheonsent.data.forge;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.threetag.palladium.compat.curiostinkets.CuriosTrinketsUtil;
import net.threetag.pantheonsent.PantheonSent;
import net.threetag.pantheonsent.item.PSItems;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class PSItemTagsProvider extends IntrinsicHolderTagsProvider<Item> {

    public PSItemTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, Registries.ITEM, completableFuture, item -> item.builtInRegistryHolder().key(), PantheonSent.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ItemTags.PIGLIN_LOVED).add(PSItems.GILDED_SANDSTONE.get(), PSItems.GILDED_SANDSTONE_PILLAR.get(), PSItems.ANCIENT_GOLD_SHARD.get());
        this.tag(CuriosTrinketsUtil.NECKLACE.getFabric()).add(PSItems.EYE_OF_HORUS.get());
        this.tag(CuriosTrinketsUtil.NECKLACE.getForge()).add(PSItems.EYE_OF_HORUS.get());
        this.tag(ItemTags.MUSIC_DISCS).add(PSItems.MUSIC_DISC_CHONS.get());
    }

    @Override
    public String getName() {
        return "PantheonSent " + super.getName();
    }
}
