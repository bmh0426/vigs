
package vig;

import java.io.*;
import java.io.IOException;
import java.util.*;
//import java.util.LinkedHashMap;

/**
 * Program to use Vigenere cipher to convert plain text into cipher text.
 * @author Brian Holland
 */
public class Vig 
{

    /**
     * @param args the command line arguments
     */
    private static String key = null;
    private static ArrayList<String> allLinesList = null;
    private static boolean outFile = false;
    private static boolean inFile = false;
    
    public static int main(String[] args) 
    {
        boolean encypt = true;
        //Check the args given to the program.
        for (int argNum = 0; argNum < args.length; argNum++)
        {
            String arg = args[argNum];
            
            //IF the argument indicates encryption
            if (arg.equals("-v"))
            {
                //Set encypt to true
                encypt = true;
                //hello
            }
            //Else If the argument indicates decryption 
            else if(arg.equals("-d"))
            {
                //Set encypt ot false
                encypt = false;
            }
            //Else If the argument is a input file name
            else if(arg.contains(".in"))
            {
                inFile = true;
                openFile(arg);
            }
            //Check if they want to do a different than default port number
            else if(arg.contains(".out"))
            {
                outFile = true;
            }
            //ELSE the argument is the key to use
            else
            {
                //check if key is set.
                if (key == null)
                {
                    key = arg;
                    System.out.println(key);
                    System.out.println(arg);
                    //if not set set key.
                    if (key != null && !key.isEmpty())
                    {
                        //loop through key.
                        for (char cha : key.toCharArray())
                        {
                            //see if all charachers are letters.
                            if (!Character.isLetter(cha))
                            {
                                System.out.println("Invalid input. Ending program.");
                                return -1;
                            }
                        }
                    }
                }
                else
                {
                    System.out.println("Using first entered key");
                }                
            }
           
        }
         //check to see if encypt or decrypt
        if (encypt)
        {
            encryption();
        }
        else
        {
            decryption();
        }
        return 0;
    }

    private static void openFile(String arg) 
    {
        //ClassLoader loader = this.getClass().getClassLoader();
        InputStream cardLineStream = Vig.class.getResourceAsStream(arg);
        allLinesList = new ArrayList();
        Scanner scan = new Scanner(cardLineStream);
        //This will loop through the file and scan in all the lines.
        while(scan.hasNextLine())
        {
            allLinesList.add(scan.nextLine());
        }
    }
    
    //this method encrypts the plain text to cipher text.
    private static void encryption() 
    {
        String temp = "";
        String plain;
        Scanner scanner = new Scanner(System.in);
        if (inFile)
        {
            
        }
        else
        {
            System.out.println("Please enter plain text!");
            plain = scanner.next();
            plain = plain.toUpperCase();
            for (int num = 0, num1 = 0; num < plain.length(); num++)
            {
                System.out.println("here");
                
                System.out.println(plain);
                char cha = plain.charAt(num);
                if (Character.isLetter(cha))
                {
                    temp += (char)((cha + key.charAt(num1) - 13 + 'A') % 26 + 'A');
                    num1 = ++num1 % key.length();
                }
                else
                {
                    temp += cha;
                }
            }
        }
        System.out.println("Encrypted Message: " + temp);
    }
    //this method decrypts the cipher text to plain text.
    private static void decryption() 
    {
        
    }

}
