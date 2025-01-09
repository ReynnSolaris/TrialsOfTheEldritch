package com.reynn.trialeldritch.items;

import com.reynn.trialeldritch.TrialEldritch;
import com.reynn.trialeldritch.items.ore.Starshard;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ItemRegistry {
    public static DeferredRegister.Items ITEMS = DeferredRegister.createItems(TrialEldritch.MODID);

    public static DeferredItem<Item> VOIDSTONE_ORE = ITEMS.registerItem("voidstone_ore", Item::new, new Item.Properties());
    public static DeferredItem<Item> VOIDSTONE = ITEMS.registerItem("voidstone", Item::new, new Item.Properties());

    public static DeferredItem<Item> STARSHARD = ITEMS.registerItem("starshard", Starshard::new, new Item.Properties());
    public static DeferredItem<Item> RAW_STARSHARD = ITEMS.registerItem("raw_starshard", Item::new, new Item.Properties());

    public static DeferredItem<Item> AETHERIUM_CRYSTAL = ITEMS.registerItem("aetherium_crystal", Item::new, new Item.Properties());
    public static DeferredItem<Item> CELESTIAL_ALLOY = ITEMS.registerItem("celestial_alloy", Item::new, new Item.Properties());

    public static void register(IEventBus modBus) {
        ITEMS.register(modBus);
    }
}
