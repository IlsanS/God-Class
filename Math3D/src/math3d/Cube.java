/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
*/
package math3d;
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
 
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;
 
public class Cube extends Applet {
  private static final long serialVersionUID = -5353540872979294434L;
  private GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
  private Canvas3D canvas = new Canvas3D(config);
  private SimpleUniverse universe = new SimpleUniverse(canvas);
 
  //This will make a Colored cube
  private ColorCube cube = new ColorCube(0.2);//If you don't know Java try 0.1 to 
 
  /*
   * A group of objects like a cube and a ball must be placed in a BranchGroup or a
   * TransfromGroup. Even a transformGroup can be placed in a BranchGroup.
   * And BranchGroup can be placed in a TransformGroup.
   * Only a BranchGroup can be place in an Universe.
   * 
   * Remember each object can be placed in only 1 BranchGroup or 1 TranformGroup.
   * A BranchGroup or a TransformGroup can containt more then 1 objects.
   * BranchGroup an TransformGroup are object, like almost everting in Java.
   */
  private BranchGroup root = new BranchGroup();
 
 
 
  public void init(){
    this.setLayout(new BorderLayout());
    this.add("Center", canvas);
 
    //Here we add the ColorCube cube in the BranchGroup root
     this.root.addChild(this.cube);
 
    //here we add the BranchGroup  root in the SimplerUniverse universe
     this.universe.addBranchGraph(this.root);
 
    universe.getViewingPlatform().setNominalViewingTransform();
  }
}

    
    

