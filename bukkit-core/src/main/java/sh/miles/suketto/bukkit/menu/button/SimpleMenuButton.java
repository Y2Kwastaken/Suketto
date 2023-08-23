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

    /**
     * New SimpleMenuButton
     */
    public SimpleMenuButton() {
    }

    /**
     * Sets the click event
     *
     * @param click the click event to set
     */
    public void setClick(Consumer<InventoryClickEvent> click) {
        this.click = click;
    }

    /**
     * Sets the icon
     *
     * @param icon the icon to set
     */
    public void setIcon(Supplier<ItemStack> icon) {
        this.icon = icon;
    }

    /**
     * executes the click
     *
     * @param event the Fevent
     */
    @Override
    public void click(@NotNull InventoryClickEvent event) {
        click.accept(event);
    }

    /**
     * Gets the icon
     *
     * @return the item stack
     */
    @Override
    public ItemStack icon() {
        return icon.get();
    }
}
