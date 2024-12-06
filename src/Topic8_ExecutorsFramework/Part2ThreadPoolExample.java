package Topic8_ExecutorsFramework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Part2ThreadPoolExample {
    private static long factorial(int n) {
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
    public static void main(String[] args) {

        // Create a thread pool with 5 threads
        // Reuses the thread once free
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 10; i++) {
            final int val = i;
            executorService.submit(() -> {
                long result = factorial(val);
                System.out.println(result);
            });
        }
        executorService.shutdown(); // to manually shut down else, it will keep on going
        try {
            executorService.awaitTermination(5, TimeUnit.SECONDS); // main threads will wait for executor
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Main Thread Ended!!");
    }
}
