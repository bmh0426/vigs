
package vig;

import java.io.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    private static String writeFile = null;
    
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
                openFile(arg);
            }
            //Check if they want to do a different than default port number
            else if(arg.contains(".out") || arg.contains("tests.txt"))
            {
                outFile = true;
                writeFile = arg;
            }
            //ELSE the argument is the key to use
            else
            {
                //check if key is set.
                if (key == null)
                {
                    key = arg.toUpperCase();
                 //   System.out.println(key);
               //     System.out.println(arg);
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
       // return 0;
    }

    private static void openFile(String arg) throws IOException 
    {
        //ClassLoader loader = this.getClass().getClassLoader();
        //InputStream cardLineStream = Vig.class.getResourceAsStream(arg);
        //InputStream cardLineStream = loader.getResourceAsStream(arg);
        allLinesList = new ArrayList();
        //Scanner scan = new Scanner(cardLineStream);
        //Path filePath = Paths.get(arg);
        FileReader filePath = new FileReader(arg);
        Scanner scan = new Scanner(filePath);
        //This will loop through the file and scan in all the lines.
        while(scan.hasNextLine())
        {
            allLinesList.add(scan.nextLine());
        }        
    }
    
    //this method get the plain text from stdin or a file to be encrypted.
    //it also prints the cipher text to stdout or file.
    private static void encryption() throws IOException 
    {     
        String plain;
        String temp = "";
        ArrayList<String> tempList = new ArrayList();
        Scanner scanner = new Scanner(System.in);
        if (inFile)
        {
            for (int num = 0; num < allLinesList.size(); num++)
            {
                plain = allLinesList.get(num).toUpperCase();
                temp = encrypt(plain); 
                tempList.add(temp);                
            }
        }
        else
        {
            System.out.println("Please enter plain text!");
            plain = scanner.nextLine();
            plain = plain.toUpperCase();
            temp = encrypt(plain);
            tempList.add(temp);
        }
        
        if (outFile)
        {
            
            File fileWriter = new File(writeFile);
            FileOutputStream fileStream = new FileOutputStream(fileWriter);
            OutputStreamWriter outStream = new OutputStreamWriter(fileStream);
            for (int num = 0; num < tempList.size(); num++)
            {
                outStream.write(tempList.get(num));
            }            
            outStream.close();
             /*
            FileWriter fileWriter = new FileWriter(writeFile);
            BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
            
            for (int num = 0; num < tempList.size(); num++)
            {
                bufferWriter.write(tempList.get(num));             
                bufferWriter.newLine();
            }
            bufferWriter.close();*/
        }
        else
        {
            for (int num = 0; num < tempList.size(); num++)
            {
                System.out.println(tempList.get(num));
            }            
        }
    }
    //this method decrypts the cipher text to plain text.
    private static void decryption() 
    {
        String temp = "";
        String cipher;
        Scanner scanner = new Scanner(System.in);
        if (inFile)
        {
            
        }
        else
        {
            System.out.println("Please enter cipher text!");
            cipher = scanner.nextLine();
            cipher = cipher.toUpperCase();
            for (int num = 0, num1 = 0; num < cipher.length(); num++)
            {
              //  System.out.println("here");                
              //  System.out.println(plain);
              //  System.out.println(key);
                char cha = cipher.charAt(num);
                if (Character.isLetter(cha))
                {
                    temp += (char)((cha - key.charAt(num1) + 26) % 26 + 'A');
                    num1 = ++num1 % key.length();
                }
                else
                {
                    temp += cha;
                }
            }
        }
        if (outFile)
        {
            
        }
        else
        {
            System.out.println("Decrypted Message: " + temp);
        }    
    }

    private static String encrypt(String plain) 
    {
        String temp  = "";
        for (int num2 = 0, num1 = 0; num2 < plain.length(); num2++)
        {
            char cha = plain.charAt(num2);
            if (Character.isLetter(cha))
            {
                temp += (char)((cha + key.charAt(num1) - 2 * 'A') % 26 + 'A');
                num1 = ++num1 % key.length();
            }
            else
            {
                temp += cha;
            }
        }
        return temp;
    }
}
