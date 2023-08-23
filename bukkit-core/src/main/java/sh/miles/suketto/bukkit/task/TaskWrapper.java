package sh.miles.suketto.bukkit.task;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

/**
 * A convenience wrapper for all bukkit tasks that does not require a plugin instance on creation. This class also helps
 * by providing a more realistic timing helper too
 */
public class TaskWrapper {

    private final Plugin plugin;

    /**
     * Creates a task wrapper
     * @param plugin the plugin
     */
    public TaskWrapper(final Plugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Runs a sync task
     *
     * @param runnable the runnable
     * @return the bukkit task
     */
    public BukkitTask runSyncTask(@NotNull final Runnable runnable) {
        return Bukkit.getScheduler().runTask(plugin, runnable);
    }

    /**
     * Runs an async task
     *
     * @param runnable the runnable
     * @return the bukkit task
     */
    public BukkitTask runAsyncTask(@NotNull final Runnable runnable) {
        return Bukkit.getScheduler().runTaskAsynchronously(plugin, runnable);
    }

    /**
     * Runs a sync task later
     *
     * @param runnable the runnable
     * @param delay    the delay
     * @param unit     the time unit
     * @return the bukkit task
     */
    public BukkitTask runSyncTaskLater(@NotNull final Runnable runnable, final long delay, @NotNull TimeUnit unit) {
        return Bukkit.getScheduler().runTaskLater(plugin, runnable, toTicks(delay, unit));
    }

    /**
     * Runs a sync task later
     *
     * @param runnable the runnable
     * @param delay    the delay in ticks
     * @return the bukkit task
     */
    public BukkitTask runSyncTaskLater(@NotNull final Runnable runnable, final long delay) {
        return Bukkit.getScheduler().runTaskLater(plugin, runnable, delay);
    }

    /**
     * Runs an asnyc task later
     *
     * @param runnable the runnable
     * @param delay    the delay
     * @param unit     the unit
     * @return the bukkit task
     */
    public BukkitTask runAsyncTaskLater(@NotNull final Runnable runnable, final long delay, @NotNull TimeUnit unit) {
        return Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, runnable, toTicks(delay, unit));
    }

    /**
     * Runs an async task later
     *
     * @param runnable the runnable
     * @param delay    the delay in ticks
     * @return the bukkit task
     */
    public BukkitTask runAsyncTaskLater(@NotNull final Runnable runnable, final long delay) {
        return Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, runnable, delay);
    }

    /**
     * Runs a sync task timer
     *
     * @param runnable the runnable
     * @param delay    the initial delay
     * @param period   how often the task is executed
     * @param unit     the time unit
     * @return the bukkit task
     */
    public BukkitTask runSyncTaskTimer(@NotNull final Runnable runnable, final long delay, final long period, @NotNull final TimeUnit unit) {
        return Bukkit.getScheduler().runTaskTimer(plugin, runnable, toTicks(delay, unit), toTicks(period, unit));
    }

    /**
     * runs a sync task timer
     *
     * @param runnable the runnable
     * @param delay    the delay in ticks
     * @param period   the period in ticks
     * @return the bukkit task
     */
    public BukkitTask runSyncTaskTimer(@NotNull final Runnable runnable, final long delay, final long period) {
        return Bukkit.getScheduler().runTaskTimer(plugin, runnable, delay, period);
    }

    /**
     * Runs a async task timer
     *
     * @param runnable the runnable
     * @param delay    the delay
     * @param period   the period
     * @param unit     the unit
     * @return the bukkit task
     */
    public BukkitTask runAsyncTaskTimer(@NotNull final Runnable runnable, final long delay, final long period, @NotNull final TimeUnit unit) {
        return Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, runnable, toTicks(delay, unit), toTicks(period, unit));
    }

    /**
     * Runs an async task timer
     *
     * @param runnable the runnable
     * @param delay    the delay in ticks
     * @param period   the period in ticks
     * @return the bukkit task
     */
    public BukkitTask runAsyncTaskTimer(@NotNull final Runnable runnable, final long delay, final long period) {
        return Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, runnable, delay, period);
    }

    private long toTicks(final long delay, TimeUnit unit) {
        return unit.toSeconds(delay) * 20L;
    }

}
