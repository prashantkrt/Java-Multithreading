package Topic11_CompletableFuture;

import java.util.concurrent.CompletableFuture;
public class CompletableFutureThenCombine {
    public static void main(String[] args) {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> 42);
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> 58);

        future1.thenCombine(future2, (result1, result2) -> {
            return result1 + result2; // Combine both results
        }).thenAccept(sum -> System.out.println("Sum of results: " + sum));


    }
}
