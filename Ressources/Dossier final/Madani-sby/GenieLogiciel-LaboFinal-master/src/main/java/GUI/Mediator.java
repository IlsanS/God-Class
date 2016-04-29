/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GodClass.GodClassAnalyser;
import GodClass.GodClassChecker;
import com.github.javaparser.ParseException;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author Sebastien
 */
public class Mediator
{
    private Gui parent;
    private GodClassChecker godClassChecker;

    public Mediator(Gui parent)
    {
        this.parent = parent;
    }
    
    public void init()
    {
        this.parent.setTitle("GodClass checker");
        this.parent.setLocationRelativeTo(null);
    }
    
    public void loadFile()
    {
        JFileChooser fileChooser = new JFileChooser(".");
        int ret = fileChooser.showOpenDialog(parent);
        
        if (ret == JFileChooser.APPROVE_OPTION)
        {
            try
            {
                File file = fileChooser.getSelectedFile();
                this.godClassChecker = new GodClassChecker();
                this.godClassChecker.loadFile(file);
            }
            catch (ParseException | IOException ex)
            {
                Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
        
    public void printResults()
    {
        // Affichage des métriques
        DecimalFormat df = new DecimalFormat("####0.00");
        String fileName = godClassChecker.getCalculator().getFinalName();
        String methodsCount = String.valueOf(godClassChecker.getCalculator().getGeneral().getMethodCount());
        String codesLine = String.valueOf(godClassChecker.getCalculator().getGeneral().getCodeLines());
        String wmc = String.valueOf(godClassChecker.getCalculator().getWcc().getMetric());
        String tcc = String.valueOf(df.format(godClassChecker.getCalculator().getClassCohesion().getMetric()));
        String atfd = String.valueOf(godClassChecker.getCalculator().getAtfd().getMetric());
        
        this.parent.getjLabelClassName().setText(fileName);
        this.parent.getjLabelMethodCount().setText(methodsCount);
        this.parent.getjLabelLinesCode().setText(codesLine);
        this.parent.getjLabelWMC().setText(wmc);
        this.parent.getjLabelTCC().setText(tcc);
        this.parent.getjLabelATFD().setText(atfd);
        
        // Affichage de l'analyse des métriques
        GodClassAnalyser analyser = new GodClassAnalyser();
        String wmcAnalyse = analyser.analyseWeightedMethodCound(godClassChecker.getCalculator().getWcc().getMetric());
        String atfdAnalyse = analyser.analyseForeignDataAccess(godClassChecker.getCalculator().getAtfd().getMetric());
        String cohesionAnalyse = analyser.analyseCohesion(godClassChecker.getCalculator().getClassCohesion().getMetric());
        
        this.parent.getjLabelATFDAnal().setText(atfdAnalyse);
        this.parent.getjLabelTCCAnal().setText(cohesionAnalyse);
        this.parent.getjLabelWMCAnal().setText(wmcAnalyse);
    }
}
