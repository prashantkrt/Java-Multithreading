package Topic5_Deadlock;

class BallPen {


    public synchronized void writeWithPenAndPaper(WhitePaper paper) {
        System.out.println(Thread.currentThread().getName() + " is using pen =>" + this + " and trying to write with paper =>" + paper);
        paper.finishedWriting();
    }

    public synchronized void finishedWriting() {
        System.out.println(Thread.currentThread().getName() + "Finished writing using pen => " + this);
    }

}

class WhitePaper {

    public synchronized void writeWithPaperAndPaper(BallPen pen) {
        System.out.println(Thread.currentThread().getName() + " is using paper =>" + this + " and trying to write with pen =>" + pen);
        pen.finishedWriting();
    }

    public synchronized void finishedWriting() {
        System.out.println(Thread.currentThread().getName() + "Finished writing using Paper => " + this);
    }
}

class Task1 implements Runnable {

    private BallPen pen;
    private WhitePaper paper;

    public Task1(WhitePaper paper, BallPen pen) {
        this.paper = paper;
        this.pen = pen;
    }

    @Override
    public void run() {
        paper.writeWithPaperAndPaper(pen);
    }
}

class Task2 implements Runnable {

    private WhitePaper paper;
    private BallPen pen;

    public Task2(WhitePaper paper, BallPen pen) {
        this.paper = paper;
        this.pen = pen;
    }

    @Override
    public void run() {
        synchronized (paper) { // paper lock tab use karo jab woh available ho nhi toh nhi
            pen.writeWithPenAndPaper(paper);
        }
    }
}

public class DeadlockResolve {
    public static void main(String[] args) {
        BallPen pen = new BallPen();
        WhitePaper paper = new WhitePaper();

        Thread t1 = new Thread(new Task1(paper,pen),"Thread1");
        Thread t2 = new Thread(new Task2(paper,pen),"Thread2");
        t1.start();
        t2.start();
    }
}
//Thread1 is using paper =>Topic5_Deadlock.WhitePaper@20cc2c59 and trying to write with pen =>Topic5_Deadlock.BallPen@2e0bc630
//Thread1Finished writing using pen => Topic5_Deadlock.BallPen@2e0bc630
//Thread2 is using pen =>Topic5_Deadlock.BallPen@2e0bc630 and trying to write with paper =>Topic5_Deadlock.WhitePaper@20cc2c59
//Thread2Finished writing using Paper => Topic5_Deadlock.WhitePaper@20cc2c59
