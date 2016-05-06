/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GodClass;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sebastien
 */
public class GodClassAnalyserTest
{
    
    public GodClassAnalyserTest()
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
     * Test of analyseCohesion method, of class GodClassAnalyser.
     */
    @Test
    public void testAnalyseCohesion()
    {
        System.out.println("analyseCohesion");
        GodClassAnalyser instance = new GodClassAnalyser();
        
        // Mauvaise cohésion
        double metric = 0.0;
        String expResult = "Mauvaise cohésion";
        String result = instance.analyseCohesion(metric);
        assertEquals(expResult, result);
        
        metric = 0.39;
        expResult = "Mauvaise cohésion";
        result = instance.analyseCohesion(metric);
        assertEquals(expResult, result);
        
        // Moyenne
        metric = 0.5;
        expResult = "Cohésion moyenne";
        result = instance.analyseCohesion(metric);
        assertEquals(expResult, result);
        
        metric = 0.4;
        expResult = "Cohésion moyenne";
        result = instance.analyseCohesion(metric);
        assertEquals(expResult, result);
        
        // Bonne
        metric = 0.9;
        expResult = "Bonne cohésion";
        result = instance.analyseCohesion(metric);
        assertEquals(expResult, result);
    }

    /**
     * Test of analyseForeignDataAccess method, of class GodClassAnalyser.
     */
    @Test
    public void testAnalyseForeignDataAccess()
    {
        System.out.println("analyseForeignDataAccess");
        GodClassAnalyser instance = new GodClassAnalyser();
        
        // Bonne accessibilité
        int metric = 0;
        String expResult = "Bonne accessibilité";
        String result = instance.analyseForeignDataAccess(metric);
        assertEquals(expResult, result);
        
        // Moyenne
        metric = 4;
        expResult = "Beaucoup de classes accédées";
        result = instance.analyseForeignDataAccess(metric);
        assertEquals(expResult, result);
        
        // Trop
        metric = 9;
        expResult = "Trop de classes accédées";
        result = instance.analyseForeignDataAccess(metric);
        assertEquals(expResult, result);
    }

    /**
     * Test of analyseWeightedMethodCound method, of class GodClassAnalyser.
     */
    @Test
    public void testAnalyseWeightedMethodCound()
    {
        System.out.println("analyseWeightedMethodCound");
        GodClassAnalyser instance = new GodClassAnalyser();
        
        // Faible complexité
        int metric = 12;
        String expResult = "Faible complexité";
        String result = instance.analyseWeightedMethodCound(metric);
        assertEquals(expResult, result);
        
        // Moyenne
        metric = 37;
        expResult = "Complexité moyenne";
        result = instance.analyseWeightedMethodCound(metric);
        assertEquals(expResult, result);
        
        // Haute
        metric = 55;
        expResult = "Haute complexité";
        result = instance.analyseWeightedMethodCound(metric);
        assertEquals(expResult, result);
    }
    
}
