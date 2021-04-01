/*
 * Name: Kabir Bhakta  Student Number: 7900098
 * Purpose: This is our main class. We run our program here.
 */

import java.util.*;

public class Main
{
    //total number of players including human players
    private static int totalPlayers;

    public static void main(String[] args){
        //Arraylist of Players,Suspects cards, Weapons cards, location cards
        ArrayList<IPlayer> playerList = new ArrayList<IPlayer> ();
        ArrayList<Card> pplNames= new ArrayList<Card> ();
        ArrayList<Card> weaponsNames= new ArrayList<Card> ();
        ArrayList<Card> locationsNames= new ArrayList<Card> ();

        //Manually adding all the suspects,weapons and location cards
        //You can uncomment as many cards as you want in the game
        pplNames.add(new Card("Suspect","Miss Scarlet"));
        pplNames.add(new Card("Suspect","Mr Green"));
        pplNames.add(new Card("Suspect","Mrs Peacock"));
        pplNames.add(new Card("Suspect","Professor Plum"));
        pplNames.add(new Card("Suspect","Mrs White"));
        //pplNames.add(new Card("Suspect","Colonel Mustard"));

        weaponsNames.add(new Card("Weapon","Revolver"));
        weaponsNames.add(new Card("Weapon","Dagger"));
        weaponsNames.add(new Card("Weapon","Pipe"));
        weaponsNames.add(new Card("Weapon","Rope"));
        weaponsNames.add(new Card("Weapon","Candlestick"));
        weaponsNames.add(new Card("Weapon","Wrench"));
        weaponsNames.add(new Card("Weapon","Sword"));
        /*weaponsNames.add(new Card("Weapon","Belt"));
        weaponsNames.add(new Card("Weapon","COVID"));*/

        locationsNames.add(new Card("Location","Dining Room"));
        locationsNames.add(new Card("Location","Lounge"));
        locationsNames.add(new Card("Location","Kitchen"));
        locationsNames.add(new Card("Location","Study"));
        locationsNames.add(new Card("Location","Hall"));
        locationsNames.add(new Card("Location","Billiard Room"));
        locationsNames.add(new Card("Location","Conservatory"));
       /* locationsNames.add(new Card("Location","Library"));
        locationsNames.add(new Card("Location","Bedroom"));
        locationsNames.add(new Card("Location","Cellar"));
        locationsNames.add(new Card("Location","Ballroom"));*/

        //Take input from user about how many computer players does user wants to play with
        System.out.println("New Game Starts...");

        totalPlayers = -1;
        while( totalPlayers < 1 ){
            try{
                System.out.println("How Many Computer Opponents will be Playing: ");
                Scanner sc= new Scanner(System.in);
                totalPlayers = sc.nextInt()+1;   //total player is input+1(user himself)
            }
            catch(Exception e){
                System.out.println("Enter valid input.");
            }
        }
        
        
        //create all the player objects and add it to player list
        Human myPlayer = new Human();
        playerList.add(myPlayer);

        for(int i = 0; i < totalPlayers-1; i ++)
        {
            Computer cpu = new Computer();
            playerList.add(cpu);
        }

        //start our main game
        Model newGame = new Model(totalPlayers,playerList,pplNames,weaponsNames,locationsNames);
        newGame.startGame();
    }

}
