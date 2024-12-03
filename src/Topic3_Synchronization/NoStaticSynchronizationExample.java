package Topic3_Synchronization;

class SharedResource {
    static int value = 0; // Shared static variable

    // Method to increment the value (not synchronized)
    public static void increment() {
        value++; // This operation is not thread-safe
        System.out.println(Thread.currentThread().getName() + " - Value: " + value);
    }
}

class WorkerThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            SharedResource.increment(); // Threads accessing the shared resource
            try {
                Thread.sleep(50); // Simulate delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class NoStaticSynchronizationExample {
    public static void main(String[] args) {
        WorkerThread thread1 = new WorkerThread();
        thread1.setName("Thread-1");

        WorkerThread thread2 = new WorkerThread();
        thread2.setName("Thread-2");

        WorkerThread thread3 = new WorkerThread();
        thread3.setName("Thread-3");

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException ignored) {
            System.out.println("Exception occurred");
        }

        System.out.println(SharedResource.value);
        System.out.println("Main end");


    }
}
