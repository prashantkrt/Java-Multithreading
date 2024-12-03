package Topic2_ThreadClassMethods;

public class MyThread2 implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(100); // Adding a slight delay to make scheduling more predictable
        } catch (InterruptedException ignored) {
        }
        // This code will run in the custom thread
        // getThreadGroup() used to retrieve the thread group to which the current thread belongs.
        System.out.println("Running in MyThread: " + Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getName()+" "+Thread.currentThread().getState()+" "+Thread.currentThread().getThreadGroup()+" "+Thread.currentThread().getPriority());
    }

    public static void main(String[] args) {
        MyThread2 myThread2 = new MyThread2();
        Thread thread1 = new Thread(myThread2);
        thread1.setName("MyThreadFirst");
        thread1.setDaemon(true);
        thread1.setPriority(Thread.MAX_PRIORITY); //10

        Thread thread2 = new Thread(myThread2);
        thread2.setName("MyThreadSecond");
        thread2.setDaemon(false);
        thread2.setPriority(Thread.MIN_PRIORITY); // 1

        Thread thread3 = new Thread(myThread2);
        thread3.setName("MyThreadThird");
        thread3.setDaemon(false);
        thread3.setPriority(Thread.NORM_PRIORITY); // default 5

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException ignored) {
        }
    }
}
//Thread.MIN_PRIORITY = 1 (the lowest priority)
//Thread.NORM_PRIORITY = 5 (default priority)
//Thread.MAX_PRIORITY = 10 (the highest priority)

// Note :=>
// In Java, setting a thread’s priority (via setPriority()) is a hint to the thread scheduler,
// but the scheduler’s behavior depends on the underlying operating system.
// It does not guarantee that a higher-priority thread will execute before a lower-priority one.
