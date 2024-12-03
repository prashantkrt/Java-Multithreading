package Topic2_ThreadClassMethods;

public class MyThread4 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i+" is running");
            //Hints to the scheduler that the current thread is willing to pause and allow other threads of the same priority to execute.
            //Hints the scheduler to give change to another thread as well!!!
            Thread.yield();// durse thread ko chance de do
            //will get the result in more random
            //hints the scheduler just a hint not strictly followed
        }
    }

    public static void main(String[] args) {
        MyThread4 myThread4 = new MyThread4();
        Thread t1 = new Thread(myThread4);
        Thread t2 = new Thread(myThread4);
        t1.start();
        t2.start();
    }
}
