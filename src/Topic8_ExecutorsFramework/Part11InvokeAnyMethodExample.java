package Topic8_ExecutorsFramework;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

//The invokeAny() method executes a collection of Callable tasks and returns the result of the first successfully completed task.
// It cancels the remaining tasks once one finishes.
public class Part11InvokeAnyMethodExample {
    public static void main(String[] args) {
        try (ExecutorService executor = Executors.newFixedThreadPool(3)) {

            List<Callable<String>> tasks = Arrays.asList(
                    () -> {
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName());
                        return "Task 1";
                    },
                    () -> {
                        Thread.sleep(500);
                        System.out.println(Thread.currentThread().getName());
                        return "Task 2";
                    },
                    () -> {
                        System.out.println(Thread.currentThread().getName());
                        return "Task 3";
                    }
            );

            try {
                String result = executor.invokeAny(tasks); // Returns the first completed result
                System.out.println("First completed task: " + result);
            } catch (CancellationException | InterruptedException | ExecutionException e) {
                System.out.println(e.getMessage());
            }
            executor.shutdown();
        }
    }
}
