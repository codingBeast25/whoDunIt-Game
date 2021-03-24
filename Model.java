import java.util.*;
public class Model
{
    private int totalPlayer;
    private ArrayList<Card> solution;
    private ArrayList<Card> suspects;
    private ArrayList<Card> weapons;
    private ArrayList<Card> locations;
    private ArrayList<IPlayer> playerList;
    private IPlayer currentP;
    public Model(int playerCount, ArrayList<Card> suspectList, ArrayList<Card> weaponList, ArrayList<Card> locationList)
    {
        totalPlayer = playerCount;
        suspects = suspectList;
        weapons = weaponList;
        locations = locationList;
        playerList = new ArrayList<IPlayer> ();
    }
    
    public void startGame()
    {
        System.out.println("Here is the list of all Suspects:");
        printList(suspects);
        System.out.println("Here is the list of all Weapons:");
        printList(weapons);
        System.out.println("Here is the list of all Locations:");
        printList(locations);
        
        selectSolution();
        initTable();
    }
    
    private void printList(ArrayList<Card> list)
    {
        String str = "";
        for(Card curr : list)
        {
            str += curr.toString()+"+";
        }
        str = str.substring(0,str.length()-1);
        System.out.println(str);
    }
    
    private void selectSolution()
    {
        Collections.shuffle(suspects);
        Collections.shuffle(weapons);
        Collections.shuffle(locations);
        
        solution.add(suspects.remove(0));
        solution.add(weapons.remove(0));
        solution.add(locations.remove(0));
    }
    
    private void initTable()
    {
        ArrayList<Card> remainingCards = new ArrayList();
        remainingCards.addAll(suspects);
        remainingCards.addAll(weapons);
        remainingCards.addAll(locations);
        Collections.shuffle(remainingCards);
        int index = 0;
        Human myPlayer = new Human();
        myPlayer.setup(totalPlayer,index++,suspects,locations,weapons);
        playerList.add(myPlayer);
        for(int i = 1; i < totalPlayer; i ++)
        {
            Computer cpu = new Computer();
            cpu.setup(totalPlayer,index++,suspects,locations,weapons);
            playerList.add(cpu);
        }
        int playerId = 0;
        for(Card curr : remainingCards)
        {
            playerList.get(playerId).setCard(curr);
            playerId = (playerId+1)%totalPlayer;
        }
        
        currentP = playerList.get(0);
        currentP.getGuess();
        
    }
}
