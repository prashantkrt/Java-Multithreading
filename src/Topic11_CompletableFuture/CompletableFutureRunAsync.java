package Topic11_CompletableFuture;

import java.util.concurrent.CompletableFuture;
public class CompletableFutureRunAsync {
    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " - Task started");
        });

        // No need to wait for a result since runAsync returns Void
        future.thenRun(() -> System.out.println("Task completed"));
        Thread.sleep(1000); // Wait to let the async task complete
    }
}
