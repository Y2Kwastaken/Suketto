package sh.miles.suketto.bukkit.world;

import com.google.common.base.Preconditions;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * A position that is bound to the world
 */
public class BoundPosition extends Position implements WorldBounded {

    private final String worldName;

    /**
     * Creates a new position
     *
     * @param x x location
     * @param y y location
     * @param z z location
     */
    public BoundPosition(@NotNull final String worldName, final int x, final int y, final int z) {
        super(x, y, z);
        this.worldName = worldName;
    }

    /**
     * Creates a new bound position
     *
     * @param world the world
     * @param x     x location
     * @param y     y location
     * @param z     z location
     */
    public BoundPosition(@NotNull final World world, final int x, final int y, final int z) {
        this(world.getName(), x, y, z);
    }

    @NotNull
    @Override
    public String getWorldName() {
        return this.worldName;
    }

    @Nullable
    @Override
    public World getWorld() {
        return Bukkit.getWorld(this.worldName);
    }

    /**
     * Sets the name of the world and returns a new world name
     *
     * @param worldName the name of the world
     * @return the new bound position
     */
    public BoundPosition setWorldName(@NotNull final String worldName) {
        return new BoundPosition(worldName, x(), y(), z());
    }

    public Position unbind() {
        return new Position(x(), y(), z());
    }

    public Location asLocation() {
        final World world = getWorld();
        Preconditions.checkNotNull(world);
        return asLocation(world);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof BoundPosition that)) return false;
        if (!super.equals(object)) return false;
        return Objects.equals(worldName, that.worldName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), worldName);
    }

    @Override
    public String toString() {
        return "BoundPosition{" + "worldName='" + worldName + '\'' + '}';
    }
}
