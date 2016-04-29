/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import com.github.javaparser.ast.expr.NameExpr;

/**
 *
 * @author Sebastien
 * @param <K>
 * @param <V>
 */
public class Pair<K extends NameExpr, V extends NameExpr>
{
    private final K element0;
    private final V element1;

    public static <K, V> Pair<NameExpr, NameExpr> createPair(NameExpr element0, NameExpr element1)
    {
        return new Pair<>(element0, element1);
    }

    public Pair(K element0, V element1)
    {
        this.element0 = element0;
        this.element1 = element1;
    }

    public K getElement0()
    {
        return element0;
    }

    public V getElement1()
    {
        return element1;
    }
    
    public boolean equalsPair(Pair p)
    {
        if (this.element0.getName().equals(p.getElement0().getName()) 
                && this.element1.getName().equals(p.getElement1().getName()))
            return true;
        
        else if (this.element0.getName().equals(p.getElement1().getName()) 
                && this.element1.getName().equals(p.getElement0().getName()))
            return true;
        
        return false;
    }

}
