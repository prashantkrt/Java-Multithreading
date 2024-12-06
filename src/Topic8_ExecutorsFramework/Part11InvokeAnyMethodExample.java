package Topic8_ExecutorsFramework;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//The invokeAny() method executes a collection of Callable tasks and returns the result of the first successfully completed task.
// It cancels the remaining tasks once one finishes.
public class Part11InvokeAnyMethodExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        List<Callable<String>> tasks = Arrays.asList(
                () -> {
                    Thread.sleep(1000);
                    return "Task 1";
                },
                () -> {
                    Thread.sleep(500);
                    return "Task 2";
                },
                () -> "Task 3"
        );

        try {
            String result = executor.invokeAny(tasks); // Returns the first completed result
            System.out.println("First completed task: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        executor.shutdown();
    }
}
