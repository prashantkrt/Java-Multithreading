package Topic3_Synchronization;

class Counter {

    private int count;

    public Counter() {
        count = 0;
    }

    // Object level
    // Only one thread can access the synchronized method on that particular object at a time.
    // The synchronized block in the increment() method ensures that only one thread at a time can modify the count variable.
    // Synchronized method to ensure thread-safety
    // Only one thread at a time
    // Other thread will be blocked or wait till the thread will complete the task
    synchronized public void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}

class MyThread extends Thread {

    private Counter counter;

    public MyThread(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            counter.increment();
        }
    }
}

public class SynchronizedExample {
    public static void main(String[] args) {
        //single instance of the counter
        Counter counter = new Counter();
        //multiple threads using a single object or instance
        MyThread myThread1 = new MyThread(counter);
        MyThread myThread2 = new MyThread(counter);
        myThread1.start();
        myThread2.start();

        try {
            myThread1.join();
            myThread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(counter.getCount());
    }
}
