package Topic9_CountDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchLambdaExample {
    public static void main(String[] args) throws InterruptedException {
        int threadCount = 5;
        CountDownLatch latch = new CountDownLatch(threadCount);
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);

        for (int i = 1; i <= threadCount; i++) {
            int taskId = i; // To use inside lambda
            executorService.submit(() -> {
                try {
                    System.out.println("Task " + taskId + " started.");
                    Thread.sleep(1000); // Simulating work
                    System.out.println("Task " + taskId + " completed.");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.err.println("Task " + taskId + " interrupted.");
                } finally {
                    latch.countDown(); // Decrement the latch count
                }
            });
        }

        System.out.println("Main thread waiting for workers to complete...");
        latch.await(); // Main thread waits here until count reaches 0
        System.out.println("All tasks are completed. Main thread resumes.");

        executorService.shutdown();
    }
}