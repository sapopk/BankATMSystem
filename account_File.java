// Programmer: Johny (sapopk)
// Project: CreditUnion Bank Account APP
// Feature: Create File for Bank Account database

import java.io.IOException;
import java.io.RandomAccessFile;

public class account_File {
    
    private account_Info data;
    private RandomAccessFile file;

    public account_File() {

        data = new account_Info();

        try{
            file = new RandomAccessFile("bankAccount.txt", "rw");
            
            for(int i=0; i < 1000; i++) {
                data.write(file);
            }
        } catch (IOException io) {
            System.err.println("File not opened");
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        new account_File();
    } 
}
