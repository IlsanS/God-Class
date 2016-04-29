/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GodClass.Visitor;

import GodClass.Calculator.Calculator;
import GodClass.Calculator.WeightedMethodCountCalulator;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.stmt.DoStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.ForeachStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.SwitchStmt;
import com.github.javaparser.ast.stmt.TryStmt;
import com.github.javaparser.ast.stmt.WhileStmt;
import java.util.List;

/**
 *
 * @author Sebastien
 */
public class NodeVisitor
{
    public static void visitNode(List<Node> nodes, Calculator calculator)
    {        
        nodes.forEach(node ->
        {
            if (node instanceof ForStmt)
            {
                System.out.println("Boucle for détectée");
                new LoopVisitor().visit((ForStmt)node, calculator);
            }
            else if (node instanceof WhileStmt)
            {
                System.out.println("Boucle while détectée");
                new LoopVisitor().visit((WhileStmt)node, calculator);
            }
            else if (node instanceof ForeachStmt)
            {
                System.out.println("Boucle foreach détectée");
                new LoopVisitor().visit((ForeachStmt)node, calculator);
            }
            else if (node instanceof DoStmt)
            {
                System.out.println("Boucle do détectée");
                new LoopVisitor().visit((DoStmt)node, calculator);
            }
            else if (node instanceof SwitchStmt)
            {
                System.out.println("Switch détecté");
                new SwitchVisitor().visit((SwitchStmt)node, calculator);
            }
            else if (node instanceof IfStmt)
            {
                System.out.println("If détecté");
                new IfVisitor().visit((IfStmt)node, calculator);
            }
            else if (node instanceof TryStmt)
            {
                System.out.println("Block try détecté");
                new TryVisitor().visit((TryStmt)node, calculator);
            }
            else if (node instanceof Statement)
            {
                Statement stmt = (Statement)node;
                System.out.println("Stmt" + node + " - Type " + node.getClass().getName() );
                List<Node> children = stmt.getChildrenNodes();
                children.forEach(child->
                {
                    System.out.println("CHILD " + child + " - " + child.getClass().getName());
                    if (child instanceof AssignExpr)
                    {
                        new VariableVisitor().visit((AssignExpr)child, calculator);
                    }
                });
            }
            else
                System.out.println("Autre " + node + " - Type " + node.getClass().getName());
        });
    }
    
}
