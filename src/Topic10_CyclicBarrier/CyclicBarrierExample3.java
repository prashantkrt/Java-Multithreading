package Topic10_CyclicBarrier;

import java.util.concurrent.*;

public class CyclicBarrierExample3 {
    public static void main(String[] args) throws InterruptedException, BrokenBarrierException, TimeoutException {
        // Create a CyclicBarrier for 3 threads
        CyclicBarrier barrier = new CyclicBarrier(3);

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

        // After the first phase, reset the barrier to reuse
        barrier.reset(); // Reset a barrier to initial state for reuse

        System.out.println("Barrier has been reset, reusing barrier...");

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


        // Wait for all threads in the second phase
        System.out.println("Main thread waiting for workers to reach the barrier in second phase.");
        barrier.await();
        System.out.println("Main thread completed.");
        executor.shutdown();

    }
}
