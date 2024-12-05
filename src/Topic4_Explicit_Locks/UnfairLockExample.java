package Topic4_Explicit_Locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UnfairLockExample {

    //private final Lock unfairLock = new ReentrantLock();

    //starvation protection as the thread waiting for longer  period will be given chance
    //ensures fairness in thread acquisition only at the lock level, meaning it tries to grant the lock to the longest-waiting thread
    //However, this fairness policy doesn't guarantee strict ordering in thread execution due to the nature of thread scheduling and OS-level factors.
    private final Lock fairLock = new ReentrantLock(true); // jo pehle aae usko lock do

    public void accessResource() {
        //unfairLock.lock();
        fairLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " is acquiring lock");
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        } finally {
            //unfairLock.unlock();
            fairLock.unlock();
            System.out.println(Thread.currentThread().getName() + " is releasing lock");
        }
    }

    public static void main(String[] args) {
        UnfairLockExample example = new UnfairLockExample();

        //Arbitrary fashion of execution with unfair locking
        Thread t1 = new Thread(example::accessResource, "Thread-1");
        Thread t2 = new Thread(example::accessResource, "Thread-2");
        Thread t3 = new Thread(example::accessResource, "Thread-3");

        t1.start();
        t2.start();
        t3.start();
    }
}
// output 1 unfairLock
//Thread-1 is acquiring lock    observation=> Even though Thread-1 - arrived first but unable to acquire it
//Thread-2 is acquiring lock
//Thread-2 is releasing lock
//Thread-1 is releasing lock
//Thread-3 is acquiring lock
//Thread-3 is releasing lock

//output 2 unfairLock
//Thread-1 is acquiring lock  observation=> Even though Thread-1 - arrived first but unable to acquire it
//Thread-2 is acquiring lock
//Thread-2 is releasing lock
//Thread-3 is acquiring lock
//Thread-3 is releasing lock
//Thread-1 is releasing lock

//output 3 unfairLock
//Thread-1 is acquiring lock  observation => arbitrary fashion
//Thread-2 is acquiring lock
//Thread-1 is releasing lock
//Thread-2 is releasing lock
//Thread-3 is acquiring lock
//Thread-3 is releasing lock
