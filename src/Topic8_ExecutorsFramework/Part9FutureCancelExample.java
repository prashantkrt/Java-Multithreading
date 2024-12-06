package Topic8_ExecutorsFramework;

import java.util.concurrent.*;

public class Part9FutureCancelExample {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        // Submit a task to the executor
        Future<Integer> future = executor.submit(() -> {
            System.out.println("Task started...");
            Thread.sleep(5000); // Simulate a long-running task
            System.out.println("Task completed.");
            return 42;
        });

        // Attempt to cancel the task after 2 seconds
        Thread.sleep(2000);
        boolean cancelResult = future.cancel(true);  // Try to cancel the task
        if (cancelResult) {
            System.out.println("Task was canceled.");
        } else {
            System.out.println("Task could not be canceled.");
        }

        // Check if the task was canceled
        if (future.isCancelled()) {
            System.out.println("Task was canceled before completion.");
        } else {
            System.out.println("Task was not canceled.");
        }

        // Try to get the result of the task (this will throw an exception if canceled)
        try {
            Integer result = future.get();
            System.out.println("Task result: " + result);
        } catch (CancellationException | ExecutionException e) {
            System.out.println("The task was canceled and cannot return a result.");
        }

        // Shutdown the executor
        executor.shutdown();
    }
}
