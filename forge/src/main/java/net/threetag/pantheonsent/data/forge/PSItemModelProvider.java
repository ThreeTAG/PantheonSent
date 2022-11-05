package net.threetag.pantheonsent.data.forge;

import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.threetag.pantheonsent.PantheonSent;
import net.threetag.pantheonsent.item.PSItems;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

@SuppressWarnings("ConstantConditions")
public class PSItemModelProvider extends ItemModelProvider {

    public PSItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, PantheonSent.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        this.defaultTool(PSItems.ANCIENT_CLAY_SHARD);
        this.defaultTool(PSItems.ANCIENT_GOLD_SHARD);
        this.defaultTool(PSItems.LUNAR_SHARD);
        this.defaultTool(PSItems.BRUSH);
        this.defaultItem(PSItems.BROKEN_LUNAR_TOTEM);
        this.defaultItem(PSItems.LUNAR_TOTEM);
        this.defaultItem(PSItems.BROKEN_EYE_OF_HORUS);
        this.defaultItem(PSItems.EYE_OF_HORUS);
        this.defaultItem(PSItems.BROKEN_SCARAB_COMPASS);
        this.withExistingParent(PSItems.KHONSHU_SPAWN_EGG.getId().getPath(), "item/template_spawn_egg");
        this.withExistingParent(PSItems.CRESCENT_BANNER_PATTERN.getId().getPath(), "item/creeper_banner_pattern");

        this.defaultBlockItem(PSItems.GILDED_SANDSTONE);
        this.defaultBlockItem(PSItems.GILDED_SANDSTONE_PILLAR);
        this.defaultBlockItem(PSItems.ARCHEOLOGY_TABLE);
        this.defaultBlockItem(PSItems.BROKEN_KHONSHU_USHABTI);
        this.defaultBlockItem(PSItems.KHONSHU_USHABTI);
        this.defaultBlockItem(PSItems.MYSTERIOUS_DIRT, PantheonSent.id("block/mysterious_dirt_0"));
        this.defaultBlockItem(PSItems.MYSTERIOUS_GRAVEL, PantheonSent.id("block/mysterious_gravel_0"));
        this.defaultBlockItem(PSItems.MYSTERIOUS_SAND, PantheonSent.id("block/mysterious_sand_0"));
        this.defaultBlockItem(PSItems.SANDSTONE_TOTEM_HOLDER);
        this.defaultBlockItem(PSItems.LUNAR_STONE, PantheonSent.id("block/lunar_stone_0"));

        // Scarab Compass
        var activeModel = this.withExistingParent("scarab_compass", "item/generated").texture("layer0", PantheonSent.id("item/scarab_compass_0"));
        this.withExistingParent("scarab_compass_held", "item/generated").transforms()
                .transform(ItemTransforms.TransformType.FIRST_PERSON_RIGHT_HAND).rotation(-85F, -11F, -15F).translation(1.13F, 3.2F, 1.13F).scale(0.68F).end()
                .transform(ItemTransforms.TransformType.FIRST_PERSON_LEFT_HAND).rotation(-85F, -11F, -15F).translation(1.13F, 3.2F, 1.13F).scale(0.68F).end();
        for (int i = -1; i < 8; i++) {
            if (i == -1) {
                var inactiveModel = this.withExistingParent("scarab_compass_inactive", "item/generated").texture("layer0", PantheonSent.id("item/scarab_compass"));
                activeModel.override().predicate(PantheonSent.id("active"), 0F).model(inactiveModel).end();
            } else {
                var subModel = this.withExistingParent("scarab_compass_" + i, PantheonSent.id("item/scarab_compass_held")).texture("layer0", PantheonSent.id("item/scarab_compass_" + i));
                activeModel.override().predicate(PantheonSent.id("angle"), i == 0 ? 0 : i / 8F).predicate(PantheonSent.id("active"), 1F).model(subModel).end();
            }
        }
    }

    public void defaultTool(Supplier<Item> item) {
        this.defaultItem(item, "item/handheld");
    }

    public void defaultItem(Supplier<Item> item) {
        this.defaultItem(item, "item/generated");
    }

    public void defaultItem(Supplier<Item> item, String parent) {
        this.singleTexture(ForgeRegistries.ITEMS.getKey(item.get()).getPath(), new ResourceLocation(parent), "layer0", new ResourceLocation(PantheonSent.MOD_ID, "item/" + ForgeRegistries.ITEMS.getKey(item.get()).getPath()));
    }

    public void defaultBlockItem(Supplier<Item> item) {
        this.withExistingParent(ForgeRegistries.ITEMS.getKey(item.get()).getPath(), new ResourceLocation(ForgeRegistries.ITEMS.getKey(item.get()).getNamespace(), "block/" + ForgeRegistries.ITEMS.getKey(item.get()).getPath()));
    }

    public void defaultBlockItem(Supplier<Item> item, ResourceLocation parent) {
        this.withExistingParent(ForgeRegistries.ITEMS.getKey(item.get()).getPath(), parent);
    }

    @NotNull
    @Override
    public String getName() {
        return "PantheonSent " + super.getName();
    }
}
