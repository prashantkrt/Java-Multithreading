package Topic8_ExecutorsFramework;

import java.util.concurrent.*;

// The submit() method submits a task (callable or runnable) for execution and returns a Future.
// The Future allows you to track the progress or result of the task.
public class SubmitMethodExample {
    public static void main(String[] args) {
        try (ExecutorService executor = Executors.newFixedThreadPool(3)) {

            Callable<Integer> task = () -> {
                Thread.sleep(1000); // Simulate a long task
                return 42; // Return some result
            };

            Future<Integer> future = executor.submit(task); // Submit the task and get a Future

            try {
                // future.get(2, TimeUnit.SECONDS) will wait and post-time completion returns Exception if not completed within the time
                // else returns the timeout exception if not completed within the time limit
                // future.get() , once completed, then returns
                // this is a waiting period until a task gets completed
                System.out.println("Task result: " + future.get()); // Get the result (blocks until done)
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

            executor.shutdown();
        }
    }
}
