package Topic8_ExecutorsFramework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Creates a single-threaded executor.
// Tasks are executed sequentially in the order they are submitted.
public class Part14NewSingleThreadExecutor {
    public static void main(String[] args) {
        try (ExecutorService executor = Executors.newSingleThreadExecutor()) {

            // Submit 3 tasks to the single-thread executor
            for (int i = 1; i <= 3; i++) {
                final int taskId = i;
                executor.submit(() -> {
                    System.out.println("Task " + taskId + " is running on thread: " + Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000); // Simulate task
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            }

            executor.shutdown();
        }
    }
}
