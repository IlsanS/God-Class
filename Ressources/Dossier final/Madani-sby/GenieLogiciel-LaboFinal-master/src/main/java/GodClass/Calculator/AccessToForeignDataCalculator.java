/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GodClass.Calculator;

import com.github.javaparser.ast.expr.Expression;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Sebastien
 */
public class AccessToForeignDataCalculator
{
    private Set<Expression> classAccessed;

    public AccessToForeignDataCalculator()
    {
        classAccessed = new HashSet<>();
    }
    
    public void addClassAccessed(Expression c)
    {
        classAccessed.add(c);
    }
    
    public int getMetric()
    {
        return classAccessed.size();
    }
}
