package sh.miles.suketto.nms.container;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * A custom container representing a grindstone
 */
public interface GrindstoneContainer {

    int ITEM_SLOT = 0;

    @NotNull
    ItemStack getItem();

    void setItem(@NotNull final ItemStack item);

}
