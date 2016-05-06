/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GodClass.Visitor;

import GodClass.Calculator.Calculator;
import GodClass.Calculator.WeightedMethodCountCalulator;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import java.util.List;

/**
 *
 * @author Sebastien
 */
public class IfVisitor extends VoidVisitorAdapter
{
    @Override
    public void visit(IfStmt n, Object arg)
    {
        Calculator calculator = (Calculator)arg;
        WeightedMethodCountCalulator wcc = calculator.getWcc();

        // Test de la condition
        if (n.getCondition() instanceof BinaryExpr)
            wcc.incrementsComplexity();
            
        // Incréménte de 1
        wcc.incrementsComplexity();

        List<Node> nodes = n.getThenStmt().getChildrenNodes();
        NodeVisitor.visitNode(nodes, calculator);
        
        // Parcours du else
        if (n.getElseStmt() != null)
        {
            nodes = n.getElseStmt().getChildrenNodes();
            NodeVisitor.visitNode(nodes, calculator);
        }
    }
}
