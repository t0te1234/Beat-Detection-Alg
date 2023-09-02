import java.io.File;
import javax.sound.sampled.*;

class MusicPlayer {
  
  Clip clip;
  
  MusicPlayer(String filename){
    try{
      File audioFile = new File(filename);
      AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
      DataLine.Info info = new DataLine.Info(Clip.class, audioStream.getFormat());
      clip = (Clip) AudioSystem.getLine(info);
      clip.open(audioStream);
    }catch(Exception e){
      e.printStackTrace();
    }
  }

  public void play(){
      
    clip.start();
  
  }
  
  public void stop(){
    
    clip.stop();
    
  }
}