// Programmer: Johny (sapopk)
// Project: CreditUnion Bank Account APP
// Feature: Open new account class

import java.io.RandomAccessFile;
import java.io.IOException;
import java.util.Scanner;

public class account_Open {
    private int accountId = 0;
    private String firstName;
    private String surName;
    private double accountBalance = 0;
    private double accountOverdraft = 0;

    private RandomAccessFile output;
    private account_Info data;
    private Scanner scan = new Scanner(System.in);

    public account_Open() {

        data = new account_Info();

        try {
            output = new RandomAccessFile("bankAccount.txt", "rw");
        } catch (IOException io) {
            System.err.println("Error to open file!");
            System.exit(1);
        }

        write(); // call write method
    }

    public void closeFile() {
        try {
            output.close();
        } catch (IOException io) {
            System.err.println("Error to close file");
        }
    }

    public void write() {

        try {

            System.out.println("\n\n    Please, enter only what is requested (numbers/letter) without spaces");
            System.out.println("+".repeat(80)); // print 67 dashes

            System.out.println("\n\n\t\tType 'Cancel' to stop the processing!");
            System.out.print("\nEnter your personal ID number: ");
            String id = scan.next();

            while (!id.matches("[0-9]+") || id.startsWith("0") || Integer.parseInt(id) <= 0) {

                System.out.println("\n\tPlease enter only numbers (1-9) & without the initial Zeros.");
                System.out.print("\nEnter your personal ID number: ");
                id = scan.next();
            }

            accountId = Integer.parseInt(id);

            if (accountId > 1 && accountId <= 1000) {
                output.seek((long) (accountId - 1) * account_Info.size());
                data.read(output);

                if (data.getAccountID() == accountId) {
                    System.out.println("\n\t\tAccount " + accountId + " already exist in the bank!");
                    System.out.println("+".repeat(80)); // print 80 dashes

                } else {

                    input();

                    data.setAccountID(accountId);
                    data.setFirstName(firstName);
                    data.setSurName(surName);
                    data.setAccountBalance(accountBalance);
                    data.setAccountOverdraft(accountOverdraft);

                    output.seek((long) (accountId - 1) * account_Info.size());
                    data.write(output);
                    System.out.println("\t\tYour account has been created");
                    System.out.println("+".repeat(80) + "\n\n"); // print 80
                }

            } else {
                System.out.println("\n\n\t\t ID number above 1000 not permitted!");
                System.out.println("+".repeat(80) + "\n\n"); // print 80 
            }

        } catch (IOException io) {
            System.err.println("Error");
        }

        closeFile(); // call closeFile method
    }

    public void input() {

        // value to identify only letters
        System.out.print("\nEnter your first name: ");
        firstName = scan.next();
        while (!firstName.matches("[a-zA-Z]+")) {
            System.out.println("\n\tENTER LETTERS ONLY");
            System.out.print("\nEnter your first name: ");
            firstName = scan.next();
        }

        // value to identify only letters
        System.out.print("Enter your surname: ");
        surName = scan.next();
        while (!surName.matches("[a-zA-Z]+")) {
            System.out.println("\n\tENTER LETTERS ONLY");
            System.out.print("\nEnter your surname: ");
            surName = scan.next();
        }

        // value to identify only numbers and accept min value of 1k
        System.out.print("Enter initial balance (min: 1000): ");
        while (true) {
            String balance = scan.next();
            if (balance.matches("(\\d+(\\.\\d{1,2})?)")) {
                double balanceValue = Double.parseDouble(balance);
                if (balanceValue >= 1000) {
                    accountBalance = balanceValue;
                    break;
                } else {
                    System.out.println("\nPlease enter a value equal to or above 1000.");
                }
            } else {
                System.out.println("\n\tPlease enter only numbers.");
            }
            System.out.print("\nEnter initial balance (min: 1000): ");
        }

        // value to identify only numbers and accept min value of 200
        System.out.print("Setup overdraft limit (max: 500): ");
        while (true) {
            String overdraft = scan.next();
            if (overdraft.matches("(\\d+(\\.\\d{1,2})?)")) {
                double overdraftValue = Double.parseDouble(overdraft);
                if (overdraftValue >= 0 && overdraftValue <= 500) {
                    accountOverdraft = overdraftValue;
                    break;
                } else {
                    System.out.println("\nPlease enter a value equal to or between 0 to 500.");
                }
            } else {
                System.out.println("\n\tPlease enter positive numbers between 0 to 500.");
            }
            System.out.print("\nSetup overdraft limit (max: 500): ");
        }
        System.out.println(); // blank line
    }
}
