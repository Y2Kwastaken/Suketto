package sh.miles.suketto.bukkit.menu;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Item;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;
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
    /**
     * the slot holder of this menu
     */
    protected final SlotHolder<H> slotHolder;

    /**
     * Creates a new AbstractMenu
     *
     * @param slotHolder the slot holder
     */
    protected AbstractMenu(SlotHolder<H> slotHolder) {
        this.buttons = new HashMap<>();
        this.slotHolder = slotHolder;
    }

    /**
     * Applies the styling to the given inventory
     *
     * @param viewer the view to apply the styling for
     */
    public void apply(V viewer) {
        boolean isHumanEntity = viewer instanceof HumanEntity;
        getButtons().forEach((slot, button) -> {
            if (button instanceof ViewMenuButton vbutton && isHumanEntity) {
                vbutton.build((HumanEntity) viewer);
            }
            slotHolder.setItem(slot, button.icon());
        });
    }

    /**
     * Sets a menu button
     *
     * @param slot   the slot
     * @param button the button to set
     */
    protected void setButton(final int slot, @NotNull final MenuButton button) {
        validateSlot(slot);
        this.buttons.put(slot, button);
    }

    /**
     * Gets a menu button
     *
     * @param slot the slot to get it from
     * @return the menu button otherwise null
     */
    @Nullable
    public MenuButton getButton(final int slot) {
        validateSlot(slot);
        return this.buttons.get(slot);
    }

    /**
     * Fills all empty slots with the specified stack
     *
     * @param itemStack the item stack
     */
    public void fillEmpty(@NotNull final ItemStack itemStack) {
        for (int i = slotHolder.getMinIndex(); i <= slotHolder.getMaxIndex(); i++) {
            if (!slotHolder.hasItemAt(i)) {
                slotHolder.setItem(i, itemStack);
            }
        }
    }

    /**
     * Returns a copy of the buttons for the current menu
     *
     * @return a copy of the slot button map
     */
    protected final Map<Integer, MenuButton> getButtons() {
        return new HashMap<>(this.buttons);
    }

    /**
     * Gets hte slot holder
     *
     * @return the slot holder
     */
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
     * <p>
     * if no viewer in specific is targeted you can use the Void class to denote that there is no player specific
     * aspects to this menu. The Void class indicates that the menu can be cached.
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
