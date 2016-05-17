/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wmc;

import japa.parser.JavaParser;
import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;
import java.io.File;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kaoutare
 */
public class WMCCalculateurIT {

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
     * Test of calcule method, of class WMCCalculateur.
     */
    @Test(expected = NullPointerException.class)
    public void testCalcule() 
    {
        System.out.println("calcule nok");
        CompilationUnit cu = null;
        WMCCalculateur instance = new WMCCalculateur();
        instance.calcule(cu);
    }
    
    @Test
    public void testCalcule2() throws ParseException, IOException 
    {
        System.out.println("calcule ok");
        CompilationUnit cu = JavaParser.parse(new File("C:\\Users\\Kaoutare\\Documents\\GitHub\\God-Class\\Ressources\\Dossier final\\Madani-sby\\GenieLogiciel-LaboFinal-master\\src\\main\\java\\Test.java"));
        WMCCalculateur instance = new WMCCalculateur();
        instance.calcule(cu);
    }

    /**
     * Test of incrementeMetrique method, of class WMCCalculateur.
     */
    @Test
    public void testIncrementeMetrique() {
        System.out.println("incrementeMetrique");
        WMCCalculateur instance = new WMCCalculateur();
        instance.incrementeMetrique();
    }

    /**
     * Test of getMetrique method, of class WMCCalculateur.
     */
    @Test
    public void testGetMetrique() {
        System.out.println("getMetrique");
        WMCCalculateur instance = new WMCCalculateur();
        int expResult = 0;
        int result = instance.getMetrique();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMetrique method, of class WMCCalculateur.
     */
    @Test
    public void testSetMetrique() {
        System.out.println("setMetrique");
        int _metrique = 0;
        WMCCalculateur instance = new WMCCalculateur();
        instance.setMetrique(_metrique);
    }
    
}
