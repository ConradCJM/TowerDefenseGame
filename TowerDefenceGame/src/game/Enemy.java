//Shaheer Amin 
//Date: Jan 11th 
//A class that contains all the fields and represents an enemy, it has their speed, their hp, the enemy type, and the level. This class will help us keep track of all the fields of the enemy. 
package game;

public class Enemy{

  //Fields
  private int type;
  private int speed; //Turns to move 1 tile
  private int hp;
  private int level;

  private int x; 
  private int y; 
  private int turnsWaited;
  private String sprite;

  //Mutator
  public void setType(int t){
    if (t>3||t<1){
      throw new IllegalArgumentException("Type must range from 1-3");
    }
    type = t;

    assignStats();
  }

  //Mutator
  public void setLevel(int l){
    if(l<1){
      throw new IllegalArgumentException("Level must be greater than 0");
    }
    level = l;
  }

  //Accessor
  public int getType(){
    return this.type;
  }

  //Accessor
  public int getSpeed(){
    return this.speed;
  }

  //Accessor
  public int getHp(){
    return this.hp;
  }

  //Overridder
  //Randomly assigns enemy type based on level 
  public Enemy(){
    this.level = 1;
    this.type = (int)((Math.random()*3)+1); 
    assignStats();
    x = 7;
    y = (int) (Math.random()*5);
  }

  //OverLoader
  public Enemy(int t,int l){
    if (t>3||t<1){
      this.type = (int)((Math.random()*3)+1);
    }
    else{
      this.type = t;
    }
    if (l<1){
      this.level = 1;
    }
    else{
      this.level = l;
    }
    assignStats();
    x = 7;
    y = (int) (Math.random()*5);
  }

  //update enemy based on type
  private void assignStats(){
    //Check type of Enemy
    //Fast
    if (type==1){
      sprite = "f";
      speed = 1; 
      turnsWaited = 1;
      hp = 2+(level*2)+((level/2)*2);
    }
    //Medium
    else if (type==2){
      sprite = "m"; 
      speed = 2; 
      turnsWaited = 2;
      hp = 7+(level*2)+((level/3)*3);
    }
    //Slow
    else if (type==3){
      sprite = "s"; 
      speed = 3;
      turnsWaited = 3;
      hp = 12+(level*4)+((level/2)*6);
    }
  }

  
  //Accessor for sprite 
  public String getSprite(){
    return sprite; 
  }

  //Mutator for hp
  public void setHp(int h){
    hp = h; 
  }

  //Mutator for x Coordinate
  public void setX(int coord){
    x = coord; 
  }

  //Accessor for x coordinate
  public int getX(){
    return x; 
  }

  //accessor for y coordinate
  public int getY(){
    return y; 
  }

  //Mutator for turns waited
  public void setTurnsWaited(int t){
    turnsWaited = t; 
  }

  //Accessor for turns waited
  public int getTurnsWaited(){
    return turnsWaited; 
  }
}
