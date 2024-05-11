// Programmer: Johny (sapopk)
// Project: CreditUnion Bank Account APP
// Feature: Close Bank Account class

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class account_Close {

    private int accountId = 0;

    private RandomAccessFile input, output;
    private account_Info data;

    private Scanner scan = new Scanner(System.in);

    public account_Close() throws IOException {
        data = new account_Info();

        try {
            input = new RandomAccessFile("bankAccount.txt", "rw");
            output = new RandomAccessFile("bankAccount.txt", "rw");
        } catch (IOException io) {
            System.err.println("Error");
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
        deleteAccount(accountId);
    }

    //delete acc method

    public void deleteAccount(int accid) throws IOException {
        boolean accFound = false;

        do {
            try {
                data.read(input);
                if (data.getAccountID() == accid) {

                    //display account content

                    int id = data.getAccountID();
                    String fullname = data.getFullName();
                    double balance = data.getAccountBalance();
                    double overdraft = data.getAccountOverdraft();

                    System.out.println();
                    System.out.println("+-".repeat(35));
                    System.out.printf("| %12s |  %12s  | %14s | %14s |%n", "Account ID", "Holder Name",
                            "Account Balance", "Overdraft Limit");
                    System.out.println("+-".repeat(35));
                    System.out.printf("| %12d |   %s  | %15.2f | %15.2f |%n", id, fullname, balance, overdraft);
                    System.out.println("-".repeat(70) + "\n");
                    System.out.println("+".repeat(85)); // print 80 +

                    //verify balance != 0
                    //else delete account from database

                    if (data.getAccountBalance() != 0) {
                        System.out.println(
                        "\nPlease, withdraw all balance or/& pay any debt from your accounts before closing it\n");
                        System.out.println("+".repeat(85)); // print 80 +
                    } else {

                        data.setAccountID(0);
                        data.setFirstName(null);
                        data.setSurName(null);
                        data.setAccountBalance(0);
                        data.setAccountOverdraft(0);

                        output.seek((long) (accountId - 1) * account_Info.size());
                        data.write(output);

                        System.out.println("\n\t\t Your accound has been closed!\n");
                        System.out.println("+".repeat(85)); // print 80 +
                    }

                    accFound = true;
                    break;
                }
            } catch (IOException io) {
                System.out.println("Error");
                System.exit(1);
            }
        } while (true);

        //display if account is not found
        if (!accFound) {
            System.out.println("\n\tACCOUNT NOT FOUND!");
        }
    }
}
