package Topic1_Creation;
// First way Extend the Thread class
// we can directly call start method from The Thread class object
public class ThreadClassImpl extends Thread{

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
