// CJ Mejia & Shaheer Amin
// Date: Jan 11, 2024
// A tower defence game called fortress guard that has a login/signup page, a main menu, a level selector, and a highscore saving system
import java.util.*;
import java.io.*;
import game.Board;




class Main {
  public static void main(String[] args) {


    //Array List of boards - Shaheer
    ArrayList <Board> games = new ArrayList<Board>();

    // Scanner
    Scanner in = new Scanner(System.in);

    // Variables - Shaheer And Cj 
    String choice,levelChoice="", choice2, user="", filename, pass = "", conPass=".", userChoice="", userChoice2 = "" ,confirm;
    int loginAttempts,level=-1;
    boolean logged,running;


    //BIG INTRO TITLE PAGE - CJ
    System.out.println("███████╗░█████╗░██████╗░████████╗██████╗░███████╗░██████╗░██████╗  ░██████╗░██╗░░░██╗░█████╗░██████╗░██████╗░");
    System.out.println("██╔════╝██╔══██╗██╔══██╗╚══██╔══╝██╔══██╗██╔════╝██╔════╝██╔════╝  ██╔════╝░██║░░░██║██╔══██╗██╔══██╗██╔══██╗");
    System.out.println("█████╗░░██║░░██║██████╔╝░░░██║░░░██████╔╝█████╗░░╚█████╗░╚█████╗░  ██║░░██╗░██║░░░██║███████║██████╔╝██║░░██║");
    System.out.println("██╔══╝░░██║░░██║██╔══██╗░░░██║░░░██╔══██╗██╔══╝░░░╚═══██╗░╚═══██╗  ██║░░╚██╗██║░░░██║██╔══██║██╔══██╗██║░░██║");
    System.out.println("██║░░░░░╚█████╔╝██║░░██║░░░██║░░░██║░░██║███████╗██████╔╝██████╔╝  ╚██████╔╝╚██████╔╝██║░░██║██║░░██║██████╔╝");
    System.out.println("╚═╝░░░░░░╚════╝░╚═╝░░╚═╝░░░╚═╝░░░╚═╝░░╚═╝╚══════╝╚═════╝░╚═════╝░  ░╚═════╝░░╚═════╝░╚═╝░░╚═╝╚═╝░░╚═╝╚═════╝░");
    System.out.println("\n\n\t\t\t\t\t\t\t\t\t\t---------------------------\n\t\t\t\t\t\t\t\t\t\t|Press \"Enter\" to continue|\n\t\t\t\t\t\t\t\t\t\t---------------------------");
    in.nextLine();
    
    
                       
    // Gameplay loop
    do{

      // Logout from menu loop
      do{

        // Login/Signup loop
        do {

          //LOGIN/Signup - CJ
          // Outputs
          clearScreen();
          System.out.println("------FORTRESS GUARD------\n\n");
          System.out.println("      -= Welcome =-      ");
    
          // Reset variables
          loginAttempts = 0;
          logged = false;
          running = false;
          confirm = "a";
    
          // choose Login/signup
          System.out.println("\n0. Quit Game\n\n1. Login\n\n2. Sign up\n");
          do {
            System.out.print("(#): ");
            choice = in.next();
          } while (!choice.matches("[012]?"));
    
          // Check if login
          if (choice.equals("1")) {

            // Title
            clearScreen();
            System.out.println("------FORTRESS GUARD------\n\n");

            // Login
            System.out.println("\n-- Login --");

            // Message
            System.out.println("\nEnter \"0\" to return to Selection page");

            // Loop user login page
            do { 
              // increase number of login attempts
              loginAttempts++;
    
              // Check if user exists
                System.out.println("\nEnter username: ");
                user = in.next();
    
    
              //Check if user went back to slection page
              if (!user.equals("0")) {
                System.out.println("Enter password: ");
                pass = in.next();
              }
    
              
              //Check if username exists
              if (!pass.equals("0") &&!user.equals("0")&& !userExists(user)){
                System.out.println("\nUser cant be found, please try again or try signing up!\n");
              }

              //check if password is correct
              if (!pass.equals("0") &&!user.equals("0")&&!checkLoginValid(user,pass)){
                System.out.println("Incorrect Password!");
              }
              
              //If the user reaches 3 attempts for logging in, then there will be a 5 second time out.
              if (!pass.equals("0") &&!user.equals("0")&&loginAttempts>=3){
                try{
                  System.out.println("Please wait a bit before trying again.\n");
                  for (int i = 5; i>=1; i--){
                    System.out.println(i);
                    Thread.sleep(1000); 
                    
                  }
                }catch(InterruptedException e){
                  System.out.println("System interrupted");
                }
              }
            } while (!pass.equals("0") && !user.equals("0") && !checkLoginValid(user, pass));

            //Check if user's login details are correct and check if user didn't skip
            if (!pass.equals("0") && !user.equals("0") && checkLoginValid(user, pass)){
              logged = true;
            }
    
          }
    
          // Check if signup
          else if (choice.equals("2")) {

            //Title
            clearScreen();
            System.out.println("------FORTRESS GUARD------\n\n");
    
            // Sign up
            System.out.println("\n-- Signup --");
            System.out.println("\n== Welcome New Player! ==");
    
            // Message
            System.out.println("\nEnter \"0\" to return to Selection page\n");
    
            // Creating username
            do {
              System.out.println("Create username: ");
              user = in.next();
    
              // invalid input message
              if (!user.equals("0") && (user.length() > 16 || user.length() < 3)) {
                System.out.println("Username must be between 3 and 16 in length\n");
              }
    
              // Invalid user message
              if (userExists(user)) {
                System.out.println("The name: \"" + user + "\" is not available\n");
              }
    
            } while (!user.equals("0") && (user.length() > 16 || user.length() < 3) || userExists(user));
    
            // Creating user password
            if (!user.equals("0")) {

              //Loop Entering password and confirming password
              do {

                //Loop Error check & validate password
                do {
                  System.out.println("Create password: ");
                  pass = in.next();
                } while (!pass.equals("0") && !checkPass(pass));
  
                //Check if user skipped to selection screen
                if (!pass.equals("0")){
                  
                  // Confirm password
                  System.out.println("Confirm password (retype): ");
                  conPass = in.next();

                  //Check if password and confirm password dont match
                  if (!conPass.equals("0") && !pass.equals(conPass)){
                    
                    System.out.println("Password does not match!\n");
                  }
                }
              } while (!conPass.equals("0") && !pass.equals("0") && !pass.equals(conPass));
    
              // Put user's details into file if user didnt return to selection page
              if (!user.equals("0") && !pass.equals("0") && !conPass.equals("0")) {

                //Try to print to file
                try {
                  FileWriter fw = new FileWriter("users.txt", true);
                  PrintWriter pw = new PrintWriter(fw);

                  //print to file
                  pw.println("u:" + user);
                  pw.println("p:" + pass);
                  pw.close();

                  //Catch IOException
                } catch (IOException e) {
                  System.out.println("Failed to write to file");
                }
                logged = true;
              }
            }
          }
          //Loop if user is not logged in && if user doesn't want to quit the game
        } while (!logged && !choice.equals("0"));

        //Check if user quit game
        if (!choice.equals("0")){
  
          // Create a file name for the user to store high scores
          filename = user.toUpperCase()+".txt";

          //Loop main menu
          do{


            //Main Menu - CJ
            clearScreen();
            System.out.println("------FORTRESS GUARD------\n\n");
            System.out.println("\n-=-=++ Main Menu ++=-=-\n\n0. Quit Game\n\n1. Logout\n\n2. Level Selector\n\n3. How to Play");
        
            //Check input
            do {
              System.out.print("\n(#): ");
              choice = in.next();
            } while (!choice.matches("[0123]?"));
        
            //Check if player selected how to play -CJ
            if(choice.equals("3")){
    
              //How to play - screen 1
              clearScreen();
              System.out.println("------FORTRESS GUARD------\n\n");
              System.out.println("-=-=+=+ How to Play +=+=-=-\n");
  
              // Info on how to play
              System.out.println(": Fortress Guard is a turn-based tower defence game\n");
              System.out.println(": You will need to strategically manage money to upgrade your towers in order to defend your base from an endless hoard of invaders\n");
              System.out.println(": Survive for as long as possible and try to set a new high score");
    
    
              //Pause until input
              System.out.println("\n\n---------------------------\n|Press \"Enter\" to continue|\n---------------------------");
              in.nextLine();
              in.nextLine();
    
              // How to play - screen 2
              //Title
              clearScreen();
              System.out.println("------FORTRESS GUARD------\n\n");
              System.out.println("-=-=+=+ How to Play +=+=-=-\n");
  
              //Instructions
              System.out.println(": Below is the game board\n");
              System.out.println(": On the left side of the field is your base\n");
              System.out.println(": Your base contains 1 generator tower and 5 broken attack towers, 1 attack tower per row\n");
    
              printGame();
    
              //Pause until input
              System.out.println("\n---------------------------\n|Press \"Enter\" to continue|\n---------------------------");
              in.nextLine();
              clearScreen();
  
              //How to play Screen 3
              //Title
              clearScreen();
              System.out.println("------FORTRESS GUARD------\n\n");
              System.out.println("-=-=+=+ How to Play +=+=-=-\n");
  
              //Instructions
              System.out.println(": Your stats are shown above the field\n");
              System.out.print(": These stats include your: \n\n1. Hp\n\n2. Score\n\n3. Difficulty/Level\n\n4. Your money");
              printGame();
  
              //Pause until input
              System.out.println("\n---------------------------\n|Press \"Enter\" to continue|\n---------------------------");
              in.nextLine();
              clearScreen();

              //How to play Screen 4
              //Title
              clearScreen();
              System.out.println("------FORTRESS GUARD------\n\n");
              System.out.println("-=-=+=+ How to Play +=+=-=-\n");

              //Instructions
              System.out.println(": Your options will look similar to this:");
              System.out.println("\n--OPTIONS--");
              System.out.println("1. Tower Upgrade Menu ");
              System.out.println("2. End Turn ");
              System.out.println("0. Quit to menu");

              System.out.println("\n: Selecting end turn will move to next turn, Ending a turn gives 10 score");
              System.out.println(": Selecting tower upgrade menu will open a new menu");

              //Pause until input
              System.out.println("\n---------------------------\n|Press \"Enter\" to continue|\n---------------------------");
              in.nextLine();
              clearScreen();

              //How to play Screen 5
              //Title
              clearScreen();
              System.out.println("------FORTRESS GUARD------\n\n");
              System.out.println("-=-=+=+ How to Play +=+=-=-\n");

              //Instructions
              System.out.println(": The upgrade menu looks like this:");
              System.out.println("\n-= UPGRADES =-\n");
              System.out.println("1. Atk Tower: 0 Dmg | LVL: 0 | $ 50");
              System.out.println("2. Atk Tower: 0 Dmg | LVL: 0 | $ 50");
              System.out.println("3. Atk Tower: 1 Dmg | LVL: 1 | $ 100");
              System.out.println("4. Atk Tower: 0 Dmg | LVL: 0 | $ 50");
              System.out.println("5. Atk Tower: 0 Dmg | LVL: 0 | $ 50");
              System.out.println("\n6. Gen Tower: $50/Turn | LVL: 0 | $ 50");
              System.out.println("0. Go back");

              System.out.println("\n: The 1-5 infront of the Attack tower represents the row");
              System.out.println(": Entering a number from 1-6 will upgrade the corresponding tower automatically");

              //Pause until input
              System.out.println("\n---------------------------\n|Press \"Enter\" to continue|\n---------------------------");
              in.nextLine();
              clearScreen();


              //How to play screen 6
              //Title
              clearScreen();
              System.out.println("------FORTRESS GUARD------\n\n");
              System.out.println("-=-=+=+ How to Play +=+=-=-\n");

              //Instructions
              System.out.println(": Your upgraded Attack towers will shoot projectiles at enemies\n");
              System.out.println(": This is your only way to prevent enemies from reaching your base\n");
              System.out.println(": If an enemy comes into contact with your base you will lose a life. If you run out of lives you lose!\n");
              System.out.print(": Further upgraded tower will do more damage");
              printGame();

              //Pause until input
              System.out.println("\n---------------------------\n|Press \"Enter\" to continue|\n---------------------------");
              in.nextLine();
              clearScreen();

              //How to play screen 7
              //Title
              clearScreen();
              System.out.println("------FORTRESS GUARD------\n\n");
              System.out.println("-=-=+=+ How to Play +=+=-=-\n");

              //Instructions
              System.out.println(": There are 3 types of enemies:\n");
              System.out.println(": \"f\" - \"Fasts\" - move every other turn, have the lowest hp");
              System.out.println(": \"m\" - \"Mediums\" - move every 2 turns, have medium hp");
              System.out.println(": \"s\" - \"Snails\" - move every 3 turns, have very high hp");

              System.out.println("\n: NOTE: Enemies will always move on the following turn that they spawned on");

              System.out.println(": Enemies have more hp the longer a game lasts, which is based on a hidden leveling system");

              System.out.println(": Defeating an Enemy gives 100 score");
              
              
              

              //Pause until input
              System.out.println("\n---------------------------\n|Press \"Enter\" to continue|\n---------------------------");
              in.nextLine();
              clearScreen();
              
            }//End how to play
            //Check if player picked level selector
            else if(choice.equals("2")){

              //Level selcetor - Shaheer
              do{
                clearScreen();
                System.out.println("------FORTRESS GUARD------\n\n");


                //Title
                System.out.println("----Level Selector----"); 

                //User access 
                System.out.println("\n0. Return to menu");

                System.out.println("\n1. Easy\n\t\n\t- User has 2 lives/hp");

                System.out.println("\n2. Normal\n\t\n\t- User has 1 life/hp"); 

                System.out.println("\n3. Hard\n\t\n\t- Enemies spawn with more hp\n\t- User has 1 life/hp"); 

                System.out.println("\n4. View Highscore"); 

                //User input 
                do{
                  System.out.print("\n\n(#): "); 
                  levelChoice = in.next(); 
                }while(!levelChoice.matches("[01234]?"));

                //If user chooses 1 
                if(levelChoice.equals("1")){
                  level = 1; 

                }//end 

                //If user chooses 2 
                else if(levelChoice.equals("2")){
                  level = 2; 
                }//end 

                //If user chooses 3 
                else if(levelChoice.equals("3")){
                  level = 3; 
                }//end 



                //If user chooses 4 
                else if(levelChoice.equals("4")){

                  //Outputs
                  clearScreen();
                  System.out.println("------FORTRESS GUARD------\n\n");
                  System.out.println("\n--HIGHSCORE--");

                  System.out.println("\nEasy - " + findHighscore(1, filename)); 
                  System.out.println("Normal - " + findHighscore(2, filename)); 
                  System.out.println("Hard - " + findHighscore(3, filename)); 

                  //Pause until input
                  System.out.println("\n\n---------------------------\n|Press \"Enter\" to continue|\n---------------------------");
                  in.nextLine();
                  in.nextLine();


                }//end 

                  //If user picks any of these options it will give them 2 options - Shaheer
                  if(levelChoice.matches("[123]?")){

                    //Outputs
                    clearScreen();
                    System.out.println("------FORTRESS GUARD------\n\n");

                    if(level == 1){
                      System.out.println("\n--EASY--");
                    }//End of if

                    else if(level == 2){
                      System.out.println("\n--NORMAL--");
                    }//End of else if

                    else if(level == 3){
                      System.out.println("\n--HARD--");
                    }//End of else if 

                    //User can either start the game or go back to level selector 
                    System.out.println("\nWould you like to: \n1. Go back to Level Selector \n2. Start Game");

                      //Gets the user input
                    do{
                      System.out.print("\n\n(#): "); 
                      choice2 = in.next(); 
                    }while(!choice2.matches("[12]?"));

                    if (choice2.equals("2")){
                      running = true;
                    }
                    else{
                      running = false;
                    }//End of level selector

                    //Clear games that were played
                    games.clear();
                    
                    //Create new game based on level if game is running
                    if (running){
                      games.add(new Board(level));
                    }

                    //reset variable
                    userChoice2 = "0";
                    
                    //Start of game - Shaheer
                    while(running && games.get(0).getIfAlive()){

                      //Clear Screen
                      clearScreen();

                      //Print game
                      System.out.println(games.get(0));

                      System.out.println("-----------------------------------");

                      //Check if user is in upgrade menu
                      if (userChoice2.equals("0")){
                        
                        //print options
                        System.out.println("\n--OPTIONS--"); 
                        System.out.println("\n1. Tower Upgrade Menu \n2. End Turn \n0. Quit to menu"); 
  
                        //check users choices
                        do{
                          System.out.print("\n\n(#): ");
                          userChoice = in.next(); 
                        }while(!userChoice.matches("[012]?"));

                        //check if user wants to quit
                        if(userChoice.equals("0")){
                          System.out.println("Are you sure? (Y/N): "); 

                          //check if user confirms quitting
                          do{
                            System.out.print("\n\n(): ");
                            confirm = in.next();
                          }while(!confirm.matches("[ynYN]?"));

                          //stops game
                          if(confirm.equalsIgnoreCase("Y")){
                            running = false; 
                            games.clear();
                          }

                          //keeps game running
                          else if(confirm.equalsIgnoreCase("N")){
                            running = true; 
                          }
                        }
                      }

                      //check if user wants to open upgrade menu
                      if(userChoice.equals("1")){
                        System.out.println("-= UPGRADES =-\n");

                        //output options - Shaheer
                        System.out.println("\n1. Atk Tower: "+games.get(0).getAtk(1)+" Dmg | LVL: "+games.get(0).getAtkLvl(1)+" | $ "+games.get(0).getAtkCost(1));
                        System.out.println("2. Atk Tower: "+games.get(0).getAtk(2)+" Dmg | LVL: "+games.get(0).getAtkLvl(2)+" | $ "+games.get(0).getAtkCost(2));
                        System.out.println("3. Atk Tower: "+games.get(0).getAtk(3)+" Dmg | LVL: "+games.get(0).getAtkLvl(3)+" | $ "+games.get(0).getAtkCost(3));
                        System.out.println("4. Atk Tower: "+games.get(0).getAtk(4)+" Dmg | LVL: "+games.get(0).getAtkLvl(4)+" | $ "+games.get(0).getAtkCost(4));
                        System.out.println("5. Atk Tower: "+games.get(0).getAtk(5)+" Dmg | LVL: "+games.get(0).getAtkLvl(5)+" | $ "+games.get(0).getAtkCost(5));
                        System.out.println("\n6. Gen Tower: $"+games.get(0).getCashPerTurn()+"/Turn | LVL: "+games.get(0).getGenLvl()+" | $ "+games.get(0).getGenCost());
                        System.out.println("0. Go back");

                        //error check
                        do{
                          System.out.print("\n\n(#): ");
                          userChoice2 = in.next(); 
                        }while(!userChoice2.matches("[0123456]?"));

                        //check if user upgraded a tower - Shaheer
                        if(userChoice2.equals("1")){
                          games.get(0).upgradeAtkTower(1);
                        }

                        else if(userChoice2.equals("2")){
                          games.get(0).upgradeAtkTower(2);
                        }

                        else if(userChoice2.equals("3")){
                          games.get(0).upgradeAtkTower(3);
                        }

                        else if(userChoice2.equals("4")){
                          games.get(0).upgradeAtkTower(4);
                        }

                        else if(userChoice2.equals("5")){
                          games.get(0).upgradeAtkTower(5);
                        }

                        else if(userChoice2.equals("6")){
                          games.get(0).upgradeGen();
                        }
                      }

                      //check if user ended turn
                      if (userChoice.equals("2")){
                        games.get(0).nextTurn();

                        //33% to level up enemies at end of turn
                        if (Math.random()<0.33){
                          games.get(0).setEnemyLvl(games.get(0).getEnemyLvl()+1);
                        }
                      }
                      
                    }
                    

                  }//End of if statement

                if (running && !confirm.matches("[yY]?")) {

                  //Gameover screen - Shaheer
                  //Clear Screen
                  clearScreen();

                  //Print game
                  System.out.println(games.get(0));

                  System.out.println("-----------------------------------");

                  //Print game over - CJ did big game over
                  System.out.println("\n\n");
                  System.out.println("██╗░░  ░██████╗░░█████╗░███╗░░░███╗███████╗  ░█████╗░██╗░░░██╗███████╗██████╗░██╗");
                  System.out.println("╚██╗░  ██╔════╝░██╔══██╗████╗░████║██╔════╝  ██╔══██╗██║░░░██║██╔════╝██╔══██╗██║");
                  System.out.println("░╚██╗  ██║░░██╗░███████║██╔████╔██║█████╗░░  ██║░░██║╚██╗░██╔╝█████╗░░██████╔╝██║");
                  System.out.println("░██╔╝  ██║░░╚██╗██╔══██║██║╚██╔╝██║██╔══╝░░  ██║░░██║░╚████╔╝░██╔══╝░░██╔══██╗╚═╝");
                  System.out.println("██╔╝░  ╚██████╔╝██║░░██║██║░╚═╝░██║███████╗  ╚█████╔╝░░╚██╔╝░░███████╗██║░░██║██╗");
                  System.out.println("╚═╝░░  ░╚═════╝░╚═╝░░╚═╝╚═╝░░░░░╚═╝╚══════╝  ░╚════╝░░░░╚═╝░░░╚══════╝╚═╝░░╚═╝╚═╝");
  

                  //Wait until next input - Shaheer
                  System.out.println("\n\n\t\t\t\t\t\t\t---------------------------\n\t\t\t\t\t\t\t|Press \"Enter\" to continue|\n\t\t\t\t\t\t\t---------------------------");
                  in.nextLine();
                  in.nextLine();

                  //reset game state to not running
                  running = false;

                  //Save score
                  saveScore(level,filename,games.get(0).getScore());
                  
                  
                }
                

                

              //as long as the user does not enter 0 the level will keep on looping
              }while(!levelChoice.equals("0")); //Shaheer
            }

            //Loop main menu
          }while(!choice.matches("[01]?"));

        

        //End check if user quit game
        }
      // Log out loop
     }while(!choice.matches("[01]?"));

      //End Game loop
    }while(!choice.equals("0"));

    //Goodbye message - CJ
    clearScreen();


    //Goodbye - CJ
    System.out.println("██╗░░  ░██████╗░░█████╗░░█████╗░██████╗░██████╗░██╗░░░██╗███████╗██╗");
    System.out.println("╚██╗░  ██╔════╝░██╔══██╗██╔══██╗██╔══██╗██╔══██╗╚██╗░██╔╝██╔════╝██║");
    System.out.println("░╚██╗  ██║░░██╗░██║░░██║██║░░██║██║░░██║██████╦╝░╚████╔╝░█████╗░░██║");
    System.out.println("░██╔╝  ██║░░╚██╗██║░░██║██║░░██║██║░░██║██╔══██╗░░╚██╔╝░░██╔══╝░░╚═╝");
    System.out.println("██╔╝░  ╚██████╔╝╚█████╔╝╚█████╔╝██████╔╝██████╦╝░░░██║░░░███████╗██╗");
    System.out.println("╚═╝░░  ░╚═════╝░░╚════╝░░╚════╝░╚═════╝░╚═════╝░░░░╚═╝░░░╚══════╝╚═╝");
    System.out.println("\n\n\n");

    //Thanks - CJ
    System.out.println("██╗░░██╗░░  ████████╗██╗░░██╗░█████╗░███╗░░██╗██╗░░██╗░██████╗");
    System.out.println("╚██╗░╚██╗░  ╚══██╔══╝██║░░██║██╔══██╗████╗░██║██║░██╔╝██╔════╝");
    System.out.println("░╚██╗░╚██╗  ░░░██║░░░███████║███████║██╔██╗██║█████═╝░╚█████╗░");
    System.out.println("░██╔╝░██╔╝  ░░░██║░░░██╔══██║██╔══██║██║╚████║██╔═██╗░░╚═══██╗");
    System.out.println("██╔╝░██╔╝░  ░░░██║░░░██║░░██║██║░░██║██║░╚███║██║░╚██╗██████╔╝");
    System.out.println("╚═╝░░╚═╝░░  ░░░╚═╝░░░╚═╝░░╚═╝╚═╝░░╚═╝╚═╝░░╚══╝╚═╝░░╚═╝╚═════╝░");

    //For playing - CJ
    System.out.println("██╗░░  ███████╗░█████╗░██████╗░  ██████╗░██╗░░░░░░█████╗░██╗░░░██╗██╗███╗░░██╗░██████╗░");
    System.out.println("╚██╗░  ██╔════╝██╔══██╗██╔══██╗  ██╔══██╗██║░░░░░██╔══██╗╚██╗░██╔╝██║████╗░██║██╔════╝░");
    System.out.println("░╚██╗  █████╗░░██║░░██║██████╔╝  ██████╔╝██║░░░░░███████║░╚████╔╝░██║██╔██╗██║██║░░██╗░");
    System.out.println("░██╔╝  ██╔══╝░░██║░░██║██╔══██╗  ██╔═══╝░██║░░░░░██╔══██║░░╚██╔╝░░██║██║╚████║██║░░╚██╗");
    System.out.println("██╔╝░  ██║░░░░░╚█████╔╝██║░░██║  ██║░░░░░███████╗██║░░██║░░░██║░░░██║██║░╚███║╚██████╔╝");
    System.out.println("╚═╝░░  ╚═╝░░░░░░╚════╝░╚═╝░░╚═╝  ╚═╝░░░░░╚══════╝╚═╝░░╚═╝░░░╚═╝░░░╚═╝╚═╝░░╚══╝░╚═════╝░");


    
  }//End main method

