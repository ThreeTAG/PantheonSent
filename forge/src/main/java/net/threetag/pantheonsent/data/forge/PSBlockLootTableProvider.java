package net.threetag.pantheonsent.data.forge;

import net.minecraft.data.DataGenerator;
import net.threetag.palladium.data.forge.BlockLootTableProvider;
import net.threetag.pantheonsent.block.PSBlocks;

public class PSBlockLootTableProvider extends BlockLootTableProvider {

    public PSBlockLootTableProvider(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }

    @Override
    public void addTables() {
        this.dropWhenSilkTouch(PSBlocks.LUNAR_STONE.get());
    }

    @Override
    public String getName() {
        return "PantheonSent " + super.getName();
    }
}
