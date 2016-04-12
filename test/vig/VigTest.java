/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vig;

import java.io.ByteArrayInputStream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Susie
 */
public class VigTest {
    
    public VigTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of main method, of class Vig.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = {"-v", "HAM"};
        ByteArrayInputStream in = new ByteArrayInputStream("To be or not to be".getBytes());
        System.out.println(in.toString());
        System.setIn(in);
        int expResult = 0;
        int result = Vig.main(args);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
      //  fail("The test case is a prototype.");
    }
}