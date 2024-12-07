package Topic9_CountDownLatch;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class CallableImpl implements Callable<String> {

    private final CountDownLatch latch;

    public CallableImpl(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public String call() {
        try {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " Service Started!!!");
            return "ok";
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            latch.countDown();
        }
    }
}

public class CountDownLatchExample {
    public static void main(String[] args) throws InterruptedException {
        int threadCount = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        //We use if we want to wait multiple Threads
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        executorService.submit(new CallableImpl(countDownLatch));
        executorService.submit(new CallableImpl(countDownLatch));
        executorService.submit(new CallableImpl(countDownLatch));
        executorService.submit(new CallableImpl(countDownLatch));
        executorService.submit(new CallableImpl(countDownLatch));
        executorService.submit(new CallableImpl(countDownLatch));
        executorService.submit(new CallableImpl(countDownLatch));
        executorService.submit(new CallableImpl(countDownLatch));
        executorService.submit(new CallableImpl(countDownLatch));
        executorService.submit(new CallableImpl(countDownLatch));


        System.out.println("Main thread waiting for workers to complete...");
        countDownLatch.await(); // Main thread waits here until count reaches 0
        System.out.println("All workers are done. Main thread resumes.");
    }
}
