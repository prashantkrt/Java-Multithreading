package Topic9_CountDownLatch;

import java.util.concurrent.CountDownLatch;

class Worker implements Runnable {
    private final CountDownLatch latch;
    private final String name;

    public Worker(CountDownLatch latch, String name) {
        this.latch = latch;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            System.out.println(name + " is working...");
            Thread.sleep(2000); // Simulate work
            System.out.println(name + " finished.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            latch.countDown(); // Decrement latch count
        }
    }
}

public class CountDownLatchManualThreadExample {
    public static void main(String[] args) throws InterruptedException {
        int numWorkers = 3;
        CountDownLatch latch = new CountDownLatch(numWorkers);

        // Worker threads
        for (int i = 1; i <= numWorkers; i++) {
            new Thread(new Worker(latch, "Worker-" + i)).start();
        }

        System.out.println("Main thread waiting for workers to complete...");
        latch.await(); // Main thread waits here until count reaches 0
        System.out.println("All workers are done. Main thread resumes.");
    }
}
