package sh.miles.suketto.bukkit.menu.handler;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.jetbrains.annotations.NotNull;
import sh.miles.suketto.annotations.Internal;

import java.util.function.Consumer;

/**
 * Listener for running events registered with the MenuHandlerManager
 */
@Internal
public class MenuHandlerListener implements Listener {

    private final MenuManager manager;

    /**
     * Creates a menu handler lister
     *
     * @param manager the manager to use
     */
    public MenuHandlerListener(@NotNull final MenuManager manager) {
        this.manager = manager;
    }

    /**
     * Click event
     * @param event event
     */
    @EventHandler
    public void onClick(@NotNull InventoryClickEvent event) {
        onEvent(event, (h) -> h.handleClick(event));
    }

    /**
     * Open Event
     * @param event event
     */
    @EventHandler
    public void onOpen(@NotNull InventoryOpenEvent event) {
        onEvent(event, (h) -> h.handleOpen(event));
    }

    @EventHandler
    public void onClose(@NotNull InventoryCloseEvent event) {
        onEvent(event, (h) -> {
            h.handleClose(event);
            if (event.getViewers().size() <= 1) {
                manager.unregisterHandler(event.getInventory());
            }
        });
    }

    private void onEvent(InventoryEvent event, Consumer<MenuHandler> action) {
        final MenuHandler handler = manager.getHandler(event.getInventory());
        if (handler == null) {
            return;
        }
        action.accept(handler);
    }
}
