package sh.miles.suketto.minecraft;

import sh.miles.suketto.core.utils.ReflectionUtils;
import sh.miles.suketto.nms.ItemHandle;
import sh.miles.suketto.nms.MenuHandle;

/**
 * Handles internal server code interfaces
 */
public final class VersionHandle {

    private static final String PATH = "sh.miles.suketto.nms.%s.%s%s";
    private static final String SUFFIX = "Impl";

    public static final MenuHandle MENU = getHandle(MenuHandle.class);
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
     *
     * @param clazz the class to get hte handle
     * @param <T>   the type
     * @return returns the type of handle
     */
    private static <T> T getHandle(Class<T> clazz) {
        return ReflectionUtils.newInstance(
                PATH.formatted(MinecraftVersion.CURRENT.getInternalName(), clazz.getSimpleName(), SUFFIX),
                new Object[0]
        );
    }
}
