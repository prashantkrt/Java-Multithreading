package Extraa;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CompletableFutureExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
                return Math.random();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, executorService).orTimeout(2, TimeUnit.SECONDS).exceptionally(
                (ex) -> {
                    System.out.println("TimedOutException Occurred");
                    throw new RuntimeException("TimedOutException Occurred");
                }
        ).thenAccept((result)-> {
            System.out.println(result);
            executorService.shutdown();
        });

    }
}
