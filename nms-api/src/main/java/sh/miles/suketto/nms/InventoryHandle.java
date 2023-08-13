package sh.miles.suketto.nms;

import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sh.miles.suketto.nms.inventory.SukettoCustomInventory;
import sh.miles.suketto.nms.inventory.SukettoCustomInventoryType;

/**
 * Handles interaction between NMS and the bukkit api concerning Inventories
 */
public interface InventoryHandle {

    /**
     * Opens an inventory with the provided title for the given player
     *
     * @param inventory the inventory to open
     * @param title     the title to open the inventory with
     * @param player    the player to open the inventory for
     * @return The view if the open event was not cancelled
     */
    @Nullable
    InventoryView open(@NotNull final Inventory inventory, @NotNull final BaseComponent title, @NotNull final Player player);

    /**
     * Creates an inventory with the provided title for the given custom title
     *
     * @param title         the title
     * @param inventoryType the inventory type
     * @return the suketto custom inventory
     */
    SukettoCustomInventory create(@NotNull final BaseComponent title, @NotNull final SukettoCustomInventoryType inventoryType);

    /**
     * Sends a title change with a component for all viewers of the provided inventory
     *
     * @param inventory the inventory to send the title change too
     * @param title     the new title
     */
    void sendTitleChange(@NotNull final Inventory inventory, @NotNull final BaseComponent title);

    /**
     * Sends a title change with a component for the inventory view
     *
     * @param view  the view to send the title too
     * @param title the new title
     */
    void sendTitleChange(@NotNull final InventoryView view, @NotNull final BaseComponent title);
}
