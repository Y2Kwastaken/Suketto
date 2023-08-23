package sh.miles.suketto.bukkit.task.ticking;

import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

/**
 * Thread worker that can work asynchronously after initially working on the Main Thread
 *
 * @param <R> the return type
 */
public interface AsyncThreadWorker<R> extends ThreadWorker<R> {

    /**
     * Computes a value asynchronously after it has been retrieved
     *
     * @param value the value to compute asynchronously. This value can be safely cast to R
     * @return the future object
     */
    CompletableFuture<Object> completedThenAsync(@Nullable final Object value);
}
