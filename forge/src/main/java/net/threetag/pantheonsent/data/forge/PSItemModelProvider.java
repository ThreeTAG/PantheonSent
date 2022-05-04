package net.threetag.pantheonsent.data.forge;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelBuilder;
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

        // Scarab Compass
        var activeModel = this.withExistingParent("scarab_compass", "item/generated").texture("layer0", PantheonSent.id("item/scarab_compass_0"));
        var parentModel = this.withExistingParent("scarab_compass_held", "item/generated").transforms()
                .transform(ModelBuilder.Perspective.FIRSTPERSON_RIGHT).rotation(-85F, -11F, -15F).translation(1.13F, 3.2F, 1.13F).scale(0.68F).end()
                .transform(ModelBuilder.Perspective.FIRSTPERSON_LEFT).rotation(-85F, -11F, -15F).translation(1.13F, 3.2F, 1.13F).scale(0.68F).end();
        for (int i = -1; i < 8; i++) {
            if(i == -1) {
                var inactiveModel = this.withExistingParent("scarab_compass_inactive", "item/generated").texture("layer0", PantheonSent.id("item/scarab_compass"));
                activeModel.override().predicate(PantheonSent.id("active"), 0F).model(inactiveModel).end();
            } else {
                var subModel = this.withExistingParent("scarab_compass_" + i, PantheonSent.id("item/scarab_compass_held")).texture("layer0", PantheonSent.id("item/scarab_compass_" + i));
                activeModel.override().predicate(PantheonSent.id("angle"), i == 0 ? 0 : i / 8F).predicate(PantheonSent.id("active"), 1F).model(subModel).end();
            }
        }
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
