package com.reynn.trialeldritch;


import com.mojang.logging.LogUtils;
import com.reynn.trialeldritch.blocks.BlockRegistry;
import com.reynn.trialeldritch.blocks.magical.AltarBlockEntity;
import com.reynn.trialeldritch.data.DataGenerators;
import com.reynn.trialeldritch.items.ItemRegistry;
import com.reynn.trialeldritch.ui.MenuRegistry;
import com.reynn.trialeldritch.ui.containers.AltarScreen;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.ICapabilityProvider;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

@Mod(TrialEldritch.MODID)
public class TrialEldritch {
    public static final String MODID = "trialeldritch";

    public static final Logger LOGGER = LogUtils.getLogger();

    public TrialEldritch(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        MenuRegistry.register(modEventBus);
        modEventBus.addListener(this::registerScreens);
        // Register DataGen
        modEventBus.addListener(DataGenerators::gatherData);

        ItemRegistry.register(modEventBus);
        BlockRegistry.register(modEventBus);
        modEventBus.addListener(this::registerCapabilities);

        NeoForge.EVENT_BUS.register(this);

        modContainer.registerConfig(ModConfig.Type.COMMON, Configuration.SPEC);
    }

    private void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, BlockRegistry.ALTAR_ENTITY.get(), (o, direction) -> o.getItemHandler());
        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, BlockRegistry.ALTAR_ENTITY.get(), (o, direction) -> o.getEnergyHandler());
    }

    private void registerScreens(RegisterMenuScreensEvent event) {
        event.register(MenuRegistry.ALTAR_BLOCK.get(), AltarScreen::new);
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");


    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        LOGGER.info("HELLO FROM COMMON SETUP");
        if (Configuration.logDirtBlock)
            LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));
    }
}
