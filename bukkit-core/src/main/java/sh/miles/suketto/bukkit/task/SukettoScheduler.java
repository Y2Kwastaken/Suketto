package sh.miles.suketto.bukkit.task;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * A executor service
 */
public class SukettoScheduler {

    private final ScheduledExecutorService service;

    public SukettoScheduler() {
        service = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
    }

    /**
     * Uses the given Service thread to supply another thread to this asnyc task
     *
     * @param supplier the supplier
     * @param <T>      the type task
     * @return the Completable Future
     */
    public <T> CompletableFuture<T> supplyAsync(@NotNull final Supplier<T> supplier) {
        return CompletableFuture.supplyAsync(supplier, service);
    }

    /**
     * Uses the given service thread to run a async task
     *
     * @param runnable the runnable
     * @return the resulting Completable Future
     */
    public CompletableFuture<Void> runAsync(@NotNull final Runnable runnable) {
        return CompletableFuture.runAsync(runnable, service);
    }

    /**
     * Schedules the given runnable at a fixed delay
     *
     * @param runnable the runnable
     * @param delay    the delay
     * @param period   the period
     * @param unit     the unit
     */
    public ScheduledFuture<?> scheduleAtFixedRate(@NotNull final Runnable runnable, final long delay, final long period, @NotNull final TimeUnit unit) {
        return service.scheduleAtFixedRate(runnable, delay, period, unit);
    }

    public List<Runnable> shutdownAll() {
        return service.shutdownNow();
    }

}
