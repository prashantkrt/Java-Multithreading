package Topic11_CompletableFuture;
import java.util.concurrent.CompletableFuture;

//Executes an action on the result of the previous stage, but doesn't transform the result.
//thenAccept() executes a side effect on the result (like logging or updating a database) without returning anything.
public class CompletableFutureThenAccept {
    public static void main(String[] args) throws InterruptedException {

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 42);

        future.thenAccept(result -> {
            System.out.println(Thread.currentThread().getName() + " - Final result: " + result); //main - Final result: 42
        });

        Thread.sleep(1000); // Wait to let the async task complete

    }
}
