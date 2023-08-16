package sh.miles.suketto.bukkit.menu;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sh.miles.suketto.bukkit.menu.button.MenuButton;
import sh.miles.suketto.bukkit.menu.button.ViewMenuButton;
import sh.miles.suketto.bukkit.menu.handler.MenuHandler;
import sh.miles.suketto.bukkit.menu.holder.SlotHolder;

import java.util.HashMap;
import java.util.Map;

/**
 * An abstract implementation of MenuHandler that provides the most basic structure needed
 *
 * @param <H> the type of holder
 * @param <V> the type of viewer
 */
public abstract class AbstractMenu<H, V> implements MenuHandler {

    private final Map<Integer, MenuButton> buttons;
    protected final SlotHolder<H> slotHolder;

    protected AbstractMenu(SlotHolder<H> slotHolder) {
        this.buttons = new HashMap<>();
        this.slotHolder = slotHolder;
    }

    public void apply(@NotNull V viewer) {
        boolean isHumanEntity = viewer instanceof HumanEntity;
        getButtons().forEach((slot, button) -> {
            if (button instanceof ViewMenuButton vbutton && isHumanEntity) {
                vbutton.build((HumanEntity) viewer);
            } else {
                Bukkit.getLogger().warning("Tried to put VButton ");
            }
            slotHolder.setItem(slot, button.icon());
        });
    }

    protected void setButton(final int slot, @NotNull final MenuButton button) {
        validateSlot(slot);
        this.buttons.put(slot, button);
    }

    @Nullable
    public MenuButton getButton(final int slot) {
        validateSlot(slot);
        return this.buttons.get(slot);
    }

    /**
     * Returns a copy of the buttons for the current menu
     *
     * @return a copy of the slot button map
     */
    protected final Map<Integer, MenuButton> getButtons() {
        return new HashMap<>(this.buttons);
    }

    public SlotHolder<H> getSlotHolder() {
        return this.slotHolder;
    }

    /**
     * Validates the given slot
     *
     * @param slot the slot to validate
     * @throws IllegalArgumentException thrown if the slot is not valid
     */
    private void validateSlot(final int slot) throws IllegalArgumentException {
        if (slot < slotHolder.getMinIndex() && slot > slotHolder.getMaxIndex()) {
            throw new IllegalArgumentException("the given slot could not be validated because the given slot %d is either less than %d or greater than %d".formatted(slot, slotHolder.getMinIndex(), slotHolder.getMaxIndex()));
        }
    }

    /**
     * The class of the type of viewer who will be viewing this inventory
     *
     * @return the class of viewer
     * @implNote if no viewer in specific is targeted you can use the Void class to denote that there is no player
     * specific aspects to this menu. The Void class indicates that the menu can be cached.
     */
    @NotNull
    public abstract Class<V> getViewerClass();

    @Override
    public void handleOpen(@NotNull InventoryOpenEvent event) {
    }

    @Override
    public void handleClose(@NotNull InventoryCloseEvent event) {
    }
}
