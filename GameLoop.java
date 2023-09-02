import java.lang.InterruptedException;

public class GameLoop implements Runnable{
  
  final int FRAMES_PER_SECOND = 70;
  final int SKIP_TICKS = 1000 / FRAMES_PER_SECOND;
  private GameFrame gameFrame;
  private GameArea game;
  long next_game_tick = System.currentTimeMillis();
  long sleep_time = 0;
  long startTime;
  boolean win;
  
  //Constructor
  GameLoop(GameFrame gameFrame){
    this.gameFrame = gameFrame;
    this.game = this.gameFrame.gamePanel;
    startTime = System.currentTimeMillis();
    win = false;
  }
  
  public void run(){
            
    while(!win){
          
      game.update((System.currentTimeMillis()-startTime)/1000.0);
      checkKeys();
      
      //Sets Framerate 
      try{
        next_game_tick += SKIP_TICKS;
        sleep_time = next_game_tick - System.currentTimeMillis();

        if(sleep_time >= 0) {
          Thread.sleep(sleep_time);
        }
        
      }catch(InterruptedException e){
        System.out.println("Thread sleep error");
      }
      
      
    }//End of game condition
  }
  
  
  public void checkKeys(){
    
    if (game.keys[0] == true) {
      System.out.println("Up Pressed");
      
      for (int i=0;i<game.map.objects.size();i++){
        if(game.map.objects.get(i) instanceof UpArrow){
          //System.out.println((System.currentTimeMillis()/1000 - game.map.objects.get(i).getTimeCreated()));
          if ((System.currentTimeMillis()/1000 - game.map.objects.get(i).getTimeCreated()) == 1){
            game.map.setScore(game.map.getScore() + 1);
          }
          game.map.objects.remove(i);
        }
      }
      
      game.keys[0] = false;
    }
    if (game.keys[1] == true) {
      System.out.println("Right Pressed");
      
      for (int i=0;i<game.map.objects.size();i++){
        if(game.map.objects.get(i) instanceof RightArrow){
          //System.out.println((System.currentTimeMillis()/1000 - game.map.objects.get(i).getTimeCreated()));
          if ((System.currentTimeMillis()/1000 - game.map.objects.get(i).getTimeCreated()) == 1){
            game.map.setScore(game.map.getScore() + 1);
          }
          game.map.objects.remove(i);
        }
      }
      game.keys[1] = false;
    }
    if (game.keys[2] == true) {
      System.out.println("Down Pressed");
            
      for (int i=0;i<game.map.objects.size();i++){
        if(game.map.objects.get(i) instanceof DownArrow){
          //System.out.println((System.currentTimeMillis()/1000 - game.map.objects.get(i).getTimeCreated()));
          if ((System.currentTimeMillis()/1000 - game.map.objects.get(i).getTimeCreated()) == 1){
            game.map.setScore(game.map.getScore() + 1);
          }
          game.map.objects.remove(i);
        }
      }
      game.keys[2] = false;
    }
    if (game.keys[3] == true) {
      System.out.println("Left Pressed");
            
      for (int i=0;i<game.map.objects.size();i++){
        if(game.map.objects.get(i) instanceof LeftArrow){
          //System.out.println((System.currentTimeMillis()/1000 - game.map.objects.get(i).getTimeCreated()));
          if ((System.currentTimeMillis()/1000 - game.map.objects.get(i).getTimeCreated()) == 1){
            game.map.setScore(game.map.getScore() + 1);
          }
          game.map.objects.remove(i);
        }
      }
      game.keys[3] = false;
    }
  }  
  
}