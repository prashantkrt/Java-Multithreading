package Topic8_ExecutorsFramework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Creates a thread pool with an unbounded number of threads.
//Threads are created as needed but reused when available.
//Ideal for tasks with short execution times.
public class Part13NewCachedThreadPool {
    public static void main(String[] args) {
        try (ExecutorService executor = Executors.newCachedThreadPool()) {

            // Submit 5 tasks to the cached thread pool
            for (int i = 1; i <= 5; i++) {
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
