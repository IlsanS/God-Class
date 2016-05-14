/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AFTD;


import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.FieldDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.VariableDeclarator;
import japa.parser.ast.expr.Expression;
import japa.parser.ast.expr.FieldAccessExpr;
import japa.parser.ast.expr.MethodCallExpr;
import japa.parser.ast.expr.VariableDeclarationExpr;
import japa.parser.ast.visitor.VoidVisitorAdapter;
import java.util.List;

public class ATFDVisitor extends VoidVisitorAdapter<MeusureEncapsulationAFTD>
{

    public void visit(ClassOrInterfaceDeclaration classOrInterfaceDeclaration,
                      MeusureEncapsulationAFTD calculator)
    {
        // If it's a class
        if (!classOrInterfaceDeclaration.isInterface())
        {
            System.out.println("[ATFD] Nouvelle déclaration de classe = " +
                classOrInterfaceDeclaration.getName());

            calculator.addClasseASupprimer(classOrInterfaceDeclaration.getName());
        }

        super.visit(classOrInterfaceDeclaration, calculator);
    }

  /****************************************************************************************************
                         DECLARATION DES VARIABLE MEMBRE
/****************************************************************************************************/ 
    public void visit(FieldDeclaration fieldDeclaration, MeusureEncapsulationAFTD calculator)
    {
        List<VariableDeclarator> variables = fieldDeclaration.getVariables();

        for(VariableDeclarator var : variables)
            System.out.println("[ATFD] Nouvelle variable membre = " +
                fieldDeclaration.getType().toString() + " " + var.getId().getName());

        calculator.addMemberVariables(variables, fieldDeclaration.getType());

        super.visit(fieldDeclaration, calculator);
    }
  
 
 /****************************************************************************************************
                       DECLARATIONS DE VARIABLES LOCALES
/****************************************************************************************************/ 
    @Override
    public void visit(VariableDeclarationExpr variableDeclarationExpr, MeusureEncapsulationAFTD calculator)
    {
        System.out.println("[ATFD] Nouvelle(s) variable(s) locale(s) = " + variableDeclarationExpr.toString());

        calculator.addLocalVariables(variableDeclarationExpr.getVars(),
                                     variableDeclarationExpr.getType());

        super.visit(variableDeclarationExpr, calculator);
    }
/****************************************************************************************************
                         DECLARATION DES METHODES : recuperation des paramètres
/****************************************************************************************************/ 

    public void visit(MethodDeclaration methodDeclaration, MeusureEncapsulationAFTD calculator)
    {
        System.out.println("[ATFD] Nouvelle méthode = " + methodDeclaration.getName());

        if (!methodDeclaration.getParameters().isEmpty())
        {
            System.out.println("[ATFD] Sauvegarde des parametres");
            calculator.addLocalVariables(methodDeclaration.getParameters());
        }

        super.visit(methodDeclaration, calculator);

        // Clear variables local to the method
        calculator.clearLocalVariables();
    }



 /****************************************************************************************************
                       APPELS DES METHODES
/****************************************************************************************************/ 
    @Override
    public void visit(MethodCallExpr methodCallExpr, MeusureEncapsulationAFTD calculator)
    {
        Expression scope = methodCallExpr.getScope();

        // scope is null or egals to "this" if it's a member method call
        if (scope != null && !scope.toString().startsWith("this"))
        {
            String methodName = methodCallExpr.getName().toUpperCase();

            if (methodName.startsWith("GET") || methodName.startsWith("SET"))
            {
                System.out.println("[ATFD] Nouvel appel d'un accesseur = " + methodCallExpr.toString());
                calculator.addVariableParAccesseur(scope.toString());
            }
        }

        super.visit(methodCallExpr, calculator);
    }
}
