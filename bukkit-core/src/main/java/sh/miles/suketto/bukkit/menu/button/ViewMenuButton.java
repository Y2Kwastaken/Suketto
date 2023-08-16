package sh.miles.suketto.bukkit.menu.button;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class ViewMenuButton extends SimpleMenuButton {

    private Function<HumanEntity, ItemStack> icon;

    public ViewMenuButton() {
        super();
    }

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
