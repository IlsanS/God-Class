/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wmc;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author Kaoutare
 */
public class WMCAnalyseurIT {
    
    private WMCAnalyseur _analyseur;
    private IWMCMockAnalyseur _persistanceMock;
    
    //Données
    private int _metrique1 = 6;
    private int _metrique2 = 199;
    
//    public WMCAnalyseurIT() {
//    }
    
//    @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
    
    @Before
    public void setUp() 
    {
        this._analyseur = new WMCAnalyseur();
        
        //Création du mock
        this._persistanceMock = mock(IWMCMockAnalyseur.class);
        
        //Injection du mock 
        this._analyseur.setPersistance(_persistanceMock);
        
        
    }
    
    @Test
    public final void testResultatWMC1()
    {
        System.out.println("resultatWMC [résultat : pas une god class]");
        when(this._persistanceMock.resultatWMC(_metrique1)).thenReturn(true);
    }
    
    @Test
    public final void testResultatWMC2()
    {
        System.out.println("resultatWMC [résultat : god class]");
        when(this._persistanceMock.resultatWMC(_metrique2)).thenReturn(false);
    }
    
    @Test
    public final void testResultatWMC3()
    {
        System.out.println("resultatWMC [résultat : appel getMetrique ok]");
        
        this._analyseur.getResult(_metrique1);
        
        verify(this._persistanceMock).getMetrique();
    }
    
//    @After
//    public void tearDown() {
//    }

    /**
     * Test of analyseurWMC method, of class WMCAnalyseur.
     */
    @Test
    public void testAnalyseurWMCFaibleBorneInf() {
        System.out.println("analyseurWMC [résultat : wmc faible (borne inf)]");
        int metrique = 0;
        WMCAnalyseur instance = new WMCAnalyseur();
        String expResult = "Classe à complexité cyclomatique faible [+]";
        String result = instance.analyseurWMC(metrique);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testAnalyseurWMCFaibleBorneSup() {
        System.out.println("analyseurWMC [résultat : wmc faible (borne sup)]");
        int metrique = 10;
        WMCAnalyseur instance = new WMCAnalyseur();
        String expResult = "Classe à complexité cyclomatique faible [+]";
        String result = instance.analyseurWMC(metrique);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testAnalyseurWMCFaibleDansIntervalle() {
        System.out.println("analyseurWMC [résultat : wmc faible (dans interv)]");
        int metrique = 5;
        WMCAnalyseur instance = new WMCAnalyseur();
        String expResult = "Classe à complexité cyclomatique faible [+]";
        String result = instance.analyseurWMC(metrique);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testAnalyseurWMCMoyenBorneInf() {
        System.out.println("analyseurWMC [résultat : wmc moyen (borne inf)]");
        int metrique = 11;
        WMCAnalyseur instance = new WMCAnalyseur();
        String expResult = "Classe à complexité cyclomatique moyenne [+/-]";
        String result = instance.analyseurWMC(metrique);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testAnalyseurWMCMoyenBorneSup() {
        System.out.println("analyseurWMC [résultat : wmc moyen (borne sup)]");
        int metrique = 30;
        WMCAnalyseur instance = new WMCAnalyseur();
        String expResult = "Classe à complexité cyclomatique moyenne [+/-]";
        String result = instance.analyseurWMC(metrique);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testAnalyseurWMCMoyenBorneDansIntervalle() {
        System.out.println("analyseurWMC [résultat : wmc moyen (dans interv)]");
        int metrique = 30;
        WMCAnalyseur instance = new WMCAnalyseur();
        String expResult = "Classe à complexité cyclomatique moyenne [+/-]";
        String result = instance.analyseurWMC(metrique);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testAnalyseurWMCEleveeBorneInf() {
        System.out.println("analyseurWMC [résultat : wmc élevé (borne inf)]");
        int metrique = 31;
        WMCAnalyseur instance = new WMCAnalyseur();
        String expResult = "Classe à complexité cyclomatique élevée [-]";
        String result = instance.analyseurWMC(metrique);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testAnalyseurWMCEleveeDansIntervalle() {
        System.out.println("analyseurWMC [résultat : wmc élevé (dans interv)]");
        int metrique = 40;
        WMCAnalyseur instance = new WMCAnalyseur();
        String expResult = "Classe à complexité cyclomatique élevée [-]";
        String result = instance.analyseurWMC(metrique);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testAnalyseurWMCEleveeBorneElevee() {
        System.out.println("analyseurWMC [résultat : wmc élevé (borne élevée)]");
        int metrique = 100;
        WMCAnalyseur instance = new WMCAnalyseur();
        String expResult = "Classe à complexité cyclomatique élevée [-]";
        String result = instance.analyseurWMC(metrique);
        assertEquals(expResult, result);
    }
}
