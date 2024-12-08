package Topic10_CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadLocalRandom;

class Friend implements Runnable {
    private final CyclicBarrier barrier;
    private final String friendName;

    public Friend(CyclicBarrier barrier, String friendName) {
        this.barrier = barrier;
        this.friendName = friendName;
    }

    @Override
    public void run() {
        System.out.println(friendName + "  Friend is getting ready!!!");
        getReadyAtMeetingPoint();
        System.out.println(friendName + " is waiting for => " + (barrier.getParties() - barrier.getNumberWaiting() - 1)
                + " at the meeting point!!!");
        try {
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(friendName + " Wait is over and now Ready to start the trip!!!");


    }

    private void getReadyAtMeetingPoint() {
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5000));
        }catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}

public class CyclicBarrierDemo {
    public static void main(String[] args) {

    }
}
