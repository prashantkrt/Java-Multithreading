package Topic8_ExecutorsFramework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
// Future allows you to check if the task is complete, retrieve the result, or cancel the task if needed.
// The Future allows you to track the progress or result of the task.
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
        // this generally initials the shutdown
        // wait for all the threads to complete the execution
        executorService.shutdown(); // to manually shut down else, it will keep on going
       // executorService.shutdownNow(); // immediately shuts
        try {
            executorService.awaitTermination(5, TimeUnit.SECONDS); // main threads will wait for executor
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Main Thread Ended!!");
    }
}
