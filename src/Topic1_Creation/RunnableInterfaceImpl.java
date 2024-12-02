package Topic1_Creation;
// Second Approach by implementing the Runnable Functional Interface
// Runnable interface doesnot have start method
// Thread class also has the constructor through which we can pass the Runnable onj
// then we can call the Thread start method from a Thread class object
public class RunnableInterfaceImpl implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }


    public static void main(String[] args) {
        RunnableInterfaceImpl runnableInterface = new RunnableInterfaceImpl();
        Thread thread = new Thread(runnableInterface);
        thread.start();
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