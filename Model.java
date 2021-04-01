/*
* Name: Kabir Bhakta  Student Number: 7900098
* Purpose: This class handles all the logic of the game.
 */

import java.util.*;
public class Model
{
    private int totalPlayer;       //total players playing
    private ArrayList<Card> solution = new ArrayList<Card> ();       //solution for the game
    private ArrayList<Card> suspects;   //suspect list
    private ArrayList<Card> weapons;    //weapon list
    private ArrayList<Card> locations;  //location list
    private ArrayList<IPlayer> playerList; //player list as a whole wont be changed in the entire program
    private ArrayList<IPlayer> currentPlayers = new ArrayList<IPlayer> ();  //list of active players
    private IPlayer currentP;   //current player
    //constructor
    public Model(int playerCount,ArrayList<IPlayer> playerList, ArrayList<Card> suspectList, ArrayList<Card> weaponList, ArrayList<Card> locationList)
    {
        totalPlayer = playerCount;
        suspects = suspectList;
        weapons = weaponList;
        locations = locationList;
        Collections.shuffle(playerList); //random locations for the players
        this.playerList = playerList;
    }
    
    //intializes table,intializes active player list, selects the solution,distribute the cards, and run the game
    public void startGame()
    {
        System.out.println("Here is the list of all Suspects:");
        printList(suspects);
        System.out.println("Here is the list of all Weapons:");
        printList(weapons);
        System.out.println("Here is the list of all Locations:");
        printList(locations);
        //sit the players onto the table
        initTable();
        //intitalize active player list
        maintainCurrentList();
        //select 3 solution cards first
        selectSolution();
        //shuffle and distribute the remaining cards to the players
        distributeCards();
        //main gamePlay
        gameLoop();
    }
    
    //intializes active player list
    private void maintainCurrentList()
    {
        for(IPlayer player : playerList)
        {
            currentPlayers.add(player);
        }
    }
    
    //Sits the player onto the table and give them index number accordingly
    private void initTable()
    {
        int index = 0;
        for(IPlayer player: playerList)
        {
            if(player instanceof Human)
            {
                player.setUp(totalPlayer,index++,suspects,locations,weapons);
            }
            else if ( player instanceof Computer)
            {
               player.setUp(totalPlayer,index++,suspects,locations,weapons);
            }
        }
        
    }
    
    //selects the solution for the game. I am shuffling so as to make sure cards are chosen randomly
    private void selectSolution()
    {
        Collections.shuffle(locations);
        Collections.shuffle(suspects);
        Collections.shuffle(weapons);
        
        //chose 1 card from each list for the solution
        solution.add(suspects.get(0));
        solution.add(weapons.get(0));
        solution.add(locations.get(0));
    }
    
    //distributes the card to all the players
    private void distributeCards()
    {
        ArrayList<Card> remainingCards = new ArrayList();
        //i am shuffling all the cards twice just to make sure cards are well shuffled
        Collections.shuffle(locations);
        Collections.shuffle(suspects);
        Collections.shuffle(weapons);
        
        remainingCards.addAll(suspects);
        remainingCards.addAll(weapons);
        remainingCards.addAll(locations);
        //remove all the soluton cards selected 
        remainingCards.removeAll(solution);
        //shuffle the deck again
        Collections.shuffle(remainingCards);
        //distribute the cards to each player one by one
        int playerId = 0;
        for(Card curr : remainingCards)
        {
            currentPlayers.get(playerId).setCard(curr);
            playerId = (playerId+1)%totalPlayer;
        }
    }
    
    //starts the game and loops until some player win
    private void gameLoop()
    {
        boolean correctAcc = false;  //is the given accustaion correct?
        currentP = playerList.get(0); //current player 
        int currentIdx = currentP.getIndex(); //current player's index
        
        //loop until correct accusation made and active players are more than 1
        while(!correctAcc && currentPlayers.size() > 1)
        {
            //if the current player is active
            if(currentPlayers.contains(currentP)){   
                
                System.out.println("Current Turn : " + currentIdx); //its his turn
                Guess newGuess = currentP.getGuess(); //get his guess
                
                if(!newGuess.isItSuggestion()) //is the guess an accusation
                {
                    System.out.println("Accusation : Player " + currentIdx + ": " + newGuess.toString());
                    //check if accusation is correct or not
                    if(solution.containsAll(newGuess.getGuessCards())){  
                        correctAcc = true;
                        System.out.println("Player "+currentIdx+ " won the game.He correctly guessed the following: ");
                        printList(solution);
                    }
                    //accusation was incorrect remove the player from active players list
                    else{
                        System.out.println("Player "+currentIdx+ " made a bad accusation and was removed from the game.");
                        currentPlayers.remove(currentP);   
                    }
                }
                //guess was suggestion
                else 
                {
                    //print out the details of the guess
                    System.out.println("Suggestion : Player " + currentIdx + ": " + newGuess.toString());
                    int nextIdx = currentIdx;
                    Card nextCard = null;
                    IPlayer next = null;
                    //loop through all the players in the playerList and ask them if they could answer the guess or not
                    for(int i = 0; i < playerList.size()-1 ; i ++)
                    {
                        nextIdx = (nextIdx + 1)%totalPlayer;
                        next = playerList.get(nextIdx);
                        nextCard = next.canAnswer(newGuess,currentP);
                        //if the nextPlayer gave some answer to the guess then break the loop
                        if(nextCard != null)
                            break;
                    }
                    //receive the details if the player answered the guess or not
                    currentP.receiveInfo(next,nextCard);
                }
            }
            //its next players turn
            currentIdx = (currentIdx+1)%totalPlayer;
            currentP = playerList.get(currentIdx);
        }
        //if active players size comes down to 1 then the remaining active player wins
        if(currentPlayers.size() == 1)
            System.out.println("Everyone made wrong accusations, so Player " + currentPlayers.get(0).getIndex() + " won.");
        System.out.println("Game Ends here");
    }
    
    //prints the arraylist
    private void printList(ArrayList<Card> list)
    {
        String str = "";
        int i = 1;
        for(Card curr : list)
        {
            System.out.println(i + ". " +curr.toString());
            i++;
        }
    }
    
}
