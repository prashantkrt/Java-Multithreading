package Topic1_Creation;

public class MainThread {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName()); // main
        System.out.println(Thread.currentThread().getId()); // 1
        System.out.println(Thread.currentThread().getState()); // New , Runnable , Running etc ..
    }
}
