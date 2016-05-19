/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc;

import japa.parser.ast.expr.NameExpr;
import java.util.ArrayList;

/**
 *
 * @author bobmastrolilli
 */
public class listPaire
{
    private ArrayList<Paire<NameExpr, NameExpr>> _pairs;

    public listPaire()
    {
        _pairs = new ArrayList<>();
    }
    public void AjoutPair(NameExpr expr1, NameExpr expr2)
    {
        Paire pair = Paire.createPair(expr1, expr2);
        
        boolean found = false;
        for (Paire item : _pairs)
        {
            if (item.estEgalPair(pair))
            {
                System.out.println("element trouvé donc pas d'ajout");
                found = true;
            }
        }
        if (!found)
            _pairs.add(pair);
    }
    
    public int getTaille()
    {
        return _pairs.size();
    }
    
    public void affiche()
    {
        
        System.out.println("Liste " + _pairs.size());
        for (Paire pair : _pairs)
        {
            System.out.println(pair.getElement0() + " - " + pair.getElement1());
        }
    }
}
