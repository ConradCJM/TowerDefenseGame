// CJ Mejia
// Date: Jan 11, 2024
// A class for attacktowers, this class contains all the fields for an attack tower including the cost to upgrade, the level, damage per attack, and which row the tower is in
package game.towers.attacktowers;


public class AttackTower{

  //Fields
  private int cost;
  private int level;
  private int damagePerAttack;
  private int row;
  private int cooldown;
  private boolean broken;
  private String sprite;

  //Mutator
  public void setRow(int r){

    //Check if row is valid
    if (r>0 && r<=5){
      this.row = r;
    }
    else{
      throw new IllegalArgumentException("Row must be 1-5");
    }
  }

  //Mutator
  public void setLevel(int l){

    //Check if level is valid
    if (l<0){
      throw new IllegalArgumentException("Level can't be negative");
    }
    level = l;
    updateStats();
  }

  //Accessor
  public int getLevel(){
    return this.level;
  }

  //Accessor
  public int getRow(){
    return row;
  }

  //Accessor
  public int getDamagePerAttack(){
    return this.damagePerAttack;
  }

  //Accessor
  public int getCost(){
    return this.cost;
  }

  //Overrider
  public AttackTower(){
    this.level=0;
    row = 1;
    updateStats();
  }

  //Overloader
  public AttackTower(int l,int r){

    //Check if row is valid
    if (r>0 && r<=5){
      this.row = r;
    }
    else
      this.row = 1;

    //Check if level is valid
    if (l<0){
      this.level=0;
    }
    else{
      this.level = l;
    }
    updateStats();
  }

  //Overloader
  public AttackTower(int r){

    //Check if row is valid
    if (r>0 && r<=5){
      this.row = r;
    }
    else
      this.row = 1;

    this.level = 0;

    updateStats();
  }

  //Update Stats
  private void updateStats(){
    damagePerAttack = (level)+((level/4));
    cost = 50+(50*(level/2))+(75*(level/4))+(100*(level/6));
    if (level<1){
      broken = true;
    }
    else{
      broken = false;
    }
  }

  //get cooldown
  public int getCooldown(){
    return cooldown;
  }

  //set cooldown
  public void setCooldown(int c){
    cooldown = c;
  }

  //get state of tower
  public boolean getState(){
    return broken;
  }

  //get sprite
  public String getSprite(){

    //Check if tower is broken or built
    if (broken){
      return "x";
    }
    return "T";
  }
}