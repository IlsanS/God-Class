/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wmc;

import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.visitor.VoidVisitorAdapter;

/**
 *
 * @author Kaoutare
 */
public class WMCVisiteur extends VoidVisitorAdapter<WMCCalculateur>
{
    @Override
    public void visit(MethodDeclaration methode, WMCCalculateur calc)
    {
        System.out.println("\tDEBUT Méthode : "+ methode.getName());
        
        //Calcul du nombre de méthodes
        calc.incrementeMetrique();
        
        System.out.println("\tFIN Méthode : "+ methode.getName());
        System.out.println("\t-------------------------------------");
    }
}
