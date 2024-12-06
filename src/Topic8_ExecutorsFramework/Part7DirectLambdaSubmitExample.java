package Topic8_ExecutorsFramework;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Part7DirectLambdaSubmitExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Direct lambda for Runnable
        Future<?> runnableFuture = executor.submit(() -> {
            System.out.println("Runnable Task running in: " + Thread.currentThread().getName());
        });

        // Since Runnable doesn't return a result, Future.get() will return null
        System.out.println("Runnable result: " + runnableFuture.get()); // Output: null

        // Direct lambda for Callable
        Future<String> callableFuture = executor.submit(() -> {
            System.out.println("Callable Task running in: " + Thread.currentThread().getName());
            return "Result from Callable Task";
        });

        // Callable returns a value, which can be fetched using Future.get()
        System.out.println("Callable result: " + callableFuture.get()); // Output: Result from Callable Task

        // Shutdown the executor
        executor.shutdown();
    }
}