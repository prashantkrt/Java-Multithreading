package Topic8_ExecutorsFramework;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Part15ScheduleTaskExample {
    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(5);

            scheduler.schedule(() -> {
                //This will execute after 5 seconds
                System.out.println("Task executed within 5 seconds with schedule() method "+Thread.currentThread().getName());
            }, 5, TimeUnit.SECONDS);

        // If a task takes longer than the specified interval, the next task starts immediately after the current one completes, without skipping any executions.
        // The delay between the start of consecutive tasks is fixed.
        // Executes tasks at fixed intervals.
        // Initial delay, After Every Time, Unit as parameter
        // in the below example initial delay is 2 seconds, then every 2 seconds it will be executed
        scheduler.scheduleAtFixedRate(() -> {
            System.out.println("Fixed Rate "+Thread.currentThread().getName());
        }, 2, 2, TimeUnit.SECONDS);


        // Never overlaps
        // If the task duration exceeds the delay, the subsequent executions will be delayed proportionally, but still maintain the delay after task completion.
        //The next task's execution starts only after the previous task has fully completed, plus the specified delay.
        // Executes tasks with a fixed delay between the completion of one task and the start of the next.
        // here we are using delay instead of period
        // Delay between the completion of one task and the start of the next.
        // will wait for delay time before next thread execution
        // ensure a fixed delay between executions, regardless of how long a task takes.
        scheduler.scheduleWithFixedDelay(() -> {
            System.out.println("Fixed Delay "+Thread.currentThread().getName());
        }, 1, 3, TimeUnit.SECONDS);

        scheduler.schedule(() -> {
            System.out.println("Initiating Shutdown!!!");
            scheduler.shutdown();
        }, 20, TimeUnit.SECONDS);

    }
}
// In Short =>
// scheduleAtFixedRate: A train departs at fixed times (e.g., every hour), whether the previous train has reached its destination or not.
// scheduleWithFixedDelay: A shuttle departs after a fixed delay (e.g., 10 minutes) only after the previous shuttle has reached its destination.

// scheduleAtFixedRate can cause overlapping if tasks exceed the period.
// scheduleWithFixedDelay ensures no overlap, even if tasks take longer than the delay.
