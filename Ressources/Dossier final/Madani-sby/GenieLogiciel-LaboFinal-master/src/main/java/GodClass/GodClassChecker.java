/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GodClass;

import GodClass.Calculator.Calculator;
import GodClass.Visitor.MethodVisitor;
import GodClass.Visitor.VariableVisitor;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Sebastien
 */
public class GodClassChecker
{
    private File        file;
    private Calculator  calculator;

    public GodClassChecker()
    {
        this.file = null;
        this.calculator = new Calculator();
    }
    
    public void loadFile(File file) throws ParseException, IOException
    {
        this.file = file;
        
        CompilationUnit parser = JavaParser.parse(this.file);
       
        new VariableVisitor().visit(parser, calculator);
        new MethodVisitor().visit(parser, calculator);  
        calculator.getClassCohesion().printVariables();
        
        // Calcul du TCC
        calculator.getClassCohesion().calculateTCC();
        
        // Nombre de lignes de code
        calculator.getGeneral().setCodeLines(parser.getEndLine());
        
        // Récupération du nom de fichier
        calculator.setFinalName(file.getName());
    }

    public Calculator getCalculator()
    {
        return calculator;
    }
}
