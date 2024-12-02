package Topic1_Creation;
// First way Extend the Thread class,
// we can directly call start method from The Thread class object
public class ThreadClassImpl extends Thread{


    //Run method has the code which will be working in executed in multiple threads
    @Override
    public void run(){
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }

    public static void main(String[] args) {
        Thread myThread = new ThreadClassImpl();
        myThread.start();
    }
}
/*
 * Life cycle of the Thread
 * New: The thread is created but hasn’t started yet. Like => Thread thread = new Thread();
 * Runnable : After calling start(), the thread enters the runnable state. The thread is eligible to run but may not execute immediately due to the thread scheduler. Like => thread.start();
 * Running : The thread is actively executing its run() method.
 * Blocked: The thread is waiting to acquire a lock or resource that another thread is holding. Example: When a thread tries to enter a synchronized block but the lock is held by another thread.
 * Waiting: The thread is waiting indefinitely until another thread signals it to continue using methods like Object.wait().
 *     synchronized (obj) {
 *        obj.wait(); // Thread waits indefinitely
 *      }
 * Timed_Wait: The thread is waiting for a specified period using methods like Thread.sleep() , Thread.join()
 * Terminated :The thread has finished executing its task and is no longer active. Example: When the run() method completes or an uncaught exception terminates the thread.
 *
 * */