/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg164788.cipher;

import java.util.ArrayList;

public class Transposition {

    Integer key;
    ArrayList<ArrayList<String>> Listoflists;

    public Transposition(int KeyOne) {
        key = KeyOne;

    }

    public String Encrypt(String plaintext) {

        char[][] rail = new char[key][plaintext.length()];
        //matrix

        for (int i = 0; i < key; i++) {
            for (int j = 0; j < plaintext.length(); j++) {
                rail[i][j] = '-';
            }
        } //for

//Putting letters in matrix in zig zag
        int row = 0;
        int check = 0;
        for (int i = 0; i < plaintext.length(); i++) {
            if (check == 0) {
                rail[row][i] = plaintext.charAt(i);
                row++;
                if (row == key) {
                    check = 1;
                    row--;

                }
            } else if (check == 1) {
                row--;
                rail[row][i] = plaintext.charAt(i);
                if (row == 0) {
                    check = 0;
                    row = 1;

                }
            }
        }

        String encryptText = "";
        for (int i = 0; i < key; i++) {
            for (int j = 0; j < plaintext.length(); j++) {
                encryptText += rail[i][j];
                System.out.print(rail[i][j]);

            }
            System.out.println();
        }

        return encryptText.replaceAll("-", "");
    }

    public String Decrypt(String ciphertext) {
        char[][] rail = new char[key][ciphertext.length()];
        //matrix

        for (int i = 0; i < key; i++) {
            for (int j = 0; j < ciphertext.length(); j++) {
                rail[i][j] = '-';
            }
        } //for
        int row = 0;
        int check = 0;
        for (int i = 0; i < ciphertext.length(); i++) {
            if (check == 0) {
                rail[row][i] = ciphertext.charAt(i);
                row++;
                if (row == key) {
                    check = 1;
                    row--;

                }
            } else if (check == 1) {
                row--;
                rail[row][i] = ciphertext.charAt(i);
                if (row == 0) {
                    check = 0;
                    row = 1;

                }
            }
        }
        //changing order of rails
        int ord = 0;
        for (int i = 0; i < key; i++) {
            for (int j = 0; j < ciphertext.length(); j++) {
                String temp = rail[i][j] + "";
                if(temp.matches("-")){
                    //skip "-"
                    continue;
                }else{
                    rail[i][j] = ciphertext.charAt(ord);
                    ord++;
                }
              
            }
        }
        System.out.println("Reorder");
        for(int i = 0; i < key; i++){
            for(int j = 0; j < ciphertext.length(); j++){
                System.out.print(rail[i][j]);
            }
            System.out.println();
        }
        
        String decryptText = "";
        check = 0;
        row = 0;
        //converting rows back into a single line message
        for(int i = 0; i < ciphertext.length(); i++){
            if(check == 0){
                decryptText += rail[row][i];
                row++;
                if(row == key){
                    check = 1;
                    row--;
                }
            }else if(check == 1){
                row--;
                decryptText+= rail[row][i];
                if(row == 0){
                    check = 0;
                    row = 1;
                }
            }
        }
        
        return decryptText;

    }
}
