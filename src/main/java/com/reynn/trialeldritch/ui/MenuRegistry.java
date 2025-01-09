package com.reynn.trialeldritch.ui;

import com.reynn.trialeldritch.TrialEldritch;
import com.reynn.trialeldritch.ui.containers.AltarContainer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class MenuRegistry {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(BuiltInRegistries.MENU, TrialEldritch.MODID);
    public static final Supplier<MenuType<AltarContainer>> ALTAR_BLOCK = MENU_TYPES.register("altar_block",
            () -> IMenuTypeExtension.create((windowId, inv, data) -> new AltarContainer(windowId, inv.player, data.readBlockPos())));

    public static void register(IEventBus eventBus) {
        MENU_TYPES.register(eventBus);
    }
}
