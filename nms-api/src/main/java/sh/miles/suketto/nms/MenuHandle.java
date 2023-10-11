package sh.miles.suketto.nms;

import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.jetbrains.annotations.NotNull;

/**
 * Menu's within NMS represent the actual display and functionality within an inventory, which is just a set of slots
 * that can be used. This Interface handles anything that could fall under the category of menus, opening, closing,
 * naming etc.
 */
public interface MenuHandle {


    /**
     * Attempts to open the provided inventory for the provided player with the given BaseComponent title
     *
     * @param player    the opening player
     * @param inventory the inventory to open
     * @param title     the title
     * @return the inventory view
     * @throws IllegalStateException    if the menu could not be opened
     * @throws IllegalArgumentException if the inventory is not openable
     */
    @NotNull
    InventoryView open(@NotNull final Player player, @NotNull final Inventory inventory, @NotNull final BaseComponent... title) throws IllegalStateException, IllegalArgumentException;

    /**
     * Opens the provided workstation for the player if no errors occur
     *
     * @param player      the player to open the workstation for
     * @param workstation the workstation to open
     * @param location    the location
     * @return the inventory view of the workstation
     * @throws IllegalArgumentException if the player passed is not a craft player, or if the material passed is not a
     *                                  workstation that can be opened
     */
    @NotNull
    InventoryView openWorkstation(@NotNull final Player player, @NotNull final Material workstation, @NotNull final Location location) throws IllegalArgumentException;

    /**
     * Sends a cosmetic only -this means that the title will be restored upon the inventory being reopened - temporary
     * title change to all viewers of the provided inventory. Note this method simply calls
     * {@link MenuHandle#sendTitleChange(InventoryView, BaseComponent...)} with a loop of all viewers
     *
     * @param inventory the inventory to send the change too
     * @param title     the title to change too
     */
    void sendTitleChange(@NotNull final Inventory inventory, @NotNull final BaseComponent... title);

    /**
     * Sends a cosmetic only Sends a cosmetic only -this means that the title will be restored upon the inventory being
     * reopened - title change to the view
     *
     * @param view  the view to change the title of
     * @param title the title
     */
    void sendTitleChange(@NotNull final InventoryView view, @NotNull final BaseComponent... title);
}
