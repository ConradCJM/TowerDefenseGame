//Shaheer Amin 
//Date: Jan 11th 
//This is a class for the player, it represents everything a player can do, for example it has the players hp, money and score. It keeps track of these things for the player while the game is running. 





//CJ KEEP YOUR DISCORD OPEN JUST IN CASE I CALL YOU PUT A "/" IF YOU READ THIS /

package game;

public class Player{

  //Fields
  private int hp;
  private int money;
  private int score;

  //Mutator
  public void setHp(int h){
    if (h<0||h>2){
      throw new IllegalArgumentException("Hp must be within the range 0-2");
    }
    this.hp = h;
  }

  //Mutator
  public void setMoney(int m){
    if (m<0){
      throw new IllegalArgumentException("Money cannot be negative");
    }
    this.money = m;
  }

  //Mutator
  public void setScore(int s){
    if (s<0){
      throw new IllegalArgumentException("Score must not be negative");
    }
    this.score = s;
  }

  //Accessor
  public int getHp(){
    return this.hp;
  }

  //Accessor
  public int getMoney(){
    return this.money;
  }

  //Accessor
  public int getScore(){
    return this.score;
  }
  
  //Overrider
  public Player(){
    this.hp = 1;
    this.money = 0;
    this.score = 0;
  }
}