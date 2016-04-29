/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GodClass.Visitor;

import GodClass.Calculator.Calculator;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import java.util.List;

/**
 *
 * @author Sebastien
 */
public class VariableVisitor extends VoidVisitorAdapter
{

    @Override
    public void visit(FieldDeclaration n, Object arg)
    {
        Calculator calculator = (Calculator)arg;
        List<VariableDeclarator> vars = n.getVariables();
        
        vars.forEach(var->
        {
            calculator.getClassCohesion().addVariables(var.toString());
        });
    }

    @Override
    public void visit(AssignExpr n, Object arg)
    {
        Calculator calculator = (Calculator)arg;
        
        System.out.println("Méthode ajoutée " + n.getTarget() + " - " + n.getValue());
        
        // Ajout de la mÃ©thode Ã  la liste des mÃ©thodes utilisant cette variables
        if (calculator.getClassCohesion().containsVariable(n.getTarget().toString()))
            calculator.getClassCohesion().addMethodConnection(n.getTarget().toString());
        else if (calculator.getClassCohesion().containsVariable(n.getValue().toString()) && n.getValue() != null)
            calculator.getClassCohesion().addMethodConnection(n.getValue().toString());

        // TODO methodCallExpr
        // ex: Interger.parseIn(x);
        
        // Calcul du ATFD mÃ©trique
        System.out.println("Enfants de assignExpr ");
        n.getChildrenNodes().forEach(node->{
            System.out.println("node " + node + " Class " + node.getClass().getName());
            
            if (node instanceof FieldAccessExpr)
            {
                calculator.getAtfd().addClassAccessed(((FieldAccessExpr)node).getScope());
            }
        });
    }
    
    
}
