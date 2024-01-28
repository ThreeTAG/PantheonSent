package net.threetag.pantheonsent.data.forge;

import dev.thomasglasser.sherdsapi.api.data.ForgeSherdDatagenSuite;
import dev.thomasglasser.sherdsapi.impl.Sherd;
import dev.thomasglasser.sherdsapi.impl.SherdsApiRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraftforge.data.event.GatherDataEvent;
import net.threetag.pantheonsent.PantheonSent;
import net.threetag.pantheonsent.item.PSItems;

public class PSSherdsProvider extends ForgeSherdDatagenSuite {

    public PSSherdsProvider(GatherDataEvent event) {
        super(event, PantheonSent.MOD_ID);
    }

    @Override
    public void generate() {
        this.makeSherdSuite(ResourceKey.create(SherdsApiRegistries.SHERD, PantheonSent.id("crescent")), new Sherd(PSItems.CRESCENT_POTTERY_SHERD, PantheonSent.id("crescent_pottery_pattern")));
    }
}
