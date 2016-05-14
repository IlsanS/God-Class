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
 */

public class MeusureEncapsulationAFTD 
{
    protected ATFDVisitor visitor;

    protected Set<String> classe_a_supprimees; //classe internes ou à supprimer
    protected Set<String> classes_externes;
    protected Set<String> type_inconnu;
    protected Map<String, Type> variable_membres;
    protected Map<String, Type> localVariables;
    private double metric;

    public MeusureEncapsulationAFTD()
    {
        
        this.visitor = new ATFDVisitor();
        this.classe_a_supprimees = new HashSet<>();
        this.classes_externes = new HashSet<>();
        this.type_inconnu = new HashSet<>();
        this.variable_membres = new HashMap<>();
        this.localVariables = new HashMap<>();
    }

  
    
    public void meusurer(CompilationUnit cu)
    {
        this.visitor.visit(cu, this);

    
        System.out.println("[ATFD] Retrait des classes suivantes : " + this.classe_a_supprimees);
        this.classes_externes.removeAll(this.classe_a_supprimees);

        // Calcule le métrique
        this.metric = (double)this.classes_externes.size();
    }

     public void reinitialiser()
    {
        this.classe_a_supprimees.clear();
        this.classes_externes.clear();
        this.type_inconnu.clear();
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
                            CLASSES INTERNES OU SOI MEME
/****************************************************************************************************/ 
    public void addClasseASupprimer(final String classeName)
    {
        this.classe_a_supprimees.add(classeName);
    }

    public Set<String> getClassesToRemove()
    {
        return this.classe_a_supprimees;
    }

        
  /****************************************************************************************************
                            CLASSES EXTERNE = METRIC
/****************************************************************************************************/ 
    public boolean addClasseExternes(final String className)
    {
        return this.classes_externes.add(className);
    }

    public Set<String> getExternalClasses()
    {
        return this.classes_externes;
    }


    public boolean addVariableParAccesseur(final String variableName)
    {
        //récupere le type de la variable //recherche parmis les var local et les var globales
        Type classType = this.recherche_type(variableName);

        // Si le type est trouvé, on la met dans classe externe
        if (classType != null)
            return this.addClasseExternes(classType.toString());

  
        return false;
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
    public void addLocalVariable(final String variable, final Type type)
    {
        this.localVariables.put(variable, type);
    }

    public void addLocalVariables(final List<Parameter> variables)
    {
        for(Parameter variable : variables)
            this.addLocalVariable(variable.getId().getName(), variable.getType());
    }

    public void addLocalVariables(final List<VariableDeclarator> variables, final Type type)
    {
        for(VariableDeclarator variable : variables)
            this.addLocalVariable(variable.getId().toString(), type);
    }

    public void clearLocalVariables()
    {
        this.localVariables.clear();
    }

  
  
}
