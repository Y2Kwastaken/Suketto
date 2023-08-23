package sh.miles.suketto.minecraft;

import sh.miles.suketto.core.utils.ReflectionUtils;
import sh.miles.suketto.nms.ContainerHandle;
import sh.miles.suketto.nms.InventoryHandle;
import sh.miles.suketto.nms.ItemHandle;

/**
 * Handles internal server code interfaces
 */
public final class VersionHandle {

    private static final String PATH = "sh.miles.suketto.nms.%s.%s%s";
    private static final String SUFFIX = "Impl";

    /**
     * The Container NMS Handle
     */
    public static final ContainerHandle CONTAINER = getHandle(ContainerHandle.class);
    /**
     * The INVENTORY NMS Handle
     */
    public static final InventoryHandle INVENTORY = getHandle(InventoryHandle.class);
    /**
     * The ITEM NMS Handle
     */
    public static final ItemHandle ITEM = getHandle(ItemHandle.class);

    /**
     * Utility
     */
    private VersionHandle() {

    }

    /**
     * Creates a NMS handle
     * @param clazz the class to get hte handle
     * @return returns the type of handle
     * @param <T> the type
     */
    private static <T> T getHandle(Class<T> clazz) {
        return ReflectionUtils.newInstance(
                PATH.formatted(MinecraftVersion.CURRENT.getInternalName(), clazz.getSimpleName(), SUFFIX),
                new Object[0]
        );
    }
}
