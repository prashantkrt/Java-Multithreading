package Topic3_Synchronization;
//This example shows why we need the static synchronization
class CommonResource {
    static int count = 0; // Shared static variable

    // We need to apply here static synchronization else data of the static value won't be correct at the end of all the execution
    // Synchronized instance method (not sufficient for static variable)
    public synchronized void increment() {
        count++; // This operation is not thread-safe with instance synchronization
        System.out.println(Thread.currentThread().getName() + " - Count: " + count);
    }
}

class MyThreadImpl extends Thread {
    CommonResource resource;

    // Constructor to initialize SharedResource instance
    public MyThreadImpl(CommonResource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            resource.increment(); // Each thread uses its own instance
            try {
                Thread.sleep(1000); // Simulate some delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class IncorrectSynchronizationExample {
    public static void main(String[] args) {
        CommonResource resource1 = new CommonResource();
        CommonResource resource2 = new CommonResource();
        CommonResource resource3 = new CommonResource();

        // Each thread gets a different instance of SharedResource
        MyThreadImpl thread1 = new MyThreadImpl(resource1);
        thread1.setName("Thread-1");

        MyThreadImpl thread2 = new MyThreadImpl(resource2);
        thread2.setName("Thread-2");

        MyThreadImpl thread3 = new MyThreadImpl(resource3);
        thread3.setName("Thread-3");

        thread1.start();
        thread2.start();
        thread3.start();

        try{
            thread1.join();
            thread2.join();
            thread3.join();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        //Since each thread operates on a different instance of SharedResource,
        // the synchronization does not protect the static variable count.
        System.out.println(CommonResource.count);
    }
}
