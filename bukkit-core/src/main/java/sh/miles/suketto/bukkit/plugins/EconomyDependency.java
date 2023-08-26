package sh.miles.suketto.bukkit.plugins;

import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a dependency that manages an economy and some basic methods required
 */
public interface EconomyDependency extends PluginDependency {

    double getBalance(@NotNull final OfflinePlayer player);

    double setBalance(@NotNull final OfflinePlayer player);

    double addBalance(@NotNull final OfflinePlayer player);

    double removeBalance(@NotNull final OfflinePlayer player);

}
