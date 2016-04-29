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
public class Calculator
{
    private String                          finalName;
    private WeightedMethodCountCalulator    wcc;
    private GeneralCalculator               general;
    private ClassCohesionCalculator         classCohesion;
    private AccessToForeignDataCalculator   atfd;
    
    public Calculator()
    {
        this.finalName = null;
        this.general = new GeneralCalculator();
        this.wcc = new WeightedMethodCountCalulator();
        this.classCohesion = new ClassCohesionCalculator();
        this.atfd = new AccessToForeignDataCalculator();
    }

    public WeightedMethodCountCalulator getWcc()
    {
        return wcc;
    }

    public GeneralCalculator getGeneral()
    {
        return general;
    }

    public ClassCohesionCalculator getClassCohesion()
    {
        return classCohesion;
    }

    public AccessToForeignDataCalculator getAtfd()
    {
        return atfd;
    }
    
    public void setFinalName(String name)
    {
        this.finalName = name;
    }
    
    public String getFinalName()
    {
        return finalName;
    }
}
