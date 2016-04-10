
package vig;

import java.io.*;
import java.io.IOException;
import java.util.*;
import java.util.LinkedHashMap;

/**
 *
 * @author Brian Holland
 */
public class Vig {

    /**
     * @param args the command line arguments
     */
    private static String key = null;
    private static ArrayList<String> allLinesList = null;
    private static boolean outFile = false;
    private static boolean inFile = false;
    
    public static int main(String[] args) {
        for (int argNum = 0; argNum < args.length; argNum++)
        {
            String arg = args[argNum];
            boolean encypt = true;
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
                if (key == null)
                {
                    key = arg;
                    if (key != null && !key.isEmpty()){
                        for (char c : key.toCharArray())
                        {
                            if (!Character.isLetter(c))
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
            if (encypt)
            {
               encryption(key, inFile, outFile);
            }
            else
            {
                decryption(key, inFile, outFile);
            }
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

    private static void encryption(String key, boolean inFile, boolean outFile) {
        
    }

    private static void decryption(String key, boolean inFile, boolean outFile) {
        
    }

}
