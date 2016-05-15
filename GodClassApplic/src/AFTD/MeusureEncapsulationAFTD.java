/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AFTD;

import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.Parameter;
import japa.parser.ast.body.VariableDeclarator;
import japa.parser.ast.type.Type;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 *
 * @author PC
 * Les methodes sont publique pour que le  visitor puisse y accéder.
 * Il rempli les map. 
 * But de cette classe : remplir les la liste class_accedees dont la taille = le metric.
 * le visitor va rechercher les "=" dont l'un des membre est un  acces par un  . ex String.valueOf.
 * Comme les acces par . peuvent venir de "super" ou "this", on va comparer les membre à la liste des variables
 * membres et locales pour être sûr.
 * 
 */

public class MeusureEncapsulationAFTD 
{
    protected ATFDVisitor visitor;
 
    protected Set<String> classe_accedees;
    protected Map<String, Type> variable_membres;
    protected Map<String, Type> localVariables;

    public MeusureEncapsulationAFTD()
    {
        this.visitor = new ATFDVisitor();
        this.classe_accedees = new HashSet<>();
        this.variable_membres = new HashMap<>();
        this.localVariables = new HashMap<>();
    }
  
    
    public double meusurer(CompilationUnit cu)
    {
        try
        {
             this.visitor.visit(cu, this);
        
        }
        catch(NullPointerException ex){ex.printStackTrace();}
       return  (double)this.classe_accedees.size();
    }

     public void reinitialiser()
    {
        this.classe_accedees.clear();
        this.variable_membres.clear();
        this.localVariables.clear();
    }


     
    public Type recherche_type(String variable)
    {
        // Parmis les variables locales
        
        for(Map.Entry<String, Type> variableType : this.localVariables.entrySet())
            if (variableType.getKey().equals(variable))
                return variableType.getValue();

        // Recherche parmis les variables membres
        
        for(Map.Entry<String, Type> variableType : this.variable_membres.entrySet())
            if (variableType.getKey().equals(variable))
                return variableType.getValue();

        //  Aucun type trouvé
        return null;
    }
    
 
  /****************************************************************************************************
                            CLASSES EXTERNE = METRIC
/****************************************************************************************************/ 
    public boolean addClasseAccedees(final String className)
    {
       if(recherche_type(className)!=null)
            return this.classe_accedees.add(className);
       else
            System.out.println("Acces à super");
       return false;
    }

    public Set<String> getClasseAccedees()
    {
        return this.classe_accedees;
    }


   /****************************************************************************************************
                            VARIABLE MEMBRE
/****************************************************************************************************/
    public void addMemberVariable(final String variable, final Type type)
    {
        this.variable_membres.put(variable, type);
    }

    public void addMemberVariables(final List<VariableDeclarator> variables, final Type type)
    {
        for(VariableDeclarator variable : variables)
            this.addMemberVariable(variable.toString(), type);
    }

 /****************************************************************************************************
                            VARIABLE LOCALEs
/****************************************************************************************************/
    public void ajoutVariableLocales(final String variable, final Type type)
    {
        this.localVariables.put(variable, type);
    }

    public void ajoutVariablesLocales(final List<Parameter> variables)
    {
        for(Parameter variable : variables)
            this.ajoutVariableLocales(variable.getId().getName(), variable.getType());
    }

    public void ajoutVariablesLocales(final List<VariableDeclarator> variables, final Type type)
    {
        for(VariableDeclarator variable : variables)
            this.ajoutVariableLocales(variable.getId().toString(), type);
    }

   

  
  
}
