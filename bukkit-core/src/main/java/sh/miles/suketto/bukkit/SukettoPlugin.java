package sh.miles.suketto.bukkit;

import org.bukkit.plugin.java.JavaPlugin;
import sh.miles.suketto.bukkit.menu.handler.MenuHandlerListener;
import sh.miles.suketto.bukkit.menu.handler.MenuHandlerManager;
import sh.miles.suketto.bukkit.task.SukettoScheduler;
import sh.miles.suketto.bukkit.task.TaskWrapper;

/**
 * A base plugin class that should be extended when wanting to use the Suketto Library
 */
public class SukettoPlugin extends JavaPlugin {

    private TaskWrapper taskWrapper;
    private SukettoScheduler schedulerService;
    private MenuHandlerManager menuManager;

    @Override
    public void onEnable() {
        taskWrapper = new TaskWrapper(this);
        schedulerService = new SukettoScheduler();
        menuManager = new MenuHandlerManager();

        getServer().getPluginManager().registerEvents(new MenuHandlerListener(menuManager), this);
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
     * @return
     */
    public MenuHandlerManager getMenuManager() {
        return this.menuManager;
    }
}
