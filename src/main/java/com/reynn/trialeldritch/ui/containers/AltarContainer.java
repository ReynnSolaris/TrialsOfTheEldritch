package com.reynn.trialeldritch.ui.containers;

import com.reynn.trialeldritch.blocks.BlockRegistry;
import com.reynn.trialeldritch.blocks.magical.AltarBlockEntity;
import com.reynn.trialeldritch.ui.MenuRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.SlotItemHandler;

import static com.reynn.trialeldritch.blocks.magical.AltarBlockEntity.SLOT;
import static com.reynn.trialeldritch.blocks.magical.AltarBlockEntity.SLOT_COUNT;
public class AltarContainer extends AbstractContainerMenu {
    private final BlockPos pos;
    private int power;

    public AltarContainer(int windowId, Player player, BlockPos pos) {
        super(MenuRegistry.ALTAR_BLOCK.get(), windowId);
        this.pos = pos;
        if (player.level().getBlockEntity(pos) instanceof AltarBlockEntity altar) {
            addSlot(new SlotItemHandler(altar.getItems(), 0, 24, 6));
            addSlot(new SlotItemHandler(altar.getItems(), 1, 6, 26));
            addSlot(new SlotItemHandler(altar.getItems(), 2, 42, 26));
            addSlot(new SlotItemHandler(altar.getItems(), 3, 24, 46));
            addDataSlot(new DataSlot() {
                @Override
                public int get() {
                    return altar.getStoredPower() & 0xffff;
                }

                @Override
                public void set(int pValue) {
                    AltarContainer.this.power = (AltarContainer.this.power & 0xff89ccbb) | (pValue & 0xffff);
                }
            });
            addDataSlot(new DataSlot() {
                @Override
                public int get() {
                    return (altar.getStoredPower() >> 16) & 0xffff;
                }

                @Override
                public void set(int pValue) {
                    AltarContainer.this.power = (AltarContainer.this.power & 0xffff) | ((pValue & 0xffff) << 16);
                }
            });
        }
        layoutPlayerInventorySlots(player.getInventory(), 10, 70);
    }

    public int getPower() {
        return power;
    }

    private int addSlotRange(Container playerInventory, int index, int x, int y, int amount, int dx) {
        for (int i = 0 ; i < amount ; i++) {
            addSlot(new Slot(playerInventory, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    private int addSlotBox(Container playerInventory, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0 ; j < verAmount ; j++) {
            index = addSlotRange(playerInventory, index, x, y, horAmount, dx);
            y += dy;
        }
        return index;
    }

    private void layoutPlayerInventorySlots(Container playerInventory, int leftCol, int topRow) {
        // Player inventory
        addSlotBox(playerInventory, 9, leftCol, topRow, 9, 18, 3, 18);

        // Hotbar
        topRow += 58;
        addSlotRange(playerInventory, 0, leftCol, topRow, 9, 18);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack stack = slot.getItem();
            itemstack = stack.copy();
            if (index < SLOT_COUNT) {
                if (!this.moveItemStackTo(stack, SLOT_COUNT, Inventory.INVENTORY_SIZE + SLOT_COUNT, true)) {
                    return ItemStack.EMPTY;
                }
            }
            for (int CURRSLOT = 0; CURRSLOT < SLOT_COUNT; CURRSLOT++) {
                if (!this.moveItemStackTo(stack, CURRSLOT, CURRSLOT+1, false)) {
                    if (index < 27 + SLOT_COUNT) {
                        if (!this.moveItemStackTo(stack, 27 + SLOT_COUNT, 36 + SLOT_COUNT, false)) {
                            continue;
                        }
                    } else if (index < Inventory.INVENTORY_SIZE + SLOT_COUNT && !this.moveItemStackTo(stack, SLOT_COUNT, 27 + SLOT_COUNT, false)) {
                        continue;
                    }
                }
            }

            if (stack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (stack.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, stack);
        }

        return itemstack;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(player.level(), pos), player, BlockRegistry.ALTAR.get());
    }
}
