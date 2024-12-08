package Extraa;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;

class CallableImpl implements Callable<String> {

    private static int count = 0; // Shared counter
    private final CyclicBarrier barrier;

    public CallableImpl(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public String call() {
        try {
            Thread.sleep(1000); // Simulate some work
            synchronized (CallableImpl.class) {
                count++;
            }
            System.out.println(Thread.currentThread().getName() + " Service Started!!!" +
                    " barrier await count: " + count);
            barrier.await(); // Block until all threads reach the barrier
            return "ok";
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }
}
