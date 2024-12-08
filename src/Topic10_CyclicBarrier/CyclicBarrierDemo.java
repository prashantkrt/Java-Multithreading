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

    // one all the thread or friends reached at a barrier or reaching a point, a barrier will be tripped
    @Override
    public void run() {
        System.out.println(friendName + "  Friend is getting ready!!!");
        getReadyAtMeetingPoint(); // some work while to be ready to reach at meeting point
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

        CyclicBarrier barrier = new CyclicBarrier(3);
        Thread thread1 = new Thread(new Friend(barrier, "friend1"));
        Thread thread2 = new Thread(new Friend(barrier, "friend2"));
        Thread thread3 = new Thread(new Friend(barrier, "friend3"));

        thread1.start();
        thread2.start();
        thread3.start();

    }
}

// output
//friend2  Friend is getting ready!!!
//friend3  Friend is getting ready!!!
//friend1  Friend is getting ready!!!
//friend2 is waiting for => 2 at the meeting point!!!
//friend1 is waiting for => 1 at the meeting point!!!
//friend3 is waiting for => 0 at the meeting point!!!
//friend3 Wait is over and now Ready to start the trip!!!
//friend2 Wait is over and now Ready to start the trip!!!
//friend1 Wait is over and now Ready to start the trip!!!
