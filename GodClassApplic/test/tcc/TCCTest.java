package tcc;

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
            instance.incrémenteNbMethode();
        
        // Ajout de variables
        instance.ajoutVariable("var1");
        instance.ajoutVariable("var2");
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
        System.out.println("incrémenteNbMethode");

        assertEquals(expectedSize, instance.getNbMethode());
    }

    /**
     * Test of addVariables method, of class ClassCohesionCalculator.
     */
    @Test
    public void testAddVariables()
    {
        System.out.println("addVariables");
        
        // Container null?
        assertNotNull(instance.getVariableConnectee());
        
        // Var existe
        String var = "var1";
        instance.ajoutVariable(var);
        assertTrue(instance.contientVariable(var));
    }

    /**
     * Test of contientVariable method, of class ClassCohesionCalculator.
     */
    @Test
    public void testContainsVariable()
    {
        System.out.println("contientVariable");
        
        // Existe pas
        String var = "testnegatif";
        
        boolean expResult = false;
        boolean result = instance.contientVariable(var);
        assertEquals(expResult, result);
        
        // Existe
        var = "var1";
        expResult = true;
        assertEquals(expResult, instance.contientVariable(var));
    }

    /**
     * Test of setCurrentMethod method, of class ClassCohesionCalculator.
     */
    @Test
    public void testSetCurrentMethod()
    {
        System.out.println("setCurrentMethod");
        
        NameExpr method = new NameExpr("method1");
        instance.setMethodeCourante(method);
        assertEquals(method, instance.getMethodeCourante());
        expectedSize++;
        assertEquals(expectedSize, instance.getNbMethode());
    }

    /**
     * Test of addMethodConnection method, of class ClassCohesionCalculator.
     */
    @Test
    public void testAddMethodConnection()
    {
        System.out.println("addMethodConnection");
        
        NameExpr method = new NameExpr("method1");
        instance.setMethodeCourante(method);    
        expectedSize++;
        
        String var = "var1";
        instance.ajoutMethodeConnection(var);
        assertNotNull(instance.getMethodeCourante());
        assertTrue(instance.getVariableConnectee().containsKey(var));
        assertNotNull(instance.getVariableConnectee().get(var));
        
        var = "tstss";
        assertFalse(instance.getVariableConnectee().containsKey(var));
    }

    /**
     * Test of printVariables method, of class ClassCohesionCalculator.
     */
    @Test
    public void testPrintVariables()
    {
        System.out.println("printVariables");
        assertNotNull(instance.getVariableConnectee());
        assertNotNull(instance.getNbMethode());
        assertEquals(expectedSize, instance.getNbMethode());
    }

    /**
     * Test of calculateTCC method, of class ClassCohesionCalculator.
     */
    @Test
    public void testCalculateTCC()
    {
        System.out.println("calculateTCC");
        assertTrue(instance.getNbMethode() != 0);
        instance.calculTCC();  
        
        // Vérification de la valeur
        assertTrue(instance.getMetrique() <= 1.0 && instance.getMetrique() >= 0.0);
    }    
}
