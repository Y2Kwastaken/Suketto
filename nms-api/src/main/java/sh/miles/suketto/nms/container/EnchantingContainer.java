package sh.miles.suketto.nms.container;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * A Custom container representing an enchantment table
 */
public interface EnchantingContainer {

    int ITEM_SLOT = 0;
    int LAPIS_SLOT = 1;

    @NotNull
    ItemStack getItem();

    @NotNull
    ItemStack getLapis();

    void setItem(@NotNull final ItemStack item);

    void setLapis(@NotNull final ItemStack item);

}
