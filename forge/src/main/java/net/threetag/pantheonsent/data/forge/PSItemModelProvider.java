package net.threetag.pantheonsent.data.forge;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.threetag.pantheonsent.PantheonSent;
import net.threetag.pantheonsent.item.PSItems;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class PSItemModelProvider extends ItemModelProvider {

    public PSItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, PantheonSent.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        this.defaultItem(PSItems.LUNAR_TOTEM);
        this.defaultBlockItem(PSItems.SANDSTONE_TOTEM_HOLDER);
        this.defaultBlockItem(PSItems.LUNAR_STONE, PantheonSent.id("block/lunar_stone_0"));
    }

    public void defaultItem(Supplier<Item> item) {
        this.defaultItem(item, "item/generated");
    }

    public void defaultItem(Supplier<Item> item, String parent) {
        this.singleTexture(item.get().getRegistryName().getPath(), new ResourceLocation(parent), "layer0", new ResourceLocation(PantheonSent.MOD_ID, "item/" + item.get().getRegistryName().getPath()));
    }

    public void defaultBlockItem(Supplier<Item> item) {
        this.withExistingParent(item.get().getRegistryName().getPath(), new ResourceLocation(item.get().getRegistryName().getNamespace(), "block/" + item.get().getRegistryName().getPath()));
    }

    public void defaultBlockItem(Supplier<Item> item, ResourceLocation parent) {
        this.withExistingParent(item.get().getRegistryName().getPath(), parent);
    }

    @NotNull
    @Override
    public String getName() {
        return "PantheonSent " + super.getName();
    }
}
