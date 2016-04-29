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
public class GeneralCalculator
{
    private int methodCount;
    private int codeLines;

    public GeneralCalculator()
    {
        methodCount = 0;
        codeLines = 0;
    }
    
    public void methodIncrements()
    {
        methodCount++;
    }

    public void setCodeLines(int codeLines)
    {
        this.codeLines = codeLines;
    }

    public int getMethodCount()
    {
        return methodCount;
    }

    public int getCodeLines()
    {
        return codeLines;
    }
}
