package Topic4_Explicit_Locks;

import java.util.concurrent.locks.ReentrantLock;

class ReentrantLockExample {

    private final ReentrantLock lock = new ReentrantLock();
    private int sharedResource = 0;

    // using lock() with a ReentrantLock, other threads attempting to acquire the same lock
    // Other thread will be blocked and wait until the thread holding the lock calls unlock() to release it.
    public void increment() {
        lock.lock(); // Acquire the lock
        try {
            sharedResource++;
            System.out.println(Thread.currentThread().getName() + " incremented to " + sharedResource);
        } finally {
            lock.unlock(); // Always release the lock in a finally block
        }
    }

}

public class LockExample {
    public static void main(String[] args) {
        ReentrantLockExample example = new ReentrantLockExample();
        Runnable task = example::increment;
        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");
        t1.start();
        t2.start();
    }
}
