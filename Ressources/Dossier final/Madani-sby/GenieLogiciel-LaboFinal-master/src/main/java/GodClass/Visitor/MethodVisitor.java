/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GodClass.Visitor;

import GodClass.Calculator.Calculator;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import java.util.List;

/**
 *
 * @author Sebastien
 */
public class MethodVisitor extends VoidVisitorAdapter
{
    @Override
    public void visit(MethodDeclaration n, Object arg)
    {
        System.out.println("################# " + n.getNameExpr() +" ###############################");
        Calculator calculator = (Calculator)arg;
        
        // Comptage des méthodes
        calculator.getGeneral().methodIncrements();
        
        // Méthode courrante pour le TCC
        calculator.getClassCohesion().setCurrentMethod(n.getNameExpr());
        
        // Nouveau calcul de métrique pour la méthode
        calculator.getWcc().newMethodComplexity();
        
        // Parcours des noeuds enfants
        List<Node> nodes = n.getBody().getChildrenNodes();
        NodeVisitor.visitNode(nodes, calculator);
        
        // Ajout du métrique de la méthode au total
        calculator.getWcc().addMethodComplexity();
        System.out.println("################################################");
    }
}
