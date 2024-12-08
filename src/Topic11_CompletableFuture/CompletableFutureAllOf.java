package Topic11_CompletableFuture;
import java.util.concurrent.CompletableFuture;

//Waits for multiple CompletableFuture tasks to finish.
//allOf() waits for multiple CompletableFuture tasks to complete and then runs a callback action once all of them have finished.
public class CompletableFutureAllOf {
    public static void main(String[] args) {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> 42);
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> 58);

        CompletableFuture<Void> allOf = CompletableFuture.allOf(future1, future2);

        allOf.thenRun(() -> {
            try {
                System.out.println("All tasks completed!");
                System.out.println("Future 1 result: " + future1.get());
                System.out.println("Future 2 result: " + future2.get());
            } catch (Exception e) {
              Thread.currentThread().interrupt();
            }
        });

//      s
    }
}
