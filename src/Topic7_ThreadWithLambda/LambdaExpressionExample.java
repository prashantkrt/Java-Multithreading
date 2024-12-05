package Topic7_ThreadWithLambda;


// longer approach without lambda expression
class MyThread implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Hello World");
    }
}

public class LambdaExpressionExample {
    public static void main(String[] args) {

        // longer approach using RunnableImpl class
        Thread t1 = new Thread(new MyThread());

        // Using anonymous inner class
        Runnable runnableInnerClass = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " Hello World");
            }
        };

        Thread t2 = new Thread(runnableInnerClass);

        //Best way or shorter way
        Thread t3 = new Thread(() -> System.out.println(Thread.currentThread().getName() + " Hello World"));


        t1.start();
        t2.start();
        t3.start();
//        try {
//            t1.join();
//            t2.join();
//            t3.join();
//        } catch (InterruptedException ignored) {
//            System.out.println("Exception Occurred");
//        }
    }
}
