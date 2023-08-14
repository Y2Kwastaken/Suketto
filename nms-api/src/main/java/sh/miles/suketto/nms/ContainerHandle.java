package sh.miles.suketto.nms;

import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.inventory.InventoryView;
import org.jetbrains.annotations.NotNull;
import sh.miles.suketto.nms.container.SukettoContainer;
import sh.miles.suketto.nms.container.SukettoContainerType;

/**
 * Handles the NMS implementation for containers.
 * <p>
 * Containers differ from inventories in that containers do not have a permanent inventory they can reference too
 */
public interface ContainerHandle {

    /**
     * creates a container, containers are not like inventories and act differently. the ContainerType specifies how the
     * container looks and convenience methods that can be used.
     *
     * @param title the title that the container will have for all viewers ever
     * @param type the type of container to create
     * @return the specified type of container
     * @throws IllegalArgumentException thrown if the supplied type is null or not available to be created
     */
    @NotNull
    SukettoContainer createContainer(@NotNull final BaseComponent title, @NotNull final SukettoContainerType type);

}
