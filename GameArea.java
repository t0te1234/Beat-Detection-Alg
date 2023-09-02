//Jpanel imports for graphics
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.io.File;
import java.awt.image.BufferedImage; 
import javax.imageio.ImageIO;
import javax.sound.sampled.*;

/**
* GameArea.java
* Class area panel of the game frame
* @author Tommy Wei, Bobby
* @version 7.0, June 12
*/

class GameArea extends JPanel {
  
  BeatMap map;
  boolean[] keys;
  BufferedImage sprite;
  
  //Constructor
  public GameArea(String filename) {
    
    map = new BeatMap(filename);
    loadSprite();
    //Pressed Keys
    keys = new boolean[4];
    
    //Action listeners
    ArrowListener keyListener = new ArrowListener(keys);
    this.addKeyListener(keyListener);
    
    //JPanel Stuff
    this.setFocusable(true);
    this.requestFocusInWindow(); 
    this.setSize(1200,800);
  }
  
  /**
   * update
   * Updates the game
   * @param void
   * @return void
   */
  public void update(double songTime) { 
    
    map.checkBeats(songTime);
    map.moveArrows();
    this.repaint();
  }
  
  /**
   * paintComponent
   * Refreshes the screen and updates all graphics
   * @param Graphics g to draw all sprites
   * @return void
   */
  @Override
  public void paintComponent(Graphics g) {   
    super.paintComponent(g); //required
    setDoubleBuffered(true); 
    
    //Draw Graphics here
    g.drawImage(sprite,0,0,1200,800,null);
    map.drawObjects(g);
    map.drawScore(g);
    
  }
  
   public void loadSprite(){
     
    try{
      sprite = ImageIO.read(new File("backdrop2.jpeg"));
    } catch(Exception e) { 
      System.out.println("error loading backdrop");
    }
  }
}
