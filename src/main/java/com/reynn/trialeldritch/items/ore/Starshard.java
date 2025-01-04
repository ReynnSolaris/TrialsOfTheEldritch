package com.reynn.trialeldritch.items.ore;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class Starshard extends Item {
    public Starshard(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }
}
