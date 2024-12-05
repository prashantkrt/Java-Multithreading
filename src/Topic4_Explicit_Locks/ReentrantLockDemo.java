package Topic4_Explicit_Locks;

import java.util.concurrent.locks.ReentrantLock;
//Synchronization disadvantage :
// blocking karta hai like infinite blocking
// Interruptiblity nhi hai
// fairness bhi nhi hai
public class ReentrantLockDemo {

    //A Reentrant Lock is a type of lock in Java that allows a thread to acquire the lock multiple times without causing a deadlock.
    private final ReentrantLock lock = new ReentrantLock();

    public void outerMethod() {
        // lock.lockInterruptibly(); interrupted by other thread if wait is very lock or blocked for longer duration
        // lock.lock() iss case me dusra thread jo wait karega woh wait karete rahega
        lock.lock(); // yaha thread ne lock critical section part ko
        try {
            System.out.println(Thread.currentThread().getName()+" Inside outerMethod");
            innerMethod(); // yaha jaega
        } finally {
            lock.unlock();
        }
    }

    // yaha khela ho gaya
    // Since we have Reentrant so deadlock nhi hoga aur dubara lock aquire kar lega
    public void innerMethod() {
        lock.lock(); // wapas se thread lock mag raha hai bina apne aap ko unlock kie toh yaha deadlock ka situation  create ho gaya
        try {
            System.out.println(Thread.currentThread().getName()+" Inside innerMethod");
        } finally {
            lock.unlock(); // yaha main lock nhi khula 2nd lock jo laga hai woh unlock hoga as per the count
        }
    }

    public static void main(String[] args) {

        ReentrantLockDemo demo = new ReentrantLockDemo();
        demo.outerMethod();

        Thread t1 = new Thread(demo::outerMethod,"Thread1");
        Thread t2 = new Thread(demo::outerMethod,"Thread2");
        t1.start();
        t2.start();
    }
}
//main Inside outerMethod
//main Inside innerMethod
//Thread1 Inside outerMethod
//Thread1 Inside innerMethod
//Thread2 Inside outerMethod
//Thread2 Inside innerMethod
