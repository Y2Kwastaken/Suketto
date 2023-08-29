package sh.miles.suketto.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import sh.miles.suketto.bukkit.chat.translation.PluginTranslationManager;
import sh.miles.suketto.bukkit.command.SCommandRegistrar;
import sh.miles.suketto.bukkit.helpers.YamlConfigHelper;
import sh.miles.suketto.bukkit.menu.handler.MenuHandlerListener;
import sh.miles.suketto.bukkit.menu.handler.MenuManager;
import sh.miles.suketto.bukkit.task.SukettoScheduler;
import sh.miles.suketto.bukkit.task.TaskWrapper;
import sh.miles.suketto.bukkit.task.ticking.ServerThreadTicker;

/**
 * Another way to interface with Suketto that does not require extending the SukettoPlugin class
 */
public final class Suketto {

    /**
     * Creates a new task wrapper
     *
     * @param plugin the plugin
     * @return the new task wrapper
     */
    public static TaskWrapper newTaskWrapper(@NotNull final Plugin plugin) {
        return new TaskWrapper(plugin);
    }

    /**
     * Creates a new Scheduler Serivce
     *
     * @return the scheduler service
     */
    public static SukettoScheduler newSchedulerService() {
        return new SukettoScheduler();
    }

    /**
     * Creates a new menu manager and registers the required listener with it
     *
     * @param plugin the plugin to use
     * @return the new menu manager
     */
    public static MenuManager newMenuManager(@NotNull final Plugin plugin) {
        final MenuManager manager = new MenuManager();
        Bukkit.getPluginManager().registerEvents(new MenuHandlerListener(manager), plugin);
        return manager;
    }

    /**
     * Creates a new command registrar
     *
     * @return the new command registrar
     */
    public static SCommandRegistrar newCommandRegistrar() {
        return new SCommandRegistrar();
    }

    /**
     * Creates a new yaml config helper
     *
     * @param plugin the plugin to create the yaml config helper for
     * @return the new yaml config helper
     */
    public static YamlConfigHelper newYamlConfigHelper(@NotNull final Plugin plugin) {
        return new YamlConfigHelper(plugin);
    }

    /**
     * Creates a new plugin translation manager
     *
     * @param plugin           the plugin to create the manager for
     * @param fileName         the file name of the translation file
     * @param yamlConfigHelper the config helper
     * @return the newly created plugin translation manager
     */
    public static PluginTranslationManager newPluginTranslationManager(@NotNull final Plugin plugin, @NotNull String fileName, @NotNull final YamlConfigHelper yamlConfigHelper) {
        return new PluginTranslationManager(plugin, "bundle", fileName, yamlConfigHelper);
    }

    /**
     * Creates a new plugin ServerThreadTicker
     *
     * @param plugin the plugin to register the new ServerThreadTicker with
     * @return the main thread ticker
     */
    public static ServerThreadTicker newMainThreadTicker(@NotNull final Plugin plugin) {
        final ServerThreadTicker ticker = new ServerThreadTicker();
        Bukkit.getScheduler().runTaskTimer(plugin, ticker, 1L, 1L);
        return ticker;
    }

}
