package Topic11_CompletableFuture;

import java.util.concurrent.CompletableFuture;


//Waits for any one of multiple CompletableFuture tasks to finish.
//anyOf() waits for the first task to complete, regardless of which one finishes first. You can then process the result of the first task.
public class CompletableFutureAnyOf {
    public static void main(String[] args) {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> 42);
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> 58);

        CompletableFuture<Object> anyOf = CompletableFuture.anyOf(future1, future2);

        anyOf.thenAccept(result -> System.out.println("First completed task result: " + result));
    }
}
