package Topic4_Explicit_Locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Synchronized gives auto locking
// Lock is an Interface
// we will be using the lock for manual lock
public class BankAccountLock {

    private Integer balance;

    private final Lock lock = new ReentrantLock();

    public void withdraw(Integer amount) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + ": withdrawing " + amount + " from bank");
        if (lock.tryLock(5,TimeUnit.SECONDS)) { // lock mila toh lock karo nhi toh nikal lo
            if (balance >= amount) {
                balance = balance - amount;
                try {
                    System.out.println(Thread.currentThread().getName() + ": Processing the withdrawal request");
                    Thread.sleep(2000);

                    System.out.println(Thread.currentThread().getName() + ": withdrawing " + amount + " from bank");
                    System.out.println(Thread.currentThread().getName() + ": Current balance: " + balance);
                    System.out.println(Thread.currentThread().getName() + ": Withdrawn amount: " + amount);
                    System.out.println(Thread.currentThread().getName() + ": Successfully withdrawn " + amount + " from bank");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); //good practice
                } finally {
                    lock.unlock();
                    System.out.println(Thread.currentThread().getName() + "Unlocked the lock");
                }
            } else {
                System.out.println(Thread.currentThread().getName() + ": Insufficient balance");
            }
        } else {
            System.out.println(Thread.currentThread().getName() + ": Could not acquire lock :)");
        }
    }

    public void deposit(Integer amount){
        System.out.println(Thread.currentThread().getName() + ": deposit " + amount + " from bank");
        if (lock.tryLock()) {
            if (amount > 0) {
                balance = balance + amount;
                try {
                    System.out.println(Thread.currentThread().getName() + " : Processing the deposit request");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    lock.unlock();
                    System.out.println(Thread.currentThread().getName() + "Unlocked the lock");
                }
            } else {
                System.out.println(Thread.currentThread().getName() + ": Could not acquire lock :)");
            }
        } else {
            System.out.println(Thread.currentThread().getName() + ": Waiting in the queue for resource to be uncloaked acquired by another thread :)");
        }
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Integer getBalance() {
        return balance;
    }
}

class MainLock {
    public static void main(String[] args) {
        BankAccountLock bankAccount = new BankAccountLock();
        bankAccount.setBalance(100);

        Thread thread1 = new Thread(() -> {
            try {
                bankAccount.withdraw(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "Thread1");

        Thread thread2 = new Thread(() -> {
            try {
                bankAccount.withdraw(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "Thread2");

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(bankAccount.getBalance());
    }
}
