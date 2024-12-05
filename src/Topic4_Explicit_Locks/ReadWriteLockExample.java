package Topic4_Explicit_Locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class ReadWriteCounter {

    private int counter = 0;

    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();

    private final Lock readLock = rwLock.readLock(); // allows multiple thread to read

    private final Lock writeLock = rwLock.writeLock(); // only allow single thread at a time

    // Write Lock: Only one thread can acquire the write lock at a time,
    // and no other threads can hold either the read or write lock when a write lock is acquired.
    public void increment() {
        System.out.println(Thread.currentThread().getName() + " trying to acquire lock of increment method");
        writeLock.lock();
        System.out.println(Thread.currentThread().getName() + " Lock acquired of increment method");
        try {
            System.out.println(Thread.currentThread().getName() + ": " + "Incrementing counter");
            counter++;
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        } finally {
            writeLock.unlock();
            System.out.println(Thread.currentThread().getName() + ": unlocking increment method¯");
        }
    }

    // if the thread has acquired some write lock then in that case read lock cannot be acquired by other threads
    // This is useful when you have multiple threads that only need to read shared data and don’t modify it.
    // In a ReadWriteLock, multiple threads can acquire the read lock simultaneously, as long as no thread holds the write lock.
    // Multiple threads can enter and read at a time
    public int getCounter() {
        System.out.println(Thread.currentThread().getName() + " trying to acquire lock of getCounter Method");
        readLock.lock();
        System.out.println(Thread.currentThread().getName() + " Lock acquired of getCounter Method");
        try {
            System.out.println(Thread.currentThread().getName() + ": " + "Reading the Counter => : " + counter);
            return counter;
        } catch (Exception e) {
            Thread.currentThread().interrupt();
            throw e;
        } finally {
            System.out.println(Thread.currentThread().getName() + ":  unlocking getCounter method");
            readLock.unlock();
        }
    }
}

public class ReadWriteLockExample {
    public static void main(String[] args) {
        ReadWriteCounter counter = new ReadWriteCounter();

        //Writer Threads
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                counter.increment();
            }
        }, "Thread-1");
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                counter.increment();
            }
        }, "Thread-2");

        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                counter.increment();
            }
        }, "Thread-3");


        //Reader Threads
        Thread t4 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                counter.getCounter();
            }
        },"Thread-4");

        Thread t5 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                counter.getCounter();
            }
        }, "Thread-5");

        Thread t6 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                counter.getCounter();
            }
        }, "Thread-6");

        //Writer Threads started
        t1.start();
        t2.start();
        t3.start();

        //Reader Thread Started
        t4.start();
        t5.start();
        t6.start();


    }
}
