import java.awt.image.BufferedImage; 
import javax.imageio.ImageIO;
import java.io.File;

class RightArrow extends MovingObject{
  
  RightArrow(int x,int y,int height,int length){
    super(x,y,length,height);
  }
  
  RightArrow(int y){
    super(490,y,100,100);
    loadSprite();
  }
  
  
  public void loadSprite(){
    try{
      setSprite(ImageIO.read(new File("rightarrow.png")));
    } catch(Exception e) { 
      System.out.println("error loading rightarrow sprite");
    }
  } 
  
}