/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCC;

import AFTD.ATFDVisitor;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.expr.NameExpr;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author bobmastrolilli
 */
public class TCCCalculator 
{
    protected TCCVisitor _visiteur;
     private int _nbMethodes;
    private double _métrique;
    private Map<String, Set<NameExpr>> _VariableConnectee;
    private NameExpr _méthodeCourrante;

    public TCCCalculator()
    {
        this._visiteur = new TCCVisitor();
        _nbMethodes = 0;
        _métrique = 0.0;
        _VariableConnectee = new HashMap<>();
    }
    public double Calcul(CompilationUnit cu)
    {
        try
        {
             this._visiteur.visit(cu, this);
        
        }
        catch(NullPointerException ex){ex.printStackTrace();return 0;}
        
        System.out.println("(double)this.connectedVariables.size()" + (double)this._VariableConnectee.size());
        return  (double)this._VariableConnectee.size();
    }
    public void incrementsMethodCount()
    {
        _nbMethodes++;
    }
    
    public boolean addVariables(String var)
    {
        if(var !="" && var!=null)
        {
        _VariableConnectee.put(var, new HashSet<>());
        return true;
        }
        else
            return false;
        
    }
    
    public boolean containsVariable(String var)
    {
        return _VariableConnectee.containsKey(var);
    }
    
    public boolean setCurrentMethod(NameExpr method)
    {
        if(method != null && method.toString() != "")
        {
        _méthodeCourrante = method;
        _nbMethodes++;
        return true;
        }
        else
            return false;
    }
    
    public boolean addMethodConnection(String var)
    {
        if (_méthodeCourrante != null && _VariableConnectee.containsKey(var))
        {
            _VariableConnectee.get(var).add(_méthodeCourrante);
            return true;
        }
        else
            return false;
    }
    
    public void showCohesion()
    {
        System.out.println(_VariableConnectee);
        System.out.println("Nombre de méthodes :  " + _nbMethodes);
    }
    
    public double CalculTCC()
    {
        // Nombre maximal de connexions
        int NP = (_nbMethodes * (_nbMethodes - 1)) / 2;
        
        // Nombre de connexions directes
        int NDC = calculateDirectConnections();
        System.out.println("NDC " + NDC);
        
        _métrique = (double)NDC/NP;
        return _métrique;
    }
    
    private int calculateDirectConnections()
    {
        PairList pairList = new PairList();
        
        // Double boucle pour parcourir les méthodes connectées
        System.out.println("connectedVariables.entrySet() = "  + _VariableConnectee.entrySet());
        for(Map.Entry<String, Set<NameExpr>> entry1 : _VariableConnectee.entrySet())
        {
            for (NameExpr exp1 : entry1.getValue())
            {
                System.out.println("exp1 : " + exp1);
                for (Map.Entry<String, Set<NameExpr>> entry2 : _VariableConnectee.entrySet())
                {
                    for (NameExpr exp2 : entry2.getValue())
                    {
                        System.out.println("exp2 : " + exp2);
                        if (!exp1.equals(exp2))
                        {
                            // Connecté par la même variable
                            if (entry1.getKey().equals(entry2.getKey()))
                            {
                                System.out.println("connecté par la même variable");
                                pairList.addPair(exp1, exp2);
                            }
                            
                        }
                    }
                }
            }
        }
        
        pairList.print();
        
        return pairList.getSize();
    }

    public double getMetric()
    {
        return _métrique;
    }

    public int getMethodCount()
    {
        return _nbMethodes;
    }

    public Map<String, Set<NameExpr>> getConnectedVariables()
    {
        return _VariableConnectee;
    }

    public NameExpr getCurrentMethod()
    {
        return _méthodeCourrante;
    }
}

