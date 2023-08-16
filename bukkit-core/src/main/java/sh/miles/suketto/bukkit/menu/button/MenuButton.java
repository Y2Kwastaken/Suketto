package sh.miles.suketto.bukkit.menu.button;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import sh.miles.suketto.bukkit.menu._old.AbstractMenu;

/**
 * A menu button that goes inside an implementation of {@link AbstractMenu}
 */
public interface MenuButton {

    /**
     * Code triggered when the item is clicked
     *
     * @param event  the event
     */
    void click(@NotNull final InventoryClickEvent event);

    /**
     * The icon that the player will see
     *
     * @return the item stack
     */
    ItemStack icon();
}
