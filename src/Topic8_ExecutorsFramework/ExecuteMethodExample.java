package Topic8_ExecutorsFramework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
// The execute() method is used to execute a Runnable task but does not return a Future.
// This means you cannot track the task or retrieve its result.
public class ExecuteMethodExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Runnable task = () -> {
            System.out.println(Thread.currentThread().getName() + " is executing a task");
        };

        executor.execute(task); // Submit the task
        executor.execute(task); // Submit another task

        executor.shutdown();
    }
}