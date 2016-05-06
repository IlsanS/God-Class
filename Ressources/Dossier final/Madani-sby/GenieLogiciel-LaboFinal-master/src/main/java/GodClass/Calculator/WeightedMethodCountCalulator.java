/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GodClass.Calculator;

/**
 *
 * @author Sebastien
 */
public class WeightedMethodCountCalulator
{
    private int methodCyclomaticComplexity;
    private int metric;

    public WeightedMethodCountCalulator()
    {
        this.metric = 0;
    }
    
    public void newMethodComplexity()
    {
        System.out.println("Nouvelle méthode");
        methodCyclomaticComplexity = 1;
    }
    
    public void addMethodComplexity()
    {
        this.metric += this.methodCyclomaticComplexity;
        System.out.println("Method interval " + metric);
    }
    
    public void incrementsComplexity()
    {
        this.methodCyclomaticComplexity++;
    }

    public int getMetric()
    {
        return metric;
    }

    public int getMethodCyclomaticComplexity()
    {
        return methodCyclomaticComplexity;
    }
}
