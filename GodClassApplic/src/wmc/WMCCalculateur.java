/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wmc;

import japa.parser.ast.CompilationUnit;

/**
 *
 * @author Kaoutare
 */
public class WMCCalculateur
{
    private int _metrique;
    private WMCVisiteur _visiteur;
    
    
    public WMCCalculateur()
    {
        this._metrique = 0;
        this._visiteur = new WMCVisiteur();
    }

    public void calcule(CompilationUnit cu)
    {
        this._visiteur.visit(cu, this);
    }

    public void incrementeMetrique()
    {
        this._metrique++;
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
