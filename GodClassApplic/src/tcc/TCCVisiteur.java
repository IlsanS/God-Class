/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc;

import aftd.MeusureEncapsulationAFTD;
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
public class TCCVisiteur extends VoidVisitorAdapter<TCCCalculateur>
{
  

/****************************************************************************************************
                         DECLARATION DES VARIABLE MEMBRE
/****************************************************************************************************/ 
    
    public void visit(FieldDeclaration n,  TCCCalculateur arg)
    {
        
        TCCCalculateur calculator = (TCCCalculateur)arg;
        List<VariableDeclarator> vars = n.getVariables();
        System.out.println("nombre vars = " + vars.size());
        vars.forEach(var->
        {
            System.out.println("Variable membre ajoutée " + var.toString() );
            calculator.ajoutVariable(var.toString());
        });
    }

 /****************************************************************************************************
 DECLARATION DES METHODES : recuperation des paramètres et accès aux assignations pour chaque méthode
/****************************************************************************************************/ 
    public void visit(MethodDeclaration n, TCCCalculateur arg)
    {
        System.out.println("################# " + n.getName() +" ###############################");
        TCCCalculateur calculator = (TCCCalculateur)arg;
        // On stocke la méthode analysée en cours dans le Calculator
        NameExpr nE = new NameExpr(n.getName());
        arg.setMethodeCourante(nE);
        // On visite toutes les expressions d'assignations de cette méthode
        super.visit(n, arg);
        System.out.println("################################################");
    }
    
    public void visit(AssignExpr exp,  TCCCalculateur arg)
    {
        TCCCalculateur calculator = (TCCCalculateur)arg;
        
        System.out.println("Assignation analysée " + exp.getTarget() + " - " + exp.getValue());
        
        // Si l'assignation(à gauche) analysée est contenue dans la map des variables connectée alors on lie cette méthode à la variable.
        if (calculator.contientVariable(exp.getTarget().toString()))
        {
            calculator.ajoutMethodeConnection(exp.getTarget().toString());
        }
        // si l'assignation(à droite) ...
        else if (calculator.contientVariable(exp.getValue().toString()) && exp.getValue() != null)
        {
            calculator.ajoutMethodeConnection(exp.getValue().toString());
        }

        
    }
 
    

}
