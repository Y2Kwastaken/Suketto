package sh.miles.suketto.bukkit.menu.handler;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sh.miles.suketto.bukkit.menu.AbstractContainerMenu;
import sh.miles.suketto.bukkit.menu.AbstractInventoryMenu;

import java.util.HashMap;
import java.util.Map;

public class MenuManager {

    private final Map<Inventory, MenuHandler> handlers;

    public MenuManager() {
        this.handlers = new HashMap<>();
    }

    /**
     * Opens an abstract inventory menu
     *
     * @param menu    the menu to open
     * @param viewers the viewers to open the menu for
     */
    @SuppressWarnings("unchecked")
    public final <V> void open(@NotNull final AbstractInventoryMenu<V> menu, @NotNull HumanEntity... viewers) {
        boolean isVoid = menu.getViewerClass().isInstance(Void.class);
        boolean isHumanEntity = menu.getViewerClass().isInstance(HumanEntity.class);
        if (isVoid) {
            menu.apply(null);
        }
        for (HumanEntity viewer : viewers) {
            if (isHumanEntity) {
                menu.apply((V) viewer);
            }
            menu.open(viewer);
        }
        registerHandler(menu.getSlotHolder().getHolder(), menu);
    }

    /**
     * Opens an abstract container menu
     *
     * @param menu   the menu to open
     * @param viewer the viewer who will view the menu
     * @param <V>    the type of viewer who must be at-least a HumanEntity
     */
    public final <V extends HumanEntity> void open(@NotNull final AbstractContainerMenu<V> menu, @NotNull V viewer) {
        menu.apply(viewer);
        menu.open(viewer);
        final InventoryView view = menu.getActiveView();
        if (view == null) {
            Bukkit.getLogger().severe("An entity opened a container, but the view could not be obtained within the same tick");
        }
        registerHandler(view.getTopInventory(), menu);
    }
w
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
