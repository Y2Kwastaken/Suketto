package sh.miles.suketto.nms.inventory;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * A custom inventory representing an anvil
 */
public interface AnvilCustomInventory extends SukettoCustomInventory {

    int FIRST_INDEX = 0;
    int SECOND_INDEX = 1;
    int RESULT_INDEX = 2;

    /**
     * The first anvil slot item
     *
     * @return the first anvil slot item
     */
    @NotNull
    ItemStack getFirst();

    /**
     * The second anvil slot item
     *
     * @return the second anvil slot item
     */
    @NotNull
    ItemStack getSecond();

    /**
     * The resulting ItemStack
     *
     * @return the result item
     */
    @NotNull
    ItemStack getResult();

    /**
     * Sets the first item in the inventory
     * @param item the item to set
     */
    void setFirst(@NotNull final ItemStack item);

    /**
     * Sets the second item in the inventory
     * @param item the item to set
     */
    void setSecond(@NotNull final ItemStack item);

    /**
     * Sets the result item in the inventory
     * @param item the item to set
     */
    void setResult(@NotNull final ItemStack item);
}
