package Topic8_ExecutorsFramework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ShutdownMethodExample {
    public static void main(String[] args) {
        try (ExecutorService executor = Executors.newFixedThreadPool(2)) {

            Runnable task = () -> {
                System.out.println(Thread.currentThread().getName() + " is running");
            };

            executor.execute(task); // Submit a task
            executor.execute(task); // Submit another task

            executor.shutdown(); // Initiate shutdown

            if (executor.isShutdown()) {
                System.out.println("Executor has been shut down.");
            }
        }
    }
}