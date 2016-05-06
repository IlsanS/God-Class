package GodClass;

/**
 *
 * @author Sebastien
 */
public class GodClassAnalyser
{
    public String analyseCohesion(double metric)
    {
        if (metric >= 0.0 && metric < 0.4)
            return "Mauvaise cohésion";
        else if (metric >= 0.4 && metric < 0.8)
            return "Cohésion moyenne";
        else 
            return "Bonne cohésion";
    }
    
    public String analyseForeignDataAccess(int metric)
    {
        if (metric >= 0 && metric < 3)
            return "Bonne accessibilité";
        else if (metric >= 3 && metric < 5)
            return "Beaucoup de classes accédées";
        else
            return "Trop de classes accédées";
    }
    
    public String analyseWeightedMethodCound(int metric)
    {
        if (metric >= 0 && metric < 25)
            return "Faible complexité";
        else if (metric >= 25 && metric < 47)
            return "Complexité moyenne";
        else
            return "Haute complexité";
    }
}
