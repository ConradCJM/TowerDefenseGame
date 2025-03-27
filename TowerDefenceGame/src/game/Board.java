//CJ Mejia
//Jan 17, 2024
//A class that has all the nessary fields and methods to make a tower defence game work
package game;
import java.util.*;
import game.*;
import game.towers.*;
import game.towers.attacktowers.*;


public class Board{

  //Level
  private int level;

  //Enemy level
  private int eLevel;

  //Attack towers
  private ArrayList <AttackTower> attackTowers; 

  // Enemies
  private ArrayList <Enemy> enemies; 

  //  Attack tower projectiles
  private ArrayList <Projectile> projectiles;

  // Generator tower
  private GenTower generator;

  // Player stats
  private Player player; 

  // Number of attack towers
  final private static int NUM_OF_ATTACK_TOWERS = 5;

  //Grid
  final private static int ROWS = 5;
  final private static int COLS = 8;
  private String[][] grid;

  //Score per turn
  final static int SCORE_PER_TURN = 10;

  //Score per kill
  final static int SCORE_PER_KILL = 100;

  

  //get enemy level
  public int getEnemyLvl(){
    return eLevel;
  }

  //Constructor overrider
  public Board(){

    //level
    level = 2; //normal

    //Create Grid
    grid = new String[ROWS][COLS];

    //Instantiate attacktowers arraylist
    attackTowers = new ArrayList<AttackTower>();

    //Instantiate projectiles arraylist
    projectiles = new ArrayList<Projectile>();

    //Instantiate enemies arraylist
    enemies = new ArrayList<Enemy>();

    //Generator tower
    generator = new GenTower();

    //Player
    player = new Player();

    //Enemy level
    eLevel = 0;

    //Assign row and create towers
    for (int i = 1; i<=NUM_OF_ATTACK_TOWERS;i++){
      attackTowers.add(new AttackTower(i));
    }

    //update grid
    updateGrid();
  }

  //Constructor overloader
  public Board(int l){

    //level
    if (1>l||l>3){
      level = 2;
    }
    else{
      level = l;
    }

    //Create Grid
    grid = new String[ROWS][COLS];

    //Instantiate attacktowers arraylist
    attackTowers = new ArrayList<AttackTower>();

    //Instantiate projectiles arraylist
    projectiles = new ArrayList<Projectile>();

    //Instantiate enemies arraylist
    enemies = new ArrayList<Enemy>();

    //Generator tower
    generator = new GenTower();

    //Player
    player = new Player();
    
    //l refers to level/difficulty 1= easy, 2=normal, 3=hard
    if (level==1){
      player.setHp(2);
    }

    //Assign row and create towers
    for (int i = 1; i<=NUM_OF_ATTACK_TOWERS;i++){
      attackTowers.add(new AttackTower(i));
    }

    //set enemies level higher if hard
    if (level == 3){
      eLevel = 3;
    }
    else{
      eLevel = 0;
    }


    //update grid
    updateGrid();
  }


  //Move Projectiles
  public void moveProjectiles(){

    //Loop moving projectiles
    for (Projectile p:projectiles){
      p.setX(p.getX()+1);
    }
  }

  //Move enemies
  public void moveEnemies(){

    //Loop moving enemies
    for (Enemy e:enemies){

      //Check if enemy has waited long enough before it can move
      if (e.getTurnsWaited() < e.getSpeed()){
        e.setTurnsWaited(e.getTurnsWaited()+1);
      }


      //Move enemy
      else{
        e.setX(e.getX()-1);
        e.setTurnsWaited(0);
      }
    }
  }

  //Shoot attacks
  public void shootAttacks(){

    //Loop creating new projectiles
    for (AttackTower a:attackTowers){

      //If cooldown of tower is 0 create new projectile && if tower is not broken
      if (a.getCooldown()==0&& !a.getState()){
        projectiles.add(new Projectile(a));

        //reset cooldown
        a.setCooldown(1);
      }

      // make tower shoot next turn
      else{
        a.setCooldown(0);
      }
    }
  }

  //update hp and remove enemy if enemy is on base
  public void enemyOnBase(){

    //loop through all enemies
    for (Enemy e:enemies){

      //check if enemy is on the base
      if (e.getX()==-1){

        //moves enemy to unplayable area    
        e.setX(-2);

        //lower hp by 1 if users hp is greater than 0
        if(player.getHp()>0){
          player.setHp(player.getHp()-1);
        }
      }
    }
  }

  //Damage an enemy if a projectile hits & increase score if enemy dies
  public void damageEnemy(){

    //loop through all projectiles
    for (Projectile p:projectiles){

      //Loop through all enemies
      for (Enemy e:enemies){

        //Check if y coords are the same
        if (p.getY()==e.getY()){

          //Check if x coords are the same
          if (p.getX()==e.getX()){

            //Damage enemy
            e.setHp(e.getHp()-p.getDmg());

            //move projectile out of game
            p.setX(10);

            //Remove enemy if hp<1
            if (e.getHp()<1){

              //move enemy out of game
              e.setX(-2);
              player.setScore(player.getScore()+SCORE_PER_KILL);
            }
          }
        }
      }
    }
  }

  // run all necessary methods, increase players score, and increase their money
  public void nextTurn(){
    player.setScore(player.getScore()+SCORE_PER_TURN);
    player.setMoney(player.getMoney()+generator.getCashPerTurn());

    moveProjectiles();
    shootAttacks();
    damageEnemy();
    moveEnemies();
    damageEnemy();
    enemyOnBase();
    createEnemy();
    updateGrid();
    clearInvalids();
  }

