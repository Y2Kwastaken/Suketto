package sh.miles.suketto.bukkit.task.ticking;

import org.bukkit.Bukkit;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.CompletableFuture;

/**
 * injects tasks into the main thread. The MainThreadTicker will attempt to only run as many tasks as possible on the
 * main thread without heavily impacting main thread performance
 */
public class MainThreadTicker implements Runnable {

    /**
     * MAX MILLIS PER TICK
     */
    public static final double MAX_MILLIS_PER_TICK = 2.5;
    /**
     * MAX NANOS PER TICK
     */
    public static final int MAX_NANOS_PER_TICK = (int) (MAX_MILLIS_PER_TICK * 1E6);

    private final Deque<ThreadWorker<?>> workers = new ArrayDeque<>();

    /**
     * Queues a task on the main thread
     *
     * @param worker the worker to queue
     */
    public void queue(ThreadWorker<?> worker) {
        this.workers.add(worker);
    }

    @Override
    public void run() {
        long stopTime = System.nanoTime() + MAX_NANOS_PER_TICK;

        ThreadWorker<?> nextWorker;

        while (System.nanoTime() <= stopTime && (nextWorker = this.workers.poll()) != null) {
            try {
                if (nextWorker instanceof AsyncThreadWorker<?> asyncWorker) {
                    asyncWorker
                            .completedThenAsync(asyncWorker.work())
                            .exceptionally(asyncWorker::exceptionally);
                } else {
                    nextWorker.work();
                }
            } catch (Exception e) {
                nextWorker.exceptionally(e);
            }
        }
    }
}
