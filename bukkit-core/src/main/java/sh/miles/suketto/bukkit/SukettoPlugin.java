package sh.miles.suketto.bukkit;

import org.bukkit.plugin.java.JavaPlugin;
import sh.miles.suketto.bukkit.command.SCommandRegistrar;
import sh.miles.suketto.bukkit.helpers.YamlConfigHelper;
import sh.miles.suketto.bukkit.menu.handler.MenuHandlerListener;
import sh.miles.suketto.bukkit.menu.handler.MenuManager;
import sh.miles.suketto.bukkit.task.SukettoScheduler;
import sh.miles.suketto.bukkit.task.TaskWrapper;

/**
 * A base plugin class that should be extended when wanting to use the Suketto Library
 */
public class SukettoPlugin extends JavaPlugin {

    private TaskWrapper taskWrapper;
    private SukettoScheduler schedulerService;
    private MenuManager menuManager;
    private SCommandRegistrar commandRegistrar;
    private YamlConfigHelper yamlConfigHelper;

    @Override
    public void onEnable() {
        taskWrapper = new TaskWrapper(this);
        schedulerService = new SukettoScheduler();
        menuManager = new MenuManager();
        commandRegistrar = new SCommandRegistrar();
        yamlConfigHelper = new YamlConfigHelper(this);

        getServer().getPluginManager().registerEvents(new MenuHandlerListener(menuManager), this);
    }

    @Override
    public void onDisable() {
        schedulerService.shutdownAll();
    }

    /**
     * Retrieves the task wrapper which provides convenience wrappers
     *
     * @return the task wrapper
     */
    public TaskWrapper getTaskWrapper() {
        return this.taskWrapper;
    }

    /**
     * Retrieves the SukettoScheduler which provides convenience methods for some asynchronous processing
     *
     * @return the suketto scheduler service
     */
    public SukettoScheduler getSchedulerService() {
        return schedulerService;
    }

    /**
     * Manages menu opening and registration for events and more
     *
     * @return the suketto MenuManager
     */
    public MenuManager getMenuManager() {
        return this.menuManager;
    }

    /**
     * Retrieves the Command Registrar which is responsible for registering commands
     *
     * @return the command registrar
     */
    public SCommandRegistrar getCommandRegistrar() {
        return this.commandRegistrar;
    }

    /**
     * Retrieves the YamlConfigHelper which is responsible for helping the loading and creation and retrieval of config
     * files.
     *
     * @return the yaml config helper
     */
    public YamlConfigHelper getConfigHelper() {
        return this.yamlConfigHelper;
    }
}
