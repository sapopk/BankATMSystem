// Programmer: Johny (sapopk)
// Project: CreditUnion Bank Account APP
// Feature: Bank Account Encapsulation class

import java.io.IOException;
import java.io.RandomAccessFile;

public class account_Info {
    
    private int accountId = 0;
    private String firstName;
    private String surName;
    private double accountBalance = 0;
    private double accountOverdraft = 0;

    public void read(RandomAccessFile file) throws IOException {

        accountId = file.readInt(); // 4 bits

        char first[] = new char[20]; // 20 bits
        for (int i = 0; i < first.length; i++)
            first[i] = file.readChar();
        firstName = new String(first);

        char last[] = new char[20]; // 20 bits
        for (int i = 0; i < last.length; i++)
            last[i] = file.readChar();
        surName = new String(last);

        accountBalance = file.readDouble(); // 8 bits
        accountOverdraft = file.readDouble(); // 8 bits
    }

    public void write(RandomAccessFile file) throws IOException {

        StringBuffer buffer;

        file.writeInt(accountId); // id to file

        if (firstName != null)
            buffer = new StringBuffer(firstName);
        else
            buffer = new StringBuffer(20);

        buffer.setLength(20); // name to file
        file.writeChars(buffer.toString());

        if (surName != null)
            buffer = new StringBuffer(surName);
        else
            buffer = new StringBuffer(20);

        buffer.setLength(20); // surname to file
        file.writeChars(buffer.toString());

        file.writeDouble(accountBalance); // balance to file
        file.writeDouble(accountOverdraft); // overdraft to file
    }

    // set methods
    public void setAccountID(int id) { accountId = id; }
    public void setFirstName(String name) { firstName = name; }
    public void setSurName(String last) { surName = last; }
    public void setAccountBalance(double balance) { accountBalance = balance; }
    public void setAccountOverdraft(double overdraft) { accountOverdraft = overdraft; }

    // get methods
    public int getAccountID() { return accountId; }
    public String getFirstName() { return firstName; }
    public String getSurName() { return surName; }
    public String getFullName() { return firstName +" " +surName; }
    public double getAccountBalance() { return accountBalance; }
    public double getAccountOverdraft() { return accountOverdraft; }
    public static int size() { return 100; }
}
