// Programmer: Johny (sapopk)
// Project: CreditUnion Bank Account APP
// Feature: Overdraft class to change value

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class account_NewOverdraft {

    private int accountId = 0;
    private double accountOverdraft = 0;

    private RandomAccessFile input, output;
    private account_Info data;

    private Scanner scan = new Scanner(System.in);

    public account_NewOverdraft() throws IOException {
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

        newOverdraft(accountId);
    }

    public void newOverdraft(int accid) {
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

                    double overdraft = data.getAccountOverdraft();

                    // value to identify only numbers and accept min value of 200
                    System.out.print("\n\tSetup new overdraft limit (max: 500): ");
                    String odfs = scan.next();

                    while(!odfs.matches("(\\d+(\\.\\d{1,2})?)")) {
                        System.out.println("\n\tPlease enter only numbers.");
                        System.out.print("\n\tSetup new overdraft limit (max: 500): ");
                        odfs = scan.next();
                    }

                    double overdraftNew = Double.parseDouble(odfs);

                    if (overdraftNew >= 0 && overdraftNew <= 500) {
                        accountOverdraft = overdraftNew;

                        data.setAccountOverdraft(accountOverdraft);

                        output.seek((long) (accountId - 1) * account_Info.size());
                        data.write(output);
                        System.out.println("\nNew overdraft value successfully added. New Overdraft: " + overdraftNew);
                        System.out.println("+".repeat(80)); // print 80 +

                        accFound = true;
                        break;

                    }
                }

            }  catch (EOFException eof) {
                break;
            } catch (IOException io) {
                System.out.println("Error");
                System.exit(1);
            }
        } while (true);

        if (!accFound) {
            System.out.println("\n\tACCOUNT NOT FOUND!");
        }
    }
}