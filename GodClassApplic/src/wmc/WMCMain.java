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

/**
 *
 * @author Kaoutare
 */
public class WMCMain 
{
    public static void main(String[] args) throws ParseException, IOException 
    {
        CompilationUnit cu = JavaParser.parse(new File("C:\\Users\\Kaoutare\\Documents\\GitHub\\God-Class\\Ressources\\Dossier final\\Madani-sby\\GenieLogiciel-LaboFinal-master\\src\\main\\java\\Test.java"));
        WMCCalculateur calculateur = new WMCCalculateur();
        calculateur.calcule(cu);
                   
        System.out.println("metrique :"+ calculateur.getMetrique());
        
    }
}
