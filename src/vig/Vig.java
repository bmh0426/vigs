package vig;

import java.io.*;
import java.io.IOException;
import java.util.*;

/**
 * Program to use Vigenere cipher to convert plain text into cipher text.
 * It will also convert cipher test into plain text.
 * @author Brian Holland
 */
public class Vig 
{

    /**
     * The main function gets the arguments from the user and runs them.
     * @param args the command line arguments
     */
    private static String key = null;
    private static boolean outFile = false;
    private static boolean inFile = false;
    private static FileReader rFile = null;
    private static FileWriter wFile = null;
    private static int number = 0;
    
    public static void main(String[] args)
    {
        boolean encypt = true;
        /*Check the args given to the program.*/
        for (int argNum = 0; argNum < args.length; argNum++)
        {
            String arg = args[argNum];
            
            /*IF the argument indicates encryption*/
            if (arg.equals("-v"))
            {
                /*Set encypt to true*/
                encypt = true;
            }
            /*Else If the argument indicates decryption*/ 
            else if(arg.equals("-d"))
            {
                /*Set encypt ot false*/
                encypt = false;
            }
            /*Else If the argument is a input file name*/
            else if(arg.contains(".in") || arg.contains("test.txt"))
            {
                inFile = true;
                try
                {
                    rFile = new FileReader(arg);
                }
                catch (FileNotFoundException ex)
                {
                    System.out.println("Cannot open input file.");
                }
            }
            /*Check if they want to do a different than default port number*/
            else if(arg.contains(".out") || arg.contains("tests.txt"))
            {
                outFile = true;
                try
                {
                    wFile = new FileWriter(arg);
                }
                catch (IOException ex)
                {
                    System.out.println("Cannot open output file.");
                }
                
                /*Check to see if an input file was given if not exit program.*/
                if (!inFile)
                {
                    System.out.println("There must be an infile to go with out file");
                    System.exit(-1);
                }
            }
            /*ELSE the argument is the key to use*/
            else
            {              
                /*check if key is set.*/
                if (key == null)
                {
                    key = arg.toUpperCase();
                    /*if not set set key.*/
                    if (key != null && !key.isEmpty())
                    {
                        /*loop through key.*/
                        for (char cha : key.toCharArray())
                        {
                            /*see if all charachers are letters. And if they are
                              not then print out message and exit.*/
                            /*commit*/
                            if (!Character.isLetter(cha))
                            {
                                System.out.println("Invalid input. "
                                        + "Ending program.");
                                System.exit(-1);
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
        
        if (key == null)
        {
            System.out.println("A key was not given in the command line."
                    + " Exiting program.");
            System.exit(-1);
        }
         /*check to see if encypt or decrypt*/
        if (encypt)
        {
            encryption();
        }
        else
        {
            decryption();
        }
        key = null;
        System.exit(1);
    }

    /*this method get the plain text from stdin or a file to be encrypted.
      it also prints the cipher text to stdout or file.*/
    private static void encryption() 
    {     
        String plain = "";
        Scanner scanner = new Scanner(System.in);
        char cha = ' ';
        int num = 0;
        
        /*if an input file is given then the program will read in each character 
         * one at a time. After the character is read in then it is immediately 
         * put to the encrypt function.
         * If no inputfile is given then the program will get the plain text
         * from stdin sends it to encrypt.*/
        /*commit*/
        if (inFile)
        {
            try
            {
                /*Loop through the file and read each character one by one. 
                 and sending it to encrypt.*/
                /*commit*/
                while ((num = rFile.read()) != -1)
                {
                    cha = (char)num;
                    encrypt(Character.toUpperCase(cha));
                }
            }
            catch (IOException ex)
            {
                System.out.println("Cannot open the file to read in characters.");
            }
            System.out.print("\n");
            try
            {
                rFile.close();
            }
            catch (IOException ex)
            {
                System.out.println("Can not close the input file.");
            }
        }
        else
        {
            System.out.println("Please enter plain text!");
            plain = scanner.nextLine();
            plain = plain.toUpperCase();
            /*loop through the input and send each character to encrypt.*/
            for (int num1 = 0; num1 < plain.length(); num1++)
            {
                encrypt(plain.charAt(num1));                
            }
            System.out.print("\n");
        }
        /*Check to see if an output file was given.*/
        if (outFile)
        {
            try
            {
                wFile.close();
            }
            catch (IOException ex)
            {
                System.out.println("Could not close the out put file.");
            }
        }
            
       
    }
    
    private static void encrypt(char cha) 
    {        
        /*Check to see if an output file is given.*/
        if (outFile)
        {
            /*Check to see it the character is a letter or other. If letter
             * then encrypt it and write it to the output file.
             * If not a letter just write it to the output file.
             */
            /*commit*/
            if (Character.isLetter(cha))
            {
                try
                {
                    wFile.write((char)((cha + key.charAt(number) - 2 * 'A') % 26 + 'A'));
                }
                catch (IOException ex)
                {
                    System.out.println("Could not write to file.");
                }
                number = ++number % key.length();
            }
            else
            {
                try
                {
                    wFile.write(cha);
                }
                catch (IOException ex)
                {
                    System.out.println("Could not write to file.");
                }
            }
        }
        else
        {
           /*Check to see it the character is a letter or other. If letter
             * then encrypt it and write it to the stdout file.
             * If not a letter just write it to the stdout file.
             */
            /*commit*/
            if (Character.isLetter(cha) && cha != '\n' && cha != -1)
            {              
                System.out.print((char)((cha + key.charAt(number) - 2 * 'A') 
                        % 26 + 'A'));
                number = ++number % key.length();
            }
            else
            {
                System.out.print(cha);
            }
        }
    }
     
    /*this method decrypts the cipher text to plain text.*/
    private static void decryption() 
    {
        String plain = "";
        Scanner scanner = new Scanner(System.in);
        char cha = ' ';
        int num = 0;
        /*if an input file is given then the program will read in each character 
         * one at a time. After the character is read in then it is immediately 
         * put to the decrypt function.
         * If no inputfile is given then the program will get the cipher text
         * from stdin sends it to encrypt.*/
        /*commit*/
        if (inFile)
        {
            try
            {
                /*Loop through the file and read each character one by one. 
                 and sending it to encrypt.*/
                /*commit*/
                while ((num = rFile.read()) != -1)
                {
                    cha = (char)num;
                    decrypt(Character.toUpperCase(cha));
                }
            }
            catch (IOException ex)
            {
                System.out.println("Decrypt is not working.");
            }
            System.out.print("\n");
            try
            {
                rFile.close();
            }
            catch (IOException ex)
            {
                System.out.println("Could not close the read file.");
            }
        }
        else
        {
            System.out.println("Please enter cipher text!");
            plain = scanner.nextLine();
            plain = plain.toUpperCase();
            /*loop through the input and send each character to decrypt.*/
            for (int num1 = 0; num1 < plain.length(); num1++)
            {
                decrypt(plain.charAt(num1));
            }
            System.out.print("\n");
        }
        /*Check to see if an output file was given.*/
        if (outFile)
        {
            try
            {
                wFile.close();
            }
            catch (IOException ex)
            {
                System.out.println("Could not close the write file.");
            }
        }            
    }  

    private static void decrypt(char cha)
    {        
        /*Check to see if an output file is given.*/
        if (outFile)
        {
            /*Check to see it the character is a letter or other. If letter
             * then decrypt it and write it to the output file.
             * If not a letter just write it to the output file.
             */
            /*commit*/
            if (Character.isLetter(cha))
            {
                try
                {
                    wFile.write((char)((char)((cha - key.charAt(number) + 26) 
                            % 26 + 'A')));
                }
                catch (IOException ex)
                {
                    System.out.println("Could not write to file.");
                }
                number = ++number % key.length();
            }
            else
            {
                try
                {
                    wFile.write(cha);
                }
                catch (IOException ex)
                {
                    System.out.println("Could not write to file.");
                }
            }
        }
        else
        {
            /*Check to see it the character is a letter or other. If letter
             * then decrypt it and write it to the stdout file.
             * If not a letter just write it to the stdout file.
             */
            /*commit*/
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

