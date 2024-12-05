package Topic5_Deadlock;

class Pen {

    //only one thread can execute either method at a time on the same Pen instance
    public synchronized void writeWithPenAndPaper(Paper paper) {
        System.out.println(Thread.currentThread().getName() + " is using pen =>" + this + " and trying to write with paper =>" + paper);
        paper.finishedWriting();
    }

    public synchronized void finishedWriting() {
        System.out.println(Thread.currentThread().getName() + "Finished writing using pen => " + this);
    }

}

class Paper {

    //only one thread can execute either method at a time on the same Paper instance
    //The thread can't hold the lock for both methods simultaneously on the same object because the lock is only associated with the instance of the Paper object.
    public synchronized void writeWithPaperAndPaper(Pen pen) {
        System.out.println(Thread.currentThread().getName() + " is using paper =>" + this + " and trying to write with pen =>" + pen);
        pen.finishedWriting();
    }

    public synchronized void finishedWriting() {
        System.out.println(Thread.currentThread().getName() + "Finished writing using Paper => " + this);
    }

}

class MyTask1 implements Runnable {

    private Paper paper;
    private Pen pen;

    public MyTask1(Paper paper, Pen pen) {
        this.paper = paper;
        this.pen = pen;
    }

    @Override
    public void run() {
        paper.writeWithPaperAndPaper(pen);
    }
}

class MyTask2 implements Runnable {

    private Paper paper;
    private Pen pen;

    public MyTask2(Paper paper, Pen pen) {
        this.paper = paper;
        this.pen = pen;
    }

    @Override
    public void run() {
        pen.writeWithPenAndPaper(paper);
    }
}

public class DeadlockExample {
    public static void main(String[] args) {
        Pen pen = new Pen();
        Paper paper = new Paper();

        Thread t1 = new Thread(new MyTask1(paper,pen),"Thread1");
        Thread t2 = new Thread(new MyTask2(paper,pen),"Thread2");
        t1.start();
        t2.start();

        // we can use lambda exp better way
        Thread t3 = new Thread(() -> paper.writeWithPaperAndPaper(pen), "Thread3");
        Thread t4 = new Thread(() -> pen.writeWithPenAndPaper(paper), "Thread4");

    }
}
//Thread2 is using pen =>Topic5_Deadlock.Pen@8e5ed20 and trying to write with paper =>Topic5_Deadlock.Paper@6221394
//Thread1 is using paper =>Topic5_Deadlock.Paper@6221394 and trying to write with pen =>Topic5_Deadlock.Pen@8e5ed20
