/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg164788.cipher;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Cipher {

    public static Substitution sub;
    public static Transposition tran;
    public static Transposition tran2;
    public static int KeyOne = 5;
    public static String KeyTwo = "HELLO";
    public static String inputText = "15zh ogWhfh24 nm igj  nu tr xlb?3Ixrbu. xr";  
    public static Scanner scanner;
   // public static String ciphertext;

    public static void main(String[] args) throws IOException {
        Menus();
       
    }

    public static void Encryption() throws IOException {
        //ENCRYPT
        System.out.println("**************ENCRYPTION************");

        //SUBSTITUTION
        
        sub = new Substitution(KeyTwo);
        sub.MsgAndKey(inputText);
        inputText = sub.cipherEncrypt();
        System.out.println("Cipher text after substitution : " + inputText);

        
        //TRANSPOSITION
        tran = new Transposition(KeyOne);
        inputText = tran.Encrypt(inputText);
        System.out.println(" ");
        System.out.println("Cipher text after transposition : " + inputText);
        System.out.println("ENCRYPTED PLAINTEXT : ");
        System.out.println(inputText);
        WriteFile(true);
        
    }

    public static void Decryption() throws IOException {

        //DECRYPT
        System.out.println("**************DECRYPTION************");
        
        
        //TRANSPOSITION  
        tran = new Transposition(KeyOne);
        System.out.println("Reverse transposition");
        inputText = tran.Decrypt(inputText);
        System.out.println();
        System.out.println("Plaintext = " + inputText);

        
        //SUBSTITUTION
        sub = new Substitution(KeyTwo);
        sub.MsgAndKey(inputText);
        inputText = sub.Decrypt();
        System.out.println("Reverse substitution : " + inputText);
        

        System.out.println("DECRYPTED CIPHER TEXT : ");
        inputText = inputText.replace((char)127 , '-');
        System.out.println(inputText);
        WriteFile(false);
    }

    public static void ListFiles() throws IOException {
        
        File aDirectory = new File(System.getProperty("user.dir"));

        // get a listing of all files in the directory
        String[] filesInDir = aDirectory.list();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < filesInDir.length; i++) {
            if (!filesInDir[i].endsWith(".txt")) {
           
                //list.remove(i);
            }else{
                
                list.add(filesInDir[i]);
            }
        }

        System.out.println("CHOOSE TEXT FILE BELOW USING NUMBER ");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + " : " + list.get(i));
        }
        Integer choice = scanner.nextInt();
        if (choice < 1) {
            choice = 1;
        }
        File textfile = new File(list.get(choice - 1));
        BufferedReader br = new BufferedReader(new FileReader(textfile));

        String st;
        inputText = "";
        while ((st = br.readLine()) != null) {
            inputText += st;
        }
        System.out.println("NORMAL TEXT");
        System.out.println(inputText);
        inputText = inputText.replace('-', (char)127);
    }

    public static void WriteFile(Boolean w) throws IOException {
        
        String name = "";
        if(w){
            name = "Encrypted Text.txt";
        }else{
            name = "Decrypted Text.txt";
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(name));
        writer.write(inputText);
        writer.close();
        scanner.next();
    }

    public static void Menus() throws IOException{
         scanner = new Scanner(System.in);
        //
        ListFiles();
        System.out.println("Enter option ~");
        System.out.println("----------------");
        System.out.println("1 : Encryption ");
        System.out.println("2 : Decryption ");
        System.out.println("----------------");

        Integer choice = scanner.nextInt();
        if (choice != 1 && choice != 2) {
            System.out.println("No option chosen, using encryption");
            choice = 1;
        }

        System.out.println("****************");
        System.out.println();
        System.out.print("ENTER KEY ONE (INTEGER) : ");
        KeyOne = scanner.nextInt();
        if (KeyOne <= 1) {
            System.out.println("INVALID KEY, must be a number greater than 1 - Using Default key value 5");
            KeyOne = 5;
        }
        System.out.println("****************");
        System.out.println();
        System.out.print("ENTER KEY TWO (STRING): ");
        KeyTwo = scanner.next().toUpperCase();
        if (KeyTwo == null || KeyTwo.length() < 2) {
            KeyTwo = "DEFAULTKEY";
            System.out.println("INVALID KEY, must be a string longer than 1 letter - Using Default key 'DEFAULTKEY'");
        }
        System.out.println();

        if (choice == 1) {
            Encryption();
        } else {
            Decryption();
        }

        scanner.next();
    }
}
