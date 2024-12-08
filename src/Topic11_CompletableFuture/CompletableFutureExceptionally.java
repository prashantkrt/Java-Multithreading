package Topic11_CompletableFuture;
import java.util.concurrent.CompletableFuture;

//Handles exceptions that occur during task execution.
public class CompletableFutureExceptionally {
    public static void main(String[] args) {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            if (true) {
                throw new RuntimeException("Something went wrong!");
            }
            return 42;
        });

        future.exceptionally(ex -> {
            System.out.println("Caught exception: " + ex.getMessage());
            return 0; // Provide default value in case of error
        }).thenAccept(result -> System.out.println("Result: " + result));

    }
}
