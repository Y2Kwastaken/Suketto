package sh.miles.suketto.bukkit.task.ticking;

import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Intended to be used within the {@link MainThreadTicker}. Basic supplier of a value and can easily be extended
 *
 * @param <R> the Type to be returned
 */
public interface ThreadWorker<R> {

    /**
     * The set of code run on the main thread
     *
     * @return the code to be run on the main thread
     * @throws Exception thrown if an error occurs
     */
    @Nullable
    R work() throws Exception;

    /**
     * runs code that executes if an exception occurs in the work and the process can no longer be processed
     *
     * @param throwable the exception that occurred
     * @return the value to return when an exception occurs
     */
    @Nullable
    default R exceptionally(@NotNull final Throwable throwable) {
        Bukkit.getLogger().throwing("ThreadWorker", "exceptionally", throwable);
        return null;
    }
}
