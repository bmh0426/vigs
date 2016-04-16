

package vig;

import java.io.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    private static ArrayList<String> allLinesList = null;
    private static boolean outFile = false;
    private static boolean inFile = false;
    private static String writeFile = null;
    private static String readFile = null;
    private static RandomAccessFile rFile;
    private static RandomAccessFile wFile;
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
                //openFile(arg);
               // readFile = arg;
                rFile = new RandomAccessFile(arg, "r");
            }
            //Check if they want to do a different than default port number
            else if(arg.contains(".out") || arg.contains("tests.txt"))
            {
                outFile = true;
              //  writeFile = arg;
                wFile = new RandomAccessFile(arg, "rw");
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

    private static void openFile(String arg) throws IOException 
    {
        //ClassLoader loader = this.getClass().getClassLoader();
        //InputStream cardLineStream = Vig.class.getResourceAsStream(arg);
        //InputStream cardLineStream = loader.getResourceAsStream(arg);
      //  allLinesList = new ArrayList();
        //Scanner scan = new Scanner(cardLineStream);
        //Path filePath = Paths.get(arg);
       // FileReader filePath = new FileReader(arg);
      //  Scanner scan = new Scanner(filePath);
        //This will loop through the file and scan in all the lines.
     //   while(scan.hasNextLine())
     //   {
    //        allLinesList.add(scan.nextLine());
    //    }    

	    
    }
    
    //this method get the plain text from stdin or a file to be encrypted.
    //it also prints the cipher text to stdout or file.
    private static void encryption() throws IOException 
    {     
        String plain = "";
       // String temp = "";
      //  ArrayList<String> tempList = new ArrayList();
        Scanner scanner = new Scanner(System.in);
        char cha = ' ';
        //If an input file is given then the program will use the array list 
        //from opening and saving the plain text into that array list of string.
        //Then the program will encrypt the plain text and save that into an
        //array list.
        //if no inputfile is given then the program will get the plain text
        //from stdin and encrypt it and save it into an array list.
        if (inFile)
        {

	    while (rFile.getFilePointer() < rFile.length())
            {
               cha = rFile.readChar();               
               encrypt(Character.toUpperCase(cha));
            }
	    
            //This loops the given array list and converts all plain text
            //lines into cipher text.
          //  for (int num = 0; num < allLinesList.size(); num++)
         //   {
         //       plain = allLinesList.get(num).toUpperCase();
        //        encrypt(plain);                
       //     }
        }
        else
        {
            System.out.println("Please enter plain text!");
            plain = scanner.nextLine();
            plain = plain.toUpperCase();
            for (int num = 0; num < plain.length(); num++)
            {
               encrypt(plain.charAt(num));
            }
        }
       
    }
    //this method decrypts the cipher text to plain text.
    private static void decryption() throws IOException 
    {
        String temp = "";
        String cipher;
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> tempList = new ArrayList();
        //If an input file is given then the program will use the array list 
        //from opening and saving the cipher text into that array list of string.
        //Then the program will decrypt the cipher text and save that into an
        //array list.
        //if no inputfile is given then the program will get the cipher text
        //from stdin and encrypt it and save it into an array list.
        if (inFile)
        {
            //This loops the given array list and converts all cipher text
            //lines into plain text.
            for (int num = 0; num < allLinesList.size(); num++)
            {
                cipher = allLinesList.get(num).toUpperCase();
                temp = decrypt(cipher); 
                tempList.add(temp);                
            }
        }
        else
        {
            System.out.println("Please enter cipher text!");
            cipher = scanner.nextLine();
            cipher = cipher.toUpperCase();
            temp = decrypt(cipher);      
            tempList.add(temp);
        }
        //If an output file is given then the plain text is saved into that file.
        //Else the plain text is put into stdout.
        if (outFile)
        {
            /*
            File fileWriter = new File(writeFile);
            FileOutputStream fileStream = new FileOutputStream(fileWriter);
            OutputStreamWriter outStream = new OutputStreamWriter(fileStream);
            
            //This loops through the array list and prints it to the output file.
            for (int num = 0; num < tempList.size(); num++)
            {
                outStream.write(tempList.get(num));
            }            
            outStream.close();
             /**/
            FileWriter fileWriter = new FileWriter(writeFile);
            BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
            
            for (int num = 0; num < tempList.size(); num++)
            {
                bufferWriter.write(tempList.get(num));             
                bufferWriter.newLine();
            }
            bufferWriter.close();
        }
        else
        {
            //This loop through the array list and prints it to stdout.
            for (int num = 0; num < tempList.size(); num++)
            {
                System.out.println(tempList.get(num));
            } 
        }    
    }

    private static void encrypt(char cha) throws IOException 
    {
        //String temp  = "";
        //File fileWriter = new File(writeFile);
        //FileOutputStream fileStream = new FileOutputStream(fileWriter);
        //OutputStreamWriter outStream = new OutputStreamWriter(fileStream);
        //This loop runs through the plain text and gets each character and
        //checks if it is a letter. If it is then the program calcualtes
        //from the key how many letters to shift the character.
        
	if (outFile)
        {
	   if (Character.isLetter(cha))
           {
              wFile.writeChar((char)((cha + key.charAt(number) - 2 * 'A') % 26 + 'A'));
              number = ++number % key.length();
           }
           else
           {
             wFile.writeChar(cha);
           }
        }
        else
	   System.out.print(cha);
        
        /*

        for (int num2 = 0, num1 = 0; num2 < plain.length(); num2++)
        {
            char cha = plain.charAt(num2);
            //If letter shift character the lenght of the key postion.
            //If not letter just add the charater to temp.
            if (outFile)
            {             
                if (Character.isLetter(cha))
                {
                    outStream.write((char)((cha + key.charAt(num1) - 2 * 'A') % 26 + 'A'));
                }
                else
                {
                    outStream.write(cha);
                }                
            }
            else
            {
                if (Character.isLetter(cha))
                {
                    System.out.println((char)((cha + key.charAt(num1) - 2 * 'A') % 26 + 'A'));
                    num1 = ++num1 % key.length();
                }
                else
                {
                    System.out.print(cha);
                }
            }
            
        }
        if (outFile)
            outStream.close();*/
    }

    private static String decrypt(String cipher)
    {
        String temp = "";
        //This loop runs through the cipher text and gets each character and
        //checks if it is a letter. If it is, then the program calcualtes
        //from the key how many letters to shift the character.
        for (int num = 0, num1 = 0; num < cipher.length(); num++)
        {
          //  System.out.println("here");                
          //  System.out.println(plain);
          //  System.out.println(key);
            char cha = cipher.charAt(num);
            //If letter shift character the lenght of the key postion.
            //If not letter just add the charater to temp.
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
        return temp;
    }
}

