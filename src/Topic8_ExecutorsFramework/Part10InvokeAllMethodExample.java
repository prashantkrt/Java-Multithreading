package Topic8_ExecutorsFramework;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
//The invokeAll() method takes a collection of Callable tasks, executes them, and returns a list of Future objects
//representing the results of each task. It waits for all tasks to complete.
public class Part10InvokeAllMethodExample {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        List<Callable<Integer>> tasks = Arrays.asList(
                () -> 1 + 1,
                () -> 2 + 2,
                () -> 3 + 3
        );

        List<Future<Integer>> results = executor.invokeAll(tasks);

        for (Future<Integer> result : results) {
            try {
                System.out.println("Result: " + result.get()); // Blocks until each task is done
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();
    }
}
