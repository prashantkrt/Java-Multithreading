package Topic8_ExecutorsFramework;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Part4SubmitRunnableExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Runnable task = () -> {
            System.out.println("Task is running in: " + Thread.currentThread().getName());
            // Simulate some work
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        // Submit the Runnable task
        Future<?> future = executor.submit(task);

        try {
            // Block and wait for the task to complete
            Object o = future.get();
            System.out.println(o); //null
            System.out.println("Task completed successfully.");
        } catch (InterruptedException e) {
            System.err.println("Task was interrupted.");
        } catch (ExecutionException e) {
            System.err.println("Exception occurred during task execution: " + e.getCause());
        }

        System.out.println(future.isDone());
        System.out.println(future.isCancelled());
        executor.shutdown();
    }
}