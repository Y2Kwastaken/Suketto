package sh.miles.suketto.bukkit;

import org.bukkit.plugin.java.JavaPlugin;
import sh.miles.suketto.bukkit.task.SukettoScheduler;
import sh.miles.suketto.bukkit.task.TaskWrapper;

/**
 * A base plugin class that should be extended when wanting to use the Suketto Library
 */
public class SukettoPlugin extends JavaPlugin {

    private TaskWrapper taskWrapper;
    private SukettoScheduler schedulerService;

    @Override
    public void onEnable() {
        taskWrapper = new TaskWrapper(this);
        schedulerService = new SukettoScheduler();
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
}
