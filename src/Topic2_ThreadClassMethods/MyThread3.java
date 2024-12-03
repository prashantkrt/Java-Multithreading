package Topic2_ThreadClassMethods;

public class MyThread3 extends Thread {

    public MyThread3(String name) {
        super(name); // for setting the Thread name
    }

    @Override
    public void run() {
        super.run();
    }

    public static void main(String[] args) {
        MyThread3 myThread3 = new MyThread3("MyThread3");

    }
}