  // get cost for generator
  public int getGenCost(){
    return generator.getCost();
  }

  // get cost for attack tower
  public int getAtkCost(int t){

    // check if tower wanted to check is out of range
    if (1>t||t>5){
      throw new IllegalArgumentException("Tower must be within 1 and 5");
    }


    return attackTowers.get(t-1).getCost();
  }

  // upgrade gen tower
  public void upgradeGen(){

    //Check if player has enough money to upgrade tower
    if (player.getMoney()>=generator.getCost()){

      //reduce the players money 
      player.setMoney(player.getMoney()-generator.getCost());

      //increase level of generator tower
      generator.setLevel(generator.getLevel()+1);
    }
  }

  //upgrade atk tower
  public void upgradeAtkTower(int t){

    //Check if tower being selected is valid
    if (1>t||t>5){
      throw new IllegalArgumentException("Tower must be within 1 and 5");
    }


    //Check if player has enough money
    if (player.getMoney()>= attackTowers.get(t-1).getCost()){

      //reduce players money
      player.setMoney(player.getMoney()-attackTowers.get(t-1).getCost());

      //increase level of attack tower
      attackTowers.get(t-1).setLevel(attackTowers.get(t-1).getLevel() + 1);

    }
  }

  //Method that updates the grids info
  public void updateGrid(){

    //Loop through rows
    for (int i = 0; i<ROWS;i++){

      //Loop through cols
      for (int j = 0; j<COLS;j++){

        //Assign as empty space
        grid[i][j]=" ";

        //loop through projectiles
        for (Projectile p: projectiles){

          //check if y level is the same
          if (i==p.getY()){

            //check if x level is the same
            if (j==p.getX()){

              //assign as projectile
              grid[i][j]=Projectile.getSprite();
            }
          }
        }


        //loop through enemies
        for (Enemy e: enemies){

           //check if y level is the same
          if (i==e.getY()){

             //check if x level is the same
            if (j==e.getX()){
              grid[i][j]=e.getSprite();
            }
          }
        }
      }
    }

  }

  //to string
  public String toString(){
    String output = "";
    
    output+="HP: "+player.getHp();
    output+="\tScore: "+ player.getScore();
    output+="\tDifficulty: "+ levelToString();
    output+="\tMoney: $"+ player.getMoney()+"\n\n";
    output+="|------------------------------------------|\n";
    output+="|        "+rowToString(0);
    output+="\n|          |-------------------------------|\n";
    output+="|        "+rowToString(1);
    output+="\n|   |=|    |-------------------------------|\n";
    output+="|   |+|  "+rowToString(2);
    output+="\n|   |=|    |-------------------------------|\n";
    output+="|        "+rowToString(3);
    output+="\n|          |-------------------------------|\n";
    output+="|        "+rowToString(4);
    output+="\n|------------------------------------------|\n";

    return output;
  }

  private String rowToString(int r){
    String row = attackTowers.get(r).getSprite()+" |";

    //loop adding elements
    for (int i = 0; i<COLS;i++){
      row += " "+grid[r][i]+" |"; 
    }
    return row;
  }

  //level to string
  private String levelToString(){

    //Check level/difficulty
    if (level==1){
      return "Easy";
    }
    else if (level == 2) {
      return "Normal";
    }
    else{
      return "Hard";
    }
  }

  //create Enemy
  public void createEnemy(){

    //randomly create an enemy
    if (1==(int)(Math.random()*2)){
      enemies.add(new Enemy(4,eLevel));
    }
  }

  //set enemy level
  public void setEnemyLvl(int l){

    //check level
    if (l<0){
      throw new IllegalArgumentException("Enemy Level must be positive");
    }
    else{
      eLevel=l;
    }
  }

  //get if player is alive
  public boolean getIfAlive(){
    if (player.getHp()>0){
      return true;
    }
    return false;
  }

  //get if user can buy a tower upgrade
  public boolean getIfCanBuyAtk(int t){

    if (player.getMoney()>= attackTowers.get(t-1).getCost()){
      return true;
    }
    
    return false;
  }

  //get atk tower level
  public int getAtkLvl(int a){
    return attackTowers.get(a-1).getLevel();
  }

  //get gen tower level
  public int getGenLvl(){
    return generator.getLevel();
  }

  //get score
  public int getScore(){
    return player.getScore();
  }

  //get cash per turn
  public int getCashPerTurn(){
    return generator.getCashPerTurn();
  }

  //get atk per turn
  public int getAtk(int a){
    return attackTowers.get(a-1).getDamagePerAttack();
  }

  //remove invalid objects
  public void clearInvalids(){
    final int NUM_OF_PROJECTILES = projectiles.size();
    final int NUM_OF_ENEMIES = enemies.size();

    try{
      
      //loops through all of projectiles backwards
      for (int i = NUM_OF_PROJECTILES-1;i>=0;i--){
        if (projectiles.get(i).getX()>9){
          projectiles.remove(i);
        }
      }
  
      //loops through all of enemies backwards
      for (int i = NUM_OF_ENEMIES-1;i>=0;i--){
        if (enemies.get(i).getX()<0){
          enemies.remove(i);
        }
      }
    }
    catch(IndexOutOfBoundsException e){

      //User wont see this so think of this as an easter egg that game devs like to leave around their game files :)
      System.out.println(":)");
    }
  }
}

