/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc;


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
public class TCCCalculateur 
{
    protected TCCVisiteur _visiteur;
    private int _nbMethodes;
    private double _métrique;
    private Map<String, Set<NameExpr>> _variableConnectee;
    private NameExpr _méthodeCourrante;

    public TCCCalculateur()
    {
        this._visiteur = new TCCVisiteur();
        _nbMethodes = 0;
        _métrique = 0.0;
        _variableConnectee = new HashMap<>();
    }
    public double calcul(CompilationUnit cu)
    {
        try
        {
             this._visiteur.visit(cu, this);
        
        }
        catch(NullPointerException ex){ex.printStackTrace();return 0;}
        
        System.out.println("(double)this.connectedVariables.size()" + (double)this._variableConnectee.size());
        return  (double)this._variableConnectee.size();
    }
    public void incrémenteNbMethode()
    {
        _nbMethodes++;
    }
    
    public boolean ajoutVariable(String var)
    {
        if(var !="" && var!=null)
        {
        _variableConnectee.put(var, new HashSet<>());
        return true;
        }
        else
            return false;
        
    }
    
    public boolean contientVariable(String var)
    {
        return _variableConnectee.containsKey(var);
    }
    
    public boolean setMethodeCourante(NameExpr method)
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
    
    public boolean ajoutMethodeConnection(String var)
    {
        if (_méthodeCourrante != null && _variableConnectee.containsKey(var))
        {
            _variableConnectee.get(var).add(_méthodeCourrante);
            return true;
        }
        else
            return false;
    }
    
    public void afficheCohesion()
    {
        System.out.println(_variableConnectee);
        System.out.println("Nombre de méthodes :  " + _nbMethodes);
    }
    
    public double calculTCC()
    {
        // Nombre maximal de connexions
        int NP = (_nbMethodes * (_nbMethodes - 1)) / 2;
        
        // Nombre de connexions directes
        int NDC = calculeConnectionDirectes();
        System.out.println("NDC " + NDC);
        
        _métrique = (double)NDC/NP;
        return _métrique;
    }
    
    private int calculeConnectionDirectes()
    {
        listPaire pairList = new listPaire();
        
        // Double boucle pour parcourir les méthodes connectées
        System.out.println("connectedVariables.entrySet() = "  + _variableConnectee.entrySet());
        for(Map.Entry<String, Set<NameExpr>> entry1 : _variableConnectee.entrySet())
        {
            for (NameExpr exp1 : entry1.getValue())
            {
                System.out.println("exp1 : " + exp1);
                for (Map.Entry<String, Set<NameExpr>> entry2 : _variableConnectee.entrySet())
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
                                pairList.AjoutPair(exp1, exp2);
                            }
                            
                        }
                    }
                }
            }
        }
        
        pairList.affiche();
        
        return pairList.getTaille();
    }

    public double getMetrique()
    {
        return _métrique;
    }

    public int getNbMethode()
    {
        return _nbMethodes;
    }

    public Map<String, Set<NameExpr>> getVariableConnectee()
    {
        return _variableConnectee;
    }

    public NameExpr getMethodeCourante()
    {
        return _méthodeCourrante;
    }
}

