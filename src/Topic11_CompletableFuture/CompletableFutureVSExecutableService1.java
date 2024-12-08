package Topic11_CompletableFuture;

import java.util.concurrent.CompletableFuture;
//CompletableFuture.supplyAsync(() -> {
//    // Simulate some work
//    return 42;
//}).thenApply(result -> {  from here it doesnot block the main method
//    // Process the result without blocking
//    return result + 1;
//}).thenAccept(finalResult -> {
//    // Perform the final action
//    System.out.println(finalResult);
//});

//ExecutorService executor = Executors.newFixedThreadPool(2);
//Future<Integer> firstTask = executor.submit(() -> 42);
//firstTask.get();  // Wait for the first task to finish, blocks the main

// Now, submit the second task, which depends on the first task
//Future<Integer> secondTask = executor.submit(() -> firstTask.get() + 1);
//secondTask.get();  // Wait for the second task to finish,  blocks the main

//example
public class CompletableFutureVSExecutableService1 {
    public static void main(String[] args) {
        // Start an asynchronous task using supplyAsync
        CompletableFuture.supplyAsync(() -> {
                    // Simulate some work in this task (it can be a time-consuming task)
                    System.out.println(Thread.currentThread().getName() + " - Task 1: Starting work");
                    try {
                        Thread.sleep(2000); // Simulating delay (2 seconds)
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    return 42; // Returning the result (42)
                })
                // Once Task 1 finishes, thenApply() is executed asynchronously
                .thenApply(result -> {
                    // This runs asynchronously after Task 1 finishes
                    System.out.println(Thread.currentThread().getName() + " - Task 2: Processing result: " + result);
                    return result + 1; // Returning result + 1 (43)
                })
                // Once Task 2 finishes, thenAccept() is executed asynchronously
                .thenAccept(finalResult -> {
                    // This runs asynchronously after Task 2 finishes
                    System.out.println(Thread.currentThread().getName() + " - Task 3: Final result: " + finalResult);
                });

        // Main thread is free to do other work, it doesn't block waiting for tasks
        System.out.println(Thread.currentThread().getName() + " - Main thread is free and not blocked!");

        // Give enough time for async tasks to finish before the program ends
        try {
            Thread.sleep(3000); // Sleep for 3 seconds so the async tasks can finish
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " - Main thread completed");
    }

}
