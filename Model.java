import java.util.*;
public class Model
{
    private int totalPlayer;
    private ArrayList<Card> solution = new ArrayList<Card> ();
    private ArrayList<Card> suspects;
    private ArrayList<Card> weapons;
    private ArrayList<Card> locations;
    private ArrayList<IPlayer> playerList;
    private ArrayList<IPlayer> currentPlayers = new ArrayList<IPlayer> ();
    private IPlayer currentP;
    public Model(int playerCount,ArrayList<IPlayer> playerList, ArrayList<Card> suspectList, ArrayList<Card> weaponList, ArrayList<Card> locationList)
    {
        totalPlayer = playerCount;
        suspects = suspectList;
        weapons = weaponList;
        locations = locationList;
        Collections.shuffle(playerList);
        this.playerList = playerList;
    }
    
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
        maintainCurrentList();
        //select 3 solution cards first
        selectSolution();
        //shuffle and distribute the remaining cards to the players
        distributeCards();
        //main gamePlay
        gameLoop();
    }
    
    private void maintainCurrentList()
    {
        for(IPlayer player : playerList)
        {
            currentPlayers.add(player);
        }
    }
    
    private void gameLoop()
    {
        boolean correctAcc = false;
        currentP = playerList.get(0);
        int currentIdx = currentP.getIndex();
        while(!correctAcc && currentPlayers.size() > 1)
        {
            if(currentPlayers.contains(currentP)){
                
                System.out.println("Current Turn : " + currentIdx);
                Guess newGuess = currentP.getGuess();
                
                if(!newGuess.isItSuggestion())
                {
                    System.out.println("Accusation : Player " + currentIdx + ": " + newGuess.toString());
                    if(solution.containsAll(newGuess.getGuessCards())){
                        correctAcc = true;
                        System.out.println("Player "+currentIdx+ " won the game.He correctly guessed the following: ");
                        printList(solution);
                    }
                    else{
                        System.out.println("Player "+currentIdx+ " made a bad accusation and was removed from the game.");
                        currentPlayers.remove(currentP);   
                    }
                }
                else
                {
                    System.out.println("Suggestion : Player " + currentIdx + ": " + newGuess.toString());
                    int nextIdx = currentIdx;
                    Card nextCard = null;
                    IPlayer next = null;
                    for(int i = 0; i < playerList.size()-1 ; i ++)
                    {
                        nextIdx = (nextIdx + 1)%totalPlayer;
                        next = playerList.get(nextIdx);
                        nextCard = next.canAnswer(newGuess,currentP);
                        if(nextCard != null)
                            break;
                    }
                    currentP.receiveInfo(next,nextCard);
                }
            }
            currentIdx = (currentIdx+1)%totalPlayer;
            currentP = playerList.get(currentIdx);
        }
        if(currentPlayers.size() == 1)
            System.out.println("Everyone made wrong accusations, so Player : " + currentPlayers.get(0).getIndex() + " won.");
        System.out.println("Game Ends here");
    }
    
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
    
    private void selectSolution()
    {
        Collections.shuffle(locations);
        Collections.shuffle(suspects);
        Collections.shuffle(weapons);
        
        
        solution.add(weapons.get(0));
        solution.add(suspects.get(0));
        solution.add(locations.get(0));
    }
    
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
    
    private void distributeCards()
    {
        ArrayList<Card> remainingCards = new ArrayList();
        remainingCards.addAll(suspects);
        remainingCards.addAll(weapons);
        remainingCards.addAll(locations);
        remainingCards.removeAll(solution);
        Collections.shuffle(remainingCards);
        int playerId = 0;
        for(Card curr : remainingCards)
        {
            currentPlayers.get(playerId).setCard(curr);
            playerId = (playerId+1)%totalPlayer;
        }
    }
}
