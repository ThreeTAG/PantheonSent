package net.threetag.pantheonsent.inventory;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.core.Registry;
import net.minecraft.world.inventory.MenuType;
import net.threetag.palladiumcore.registry.DeferredRegister;
import net.threetag.palladiumcore.registry.RegistrySupplier;
import net.threetag.pantheonsent.PantheonSent;
import net.threetag.pantheonsent.client.screen.RestorationScreen;

public class PSMenuTypes {

    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(PantheonSent.MOD_ID, Registry.MENU_REGISTRY);

    public static final RegistrySupplier<MenuType<RestorationMenu>> RESTORATION = MENU_TYPES.register("restoration", () -> new MenuType<>(RestorationMenu::new));

    @Environment(EnvType.CLIENT)
    public static void initScreens() {
        MenuScreens.register(RESTORATION.get(), RestorationScreen::new);
    }

}
