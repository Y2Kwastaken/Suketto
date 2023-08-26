package sh.miles.suketto.bukkit.world;

import com.google.common.base.Preconditions;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * A piece that is bounded to the world
 */
public class BoundPiece extends Piece implements WorldBounded {

    private final String worldName;

    /**
     * Creates a new piece
     *
     * @param worldName world name
     * @param x         x location
     * @param z         z location
     */
    public BoundPiece(@NotNull final String worldName, final int x, final int z) {
        super(x, z);
        this.worldName = worldName;
    }

    /**
     * Creates a new piece
     *
     * @param world the world
     * @param x     x location
     * @param z     z location
     */
    public BoundPiece(@NotNull final World world, final int x, final int z) {
        this(world.getName(), x, z);
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
     * Sets the world name
     *
     * @param worldName the string to set it to
     * @return the new bound piece
     */
    @NotNull
    public BoundPiece setWorldName(@NotNull final String worldName) {
        return new BoundPiece(worldName, x(), z());
    }

    /**
     * Sets the world
     *
     * @param world the world to set
     * @return the new bound piece with world
     */
    @NotNull
    public BoundPiece setWorld(@NotNull final World world) {
        return new BoundPiece(world.getName(), x(), z());
    }

    /**
     * Turns the current bound piece into a chunk
     *
     * @return the chunk
     * @throws IllegalArgumentException if the world this piece is bounded to has not been loaded
     */
    public Chunk asChunk() throws IllegalArgumentException {
        final World world = getWorld();
        Preconditions.checkNotNull(world);
        return super.asChunk(world);
    }

    public Piece unbind() {
        return new Piece(x(), z());
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof BoundPiece that)) return false;
        if (!super.equals(object)) return false;
        return Objects.equals(worldName, that.worldName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), worldName);
    }

    @Override
    public String toString() {
        return "BoundPiece{" + "worldName='" + worldName + '\'' + '}';
    }
}
