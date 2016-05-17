/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wmc;

/**
 *
 * @author Kaoutare
 */
public class WMCAnalyseur 
{
    // Objet persistance qui sera lié par le principe d'injection de dépendance 
    private IWMCMockAnalyseur _persistance;
    private int _metrique;
    
    public WMCAnalyseur()
    {
        
    }
    
    public String analyseurWMC(int metrique)
    {
        if (metrique >= 0 && metrique <= 10)
        {
            return "Classe à complexité cyclomatique faible [+]";
        }
        else if (metrique >= 11 && metrique <= 30)
        {
            return "Classe à complexité cyclomatique moyenne [+/-]";
        }
        else
        {
            return "Classe à complexité cyclomatique élevée [-]";
        }
             
    }
    
    public boolean getResult(int metrique)
    {
        if(metrique >= 0 && metrique <= 10)
        {
            this._persistance.getMetrique();
        }
            
        return this._persistance.resultatWMC(metrique);
    }

    /**
     * @return the _persistance
     */
    public IWMCMockAnalyseur getPersistance() {
        return _persistance;
    }

    /**
     * @param _persistance the _persistance to set
     */
    public void setPersistance(IWMCMockAnalyseur _persistance) {
        this._persistance = _persistance;
    }

    /**
     * @return the _metrique
     */
    public int getMetrique() {
        return _metrique;
    }

    /**
     * @param _metrique the _metrique to set
     */
    public void setMetrique(int _metrique) {
        this._metrique = _metrique;
    }
}
