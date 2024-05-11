// Programmer: Johny (sapopk)
// Project: CreditUnion Bank Account APP
// Feature: Money lodgment class

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class account_Lodgement {

    private int accountId = 0;
    private double balanceDeposit = 0;

    private account_Info data;
    private RandomAccessFile input, output;

    private Scanner scan = new Scanner(System.in);

    public account_Lodgement() throws IOException {
        data = new account_Info();

        try {
            input = new RandomAccessFile("bankAccount.txt", "rw");
            output = new RandomAccessFile("bankAccount.txt", "rw");
        } catch (IOException io) {
            System.err.println("File not opened");
            System.exit(1);
        }

        // value to identify only numbers
        System.out.print("\nEnter your personal ID number: ");
        String id = scan.next();

        while (!id.matches("[0-9]+") || id.startsWith("0") || Integer.parseInt(id) <= 0) {
            System.out.println("\n\tPlease enter only numbers (1-9) & without the initial Zeros.");
            System.out.print("\nEnter your personal ID number: ");
            id = scan.next();
        }

        accountId = Integer.parseInt(id);
        deposit(accountId);
    }

    public void deposit(int accid) throws IOException {
        boolean accFound = false;

        do {
            try {
                data.read(input);

                if (data.getAccountID() == accid) {

                    int id = data.getAccountID();
                    String fullname = data.getFullName();
                    double bll = data.getAccountBalance();
                    double odft = data.getAccountOverdraft();

                    System.out.printf("%nAccount ID: %d %n", id);
                    System.out.printf("Holder Name: %s %n", fullname);
                    System.out.printf("Account Balance: %.2f %n", bll);
                    System.out.printf("Overdraft Limit: %.2f %n", odft);

                    double balance = data.getAccountBalance();

                    System.out.println("\n\n\t\tType 'Cancel' to stop the processing!");
                    System.out.print("\n\n\tEnter deposit amount: ");
                    String amount = scan.next();

                    while (!amount.matches("(\\d+(\\.\\d{1,2})?)") || amount.startsWith("0") || Double.parseDouble(amount) <= 0) {
                        System.out.println("\n\tPlease enter only positive numbers.");
                        System.out.print("\n\tEnter deposit amount: ");
                        amount = scan.next();
                    }

                    balanceDeposit = Double.parseDouble(amount);

                    if (balanceDeposit > 0) {

                        balance += balanceDeposit;

                        data.setAccountBalance(balance);

                        output.seek((long) (accountId - 1) * account_Info.size());
                        data.write(output);

                        System.out.println("\n\tWithdrawal successful. New balance: " + balance);
                        System.out.println("+".repeat(80)); // print 80 +

                        accFound = true;
                    }
                }

            } catch (EOFException eof) {
                break;
            } catch (IOException io) {
                System.err.println("Error");
                System.exit(1);
            }
        } while (true);

        if (!accFound) {
            System.out.println("\n\tACCOUNT NOT FOUND!");
        }
    }
}
