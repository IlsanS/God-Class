/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AFTD;

import aftd.MeusureEncapsulationAFTD;
import japa.parser.JavaParser;
import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.Parameter;
import japa.parser.ast.body.VariableDeclarator;
import japa.parser.ast.type.Type;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import org.dummycreator.DummyCreator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 *
 * @author PC
 */
public class MeusureEncapsulationAFTDIT {
    
    public MeusureEncapsulationAFTDIT() {
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

 /****************************************************************************************************
                         TEST MESURER
/****************************************************************************************************/ 
  
    /**
     * RESULT NULL 
     */
    @Test
    public void testMeusurer() {
        System.out.println("meusurer_retour NULL");
        CompilationUnit cu = null;
        MeusureEncapsulationAFTD instance = new MeusureEncapsulationAFTD();
        double expResult = 0.0;
        double result = instance.meusurer(cu);
        assertEquals(expResult, result, 0.0);
        
    }

    /**
     * RESULT 2
     */
      @Test
    public void testMeusurer_2() throws ParseException, IOException {
        System.out.println("meusurer_retour 3");
        CompilationUnit cu  = JavaParser.parse(new File("C:\\Users\\PC\\Documents\\GitHub\\God-Class\\GodClassApplic\\test_AFTD.java"));
      
        MeusureEncapsulationAFTD instance = new MeusureEncapsulationAFTD();
        double expResult = 2;
        double result = instance.meusurer(cu);
        assertEquals(expResult, result, 0.0);
        
    }
    
    
  
 /****************************************************************************************************
                        TEST RECHERCHE_TYPE
/****************************************************************************************************/ 
  
    /**
      *  RETOUR NULL
     */
    @Test
    public void testRecherche_type() {
        System.out.println("recherche_type retour NULL");
        String variable = "";
        MeusureEncapsulationAFTD instance = new MeusureEncapsulationAFTD();
        Type expResult = null;
        Type result = instance.recherche_type(variable);
        assertEquals(expResult, result);
       
    }
    @Test
        
    /**
      *   RETOUR TRUE
     */
    public void testRecherche_type_2() {
        System.out.println("recherche_type retour NULL");
        MeusureEncapsulationAFTD instance = new MeusureEncapsulationAFTD();
        Type type= mock(japa.parser.ast.type.Type.class);
        instance.addMemberVariable("coco", type);
        String variable = "coco";
        Type result = instance.recherche_type(variable);
        assertNotNull(result);
       
    }
    
    
    
 /****************************************************************************************************
                        TEST ADD CLASS ACCEDEE
/****************************************************************************************************/ 
  
     /**
      *   RETOUR NULL
     */
    @Test
    public void testAddClasseAccedees() {
        System.out.println("addClasseAccedees");
        String className = "";
        MeusureEncapsulationAFTD instance = new MeusureEncapsulationAFTD();
  
        boolean result = instance.addClasseAccedees(className);
        assertFalse( result);
      
    }
  /**
      *   RETOUR True
     */
    @Test
    public void testAddClasseAccedees_2() {
        System.out.println("addClasseAccedees_");
        
        String className = "coco";
        MeusureEncapsulationAFTD instance = new MeusureEncapsulationAFTD();
        Type type= mock(japa.parser.ast.type.Type.class);
        instance.addMemberVariable("coco", type);
        boolean result= instance.addClasseAccedees(className);
        assertTrue(result);
      
    }
    
       @Test
    public void testAddClasseAccedees_3() {
        System.out.println("addClasseAccedees_");
        
        String className = "coco";
        MeusureEncapsulationAFTD instance = new MeusureEncapsulationAFTD();
        Type type= mock(japa.parser.ast.type.Type.class);
        instance.addMemberVariable("coco", type);
        instance.addClasseAccedees(className);
        double result =instance.getClasseAccedees().size();
        
        assertEquals(result,1,0);
      
    }


 
   
    
}
