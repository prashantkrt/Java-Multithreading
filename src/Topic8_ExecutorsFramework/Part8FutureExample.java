package Topic8_ExecutorsFramework;


import java.util.concurrent.*;

//get(): Retrieves the result of the computation (blocks if the task is not complete).
//isDone(): Checks if the task is complete.
//cancel(): Attempts to cancel the task.
//isCancelled(): Checks if the task was canceled.

public class Part8FutureExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        // Submit a Callable task
        Future<Integer> future = executor.submit(() -> {
            System.out.println("Task started...");
            Thread.sleep(3000); // Simulate a long-running task
            System.out.println("Task completed.");
            return 42; // Result of the task
        });

        try {
            // Check if the task is done
            if (!future.isDone()) {
                System.out.println("Task is still in progress...");
            }
            if (future.isCancelled()) {
                System.out.println("Task is cancelled...");
            }

            // Get the result of the task (blocks until the task is complete)
            Integer result = future.get();
            System.out.println("Task result: " + result);

            Integer resultTimedWaited = future.get(4, TimeUnit.SECONDS);
            System.out.println("Task result: " + resultTimedWaited);

        } catch (InterruptedException e) {
            System.err.println("Task was interrupted.");
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            System.err.println("An exception occurred: " + e.getCause());
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }

        // Cancel the task before shutting down the executor
        boolean canceled = future.cancel(true); // Interrupts the task if it's running
        if (canceled) {
            System.out.println("Task was canceled.");
        }

        // Shutdown the executor
        executor.shutdown();

    }
}
