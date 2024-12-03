package Topic2_ThreadClassMethods;
// Demon thread
// low priority threads
//A daemon thread in Java is a thread that runs in the background and is designed to perform background tasks.

// **** The key characteristic of a daemon thread is that it does not prevent the JVM from exiting.
// In other words, if all non-daemon threads have finished executing, the JVM will terminate, even if daemon threads are still running.
public class MyThread5 implements Runnable {
    @Override
    public void run() {
      while (true) {
          //infinite
      }
    }

    public static void main(String[] args) {
        MyThread5 myThread5 = new MyThread5();
        Thread t1 = new Thread(myThread5);
        t1.setDaemon(true);
        t1.start();
        // if all the non-demon thread complete JVM terminate, even the Demon is running in the background
    }
}
