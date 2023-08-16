package sh.miles.suketto.bukkit.menu.handler;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.jetbrains.annotations.NotNull;

/**
 * Handles events related to menus
 */
public interface MenuHandler {

    /**
     * Handles menu clicks
     *
     * @param event the click event
     */
    void handleClick(@NotNull final InventoryClickEvent event);

    /**
     * Handles menu opening
     *
     * @param event the open event
     */
    void handleOpen(@NotNull final InventoryOpenEvent event);

    /**
     * Handles menu closing
     *
     * @param event the close event
     */
    void handleClose(@NotNull final InventoryCloseEvent event);

    void open(@NotNull final HumanEntity entity);
}
