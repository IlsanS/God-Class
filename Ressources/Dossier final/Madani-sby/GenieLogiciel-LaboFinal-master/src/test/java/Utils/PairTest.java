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
public class PairTest
{
    
    public PairTest()
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
     * Test of equalsPair method, of class Pair.
     */
    @Test
    public void testEqualsPair()
    {
        System.out.println("equals");
        Pair pair1 = Pair.createPair(new NameExpr("test1"), new NameExpr("test2"));
        Pair pair2 = Pair.createPair(new NameExpr("test1"), new NameExpr("test2"));
        
        assertTrue(pair1.equalsPair(pair2));
        
        pair2 = Pair.createPair(new NameExpr("test1"), new NameExpr("test3"));
        assertFalse(pair1.equalsPair(pair2));
    }
    
}
