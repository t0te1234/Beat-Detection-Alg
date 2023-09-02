import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.image.BufferedImage; 
import javax.imageio.ImageIO;
import java.io.File;
import java.util.Scanner;

/* RhythmGame.java
 * 
 * Authors: Tommy Wei, Bobby Yan
 * 
 */ 

class RhythmGame{
  
  static JFrame frame;
  static BufferedImage sprite;
  
  public static void main(String[] args) { 
    
    StartingFrame startFrame = new StartingFrame();
    Scanner input = new Scanner(System.in);
    String filename = input.nextLine();
    
    frame.dispose();
        
    GameFrame game = new GameFrame(filename);
    MusicPlayer music = new MusicPlayer(filename);
    music.play();
    Runnable thread = new GameLoop(game);
    Thread t = new Thread(thread);
    t.start();
  }
  
  //Constructor
  public static class StartingFrame {
    StartingFrame() {
      frame = new JFrame("Harmony Hero");
      JPanel startPanel = new StartingPanel();
      frame.getContentPane().add(startPanel);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(500,500);
      frame.setUndecorated(false);
      frame.setVisible(true);
      frame.setResizable(false);
    }
    
    //Starting panel
    public static class StartingPanel extends JPanel {
      
      //Constructor
      StartingPanel(){
        loadSprite();
      }
      
        public void paintComponent(Graphics g) {          
          setDoubleBuffered(true);  
          g.drawImage(sprite,0,0,500,500,null);
          g.setFont(new Font("Impact", Font.BOLD, 56));
          g.drawString("Harmony Hero", 100,100);

      }
    }
  }
  
    public static void loadSprite(){
    try{
      sprite = ImageIO.read(new File("Backdrop.png"));
    } catch(Exception e) { 
      System.out.println("error loading backdrop");
    }
  }

}