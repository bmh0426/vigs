/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vig;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * this
 * @author Brian
 */
public class VigTest 
{
    /**
     * Test of main method, of class Vig.
     */
    @Test
    public void testMain() throws IOException, InterruptedException 
    {
        System.out.println("main");
        String[] args = {"-v", "HAM", "test.in"};
        ByteArrayInputStream in = new ByteArrayInputStream("To be or not to be".getBytes());
        System.setIn(in);
        Vig.main(args); 
        
        
        
      //  assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
      //  fail("The test case is a prototype.");
    }
    /**
     *  test
     * @throws IOException 
     *//*
    @Test
    public void testMain1() throws IOException
    {
        System.out.println("main1");
        String[] args1 = {"-d", "Ham"};
        ByteArrayInputStream in2 = new ByteArrayInputStream("AO NL OD UOF AO NL".getBytes());
        System.setIn(in2);
        Vig.main(args1);
    }
    /**
     * test
     * @throws IOException 
     *//*
    @Test
    public void testMain2() throws IOException
    {
        System.out.println("main2");
        String[] args2 = {"-v", "d", "test.in"};
       // ByteArrayInputStream in3 = new ByteArrayInputStream("AO NL OD UOF AO NL".getBytes());
        //System.setIn(in2);
        Vig.main(args2);
    }*/
}