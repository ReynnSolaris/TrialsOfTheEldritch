package com.reynn.trialeldritch.blocks;

import com.reynn.trialeldritch.TrialEldritch;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BlockRegistry {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(TrialEldritch.MODID);

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
    }
}
