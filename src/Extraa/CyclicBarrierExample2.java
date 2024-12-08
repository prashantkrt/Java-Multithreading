package Extraa;

import java.util.concurrent.*;

public class CyclicBarrierExample2 {
    public static void main(String[] args) {
        int threadCount = 10;
        CyclicBarrier barrier = new CyclicBarrier(threadCount + 1); // Include the main thread
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);

        // Submit tasks
        for (int i = 0; i < threadCount; i++) {
            executorService.submit(new CallableImpl(barrier));
        }

        try {
            System.out.println("Main thread waiting at the barrier...");
            barrier.await(5, TimeUnit.SECONDS); // Main thread waits with a timeout
            System.out.println("All threads have reached the barrier. Main thread proceeds...");
        } catch (InterruptedException | BrokenBarrierException | TimeoutException e) {
            System.err.println("Main thread encountered an issue: " + e.getMessage());
        } finally {
            executorService.shutdown(); // Ensure graceful shutdown
        }
    }
}