  // Method to validate login - CJ
  public static boolean checkLoginValid(String u, String p) {
    String fileIn;

    //Try to read file
    try {
      FileReader fr = new FileReader("users.txt");
      BufferedReader br = new BufferedReader(fr);

      //Read file
      fileIn = br.readLine();

      //Loop reading file until file empty
      while (fileIn != null) {

        // check usernames
        if (fileIn.substring(2).equalsIgnoreCase(u)) {

          //Input password
          fileIn = br.readLine();

          // Check password
          if (fileIn.substring(2).equalsIgnoreCase(p)) {
            return true;
          }
        }

        //Go to next line
        fileIn = br.readLine();

        // insure that password is not being check as a username by going to next line
        if (fileIn.substring(0, 2).equals("p:")) {
          fileIn = br.readLine();
        }
      }
      //Catch IOException
    } catch (IOException e) {
      System.out.println("Failed to read file \"users.txt\"");
    }
    return false;
  }

  // method to validate username - CJ
  public static boolean userExists(String u) {
    String fileU;

    // check if user already exists
    try {
      FileReader fr = new FileReader("users.txt");
      BufferedReader br = new BufferedReader(fr);

      fileU = br.readLine();

      // Loop to check if existing usernames are the same as user's username
      while (fileU != null) {

        // check usernames
        if (fileU.substring(2).equalsIgnoreCase(u)) {

          return true;
        }

        fileU = br.readLine();

        // insure that password is not being check as a username
        if (fileU.substring(0, 2).equals("p:")) {
          fileU = br.readLine();
        }
      }
    }
    // Catch IOException
    catch (IOException e) {
      System.out.println("Failed to read users, no saved users & passwords can be found");
    }
    return false;
  }

