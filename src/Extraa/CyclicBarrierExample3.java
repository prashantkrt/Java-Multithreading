package Extraa;
import java.util.concurrent.*;

public class CyclicBarrierExample3{
    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        int threadCount = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);

        CyclicBarrier barrier = new CyclicBarrier(threadCount+1); // Including the main thread

        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " reached the barrier.");
                    barrier.await(); // Worker threads wait at the barrier
                    System.out.println(Thread.currentThread().getName() + " proceeding.");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }

        // Main thread waits at the barrier
        System.out.println("Main thread waiting at the barrier...");
        barrier.await(); // The main thread waits here
        System.out.println("Main thread proceeding...");
        executorService.shutdown();
    }
}