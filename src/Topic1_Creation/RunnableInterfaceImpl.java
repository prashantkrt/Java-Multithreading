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
