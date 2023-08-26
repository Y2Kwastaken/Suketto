package sh.miles.suketto.bukkit.plugins;

/**
 * Represents a dependency on a plugin
 */
public interface PluginDependency {

    /**
     * Logic to be run on plugin start
     */
    void onLaunch();

    /**
     * Retrieves whether the plugin is running or not
     *
     * @return true if the plugin is running
     */
    boolean isRunning();
}
