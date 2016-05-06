/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GodClass.Calculator;

import Utils.PairList;
import com.github.javaparser.ast.expr.NameExpr;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author Sebastien
 */
public class ClassCohesionCalculator
{
    private int methodCount;
    private double metric;
    private Map<String, Set<NameExpr>> connectedVariables;
    private NameExpr currentMethod;

    public ClassCohesionCalculator()
    {
        methodCount = 0;
        metric = 0.0;
        connectedVariables = new HashMap<>();
    }
    
    public void incrementsMethodCount()
    {
        methodCount++;
    }
    
    public void addVariables(String var)
    {
        connectedVariables.put(var, new HashSet<>());
    }
    
    public boolean containsVariable(String var)
    {
        return connectedVariables.containsKey(var);
    }
    
    public void setCurrentMethod(NameExpr method)
    {
        currentMethod = method;
        methodCount++;
    }
    
    public void addMethodConnection(String var)
    {
        if (currentMethod != null && connectedVariables.containsKey(var))
            connectedVariables.get(var).add(currentMethod);
    }
    
    public void printVariables()
    {
        System.out.println(connectedVariables);
        System.out.println("Nb mé " + methodCount);
    }
    
    public void calculateTCC()
    {
        // Nombre maximal de connexions
        int NP = (methodCount * (methodCount - 1)) / 2;
        
        // Nombre de connexions directes
        int NDC = calculateDirectConnections();
        System.out.println("NDC " + NDC);
        
        metric = (double)NDC/NP;
    }
    
    private int calculateDirectConnections()
    {
        PairList pairList = new PairList();
        
        // Double boucle pour parcourir les méthodes connectées
        for(Entry<String, Set<NameExpr>> entry1 : connectedVariables.entrySet())
        {
            for (NameExpr exp1 : entry1.getValue())
            {
                for (Entry<String, Set<NameExpr>> entry2 : connectedVariables.entrySet())
                {
                    for (NameExpr exp2 : entry2.getValue())
                    {
                        if (!exp1.equals(exp2))
                        {
                            // Connecté par la même variable
                            if (entry1.getKey().equals(entry2.getKey()))
                            {
                                pairList.addPair(exp1, exp2);
                            }
                            // Connecté par une autre variable
                            else if (entry1.getValue().contains(exp2))
                            {
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
        return metric;
    }

    public int getMethodCount()
    {
        return methodCount;
    }

    public Map<String, Set<NameExpr>> getConnectedVariables()
    {
        return connectedVariables;
    }

    public NameExpr getCurrentMethod()
    {
        return currentMethod;
    }
}
