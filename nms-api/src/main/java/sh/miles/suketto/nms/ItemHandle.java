package sh.miles.suketto.nms;

import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.Component;
import java.util.List;

/**
 * Handles interaction between NMS and the bukkit api concerning Items
 */
public interface ItemHandle {

    /**
     * Sets the provided Item Stack's name as the provided component
     *
     * @param item the item to set the name of
     * @param name the name of the item
     * @return The ItemStack with the applied name
     */
    ItemStack setName(@NotNull final ItemStack item, @NotNull final BaseComponent... name);

    /**
     * Gets the item name as the provided component
     *
     * @param item the item
     * @return the name of the item or null if it has no name
     */
    @Nullable
    BaseComponent
    getName(@NotNull final ItemStack item);

    /**
     * Sets the lore on the Item Stack with the provided list of components
     *
     * @param item the item to set the lore of
     * @param lore the lore to set on the item
     * @return the ItemStack with the applied lore
     */
    ItemStack setLore(@NotNull final ItemStack item, @NotNull final List<BaseComponent> lore);

    /**
     * Sets the lore on the Item Stack with the provided list of components
     *
     * @param item the item to set the lore of
     * @param lore the lore to set on the item
     * @return the ItemStack with the applied lore
     */
    ItemStack setLoreArray(@NotNull final ItemStack item, @NotNull final List<BaseComponent[]> lore);

    /**
     * Gets hte lore on the item or an empty list if there is none
     *
     * @param item the item to get the lore
     * @return a list of lore
     */
    @NotNull
    List<BaseComponent> getLore(@NotNull final ItemStack item);
}