  //Method to check if password fits criteria - CJ
  public static boolean checkPass(String p) {
    int numOfCriteria = 0;
    final int size = p.length();
    boolean hasCap = false;
    boolean hasChar = false;
    boolean hasNum = false;

    // Check for uppercase letters
    for (int i = 0; i < size; i++) {

      //check if password has uppercase
      if (p.substring(i, i + 1).matches("[A-Z]+")) {
        hasCap=true;
        
        i = size; //exit loop
      }
    }

    // Check for numbers
    for (int i = 0; i < size; i++) {

      //check if password has numbers
      if (p.substring(i, i + 1).matches("^[0-9]+$")) {
        hasNum=true;
        
        i = size; // exit loop
      }
    }

    // Check for special characters
    for (int i = 0; i < size; i++) {

      //check if password contains special characters
      if (p.substring(i, i + 1).matches("[-`~!@#$%^&*()+_=|\\:;\"\'<>,./?{[}]]+")) {
        hasChar=true;
        
        i = size; //exit loop
      }
    }
    if(hasChar){
      numOfCriteria++;
    }
    if(hasCap){
      numOfCriteria++;
    }
    if(hasNum){
      numOfCriteria++;
    }

    // Check if password fits criteria
    if (numOfCriteria >= 2) {
      return true;
    }

    //print message if password is invalid
    System.out.println(
        "\nPassword must contain at least 2 of the following:\n\n1. An Uppercase Character\n2. A Number\n3. A Special Character\n");
    return false;
  }

