/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg164788.cipher;

/**
 *
 * @author zaviy
 */
public class Substitution {

    public String key;
    public String message;
    public String mappedKey;

    public Substitution(String keyTwo) {
        key = keyTwo.toUpperCase();

    }

    public String Decrypt() {
        //int[][] table = createVigenereTable();
        String decryptedText = "";

        for (int i = 0; i < message.length(); i++) {
            if (message.charAt(i) == (char) 32 && mappedKey.charAt(i) == (char) 32) {
                decryptedText += " ";

            } else {
                if (Character.isDigit(message.charAt(i)) || !Character.isLetter(message.charAt(i))) {
                    decryptedText += message.charAt(i);
                } else {
                    if (Character.isUpperCase(message.charAt(i))) {
                        decryptedText += (char) (65 + itrCount((int) mappedKey.charAt(i), (int) message.charAt(i)));
                    } else {
                        char temp = Character.toUpperCase(message.charAt(i));
                        decryptedText += Character.toLowerCase((char) (65 + itrCount((int) mappedKey.charAt(i), (int) temp)));
                    }
                }
            }
        }
        return decryptedText;
    }

    private int itrCount(int key, int msg) {
        //this function will return the count which it takes from key's letter to reach cipher letter
        //and then this count will be used to calculate decryption of encrypted letter in message.
        int counter = 0;
        String result = "";
        for (int i = 0; i < 26; i++) {
            if (key + i > 90) {
                //90 being ASCII of Z
                result += (char) (key + (i - 26));

            } else {
                result += (char) (key + i);
            }
        }

        //counting from keys letter to cipher letter in vege table
        for (int i = 0; i < result.length(); i++) {
            if (result.charAt(i) == msg) {
                break;
            } else {
                counter++;
            }
        }
        return counter;
    }

    public void MsgAndKey(String text) {
        //map key to message
        // plaintext = plaintext.toUpperCase();
        String keyMap = "";
        for (int i = 0, j = 0; i < text.length(); i++) {
            if (text.charAt(i) == (char) 32) {
                //ignoring space
                keyMap += (char) 32;
            } else {
                //mapping letters of key with message
                if (j < key.length()) {
                    keyMap += key.charAt(j);
                    j++;
                } else {
                    //restarting the key from beginning once its length is complete
                    //and its still not mapped to message
                    j = 0;
                    keyMap += key.charAt(j);
                    j++; //without incrementing here, key's first letter will be mapped twice
                }
            } //if-else

        } //fpr
        message = text;
        mappedKey = keyMap;

      //  System.out.println("Message : " + message);
       // System.out.println("Mapped Key : " + mappedKey);

    }

    public String cipherEncrypt() {
        int[][] table = createVigenereTable();

        String encryptedText = "";
        for (int i = 0; i < message.length(); i++) {
            if (message.charAt(i) == (char) 32 && mappedKey.charAt(i) == (char) 32) {
                encryptedText += " ";

            } else {
                if (Character.isDigit(message.charAt(i)) || !Character.isLetter(message.charAt(i))) {
                    encryptedText += message.charAt(i);
                } else {
                    //accessing element at table[i][j] position to replace it with letter in message

                    //System.out.println("Text : " + message.charAt(i));
                    //System.out.println(" <> " + (message.charAt(i) - 65));

                    if (Character.isLowerCase(message.charAt(i))) {
                        char temp = Character.toUpperCase(message.charAt(i));
                        encryptedText += Character.toLowerCase((char) table[(int) temp - 65][(int) mappedKey.charAt(i) - 65]);

                    } else {
                        encryptedText += (char) table[(int) message.charAt(i) - 65][(int) mappedKey.charAt(i) - 65];
                    }
                }
            }
        }
        return encryptedText;
    }

    public int[][] createVigenereTable() {
        //creating 26x26 table containing alphabets
        int[][] tableArr = new int[26][26];
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                int temp;
                if ((i + 65) + j > 90) {
                    temp = ((i + 65) + j) - 26;
                    tableArr[i][j] = temp;

                } else {
                    temp = (i + 65) + j;
                    tableArr[i][j] = temp;
                }
            }
        }

        //print table
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                //System.out.print((char) tableArr[i][j] + " ");
            }
           // System.out.println();
        }

        return tableArr;
    }
}
