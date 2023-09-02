import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.lang.Math;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

class BeatMap{
  
  //Variables
  public ArrayList<MovingObject> stationary = new ArrayList<MovingObject>();
  public ArrayList<MovingObject> objects = new ArrayList<MovingObject>();
  private String filename;
  private int score = 0;
  public Queue<Double> queue = new Queue<Double>();
  public Queue<Double> beatQueue = new Queue<Double>();
  private int lastArrowSent = 0;
  
  //For analyzing .wav files
  private int count = 0;    
  private int[] samples = new int[1024];
  private ArrayList<Boolean> beats = new ArrayList<Boolean>();
  private ArrayList<Double> beatTimes = new ArrayList<Double>();
  
  BeatMap(String filename){
    this.filename = filename; 
    
    //Initalize inital arrows
    UpArrow arrow = new UpArrow(100);
    DownArrow arrow2 = new DownArrow(100);
    LeftArrow arrow3 = new LeftArrow(100);
    RightArrow arrow4 = new RightArrow(100);
    stationary.add(arrow);
    stationary.add(arrow2);
    stationary.add(arrow3);
    stationary.add(arrow4);
    
    getTimes();
    
  }
  
  public boolean checkBeats(double songTime){
    System.out.println(songTime);
    if (beatQueue.getIndex(0) == null){
      return false;
    }
    
    if ((beatQueue.getIndex(0) - songTime - 1.00) < 0.1){
     
      System.out.println((beatQueue.getIndex(0) - songTime - 1.0));
      int rand = ((int)(Math.random() * 4) + 1);
      if (lastArrowSent == 0){
        lastArrowSent = rand;
        sendArrow(rand);
      }else if (rand  == lastArrowSent){
        while(rand == lastArrowSent){
          rand = ((int)(Math.random() * 4) + 1);
        }
        sendArrow(rand);
        lastArrowSent = rand;
      }else{
        sendArrow(rand);
        lastArrowSent = rand;
      }
      beatQueue.dequeue();
      return true;
    }
    return false;
  }
  
  public void sendArrow(int type){
    
    //up, right, down, left = 1, 2, 3, 4 respectively
    if(type == 1){
      objects.add(new UpArrow(800));
      return;
    }else if(type == 2){
      objects.add(new RightArrow(800));
      return;
    }else if(type == 3){
      objects.add(new DownArrow(800));
      return;
    }else if(type == 4){
      objects.add(new LeftArrow(800));
      return;
    }
    
  }
  
  public void moveArrows(){
    for (int i = 0; i < objects.size() ; i++){
      (objects.get(i)).setY(objects.get(i).getY() - 10);
      if (objects.get(i).getY() == 0){
        objects.remove(i);
      }
    }
  }
  
  public void drawObjects(Graphics g){
    for (int i = 0; i < stationary.size() ; i++){
      (stationary.get(i)).draw(g);
    }
    for (int i = 0; i < objects.size() ; i++){
      (objects.get(i)).draw(g);
    }
  }
  
  public void drawScore(Graphics g){
    g.drawString(Integer.toString(score),10,10);
  }
  
  public void setScore(int i){
    score = i;
  }
  
  public int getScore(){
    return score;
  }
  
  
    /**
     * getTimes
     * 
     * Gets the times of beats in a wav file
     * 
     * @return An array list filled with times at which a beat occurs
     */
    public void getTimes (){
      try {
        File file = new File(filename);
        WavFile wav = new WavFile(file);
            
        //Create array that holds the number of amplitudes in the file
        int[] amplitudes = new int[(int)wav.getFramesCount()];
            
        //Add each of the amplitudes to the file
        for (int i = 0; i < wav.getFramesCount(); i++) {
                
          //Account for random high jumps in amplitude
          if (wav.getSampleInt(i) + 5 > 65534){
            amplitudes[i] = 0;
           } else {
             amplitudes[i] = wav.getSampleInt(i);
           }
         }

         //Add the next 1024 amplitudes into an array and check for a beat
         for (int i = 0; i < amplitudes.length; i++){
           samples[count] = amplitudes[i];
           count++;
           if (count == 1024){
             //Add the beat (either true or false) into a array list
             beats.add(getBeat(samples));
             count = 0;
           }
         }

         //Get the how much time is in each beat
         double timePerBeat = wav.getDurationTime()/beats.size();
            
         //Add the times where the beat
         for (int i = 0; i < beats.size(); i++){
           if (beats.get(i)){
             if (i * timePerBeat > 3){
               beatQueue.enqueue(i * timePerBeat);
               i = i + 20;
             }
                    
           }
         }
        
      } catch (UnsupportedAudioFileException e){
        System.err.println("Unsupported Audio File");

      } catch (IOException e){
        System.err.println("File Not Found");

      }
    }
    
     
    /**
     * getBeat
     * 
     * A helper method for getTimes
     * Finds the beats in the song
     * @param samples the amplitudes of a part of the song
     * @return true or false, depending on whether or not that section of amplitudes is a beat
     */
    private boolean getBeat(int[] samples){
        double instantEnergy = getInstantEnergy(samples);
        double localAverageEnergy = getLocalAverageEnergy();
        double CONSTANT = 1.2;
        return instantEnergy > CONSTANT * localAverageEnergy;
    }

    /**
     * getInstantEnergy
     * 
     * A helper method for getBeat
     * Gets the sum of all the amplitudes in this section of the song
     * 
     * @param samples the amplitudes of a part of the song
     * @return the sum of the amplitudes
     */
    private double getInstantEnergy(int[] samples){
        double sum = 0;
        for (int i = 0 ; i < samples.length; i++){
            //sum = Math.pow(samples[i], 2) + sum;
            sum = samples[i] + sum;
            
        }
        queue.enqueue(sum);
        if (queue.size() > 43){
            queue.dequeue();
        }
        return sum;
    }

    /**
     * getLocalAverageEnergy
     * 
     * A helper method for getBeat
     * Gets the average off all the local sum of amplitudes
     * 
     * @return the average of all the local sum of amplitudes
     */
    private double getLocalAverageEnergy(){
        double sum = 0;
        for (int i = 0 ; i < queue.size(); i++){
            //sum = Math.pow(queue.getIndex(i), 2) + sum;
            sum = queue.getIndex(i) + sum;
        }
        
        return sum/queue.size();
        
    }
  
}