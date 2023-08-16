package sh.miles.suketto.bukkit.command;

import com.google.common.base.Preconditions;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides information to a command like name usage etc
 */
public final class SCommandLabel {

    public static final String DEFAULT_DESCRIPTION = "A Command created with Suketto";

    private final String name;
    private final String permission;
    private final String description;
    private final List<String> aliases;

    public SCommandLabel(@NotNull final String name, @NotNull final String permission, @NotNull final String description, List<String> aliases) {
        Preconditions.checkNotNull(name);
        Preconditions.checkNotNull(permission);
        Preconditions.checkNotNull(description);
        Preconditions.checkNotNull(aliases);

        this.name = name;
        this.permission = permission;
        this.description = description;
        this.aliases = aliases;
    }

    public SCommandLabel(@NotNull final String name, @NotNull final String permission, @NotNull final String description) {
        this(name, permission, description, new ArrayList<>());
    }

    public SCommandLabel(@NotNull final String name, @NotNull final String permission) {
        this(name, permission, DEFAULT_DESCRIPTION);
    }

    /**
     * Retrieves the name
     *
     * @return a string name
     */
    @NotNull
    public String getName() {
        return this.name;
    }

    /**
     * Retrieves the permission
     *
     * @return a string permission
     */
    @NotNull
    public String getPermission() {
        return this.permission;
    }

    /**
     * Retrieves the description
     *
     * @return a string description
     */
    @NotNull
    public String getDescription() {
        return this.description;
    }

    /**
     * Retrieves the aliases
     *
     * @return a list of aliases
     */
    @NotNull
    public List<String> getAliases() {
        return new ArrayList<>(this.aliases);
    }

}
