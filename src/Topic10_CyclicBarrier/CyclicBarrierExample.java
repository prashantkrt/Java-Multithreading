package Topic10_CyclicBarrier;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

// When each thread reaches the barrier.await() line, it will block and wait until all other threads in the group also reach this point.
// Once the last thread arrives at the barrier (i.e., the number of threads calling await() equals the number of threads the barrier was initialized with), all threads are released simultaneously. They are allowed to proceed with their execution.
class CallableImpl implements Callable<String> {

    private static final AtomicInteger count = new AtomicInteger(0);
    //  or
    //  private static int count = 0; // Shared counter
    private final CyclicBarrier barrier;

    public CallableImpl(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public String call() {
        try {
            Thread.sleep(1000);
            // int currentCount = incrementCount(); // Increment the count in a thread-safe manner
            System.out.println(Thread.currentThread().getName() + " Service Started!!!" + " barrier await count " + count.incrementAndGet());
            //ensures that all threads involved in the task must wait at the barrier point until every other thread reaches it.
            barrier.await(); // Block until all threads reach the barrier
            return "ok";
        } catch (InterruptedException | BrokenBarrierException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        } finally {
            barrier.reset();
        }
    }

//    Synchronized method to ensure thread-safe increment
//    private synchronized int incrementCount() {
//        return ++count;
//    }
}


//We can reuse the CyclicBarrier
public class CyclicBarrierExample {
    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        int threadCount = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);

        CyclicBarrier barrier = new CyclicBarrier(threadCount);

        executorService.submit(new CallableImpl(barrier)); //1
        executorService.submit(new CallableImpl(barrier)); //2
        executorService.submit(new CallableImpl(barrier)); //3
        executorService.submit(new CallableImpl(barrier)); //4
        executorService.submit(new CallableImpl(barrier)); //5
        executorService.submit(new CallableImpl(barrier)); //6
        executorService.submit(new CallableImpl(barrier)); //7
        executorService.submit(new CallableImpl(barrier)); //8
        executorService.submit(new CallableImpl(barrier)); //9
        executorService.submit(new CallableImpl(barrier)); //10

        // Wait for all threads to complete their tasks
        System.out.println("Main thread waiting for all worker threads to reach the barrier...");
        barrier.await(); // The Main thread will wait until all threads reach the barrier
        System.out.println("Main thread completed...");
        executorService.shutdown();

    }
}