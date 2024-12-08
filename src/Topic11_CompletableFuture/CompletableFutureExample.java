package Topic11_CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

// CompletableFuture is an extension of the Future that provides a more powerful way to handle asynchronous computation.
// It not only allows you to run tasks asynchronously but also enables non-blocking operations and task chaining.

// Non-blocking: CompletableFuture provides methods like .thenApply(), .thenAccept(), .thenCompose(), etc., to chain tasks together in a non-blocking manner.

// A higher-level abstraction that extends Future with built-in mechanisms for chaining, handling exceptions, and combining multiple tasks without explicitly managing threads.
// It's non-blocking and provides cleaner, more maintainable asynchronous code.
public class CompletableFutureExample{
    public static void main(String[] args) {
        // Start an asynchronous task
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("Calculating...");
            try {
                Thread.sleep(2000); //some work
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
                Thread.currentThread().interrupt();
            }
            return 42;
        });

        try {
            Integer ans = future.get();
            System.out.println(ans);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
