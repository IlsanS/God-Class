/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GodClass.Calculator;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sebastien
 */
public class GeneralCalculatorTest
{
    
    public GeneralCalculatorTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }

    /**
     * Test of methodIncrements method, of class GeneralCalculator.
     */
    @Test
    public void testMethodIncrements()
    {
        System.out.println("methodIncrements");
        int expectedSize = 5;
        GeneralCalculator instance = new GeneralCalculator();
        for (int i = 0; i < expectedSize; i++)
            instance.methodIncrements();
        
        assertEquals(expectedSize, instance.getMethodCount());
    }
}
