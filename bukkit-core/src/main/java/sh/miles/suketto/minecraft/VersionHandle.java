package sh.miles.suketto.minecraft;

import sh.miles.suketto.core.utils.ReflectionUtils;

/**
 * Handles internal server code interfaces
 */
public final class VersionHandle {


    private static final String PATH = "sh.miles.suketto.nms.%s.%s%s";
    private static final String SUFFIX = "Impl";

    /**
     * Utility
     */
    private VersionHandle() {

    }

    private static <T> T getHandle(Class<T> clazz) {
        return ReflectionUtils.newInstance(
                PATH.formatted(MinecraftVersion.CURRENT.getInternalName(), clazz.getSimpleName(), SUFFIX),
                new Object[0]
        );
    }
}
