/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GodClass.Visitor;

import GodClass.Calculator.Calculator;
import GodClass.Calculator.WeightedMethodCountCalulator;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.stmt.SwitchEntryStmt;
import com.github.javaparser.ast.stmt.SwitchStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import java.util.List;

/**
 *
 * @author Sebastien
 */
public class SwitchVisitor extends VoidVisitorAdapter
{
    @Override
    public void visit(SwitchStmt n, Object arg)
    {
        Calculator calculator = (Calculator)arg;
        WeightedMethodCountCalulator wcc = calculator.getWcc();
        
        List<Node> nodes = n.getChildrenNodes();
        nodes.forEach(node->
        {
            if (node instanceof SwitchEntryStmt)
            {
                // Incréménte de 1
                wcc.incrementsComplexity();
                
                System.out.println("Case détecté");

                // Visite le reste du noeud
                SwitchEntryStmt entry = (SwitchEntryStmt)node;
                List<Node> children = entry.getChildrenNodes();
                NodeVisitor.visitNode(children, calculator);
            }
        });
    }
}
