package sh.miles.suketto.bukkit.menu.handler;

import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sh.miles.suketto.bukkit.menu.SimpleInventoryMenu;

import java.util.HashMap;
import java.util.Map;

public class MenuHandlerManager {

    private final Map<Inventory, MenuHandler> handlers;

    public MenuHandlerManager() {
        this.handlers = new HashMap<>();
    }

    /**
     * Opens a simple inventory menu
     *
     * @param menu    the menu to open
     * @param viewers the viewers to open the menu for
     */
    public void open(@NotNull final SimpleInventoryMenu menu, @NotNull HumanEntity... viewers) {
        this.handlers.put(menu.getInventory(), menu);
        menu.apply(null);
        for (HumanEntity viewer : viewers) {
            menu.open(viewer);
        }
    }

    /**
     * Registers a menu handler to a specific inventory
     *
     * @param inventory   the inventory
     * @param menuHandler the menu handler to register to the menu
     */
    public void registerHandler(@NotNull final Inventory inventory, @NotNull final MenuHandler menuHandler) {
        this.handlers.put(inventory, menuHandler);
    }

    /**
     * Unregisters an inventory from the MenuHandlerManager
     *
     * @param inventory the inventory to unregister
     * @return the menu handler associated with the inventory
     */
    @Nullable
    public MenuHandler unregisterHandler(@NotNull final Inventory inventory) {
        return this.handlers.remove(inventory);
    }

    @Nullable
    public MenuHandler getHandler(@NotNull final Inventory inventory) {
        return this.handlers.get(inventory);
    }
}
