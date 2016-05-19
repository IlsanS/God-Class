/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCC;

import japa.parser.ast.expr.NameExpr;
import java.util.ArrayList;

/**
 *
 * @author bobmastrolilli
 */
public class PairList
{
    private ArrayList<Pair<NameExpr, NameExpr>> pairs;

    public PairList()
    {
        pairs = new ArrayList<>();
    }
    
    public void addPair(NameExpr expr1, NameExpr expr2)
    {
        Pair pair = Pair.createPair(expr1, expr2);
        
        boolean found = false;
        for (Pair item : pairs)
        {
            if (item.equalsPair(pair))
            {
                System.out.println("element trouvé donc pas d'ajout");
                found = true;
            }
        }
        
        if (!found)
            pairs.add(pair);
    }
    
    public int getSize()
    {
        return pairs.size();
    }
    
    public void print()
    {
        
        System.out.println("Liste " + pairs.size());
        for (Pair pair : pairs)
        {
            System.out.println(pair.getElement0() + " - " + pair.getElement1());
        }
    }
}
