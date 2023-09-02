import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class ArrowListener implements KeyListener{
  
  boolean[] keys;
  
  
  ArrowListener(boolean[] keys){
    this.keys = keys;
  }
  
  @Override
  public void keyPressed(KeyEvent e){
    
    if(e.getKeyCode() == 38){
      //System.out.println("Up");
      keys[0] = true;
      
    }
    if(e.getKeyCode() == 37){
      //System.out.println("Left");
      keys[3] = true;
    }
    if(e.getKeyCode() == 39){
      //System.out.println("Right");
      keys[1] = true;
    }
    if(e.getKeyCode() == 40){
      //System.out.println("Down");
      keys[2] = true;
    }
    
  }
  
  @Override
  public void keyReleased(KeyEvent e){
  }
  
  @Override
  public void keyTyped(KeyEvent e){
  }
  
}