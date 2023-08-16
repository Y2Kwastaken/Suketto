package sh.miles.suketto.bukkit.menu.impl;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.chat.ComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import sh.miles.suketto.bukkit.menu.AbstractInventoryMenu;

/**
 * A Simple implementation of the AbstractInventory menu for a player
 */
public class SimpleInventoryMenu extends AbstractInventoryMenu<Player> {

    public SimpleInventoryMenu(@NotNull BaseComponent title, final int size) {
        super(Bukkit.createInventory(null, size, ComponentSerializer.toString(title)), title);
    }

    @NotNull
    @Override
    public Class<Player> getViewerClass() {
        return Player.class;
    }
}
