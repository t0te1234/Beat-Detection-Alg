import java.awt.image.BufferedImage; 
import javax.imageio.ImageIO;
import java.io.File;

class LeftArrow extends MovingObject{
  
  LeftArrow(int x,int y,int height,int length){
    super(x,y,length,height);
  }
  
  LeftArrow(int y){
    super(720,y,100,100);
    loadSprite();
  }
  
  
  public void loadSprite(){
    try{
      setSprite(ImageIO.read(new File("leftarrow.png")));
    } catch(Exception e) { 
      System.out.println("error loading leftarrow sprite");
    }
  } 
  
}