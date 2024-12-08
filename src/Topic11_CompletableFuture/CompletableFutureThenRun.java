package Topic11_CompletableFuture;
import java.util.concurrent.CompletableFuture;

//Executes an action after the task completes but doesn't receive any result.
//thenRun() is used when you want to run an action after the completion of the previous task, but no result is passed along.
public class CompletableFutureThenRun {
    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " - Task 1");
        });
        future.thenRun(() -> {
            System.out.println(Thread.currentThread().getName() + " - Task Completed");
        });
        future.thenRun(() -> {
            System.out.println(Thread.currentThread().getName() + " - Task Completed !!!");
        });
        Thread.sleep(1000); // Wait to let the async task complete
    }
}
