package sh.miles.suketto.bukkit.menu.holder;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * Implementation of SlotHolder for inventories from bukkit
 */
public class InventorySlotHolder implements SlotHolder<Inventory> {

    private final Inventory inventory;

    InventorySlotHolder(@NotNull final Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public void setItem(final int index, @NotNull ItemStack item) {
        inventory.setItem(index, item);
    }

    @Override
    public Inventory getHolder() {
        return inventory;
    }

    @Override
    public int getMaxIndex() {
        return inventory.getSize() - 1;
    }

    @Override
    public int getMinIndex() {
        return 0;
    }
}
