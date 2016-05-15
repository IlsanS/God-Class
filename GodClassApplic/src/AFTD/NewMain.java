/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AFTD;

import japa.parser.ast.CompilationUnit;
import japa.parser.JavaParser;
import japa.parser.ParseException;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author PC
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException, IOException {
    
        
        CompilationUnit cu=JavaParser.parse(new File("C:\\Users\\PC\\Documents\\GitHub\\God-Class\\GodClassApplic\\Test.java"));
           MeusureEncapsulationAFTD m= new MeusureEncapsulationAFTD();
           double b = m.meusurer(cu);
                   
        System.out.println("metric :"+ b);
        
    }
    
}
