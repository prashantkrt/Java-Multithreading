package Topic2_ThreadClassMethods;

public class MyThread1 implements Runnable {
    @Override
    public void run() {
        // run() method:
        // Contains the code executed by the thread.
        // Directly calling run() does not create a new thread, it executes in the current thread.
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            try {
                Thread.sleep(1000); //Puts the current thread to sleep for the specified time in milliseconds.
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        // main thread calls the MyThread which is executed in run method
        System.out.println(Thread.currentThread().getName()); // Static method Thread.currentThread : Returns a reference to the currently executing thread.
        MyThread1 myThread = new MyThread1();
        Thread thread = new Thread(myThread);
        //start() method : Starts a new thread and invokes the run() method.
        thread.start(); //starts the new thread
        thread.setName("MyThread");
        System.out.println(Thread.currentThread().getName()+"----->"+Thread.activeCount()); //Returns the number of active threads in the current thread's thread group.
        System.out.println(thread.getName()+"-------"+thread.getState());
        System.out.println(Thread.currentThread().getName()+"-------"+Thread.currentThread().getState());
        System.out.println(Thread.currentThread().getName()+"----->"+Thread.activeCount()); //Returns the number of active threads in the current thread's thread group.
        try {
            // join() makes the calling thread wait until the thread on which join() is invoked has finished executing.
            // Main thread will wait for the MyThread to complete its execution
            thread.join(); //Waits for the thread to die or complete execution.
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(thread.getName()+"-------"+thread.getState()+"----->"+thread.threadId()); //Returns the thread's current state (e.g., NEW, RUNNABLE, WAITING, etc.).
        System.out.println(Thread.currentThread().getName()+"-------"+Thread.currentThread().getState()+"----->"+Thread.currentThread().threadId());
        System.out.println(thread.getName()+"-------"+thread.isDaemon());
        System.out.println(Thread.currentThread().getName()+"-------"+Thread.currentThread().isDaemon());
    }
}
