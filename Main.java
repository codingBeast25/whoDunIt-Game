import java.util.*;

public class Main
{
    private static int totalPlayers;

    public static void main(){
        ArrayList<IPlayer> playerList = new ArrayList<IPlayer> ();
        ArrayList<Card> pplNames= new ArrayList<Card> ();
        ArrayList<Card> weaponsNames= new ArrayList<Card> ();
        ArrayList<Card> locationsNames= new ArrayList<Card> ();
        
        pplNames.add(new Card("Suspect","Miss Scarlet"));
        pplNames.add(new Card("Suspect","Mr Green"));
        pplNames.add(new Card("Suspect","Mrs Peacock"));
        pplNames.add(new Card("Suspect","Professor Plum"));
        pplNames.add(new Card("Suspect","Mrs White"));
        pplNames.add(new Card("Suspect","Colonel Mustard"));

        weaponsNames.add(new Card("Weapon","Revolver"));
        weaponsNames.add(new Card("Weapon","Dagger"));
        weaponsNames.add(new Card("Weapon","Pipe"));
        weaponsNames.add(new Card("Weapon","Rope"));
        weaponsNames.add(new Card("Weapon","Candlestick"));
        weaponsNames.add(new Card("Weapon","Wrench"));
        weaponsNames.add(new Card("Weapon","Sword"));
        weaponsNames.add(new Card("Weapon","Belt"));
        weaponsNames.add(new Card("Weapon","COVID"));

        locationsNames.add(new Card("Location","Dining Room"));
        locationsNames.add(new Card("Location","Lounge"));
        locationsNames.add(new Card("Location","Kitchen"));
        locationsNames.add(new Card("Location","Study"));
        locationsNames.add(new Card("Location","Hall"));
        locationsNames.add(new Card("Location","Billiard Room"));
        locationsNames.add(new Card("Location","Conservatory"));
        locationsNames.add(new Card("Location","Library"));
        locationsNames.add(new Card("Location","Bedroom"));
        locationsNames.add(new Card("Location","Cellar"));
        locationsNames.add(new Card("Location","Ballroom"));
        
        System.out.println("New Game Starts...");
        Scanner sc= new Scanner(System.in);
        System.out.println("How Many Computer Opponents will be Playing: ");
        totalPlayers = sc.nextInt()+1;

        Human myPlayer = new Human();
        playerList.add(myPlayer);
        
        for(int i = 0; i < totalPlayers-1; i ++)
        {
            Computer cpu = new Computer();
            playerList.add(cpu);
        }
        
        Model newGame = new Model(totalPlayers,playerList,pplNames,weaponsNames,locationsNames);
        newGame.startGame();
    }


}
