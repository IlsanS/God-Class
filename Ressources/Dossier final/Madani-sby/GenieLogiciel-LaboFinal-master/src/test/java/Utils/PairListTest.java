/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import com.github.javaparser.ast.expr.NameExpr;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sebastien
 */
public class PairListTest
{
    
    private static int expectedSize;
    private static PairList testList;
    
    @BeforeClass
    public static void setUpClass()
    {
        expectedSize = 10;
        testList = new PairList();
        
        for (int i = 0; i < 10; i++)
        {
            testList.addPair(new NameExpr((i%2==0) ? "x" : "y"), new NameExpr("test"+i));
            System.out.println(new NameExpr((i%2==0) ? "x" : "y") + " - "+ new NameExpr("test"+i));
        } 
    }
    
    @AfterClass
    public static void tearDownClass()
    {
        
    }

    /**
     * Test of addPair method, of class PairList.
     */
    @Test
    public void testAddPair()
    {
        System.out.println("addPair");
        
        // Vérifie taille
        assertNotNull(testList);
        assertEquals(10, testList.getSize());
        
        // Ajout élément valide
        testList.addPair(new NameExpr("z"), new NameExpr("test9"));
        expectedSize++;
        assertEquals(expectedSize, testList.getSize());
        
        // Ajout élement non valide
        testList.addPair(new NameExpr("x"), new NameExpr("test2"));
        assertEquals(expectedSize, testList.getSize());
    }

    /**
     * Test of getSize method, of class PairList.
     */
    @Test
    public void testGetSize()
    {
        System.out.println("getSize");
        assertNotNull(testList);
        assertEquals(expectedSize, testList.getSize());
    }

    
    /**
     * Test of print method, of class PairList.
     */
    @Test
    public void testPrint()
    {
        System.out.println("print");
        assertNotNull(testList);
        assertEquals(expectedSize, testList.getSize());
    }
    
}
