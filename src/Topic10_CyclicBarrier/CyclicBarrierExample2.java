package Topic10_CyclicBarrier;

import java.util.concurrent.*;

class CallableImpl2 implements Callable<String> {

    private static int count = 0;

    private final CyclicBarrier barrier;

    public CallableImpl2(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public String call() {
        try {
            Thread.sleep(1000);
            int currentCount = incrementCount(); // Increment the count in a thread-safe manner
            System.out.println(Thread.currentThread().getName() + " Service Started!!!" + " barrier await count " + currentCount);
            barrier.await();
            return "ok";
        } catch (InterruptedException | BrokenBarrierException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    private synchronized int incrementCount() {
        return ++count;
    }
}


//We can reuse the CyclicBarrier
public class CyclicBarrierExample2 {
    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        int threadCount = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);

        CyclicBarrier barrier = new CyclicBarrier(threadCount+1); // one for main

        executorService.submit(new CallableImpl2(barrier)); //1
        executorService.submit(new CallableImpl2(barrier)); //2
        executorService.submit(new CallableImpl2(barrier)); //3
        executorService.submit(new CallableImpl2(barrier)); //4
        executorService.submit(new CallableImpl2(barrier)); //5
        executorService.submit(new CallableImpl2(barrier)); //6
        executorService.submit(new CallableImpl2(barrier)); //7
        executorService.submit(new CallableImpl2(barrier)); //8
        executorService.submit(new CallableImpl2(barrier)); //9
        executorService.submit(new CallableImpl2(barrier)); //10

        // Wait for all threads to complete their tasks
        System.out.println("Main thread waiting for all worker threads to reach the barrier...");
        barrier.await(); // The Main thread will wait until all threads reach the barrier
        System.out.println("Main thread completed...");
        executorService.shutdown();

    }
}
