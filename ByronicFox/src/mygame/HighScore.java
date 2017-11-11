/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

/**
 *
 * @author Richard
 */
public class HighScore extends Score{

    private int score;
    
    public Score(){
      super();
    }
  
  public int updateHighScore(int score){
    if(this.score<score){
      this.score = score;
    }
  }
        
}
