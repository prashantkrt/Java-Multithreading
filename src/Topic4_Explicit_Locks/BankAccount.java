package Topic4_Explicit_Locks;

//Without lock
public class BankAccount {

    private Integer balance;

    //auto locking and will take over the entire method for each thread one by one
    public synchronized void withdraw(Integer amount) {
        System.out.println(Thread.currentThread().getName() + " : withdrawing " + amount + " from bank");
        if (amount <= balance) {
            balance = balance - amount;
            try {
                System.out.println(Thread.currentThread().getName() + " : Processing the withdrawal request");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " : withdrawing " + amount + " from bank");
            System.out.println(Thread.currentThread().getName() +" : Current balance: " + balance);
            System.out.println(Thread.currentThread().getName() +" : Withdrawn amount: " + amount);
            System.out.println(Thread.currentThread().getName() +" : Successfully withdrawn " + amount + " from bank");
        } else {
            System.out.println(Thread.currentThread().getName() +" : Insufficient balance");
        }
    }

    public void deposit(Integer amount) {
        System.out.println(Thread.currentThread().getName() +" : deposit " + amount + " from bank");
        if (amount > 0) {
            balance = balance + amount;
            try {
                System.out.println(Thread.currentThread().getName() +" : Processing the deposit request");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println(Thread.currentThread().getName() +" : Insufficient amount to deposit");
        }
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Integer getBalance() {
        return balance;
    }
}

class Main {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setBalance(200);

        Thread thread1 = new Thread(() -> {
            bankAccount.withdraw(20);
        }, "Thread1");

        Thread thread2 = new Thread(() -> {
            bankAccount.withdraw(20);
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
