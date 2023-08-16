package sh.miles.suketto.bukkit.menu.impl;

import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import sh.miles.suketto.annotations.NMS;
import sh.miles.suketto.bukkit.menu.AbstractContainerMenu;
import sh.miles.suketto.minecraft.VersionHandle;
import sh.miles.suketto.nms.container.SukettoContainerType;

/**
 * A Simple implementation of the AbstractContainer menu
 */
@NMS
public class SimpleContainerMenu extends AbstractContainerMenu<Player> {

    public SimpleContainerMenu(@NotNull final BaseComponent title, @NotNull final SukettoContainerType type) {
        super(VersionHandle.CONTAINER.createContainer(title, type));
    }

    @NotNull
    @Override
    public Class<Player> getViewerClass() {
        return Player.class;
    }
}
