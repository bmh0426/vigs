

package vig;

import java.io.*;
import java.io.IOException;
import java.util.*;

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
    private static boolean outFile = false;
    private static boolean inFile = false;
    private static FileReader rFile = null;
    private static FileWriter wFile = null;
    private static int number = 0;
    
    public static void main(String[] args) throws IOException 
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
            }
            //Else If the argument indicates decryption 
            else if(arg.equals("-d"))
            {
                //Set encypt ot false
                encypt = false;
            }
            //Else If the argument is a input file name
            else if(arg.contains(".in") || arg.contains("test.txt"))
            {
                inFile = true;
                rFile = new FileReader(arg);
            }
            //Check if they want to do a different than default port number
            else if(arg.contains(".out") || arg.contains("tests.txt"))
            {
                outFile = true;
                wFile = new FileWriter(arg);
                
                if (!inFile)
                {
                    System.out.println("There must be an infile to go with out file");
                    System.exit(-1);
                }
            }
            //ELSE the argument is the key to use
            else
            {              
                //check if key is set.
                if (key == null)
                {
                    key = arg.toUpperCase();
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
                           //     return -1;
                            }
                        }
                    }
                }
                else
                {
               
                    System.out.println("Using first entered key " + key);
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
        key = null;
       // return 0;
    }

    //this method get the plain text from stdin or a file to be encrypted.
    //it also prints the cipher text to stdout or file.
    private static void encryption() throws IOException 
    {     
        String plain = "";
        Scanner scanner = new Scanner(System.in);
        char cha = ' ';
        int num = 0;
        //If an input file is given then the program will use the array list 
        //from opening and saving the plain text into that array list of string.
        //Then the program will encrypt the plain text and save that into an
        //array list.
        //if no inputfile is given then the program will get the plain text
        //from stdin and encrypt it and save it into an array list.
        if (inFile)
        {
            while ((num = rFile.read()) != -1)
            {               
               cha = (char)num;
               encrypt(Character.toUpperCase(cha));
            }
            System.out.print("\n");
            rFile.close();
        }
        else
        {
            System.out.println("Please enter plain text!");
            plain = scanner.nextLine();
            plain = plain.toUpperCase();
            for (int num1 = 0; num1 < plain.length(); num1++)
            {
               encrypt(plain.charAt(num1));
            }
            System.out.print("\n");
        }
        
        if (outFile)
            wFile.close();
       
    }
    
     private static void encrypt(char cha) throws IOException 
    {
        //OutputStreamWriter outStream = new OutputStreamWriter(fileStream);
        //This loop runs through the plain text and gets each character and
        //checks if it is a letter. If it is then the program calcualtes
        //from the key how many letters to shift the character.
        
	if (outFile)
        {
	   if (Character.isLetter(cha))
           {
              wFile.write((char)((cha + key.charAt(number) - 2 * 'A') % 26 + 'A'));
              number = ++number % key.length();
           }
           else
           {
             wFile.write(cha);
           }
        }
        else
        {
           if (Character.isLetter(cha) && cha != '\n' && cha != -1)
           {
              
              System.out.print((char)((cha + key.charAt(number) - 2 * 'A') % 26 + 'A'));
              number = ++number % key.length();
           }
           else
           {
             System.out.print(cha);
           }
        }
	
    }
     
    //this method decrypts the cipher text to plain text.
    private static void decryption() throws IOException 
    {
        String plain = "";
        Scanner scanner = new Scanner(System.in);
        char cha = ' ';
        int num = 0;
        //If an input file is given then the program will use the array list 
        //from opening and saving the plain text into that array list of string.
        //Then the program will encrypt the plain text and save that into an
        //array list.
        //if no inputfile is given then the program will get the plain text
        //from stdin and encrypt it and save it into an array list.
        if (inFile)
        {
            while ((num = rFile.read()) != -1)
            {               
               cha = (char)num;
               decrypt(Character.toUpperCase(cha));
            }
            System.out.print("\n");
            rFile.close();
        }
        else
        {
            System.out.println("Please enter cipher text!");
            plain = scanner.nextLine();
            plain = plain.toUpperCase();
            for (int num1 = 0; num1 < plain.length(); num1++)
            {
               decrypt(plain.charAt(num1));
            }
            System.out.print("\n");
        }
        
        if (outFile)
            wFile.close();
    }  

    private static void decrypt(char cha) throws IOException
    {
        //This loop runs through the cipher text and gets each character and
        //checks if it is a letter. If it is, then the program calcualtes
        //from the key how many letters to shift the character.
        
        
        if (outFile)
        {
	   if (Character.isLetter(cha))
           {
              wFile.write((char)((char)((cha - key.charAt(number) + 26) % 26 + 'A')));
              number = ++number % key.length();
           }
           else
           {
             wFile.write(cha);
           }
        }
        else
        {
           if (Character.isLetter(cha))
           {              
              System.out.print((char)((cha - key.charAt(number) + 26) % 26 + 'A'));
              number = ++number % key.length();
           }
           else
           {
             System.out.print(cha);
           }
        }
    }
}

