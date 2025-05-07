/*
===== ATM Menu =====
1. Check Balance
2. Withdraw
3. Deposit
4. Exit
Choose an option (1-4): 1
Current balance: $5000.0

===== ATM Menu =====
1. Check Balance
2. Withdraw
3. Deposit
4. Exit
Choose an option (1-4): 2
Enter amount to withdraw: $2500
Withdrawal successful.
New balance: $2500.0

===== ATM Menu =====
1. Check Balance
2. Withdraw
3. Deposit
4. Exit
Choose an option (1-4): 2
Enter amount to withdraw: $3000
Withdrawal failed.
Make sure the amount is valid and within your balance.

===== ATM Menu =====
1. Check Balance
2. Withdraw
3. Deposit
4. Exit
Choose an option (1-4): 3
Enter amount to deposit: $5000
Deposit successful.
New balance: $7500.0

===== ATM Menu =====
1. Check Balance
2. Withdraw
3. Deposit
4. Exit
Choose an option (1-4): 6
Invalid selection. Please try again.

===== ATM Menu =====
1. Check Balance
2. Withdraw
3. Deposit
4. Exit
Choose an option (1-4): 4
Thanks for banking with us! See you next time.
*/
import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public boolean withdraw(double amt) {
        if (amt > 0 && amt <= balance) {
            balance -= amt;
            return true;
        }
        return false;
    }

    public boolean deposit(double amt) {
        if (amt > 0) {
            balance += amt;
            return true;
        }
        return false;
    }
}

class ATM {
    private BankAccount myAccount;

    public ATM(BankAccount acc) {
        this.myAccount = acc;
    }

    public void checkBalance() {
        System.out.println("Current balance: $" + myAccount.getBalance());
    }

    public void withdrawMoney(double amount) {
        System.out.println(myAccount.withdraw(amount) ? "Withdrawal successful. \nNew balance: $" + myAccount.getBalance() : "Withdrawal failed. \nMake sure the amount is valid and within your balance.");
    }

    public void depositMoney(double amount) {
        System.out.println(myAccount.deposit(amount) ? "Deposit successful. \nNew balance: $" + myAccount.getBalance() : "Deposit failed. Enter a valid amount.");
    }

    public void runATM() {
        Scanner sc = new Scanner(System.in);
        int option;

        System.out.println("Welcome to the ATM Machine!");

        do {
            System.out.println("\n===== ATM Menu =====");
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Exit");
            System.out.print("Choose an option (1-4): ");

            option = sc.nextInt();

            switch (option) {
                case 1:
                    checkBalance();
                    break;

                case 2:
                    System.out.print("Enter amount to withdraw: $");
                    double withdrawAmt = sc.nextDouble();
                    withdrawMoney(withdrawAmt);
                    break;

                case 3:
                    System.out.print("Enter amount to deposit: $");
                    double depositAmt = sc.nextDouble();
                    depositMoney(depositAmt);
                    break;

                case 4:
                    System.out.println("Thanks for banking with us! See you next time.");
                    break;

                default:
                    System.out.println("Invalid selection. Please try again.");
                    break;
            }

        } while (option != 4);

        sc.close();
    }
}

public class ATMInterface {
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(5000.0);
        ATM atmMachine = new ATM(userAccount);
        atmMachine.runATM();
    }
}
