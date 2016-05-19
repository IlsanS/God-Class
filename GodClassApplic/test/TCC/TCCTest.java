package TCC;

import tcc.TCCCalculateur;
import japa.parser.ast.expr.NameExpr;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 * @author bobmastrolilli
 */
public class TCCTest
{    
    private static TCCCalculateur instance;
    private static int expectedSize;
    
    public TCCTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
        // Ajout des variables
        instance = new TCCCalculateur();
        expectedSize = 6;
        
        for (int i = 0; i < expectedSize; i++)
            instance.incrementsMethodCount();
        
        // Ajout de variables
        instance.addVariables("var1");
        instance.addVariables("var2");
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }

    /**
     * Test of incrementsMethodCount method, of class ClassCohesionCalculator.
     */
    @Test
    public void testIncrementsMethodCount()
    {
        System.out.println("incrementsMethodCount");

        assertEquals(expectedSize, instance.getMethodCount());
    }

    /**
     * Test of addVariables method, of class ClassCohesionCalculator.
     */
    @Test
    public void testAddVariables()
    {
        System.out.println("addVariables");
        
        // Container null?
        assertNotNull(instance.getConnectedVariables());
        
        // Var existe
        String var = "var1";
        instance.addVariables(var);
        assertTrue(instance.containsVariable(var));
    }

    /**
     * Test of containsVariable method, of class ClassCohesionCalculator.
     */
    @Test
    public void testContainsVariable()
    {
        System.out.println("containsVariable");
        
        // Existe pas
        String var = "testnegatif";
        
        boolean expResult = false;
        boolean result = instance.containsVariable(var);
        assertEquals(expResult, result);
        
        // Existe
        var = "var1";
        expResult = true;
        assertEquals(expResult, instance.containsVariable(var));
    }

    /**
     * Test of setCurrentMethod method, of class ClassCohesionCalculator.
     */
    @Test
    public void testSetCurrentMethod()
    {
        System.out.println("setCurrentMethod");
        
        NameExpr method = new NameExpr("method1");
        instance.setCurrentMethod(method);
        assertEquals(method, instance.getCurrentMethod());
        expectedSize++;
        assertEquals(expectedSize, instance.getMethodCount());
    }

    /**
     * Test of addMethodConnection method, of class ClassCohesionCalculator.
     */
    @Test
    public void testAddMethodConnection()
    {
        System.out.println("addMethodConnection");
        
        NameExpr method = new NameExpr("method1");
        instance.setCurrentMethod(method);    
        expectedSize++;
        
        String var = "var1";
        instance.addMethodConnection(var);
        assertNotNull(instance.getCurrentMethod());
        assertTrue(instance.getConnectedVariables().containsKey(var));
        assertNotNull(instance.getConnectedVariables().get(var));
        
        var = "tstss";
        assertFalse(instance.getConnectedVariables().containsKey(var));
    }

    /**
     * Test of printVariables method, of class ClassCohesionCalculator.
     */
    @Test
    public void testPrintVariables()
    {
        System.out.println("printVariables");
        assertNotNull(instance.getConnectedVariables());
        assertNotNull(instance.getMethodCount());
        assertEquals(expectedSize, instance.getMethodCount());
    }

    /**
     * Test of calculateTCC method, of class ClassCohesionCalculator.
     */
    @Test
    public void testCalculateTCC()
    {
        System.out.println("calculateTCC");
        assertTrue(instance.getMethodCount() != 0);
        instance.CalculTCC();  
        
        // Vérification de la valeur
        assertTrue(instance.getMetric() <= 1.0 && instance.getMetric() >= 0.0);
    }    
}
