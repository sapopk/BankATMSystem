// Programmer: Johny (sapopk)
// Project: CreditUnion Bank Account APP
// Feature: Main Credit Union frame to manipulate all other classes

import java.io.IOException;
import java.util.Scanner;

public class account_Frame {

    public static void main(String[] args) throws IOException {

        Scanner scan = new Scanner(System.in);

        //instance of object classes

        account_Open open;
        account_Close close;
        account_FindAcc findAcc;
        account_Lodgement lodge;
        account_Withdrawal withdraw;
        account_NewOverdraft overdraft;

        // menu options

        while (true) {
            System.out.println("\n\n\tWelcome to Credit Bank!");
            System.out.println(
                    "\t    Chosse an Option"
                            + "\n\n 1 - Open a new account."
                            + "\n 2 - View my account status."
                            + "\n 3 - Set new overdraft value."
                            + "\n 4 - Proceed to make a Lodgement."
                            + "\n 5 - Proceed to Withdrawal."
                            + "\n 6 - Close Account."
                            + "\n 7 - Quit.");

            System.out.print("\n  Enter an option: ");
            String opt = scan.next();
            while (!opt.matches("[1-7]")) {
                System.out.println("\n\n\tLetters or Values out of range are not permitted!");
                System.out.print("\n\n  Enter an option: ");
                opt = scan.next();
            }

            int option = Integer.parseInt(opt);
            scan.nextLine(); // clean scan

            switch (option) {
                case 1:
                    open = new account_Open();
                    break;
                case 2:
                    findAcc = new account_FindAcc();
                    break;
                case 3:
                    overdraft = new account_NewOverdraft();
                    break;
                case 4:
                    lodge = new account_Lodgement();
                    break;
                case 5:
                    withdraw = new account_Withdrawal();
                    break;
                case 6:
                    close = new account_Close();
                    break;
                case 7:
                    return; //quit system.
            }
        }
    }
}
