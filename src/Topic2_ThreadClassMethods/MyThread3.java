package Topic2_ThreadClassMethods;

public class MyThread3 extends Thread {

    public MyThread3(String name) {
        super(name); // for setting the Thread name
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName()+"....... is running");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        MyThread3 myThread3 = new MyThread3("MyThread3");
        myThread3.start();
        // Interrupts a thread, signaling it to stop its execution or terminate early (if it is sleeping or waiting).
        // main method interrupt kar degas once the MyThread ask it to do so
        myThread3.interrupt(); // singles the main or current thread => jo bhi kaam kar rahe ho usko wahi rok do
    }
}
