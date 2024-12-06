package Topic8_ExecutorsFramework;

import java.util.ArrayList;
import java.util.List;

public class Part1RequirementOfThreadPool{

    private static long calculateFactorial(int n) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        long factorial = 1;
        for (int i = 1; i <= n; i++) {
            factorial *= i;
        }
        return factorial;
    }

    public static void main(String[] args) {
        //WithOut Threading
        long start = System.currentTimeMillis();

        for (int i = 0; i < 10; i++) {
            long factorial = calculateFactorial(i);
            System.out.println(factorial);
        }

        long end = System.currentTimeMillis();
        // around 10-15 secs
        System.out.println("Total Time of Execution without threading " + (end - start) / 1000 + " seconds");


        // With Threading
        long startTime = System.currentTimeMillis();

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            Thread thread = new Thread(() -> {
                long l = calculateFactorial(finalI);
                System.out.println(l);
            });
            thread.start();
            threads.add(thread); // Keep track of the thread
        }

        // Join all threads
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        long endTime = System.currentTimeMillis();
        //around 1 sec to complete
        System.out.println("Total Time of Execution with threading: " + (endTime - startTime) / 1000 + " seconds");
    }
}
