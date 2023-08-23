package sh.miles.suketto.bukkit.menu.button;

import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * the ViewMenuButton is an extension of a SimpleMenu button which supports using the player in the Icon
 */
public class ViewMenuButton extends SimpleMenuButton {

    private Function<HumanEntity, ItemStack> icon;

    /**
     * Creates a view menu button
     */
    public ViewMenuButton() {
        super();
    }

    /**
     * sets the icon
     *
     * @param icon icon function
     */
    public void setIcon(@NotNull final Function<HumanEntity, ItemStack> icon) {
        this.icon = icon;
    }

    @Deprecated
    @Override
    public void setIcon(Supplier<ItemStack> icon) {
        throw new UnsupportedOperationException("this method can not be called in ViewMenuButton");
    }

    /**
     * Builder method that transforms ViewMenuButton specific attributes into a usable MenuButton
     *
     * @param human the player
     */
    public void build(@NotNull final HumanEntity human) {
        super.setIcon(() -> icon.apply(human));
    }
}
