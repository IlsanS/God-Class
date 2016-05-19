/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCC;

import AFTD.MeusureEncapsulationAFTD;
import japa.parser.ast.Node;
import japa.parser.ast.body.FieldDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.VariableDeclarator;
import japa.parser.ast.expr.AssignExpr;
import japa.parser.ast.expr.NameExpr;
import japa.parser.ast.visitor.VoidVisitorAdapter;
import java.util.List;
import jdk.nashorn.internal.ir.visitor.NodeVisitor;


/**
 *
 * @author bobmastrolilli
 */
public class TCCVisitor extends VoidVisitorAdapter<TCCCalculator>
{
  

/****************************************************************************************************
                         DECLARATION DES VARIABLE MEMBRE
/****************************************************************************************************/ 
    
    public void visit(FieldDeclaration n,  TCCCalculator arg)
    {
        
        TCCCalculator calculator = (TCCCalculator)arg;
        List<VariableDeclarator> vars = n.getVariables();
        System.out.println("nombre vars = " + vars.size());
        vars.forEach(var->
        {
            System.out.println("Variable membre ajoutée " + var.toString() );
            calculator.addVariables(var.toString());
        });
    }

 /****************************************************************************************************
 DECLARATION DES METHODES : recuperation des paramètres et accès aux assignations pour chaque méthode
/****************************************************************************************************/ 
    public void visit(MethodDeclaration n, TCCCalculator arg)
    {
        System.out.println("################# " + n.getName() +" ###############################");
        TCCCalculator calculator = (TCCCalculator)arg;
        // On stocke la méthode analysée en cours dans le Calculator
        NameExpr nE = new NameExpr(n.getName());
        arg.setCurrentMethod(nE);
        // On visite toutes les expressions d'assignations de cette méthode
        super.visit(n, arg);
        System.out.println("################################################");
    }
    
    public void visit(AssignExpr exp,  TCCCalculator arg)
    {
        TCCCalculator calculator = (TCCCalculator)arg;
        
        System.out.println("Assignation analysée " + exp.getTarget() + " - " + exp.getValue());
        
        // Si l'assignation(à gauche) analysée est contenue dans la map des variables connectée alors on lie cette méthode à la variable.
        if (calculator.containsVariable(exp.getTarget().toString()))
        {
            calculator.addMethodConnection(exp.getTarget().toString());
        }
        // si l'assignation(à droite) ...
        else if (calculator.containsVariable(exp.getValue().toString()) && exp.getValue() != null)
        {
            calculator.addMethodConnection(exp.getValue().toString());
        }

        
    }
 
    

}
