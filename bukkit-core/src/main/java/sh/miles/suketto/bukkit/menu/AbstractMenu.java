package sh.miles.suketto.bukkit.menu;

import org.jetbrains.annotations.NotNull;
import sh.miles.suketto.bukkit.menu.button.MenuButton;
import sh.miles.suketto.bukkit.menu.handler.MenuHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Defines a specific set of instructions that an inventory can follow.
 *
 * @param <V>
 */
public abstract class AbstractMenu<V> implements MenuHandler {

    private final Map<Integer, MenuButton> buttons;
    private final int minSlot;
    private final int maxSlot;

    protected AbstractMenu(final int minSlot, final int maxSlot) {
        this.buttons = new HashMap<>();
        this.minSlot = minSlot;
        this.maxSlot = maxSlot;
    }

    protected void setButton(final int slot, @NotNull final MenuButton button) throws IllegalArgumentException {
        validateSlot(slot);
        this.buttons.put(slot, button);
    }

    protected MenuButton getButton(final int slot) {
        validateSlot(slot);
        return this.buttons.get(slot);
    }

    protected Map<Integer, MenuButton> getButtons() {
        return new HashMap<>(this.buttons);
    }

    protected void validateSlot(final int slot) {
        if (slot < minSlot && slot > maxSlot) {
            throw new IllegalArgumentException("the given slot could not be validated because the given slot %d is either less than %d or greater than %d".formatted(slot, minSlot, maxSlot));
        }
    }

    /**
     * this method should be implemented in order to decorate the inventory. when implementation your decorates you must
     * ensure the super method is called after all decorations have been added.
     *
     * @param viewer the viewer of the inventory
     * @implNote if you are extending hte AbstractMenu class you do not have to call the apply method
     */
    protected abstract void apply(@NotNull final V viewer);
}
