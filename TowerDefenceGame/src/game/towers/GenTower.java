// CJ Mejia
// Date: Jan 11, 2024
// A class for generator towers that contains all of its fields including cost to upgrade, level, and cash per turn
package game.towers;

//GenTower Class; "Gen" short for "Generator"
public class GenTower{

  //Fields
  private int cost;
  private int level;
  private int cashPerTurn;

  //Mutator
  public void setLevel(int l){

    //check if level is valid
    if (l < 0){
      throw new IllegalArgumentException("Level can't be negative");
    }
    this.level = l;
    updateStats();
  }

  //Accessor
  public int getLevel(){
    return this.level;
  }

  //Accessor
  public int getCashPerTurn(){
    return this.cashPerTurn;
  }
  
  //Accessor
  public int getCost(){
    return this.cost;
  }

  //Method to calculate stats
  private void updateStats(){
    cost = 50+(100*(level/2))+(50*level)+(100*(level/4))+(200*(level/5));
    cashPerTurn = 50+(level*50)+(level/5*100)+((level/10)*150);
  }

  //Overrider
  public GenTower(){
    this.level = 0;
    updateStats();
  }

  //Overloader
  public GenTower(int l){

  //check if level is valid
    if (l<0){
      this.level = 0;
    }
    else{
      this.level = l;
    }
    updateStats();
  }

}