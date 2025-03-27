// CJ Mejia
// Date: Jan 11, 2024
// A Class for attack towers projectiles, the classes fields include the x/y coordinates and the damage of the projectile
package game.towers.attacktowers;

public class Projectile{

  // fields
  private int xCoord;
  private int yCoord; 
  private int dmg;
  private final static String SPRITE = ">";

  // constructor
  public Projectile(){
    xCoord = 0; 
    yCoord = 0; 
    dmg = 0; 
  }

  //Overloader 
  public Projectile(AttackTower a){
    xCoord = 0;
    yCoord = a.getRow()-1;
    dmg = a.getDamagePerAttack();
  }

  //Set x coord
  public void setX(int x){
    this.xCoord = x; 
  }
  
  //Get x coord
  public int getX(){
    return xCoord; 
  }

  //Get y coord
  public int getY(){
    return yCoord;
  }

  //Get Damage
  public int getDmg(){
    return dmg; 
  }

  //Get sprite
  public static String getSprite(){
    return SPRITE;
  }
  
}