import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Toolkit;

/**
* GameFrame.java
* Class main frame
* @author Tommy Wei, Bobby Yaan
* @version 7.0, June 12
*/

class GameFrame extends JFrame { 
  //Variables
  static GameArea gamePanel;
  
  //Constructor
  GameFrame(String filename) { 
    super(filename);
    
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(1200,800);
    this.setResizable(false);
    
    //Set up the game panel (where we put our graphics)
    gamePanel = new GameArea(filename);
    this.add(gamePanel);
    this.setFocusable(false);  //we will focus on the JPanel
    this.setVisible(true);
    
  } 

}