package sh.miles.suketto.bukkit.menu.button;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class ViewMenuButton extends SimpleMenuButton {

    private BiConsumer<Player, InventoryClickEvent> click;
    private Function<Player, ItemStack> icon;

    public ViewMenuButton() {
        super();
    }

    public void setClick(@NotNull final BiConsumer<Player, InventoryClickEvent> click) {
        this.click = click;
    }

    public void setIcon(@NotNull final Function<Player, ItemStack> icon) {
        this.icon = icon;
    }

    @Deprecated
    @Override
    public void setIcon(Supplier<ItemStack> icon) {
        throw new UnsupportedOperationException("this method can not be called in ViewMenuButton");
    }

    @Deprecated
    @Override
    public void setClick(Consumer<InventoryClickEvent> click) {
        throw new UnsupportedOperationException("this method can not be called in ViewMenuButton");
    }

    /**
     * Builder method that transforms ViewMenuButton specific attributes into a usable MenuButton
     *
     * @param player the player
     */
    public void build(@NotNull final Player player) {
        super.setClick((e) -> click.accept(player, e));
        super.setIcon(() -> icon.apply(player));
    }
}
