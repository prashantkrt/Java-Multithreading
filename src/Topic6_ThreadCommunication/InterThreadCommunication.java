package Topic6_ThreadCommunication;

class SharedResource {

    private Integer data = 0;
    private boolean hasData = false;

    public synchronized void produce() {
        while (hasData) { // Use `while` to prevent spurious wake-ups
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        data++;
        hasData = true;
        System.out.println("Produced data: " + data);
        notifyAll();
    }

    public synchronized int consume() {
        while (!hasData) {//Use `while` to prevent spurious wake-ups
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        data--;
        hasData = false;
        System.out.println("Consumed data: " + data);
        notifyAll();
        return data;
    }
}

class Consumer implements Runnable {

    private final SharedResource sharedResource;

    public Consumer(SharedResource sharedResource) {
        this.sharedResource = sharedResource;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            sharedResource.consume();
        }
    }
}

class Producer implements Runnable {

    private final SharedResource sharedResource;

    public Producer(SharedResource sharedResource) {
        this.sharedResource = sharedResource;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            sharedResource.produce();
        }
    }
}

public class InterThreadCommunication {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();

        Thread thread1 = new Thread(new Producer(sharedResource), "Producer");
        Thread thread2 = new Thread(new Consumer(sharedResource), "Consumer");

        thread1.start();
        thread2.start();


//        //Thread Creation in different Ways
//        Thread thread3 = new Thread(() -> sharedResource.produce(), "Produce2");
//        Thread thread4 = new Thread(() -> sharedResource.consume(), "Consume2");
//
//        Thread thread5 = new Thread(sharedResource::produce, "Produce3");
//        Thread thread6 = new Thread(sharedResource::consume, "Consume3");

    }
}