  //Method to print display of game in tutorial - CJ
  public static void printGame() {
    System.out.println("\n\n\n");
    System.out.println("Hp: 1    Score: 1000    Difficulty: Easy    Money: $400");
    System.out.println("");
    System.out.println("|------------------------------------------|");
    System.out.println("|        x |   |   |   |   |   |   |   |   |");
    System.out.println("|          |-------------------------------|");
    System.out.println("|        x |   |   |   |   |   |   |   |   |");
    System.out.println("|   |=|    |-------------------------------|");
    System.out.println("|   |+|  T | > |   | > |   | E |   |   |   |");
    System.out.println("|   |=|    |-------------------------------|");
    System.out.println("|        x |   |   |   |   |   |   |   |   |");
    System.out.println("|          |-------------------------------|");
    System.out.println("|        x |   |   |   |   |   |   |   |   |");
    System.out.println("|------------------------------------------|");
  }

  //Clear screen method - CJ
  public static void clearScreen() {
      System.out.print("\033[H\033[2J");
      System.out.flush();
  } 


  
  //Method to read highscores - Shaheer  
  public static int findHighscore(int level, String filename){

    //local variables
    int max = 0;
    String line = "";
    final String ident; //short for identifier

    //assign identifier
    if (level == 1){
      ident = "e:";
    }
    else if (level == 2){
      ident = "n:";  
    }
    else{
      ident = "h:"; 
    }

    
    try{
      FileReader fr = new FileReader(filename);
      BufferedReader br = new BufferedReader(fr);

      line = br.readLine();

      while(line!=null){

        //check identifier
        if(line.substring(0,2).equals(ident)){

          //get score and check if its larger than max
          if (Integer.parseInt(line.substring(2))>max){

            //assign score to max
            max = Integer.parseInt(line.substring(2));
          }
        }
        line = br.readLine();
      } 
    //Throws an exception if the file is unreadable
    }catch(IOException e){

      
      System.out.print("");
    }//end of catch
    catch(NumberFormatException e){
      System.out.println("Failed to parse score");
    }

    return max;
  }

    //method that saves score in file - Shaheer
    public static void saveScore(int level, String filename,int score){

      //local variables
      final String ident; //short for identifier
      
      try{
        FileWriter fw = new FileWriter(filename,true);
        PrintWriter pw = new PrintWriter(fw);

        //assign identifier
        if (level == 1){
          ident = "e:";
        }
        else if (level == 2){
          ident = "n:";  
        }
        else{
          ident = "h:"; 
        }

        //print to file
        pw.println(ident+score);

        pw.close();
      }
      catch(IOException e){
        System.out.println("Failed to write to file");
      }
    }
}