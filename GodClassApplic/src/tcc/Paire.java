/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc;

import japa.parser.ast.expr.NameExpr;

/**
 *
 * @author bobmastrolilli
 */
public class Paire<K extends NameExpr, V extends NameExpr>
{
    private final K _element0;
    private final V _element1;

    public static <K, V> Paire<NameExpr, NameExpr> createPair(NameExpr element0, NameExpr element1)
    {
        return new Paire<>(element0, element1);
    }

    public Paire(K element0, V element1)
    {
        this._element0 = element0;
        this._element1 = element1;
    }

    public K getElement0()
    {
        return _element0;
    }

    public V getElement1()
    {
        return _element1;
    }
    
    public boolean estEgalPair(Paire p)
    {
        if(this._element0.getName().equals(p.getElement0().getName()) && this._element1.getName().equals(p.getElement1().getName()))
        {
            return true;
        }
        
        else if(this._element0.getName().equals(p.getElement1().getName()) && this._element1.getName().equals(p.getElement0().getName()))
        {
            return true;
        }
        
        return false;
    }

}