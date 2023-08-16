package sh.miles.suketto.bukkit.menu;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.InventoryView;
import org.jetbrains.annotations.NotNull;
import sh.miles.suketto.bukkit.menu.button.MenuButton;
import sh.miles.suketto.bukkit.menu.button.ViewMenuButton;
import sh.miles.suketto.bukkit.menu.holder.SlotHolder;
import sh.miles.suketto.nms.container.SukettoContainer;

public abstract class AbstractContainerMenu extends AbstractMenu<SukettoContainer, HumanEntity> {

    private InventoryView activeView;

    public AbstractContainerMenu(@NotNull final SukettoContainer container) {
        super(SlotHolder.of(container));
    }

    @Override
    public void apply(@NotNull HumanEntity viewer) {
        getButtons().forEach((slot, button) -> {
            if (button instanceof ViewMenuButton vbutton) {
                vbutton.build(viewer);
            }
            slotHolder.setItem(slot, button.icon());
        });
    }

    @Override
    public void handleClick(@NotNull InventoryClickEvent event) {
        if (!event.getInventory().equals(activeView.getTopInventory())) {
            return;
        }

        final int slot = event.getSlot();
        final MenuButton button = getButton(slot);
        if (button != null) {
            button.click(event);
        }
    }

    @Override
    public void handleClose(@NotNull InventoryCloseEvent event) {
        this.activeView = null;
    }

    @Override
    public void open(@NotNull HumanEntity entity) {
        if (this.activeView != null) {
            throw new IllegalStateException("The given menu is already being viewed. Multiple viewers can not view it simultaneously");
        }
        this.activeView = slotHolder.getHolder().open(entity);
    }
}
