import java.awt.image.BufferedImage;
import java.awt.Graphics;

abstract class MovingObject{
  
  //Variables
  private int x;
  private int y;
  private int length;
  private int height;
  private BufferedImage sprite;
  private long timeCreated;
  
  //Constructor
  MovingObject(int x,int y,int length,int height){
    this.x = x;
    this.y = y;
    this.length = length;
    this.height = height;
    this.timeCreated = System.currentTimeMillis() / 1000;
  }
  
  /**
   * draw
   * Draws the object onto the jpanel
   * @param Graphics g to draw sprite
   * @return void
   */
  public void draw(Graphics g){
     g.drawImage(sprite,x,y,length,height,null);
  }
  
  public long getTimeCreated(){
    return this.timeCreated;
  }
  
  public int getX(){
    return this.x;
  }
  
  public int getY(){
    return this.y;
  }
  
  public int getLength(){
    return this.length;
  }
  
  public int getHeight(){
    return this.height;
  }
  
  public void setX(int x){
    this.x = x;
  }
  
  public void setY(int y){
    this.y = y;
  }
  
  public void setLength(int length){
    this.length = length;
  }
  
  public void setHeight(int height){
    this.height = height;
  }
  
  public void setSprite(BufferedImage sprite){
    this.sprite = sprite;
  }
  
  abstract void loadSprite();

  
}

