package sh.miles.suketto.nms.inventory;

import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * an inventory for inventories not covered by bukkit.
 * <p>
 * Note: after the inventory is opened changes in the inventory are not reflected live to the player you must use the
 * view or alternatively the view can apply updates by using {@link SukettoCustomInventory#update(InventoryView)}
 * </p>
 */
public interface SukettoCustomInventory {

    /**
     * Sets the item stack at the provided index
     *
     * @param index the index to set the item at
     * @param item  the item to set
     */
    void setItem(final int index, @NotNull final ItemStack item);

    /**
     * Gets the item at the provided index
     *
     * @param index the index
     * @return the item stack at the position
     */
    @NotNull
    ItemStack getItem(final int index);

    /**
     * Removes the item at the provided index
     *
     * @param index the index
     * @return the item stack at the position
     */
    @NotNull
    ItemStack removeItem(final int index);

    /**
     * Clears the inventory
     */
    void clear();

    /**
     * The title of the inventory
     *
     * @return a component title
     */
    BaseComponent getTitle();

    /**
     * Opens the inventory for the entity human
     *
     * @param human the human to open the inventory
     */
    InventoryView open(@NotNull final HumanEntity human);

    /**
     * Sends all inventory updates to the view
     *
     * @param view the view to update
     */
    void update(@NotNull final InventoryView view);
}
