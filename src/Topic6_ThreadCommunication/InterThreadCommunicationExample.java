package Topic6_ThreadCommunication;
class CommonSharedResource {

    private int data;
    private boolean isDataAvailable = false; // first product

    public synchronized void produce(int value) {
        while (isDataAvailable) { // ruko data khatam hone do
            try {
                wait(); // Wait if data is already available
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted");
            }
        }
        data = value;
        isDataAvailable = true;
        System.out.println("Produced: " + data);
        notify(); // Notify consumer thread
    }

    public synchronized void consume() {
        while (!isDataAvailable) {
            try {
                wait(); // Wait if no data is available
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted");
            }
        }
        System.out.println("Consumed: " + data);
        isDataAvailable = false;
        notify(); // Notify producer thread
    }
}
public class InterThreadCommunicationExample {
    public static void main(String[] args) {
        CommonSharedResource commonSharedResource = new CommonSharedResource();

        Thread producer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                commonSharedResource.produce(i);
                try {
                    Thread.sleep(500); // Simulate time taken to produce
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Producer interrupted");
                }
            }
        });

        Thread consumer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                commonSharedResource.consume();
                try {
                    Thread.sleep(500); // Simulate time taken to consume
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Consumer interrupted");
                }
            }
        });

        producer.start();
        consumer.start();
    }
}

//Produced: 1
//Consumed: 1
//Produced: 2
//Consumed: 2
//Produced: 3
//Consumed: 3
//Produced: 4
//Consumed: 4
//Produced: 5
//Consumed: 5
