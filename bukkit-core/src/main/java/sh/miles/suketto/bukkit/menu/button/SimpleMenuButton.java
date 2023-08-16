package sh.miles.suketto.bukkit.menu.button;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * A simple implementation of {@link MenuButton}
 */
public class SimpleMenuButton implements MenuButton {

    private Consumer<InventoryClickEvent> click = (i) -> {
    };

    private Supplier<ItemStack> icon = () -> new ItemStack(Material.AIR);

    public SimpleMenuButton() {
    }

    public void setClick(Consumer<InventoryClickEvent> click) {
        this.click = click;
    }

    public void setIcon(Supplier<ItemStack> icon) {
        this.icon = icon;
    }

    @Override
    public void click(@NotNull InventoryClickEvent event) {
        click.accept(event);
    }

    @Override
    public ItemStack icon() {
        return icon.get();
    }
}
