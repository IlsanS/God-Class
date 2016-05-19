/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aftd;


import japa.parser.ast.body.FieldDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.VariableDeclarator;
import japa.parser.ast.expr.AssignExpr;
import japa.parser.ast.expr.FieldAccessExpr;
import japa.parser.ast.expr.VariableDeclarationExpr;
import japa.parser.ast.visitor.VoidVisitorAdapter;
import java.util.List;

public class ATFDVisiteur extends VoidVisitorAdapter<MeusureEncapsulationAFTD>
{
/****************************************************************************************************
                         DECLARATION DES VARIABLE MEMBRE
/****************************************************************************************************/ 
    
    
    public void visit(FieldDeclaration fieldDeclaration, MeusureEncapsulationAFTD m)
    {
        List<VariableDeclarator> variables = fieldDeclaration.getVariables();

        for(VariableDeclarator var : variables)
            System.out.println("var membre : " +
                fieldDeclaration.getType().toString() + " " + var.getId().getName());

        m.addMemberVariables(variables, fieldDeclaration.getType());

        //super.visit(fieldDeclaration, m);
    }
  
    
    
    
    
    
 
 /****************************************************************************************************
                       DECLARATIONS DE VARIABLES LOCALES
/****************************************************************************************************/ 
    @Override
    public void visit(VariableDeclarationExpr variableDeclarationExpr, MeusureEncapsulationAFTD m)
    {
        System.out.println("var locale : " + variableDeclarationExpr.toString());

        m.ajoutVariablesLocales(variableDeclarationExpr.getVars(),
                                     variableDeclarationExpr.getType());

       // super.visit(variableDeclarationExpr, m);
    }
    
    
    
    
    
/****************************************************************************************************
                         DECLARATION DES METHODES : recuperation des paramètres
/****************************************************************************************************/ 

    public void visit(MethodDeclaration methodDeclaration, MeusureEncapsulationAFTD m)
    {
        System.out.println("methode : " + methodDeclaration.getName());
        try {
            
   
        if (!methodDeclaration.getParameters().isEmpty())
        {
        
            m.ajoutVariablesLocales(methodDeclaration.getParameters());
        }
        } catch (NullPointerException e) {
            
        }
        super.visit(methodDeclaration, m);
    }
    
    
    
    
/****************************************************************************************************
                         DECLARATION DES METHODES : recuperation des paramètres
/****************************************************************************************************/ 
   
    @Override
    public void visit(AssignExpr n,  MeusureEncapsulationAFTD calculator)
    {
       System.out.println("égalité : "+ n.getTarget() +", "+ n.getValue());
            if (  n.getTarget()instanceof FieldAccessExpr )
                
            {   System.out.println("acces var par (.) ");

                calculator.addClasseAccedees(((FieldAccessExpr)  n.getTarget()).getScope().toString());
            }
            if ( n.getValue()instanceof FieldAccessExpr)
                
            {   System.out.println("acces var par (.)  ");         

                calculator.addClasseAccedees(((FieldAccessExpr)  n.getValue()).getScope().toString());
            }
         super.visit(n, calculator);
    }
}
