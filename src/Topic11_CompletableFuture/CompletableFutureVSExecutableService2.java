package Topic11_CompletableFuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CompletableFutureVSExecutableService2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Create an ExecutorService with a fixed thread pool
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        // Submit tasks to the executor service
        Future<Integer> future1 = executorService.submit(() -> {
            System.out.println(Thread.currentThread().getName() + " - Task 1: Starting work");
            Thread.sleep(2000); // Simulate work
            return 42; // Return result
        });

        Future<Integer> future2 = executorService.submit(() -> {
            System.out.println(Thread.currentThread().getName() + " - Task 2: Starting work");
            Thread.sleep(1000); // Simulate work
            return 100; // Return result
        });

        // Wait for tasks to complete and get their results
        Integer result1 = future1.get(); // This blocks until task 1 is done
        Integer result2 = future2.get(); // This blocks until task 2 is done

        System.out.println("Task 1 completed with result: " + result1);
        System.out.println("Task 2 completed with result: " + result2);

        // Shutdown the executor service
        executorService.shutdown();

    }
}
