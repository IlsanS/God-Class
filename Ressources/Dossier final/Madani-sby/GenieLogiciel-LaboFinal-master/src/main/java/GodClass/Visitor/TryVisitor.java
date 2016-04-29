/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GodClass.Visitor;

import GodClass.Calculator.Calculator;
import GodClass.Calculator.WeightedMethodCountCalulator;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.CatchClause;
import com.github.javaparser.ast.stmt.TryStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import java.util.List;

/**
 *
 * @author Sebastien
 */
public class TryVisitor extends VoidVisitorAdapter
{

    @Override
    public void visit(TryStmt n, Object arg)
    {
        
        Calculator calculator = (Calculator)arg;
        WeightedMethodCountCalulator wcc = calculator.getWcc();
        
        List<Node> nodes = n.getChildrenNodes();
        nodes.forEach(node->
        {
            if (node instanceof BlockStmt)
            {
                System.out.println("Block try catch");
                BlockStmt block = (BlockStmt)node;
                List<Node> children = block.getChildrenNodes();
                // Recherche des If , boucle, try, switch, ...
                NodeVisitor.visitNode(children, calculator);
            }
            else if (node instanceof CatchClause)
            {
                wcc.incrementsComplexity();
                System.out.println("Catch rencontré");
                CatchClause clause = (CatchClause)node;
                List<Node> children = clause.getChildrenNodes();
                NodeVisitor.visitNode(children, calculator);
            }
        }); 
    }
}
