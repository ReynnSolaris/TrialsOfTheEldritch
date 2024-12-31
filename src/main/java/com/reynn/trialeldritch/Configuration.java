package com.reynn.trialeldritch;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

@EventBusSubscriber(modid = TrialEldritch.MODID, bus = EventBusSubscriber.Bus.MOD)
public class Configuration {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    //Do Config Here
    private static final ModConfigSpec.BooleanValue LOG_DIRT_BLOCK = BUILDER
            .comment("Whether to log the dirt block on common setup")
            .define("logDirtBlock", true);
    static final ModConfigSpec SPEC = BUILDER.build();
    public static boolean logDirtBlock;
    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        logDirtBlock = LOG_DIRT_BLOCK.get();
    }
}
