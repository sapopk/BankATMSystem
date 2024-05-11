// Programmer: Johny (sapopk)
// Project: CreditUnion Bank Account APP
// Feature: Find Personal Bank Account class

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class account_FindAcc {

    private int accountId = 0;
    private RandomAccessFile input;
    private account_Info data;

    private Scanner scan = new Scanner(System.in);

    public account_FindAcc() throws IOException {

        data = new account_Info();

        try {
            input = new RandomAccessFile("bankAccount.txt", "r");

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
        findAcc(accountId);
    }

    public void findAcc(int accid) throws IOException {
        boolean accFound = false;

        do {
            try {
                data.read(input);
                if (data.getAccountID() == accid) {

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
                    System.out.println("+".repeat(80)); // print 80 +

                    accFound = true;
                }
            }  catch (EOFException eof) {
                break;
            } catch (IOException io) {
                System.out.println("Error" +io.toString());
                System.exit(1);
            }
        } while (true);

        if (!accFound) {
            System.out.println("\n\tACCOUNT NOT FOUND!");
        }
    }
}