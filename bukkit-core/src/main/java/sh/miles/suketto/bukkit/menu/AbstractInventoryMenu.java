package sh.miles.suketto.bukkit.menu;

import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import sh.miles.suketto.annotations.NMS;
import sh.miles.suketto.bukkit.menu.button.MenuButton;
import sh.miles.suketto.bukkit.menu.holder.SlotHolder;
import sh.miles.suketto.minecraft.VersionHandle;

/**
 * An abstract inventory implementation of AbstractMenu intended for use with bukkit inventories
 *
 * @param <V> the Viewer type
 */
public abstract class AbstractInventoryMenu<V> extends AbstractMenu<Inventory, V> {

    private final BaseComponent title;

    /**
     * Creates AbstractInventory menu
     *
     * @param inventory inventory to use
     * @param title     title to use
     */
    protected AbstractInventoryMenu(@NotNull final Inventory inventory, @NotNull final BaseComponent title) {
        super(SlotHolder.of(inventory));
        this.title = title;
    }

    @Override
    public void handleClick(@NotNull InventoryClickEvent event) {
        if (!event.getInventory().equals(slotHolder.getHolder())) {
            return;
        }

        final int slot = event.getSlot();
        final MenuButton button = getButton(slot);
        if (button != null) {
            button.click(event);
        }
    }

    @NMS
    @Override
    public void open(@NotNull HumanEntity entity) {
        if (!(entity instanceof Player player)) {
            entity.openInventory(slotHolder.getHolder());
            VersionHandle.INVENTORY.sendTitleChange(entity.getOpenInventory(), title);
            return;
        }
        VersionHandle.INVENTORY.open(slotHolder.getHolder(), title, player);
    }
}
