package sh.miles.suketto.nms.v1_20_1.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.level.block.Block;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftLocation;
import org.bukkit.inventory.InventoryView;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

/**
 * NMS Inventory Utils for 1.20.1
 */
public final class ServerInventoryUtils {

    /**
     * Opens a container inventory
     *
     * @param entity the entity to open the inventory
     * @param block  the block type to open a virtual inventory
     * @return the inventory view or null
     */
    @Nullable
    public static InventoryView openWorkstation(@NotNull final CraftHumanEntity entity, @NotNull final Block block) {
        return openWorkstation(entity, block, (__) -> null);
    }

    /**
     * Opens a container inventory
     *
     * @param entity    the entity to open the inventory
     * @param block     the block type to make virtual
     * @param exception exceptions overload if Block#getMenuProvider returns null, this is only needed on certain
     *                  inventories
     * @return the inventory view or null
     */
    @Nullable
    public static InventoryView openWorkstation(@NotNull final CraftHumanEntity entity, @NotNull final Block block, @NotNull Function<BlockPos, MenuProvider> exception) {
        final BlockPos position = CraftLocation.toBlockPosition(entity.getLocation());
        MenuProvider provider = block.getMenuProvider(null, entity.getHandle().level(), position);
        if (provider == null) {
            provider = exception.apply(position);
        }

        if (provider == null) {
            return null;
        }

        entity.getHandle().openMenu(provider);
        entity.getHandle().containerMenu.checkReachable = false;
        return entity.getHandle().containerMenu.getBukkitView();
    }

}
