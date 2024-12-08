package Topic11_CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureThenApply {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 42);

        CompletableFuture<Integer> value = future.thenApply(result -> {
            System.out.println(Thread.currentThread().getName() + " - Task 1 result: " + result);
            return result * 2; // Transform result by doubling it
        });

        System.out.println(value.get());

        Thread.sleep(1000); // Wait to let the async task complete
    }
}
