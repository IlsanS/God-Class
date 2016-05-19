/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCC;

import AFTD.MeusureEncapsulationAFTD;
import japa.parser.JavaParser;
import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author bobmastrolilli
 */
public class Main 
{
    public static void main(String[] args) throws ParseException, IOException {
    
        
//        CompilationUnit cu=JavaParser.parse(new File("C:\\Users\\PC\\Documents\\GitHub\\God-Class\\GodClassApplic\\Test.java"));
        CompilationUnit cu = JavaParser.parse(new File("/Users/bobmastrolilli/NetBeansProjects/God-Class/GodClassApplic/test.java"));
           
        TCCCalculator m= new TCCCalculator();
        double b =m.Calcul(cu);
        m.CalculTCC();
        m.showCohesion();
        System.out.println("métric = " + m.getMetric());

        
    }
    
}
