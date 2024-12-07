package Topic9_CountDownLatch;

import java.util.concurrent.*;

class DependentService implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName() + " Service Started!!!");
        Thread.sleep(5000);
        return "ok";
    }
}

public class Requirement {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // we could have used the invokeAll() method as well
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        Future<String> future1 = executorService.submit(new DependentService());
        Future<String> future2 = executorService.submit(new DependentService());
        Future<String> future3 = executorService.submit(new DependentService());

        System.out.println(future1.get()); // Blocks the Main Thread
        System.out.println(future2.get());
        System.out.println(future3.get());

        //Since the main thread is blocked during each get() call, the line System.out.println("Main end") executes only after:
        System.out.println("Main end");
        executorService.shutdown();
    }
}
