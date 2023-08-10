package sh.miles.suketto.bukkit.serialization;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * Manages all bukkit adapters and supports runtime registration of new adapters
 */
public final class BukkitAdapterManager {

    /**
     * Class is the generic input bukkit class
     */
    private final Map<Class<?>, BukkitAdapter<?, ?>> adapters;

    private BukkitAdapterManager() {
        this.adapters = new HashMap<>();
    }

    public void registerAdapter(@NotNull final Class<?> bukkitClass, BukkitAdapter<?, ?> adapter) {

    }
}
