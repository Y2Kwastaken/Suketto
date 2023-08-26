package sh.miles.suketto.bukkit.world;

import org.bukkit.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a set of coordinates no matter the range that is some way bound to the world
 */
public interface WorldBounded {

    /**
     * The world name
     *
     * @return the name of the world the object is bounded too
     */
    @NotNull
    String getWorldName();

    /**
     * The world the object is bound to given that the world is loaded
     *
     * @return the world if loaded or null
     */
    @Nullable
    World getWorld();

}
