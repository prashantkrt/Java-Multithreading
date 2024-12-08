package Extraa;
import java.util.concurrent.*;

public class CyclicBarrierExample1 {
    public static void main(String[] args) throws InterruptedException {
        int threadCount = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);

        CyclicBarrier barrier = new CyclicBarrier(threadCount, () -> {
            System.out.println("All worker threads have reached the barrier!");
        });

        // Submit 10 tasks
        for (int i = 0; i < threadCount; i++) {
            executorService.submit(new CallableImpl(barrier));
        }

        // Wait for all threads to finish
        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.SECONDS);
        System.out.println("Main thread completed after all tasks finished.");
    }
}

//pool-1-thread-5 Service Started!!! barrier await count: 6
//pool-1-thread-3 Service Started!!! barrier await count: 9
//pool-1-thread-6 Service Started!!! barrier await count: 10
//pool-1-thread-8 Service Started!!! barrier await count: 8
//pool-1-thread-4 Service Started!!! barrier await count: 9
//pool-1-thread-7 Service Started!!! barrier await count: 7
//pool-1-thread-2 Service Started!!! barrier await count: 10
//pool-1-thread-9 Service Started!!! barrier await count: 9
//pool-1-thread-10 Service Started!!! barrier await count: 10
//pool-1-thread-1 Service Started!!! barrier await count: 6
//All worker threads have reached the barrier!
//Main thread completed after all tasks finished.
