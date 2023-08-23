package sh.miles.suketto.bukkit.menu.holder;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import sh.miles.suketto.nms.container.SukettoContainer;

/**
 * A Slot Holder can put items in something that can hold slots
 *
 * @param <H> the type of holder
 */
public interface SlotHolder<H> {

    /**
     * Set the item in the holder
     *
     * @param index the index to set it at
     * @param item  the item to set
     */
    void setItem(final int index, @NotNull final ItemStack item);

    /**
     * Gets the minimum index of the given slot holder
     *
     * @return the minimum index of the slot holder
     */
    int getMinIndex();

    /**
     * Gets the maximum index of the given slot holder
     *
     * @return the maximum index of the slot holder
     */
    int getMaxIndex();

    /**
     * Gets the slot holder
     *
     * @return the slot holder
     */
    H getHolder();

    /**
     * Creates a SlotHolder
     *
     * @param inventory the inventory to use to create the slot holder
     * @return the slot holder to use
     */
    static SlotHolder<Inventory> of(@NotNull final Inventory inventory) {
        return new InventorySlotHolder(inventory);
    }

    /**
     * Creates a new slot holder
     *
     * @param container the container to create it with
     * @return the new slot holder
     */
    static SlotHolder<SukettoContainer> of(@NotNull final SukettoContainer container) {
        return new ContainerSlotHolder(container);
    }

}
