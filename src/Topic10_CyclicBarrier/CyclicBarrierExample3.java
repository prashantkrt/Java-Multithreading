package Topic10_CyclicBarrier;

import java.util.concurrent.*;

public class CyclicBarrierExample3 {
    public static void main(String[] args) throws InterruptedException, BrokenBarrierException, TimeoutException {
        // Create a CyclicBarrier for 3 threads
        CyclicBarrier barrier = new CyclicBarrier(4);

        // First set of threads
        ExecutorService executor = Executors.newFixedThreadPool(3);

        executor.submit(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " reached barrier.");
                barrier.await(); // All threads wait here until count reaches 0
            } catch (InterruptedException | BrokenBarrierException e) {
                System.out.println("Exception: " + e);
            }
        });

        executor.submit(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " reached barrier.");
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                System.out.println("Exception: " + e);
            }
        });

        executor.submit(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " reached barrier.");
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                System.out.println("Exception: " + e);
            }
        });

        // Main thread also waits at the barrier in the first phase
        System.out.println("Main thread waiting for workers to reach the barrier...");
        barrier.await(); // Barrier trips when 4 participants reach it
        System.out.println("Main thread proceeding after first phase...");

        // Reset barrier for reuse
        barrier.reset();
        System.out.println("Barrier has been reset for the second phase...");


        // Second set of threads (same barrier)
        executor.submit(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " reached barrier in second phase.");
                barrier.await(); // Threads wait again after reset
            } catch (InterruptedException | BrokenBarrierException e) {
                System.out.println("Exception: " + e);
            }

        });

        executor.submit(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " reached barrier in second phase.");
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                System.out.println("Exception: " + e);
            }

        });

        executor.submit(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " reached barrier in second phase.");
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                System.out.println("Exception: " + e);
            }
        });


        // Main thread also waits at the barrier in the second phase
        System.out.println("Main thread waiting for workers to reach the barrier in second phase...");
        barrier.await(); // Barrier trips again
        System.out.println("Main thread completed after second phase.");
        executor.shutdown();

        //also we can add optional not needed
        //t returns a boolean value that indicates whether the service terminated successfully or not within the given timeout.
        if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
            executor.shutdownNow();
        }


    }
}
