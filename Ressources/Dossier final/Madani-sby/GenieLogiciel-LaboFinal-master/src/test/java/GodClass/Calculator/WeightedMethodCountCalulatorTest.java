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
public class WeightedMethodCountCalulatorTest
{
    
    public WeightedMethodCountCalulatorTest()
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
     * Test of newMethodComplexity method, of class WeightedMethodCountCalulator.
     */
    @Test
    public void testNewMethodComplexity()
    {
        System.out.println("newMethodComplexity");
        WeightedMethodCountCalulator instance = new WeightedMethodCountCalulator();
        instance.newMethodComplexity();
        assertEquals(1, instance.getMethodCyclomaticComplexity());
    }

    /**
     * Test of addMethodComplexity method, of class WeightedMethodCountCalulator.
     */
    @Test
    public void testAddMethodComplexity()
    {
        System.out.println("addMethodComplexity");
        WeightedMethodCountCalulator instance = new WeightedMethodCountCalulator();
        instance.newMethodComplexity();
        int expectedSize;
        int expectedMetric = 0;
        for (int m = 0; m < 3; m++)
        {
            instance.newMethodComplexity();
            expectedSize = 1;
            for (int i = 0; i < 6; i++)
            {
                instance.incrementsComplexity();
                expectedSize++;
            }
            instance.addMethodComplexity();
            expectedMetric += expectedSize;
        }

        assertEquals(expectedMetric, instance.getMetric());
    }

    /**
     * Test of incrementsComplexity method, of class WeightedMethodCountCalulator.
     */
    @Test
    public void testIncrementsComplexity()
    {
        System.out.println("incrementsComplexity");
        int expectedSize;
        WeightedMethodCountCalulator instance = new WeightedMethodCountCalulator();
        instance.newMethodComplexity();
        expectedSize = 1;
        for (int i = 0; i < 6; i++)
            instance.incrementsComplexity();
        expectedSize += 6;
        
        assertEquals(expectedSize, instance.getMethodCyclomaticComplexity());
    }    
}
