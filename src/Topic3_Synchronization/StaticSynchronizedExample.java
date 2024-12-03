package Topic3_Synchronization;

class Counter2 {

    private static int count = 0;

    // Synchronized static method to ensure thread-safety at the class level
    public synchronized static void increment() {
        count++;
    }

    public static int getCount() {
        return count;
    }
}

class MyThread2 extends Thread {

    private Counter2 counter;

    public MyThread2(Counter2 counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            Counter2.increment();
        }
    }
}

public class StaticSynchronizedExample {
    public static void main(String[] args) {

        Counter2 counter1 = new Counter2(); // instance1
        MyThread2 myThread1 = new MyThread2(counter1);
        myThread1.start();

        Counter2 counter2 = new Counter2(); //instance2
        MyThread2 myThread2 = new MyThread2(counter2);
        myThread2.start();

        try {
            myThread1.join();
            myThread2.join();
        } catch (InterruptedException ignored) {
        }

        System.out.println(Counter2.getCount()); // final value should be correct
    }
}
