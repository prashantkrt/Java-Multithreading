package Topic3_Synchronization;

class BankAccount {
    private int balance = 0;

    public void deposit() {
        //I don't want to block this section
        System.out.println("Inside the deposit Section");
        System.out.println("Please wait a bit to deposit your money");
        // only the balance update part I wanted to block for multiple thread access
        // Synchronizing on the BankAccount object to ensure thread safety
        synchronized (this) {
            balance++;
            System.out.println(Thread.currentThread().getName() + " deposited 1 unit. Current balance: " + balance);
        }
    }

    public int getBalance() {
        return balance;
    }
}

class BankEmployee extends Thread {

    private BankAccount account;

    public BankEmployee(BankAccount account) {
        this.account = account;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            account.deposit();
        }
    }
}

public class SynchronizedBlock {

    public static void main(String[] args) {
        BankAccount sharedAccount = new BankAccount();

        // Creating multiple threads representing bank employees
        Thread employee1 = new BankEmployee(sharedAccount);
        Thread employee2 = new BankEmployee(sharedAccount);
        Thread employee3 = new BankEmployee(sharedAccount);

        employee1.setName("Employee-1");
        employee2.setName("Employee-2");
        employee3.setName("Employee-3");

        // Starting threads
        employee1.start();
        employee2.start();
        employee3.start();


        try {
            employee1.join();
            employee2.join();
            employee3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(sharedAccount.getBalance());
    }
}
