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
import com.github.javaparser.ast.stmt.DoStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.ForeachStmt;
import com.github.javaparser.ast.stmt.WhileStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import java.util.List;

/**
 *
 * @author Sebastien
 */
public class LoopVisitor extends VoidVisitorAdapter
{
    @Override
    public void visit(ForStmt n, Object arg)
    {
        Calculator calculator = (Calculator)arg;
        WeightedMethodCountCalulator wcc = calculator.getWcc();
        
        // Test de la condition
        if (n.getCompare() instanceof BinaryExpr)
            wcc.incrementsComplexity();
        
        wcc.incrementsComplexity();
        
        List<Node> nodes = n.getBody().getChildrenNodes();
        NodeVisitor.visitNode(nodes, calculator);
    }

    @Override
    public void visit(WhileStmt n, Object arg)
    {
        Calculator calculator = (Calculator)arg;
        WeightedMethodCountCalulator wcc = calculator.getWcc();
        
        // Test de la condition
        if (n.getCondition() instanceof BinaryExpr)
            wcc.incrementsComplexity();
        
        wcc.incrementsComplexity();
        
        List<Node> nodes = n.getBody().getChildrenNodes();
        NodeVisitor.visitNode(nodes, calculator);
    }

    @Override
    public void visit(ForeachStmt n, Object arg)
    {
        Calculator calculator = (Calculator)arg;
        WeightedMethodCountCalulator wcc = calculator.getWcc();

        wcc.incrementsComplexity();
        
        List<Node> nodes = n.getBody().getChildrenNodes();
        NodeVisitor.visitNode(nodes, calculator);
    }  

    @Override
    public void visit(DoStmt n, Object arg)
    {
        Calculator calculator = (Calculator)arg;
        WeightedMethodCountCalulator wcc = calculator.getWcc();
        
        // Test de la condition
        if (n.getCondition() instanceof BinaryExpr)
            wcc.incrementsComplexity();
        
        wcc.incrementsComplexity();
        
        List<Node> nodes = n.getBody().getChildrenNodes();
        NodeVisitor.visitNode(nodes, calculator);
    }
    
}
