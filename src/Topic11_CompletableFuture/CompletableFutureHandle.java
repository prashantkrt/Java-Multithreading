package Topic11_CompletableFuture;
import java.util.concurrent.CompletableFuture;

//Handles both normal and exceptional outcomes.
//handle() lets you handle both the result and any exception. It provides a way to return a fallback value or process the result further after an exception.
public class CompletableFutureHandle {
    public static void main(String[] args) {

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            if (true) {
                throw new RuntimeException("Task failed!");
            }
            return 42;
        });
        future.handle((result, ex) -> {
            if (ex != null) {
                System.out.println("Exception caught: " + ex.getMessage());
                return 0; // Provide fallback value
            } else {
                return result; // Return normal result
            }
        }).thenAccept(result -> System.out.println("Handled result: " + result));
    }
}
