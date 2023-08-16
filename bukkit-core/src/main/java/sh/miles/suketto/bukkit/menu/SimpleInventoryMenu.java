package sh.miles.suketto.bukkit.menu;

import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sh.miles.suketto.annotations.NMS;
import sh.miles.suketto.bukkit.menu.button.MenuButton;
import sh.miles.suketto.minecraft.VersionHandle;

/**
 * A Simple implementation of AbstractMenu which does not consider player attributes and is created. SimpleMenu's should
 * be cached as they are not bound to specific players for data and other information.
 */
public class SimpleInventoryMenu extends AbstractMenu<Void> {

    private final Inventory inventory;
    private final BaseComponent title;

    public SimpleInventoryMenu(final Inventory inventory, final BaseComponent title) {
        super(0, inventory.getSize());
        this.inventory = inventory;
        this.title = title;
    }

    @Override
    public void apply(@Nullable Void viewer) {
        getButtons().forEach((slot, button) -> {
            inventory.setItem(slot, button.icon());
        });
    }

    @Override
    public void handleClick(@NotNull InventoryClickEvent event) {
        if (!event.getInventory().equals(inventory)) {
            return;
        }

        final int slot = event.getSlot();
        final MenuButton button = getButton(slot);
        if (button != null) {
            button.click(event);
        }
    }

    @Override
    public void handleOpen(@NotNull InventoryOpenEvent event) {
    }

    @Override
    public void handleClose(@NotNull InventoryCloseEvent event) {
    }

    @NMS
    @Override
    public void open(@NotNull HumanEntity entity) {
        if (!(entity instanceof Player player)) {
            entity.openInventory(inventory);
            VersionHandle.INVENTORY.sendTitleChange(entity.getOpenInventory(), title);
            return;
        }
        VersionHandle.INVENTORY.open(inventory, title, player);
    }

    public Inventory getInventory() {
        return this.inventory;
    }
}
