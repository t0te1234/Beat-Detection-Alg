import java.awt.image.BufferedImage; 
import javax.imageio.ImageIO;
import java.io.File;

class DownArrow extends MovingObject{
  
  DownArrow(int x,int y,int height,int length){
    super(x,y,length,height);
  }
  
  DownArrow(int y){
    super(600,y,100,100);
    loadSprite();
  }
  
  
  public void loadSprite(){
    try{
      setSprite(ImageIO.read(new File("downarrow.png")));
    } catch(Exception e) { 
      System.out.println("error loading downarrow sprite");
    }
  } 
  
}