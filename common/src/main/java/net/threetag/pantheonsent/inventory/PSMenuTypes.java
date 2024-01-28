package net.threetag.pantheonsent.inventory;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.threetag.palladiumcore.registry.DeferredRegister;
import net.threetag.palladiumcore.registry.RegistrySupplier;
import net.threetag.pantheonsent.PantheonSent;
import net.threetag.pantheonsent.client.screen.RestorationScreen;

public class PSMenuTypes {

    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(PantheonSent.MOD_ID, Registries.MENU);

    public static final RegistrySupplier<MenuType<RestorationMenu>> RESTORATION = MENU_TYPES.register("restoration", () -> new MenuType<>(RestorationMenu::new, FeatureFlags.VANILLA_SET));

    @Environment(EnvType.CLIENT)
    public static void initScreens() {
        MenuScreens.register(RESTORATION.get(), RestorationScreen::new);
    }

}
