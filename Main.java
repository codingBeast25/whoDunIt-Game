import java.util.*;

public class Main
{
    private static int totalPlayers;
    
    public static void main(){
        ArrayList<String> cardsAdded = new ArrayList<String> ();
        ArrayList<Card> suspect = new ArrayList<Card> ();
        ArrayList<Card> weapon = new ArrayList<Card> ();
        ArrayList<Card> location = new ArrayList<Card> ();
        
        System.out.println("New Game Starts...");
        Scanner sc= new Scanner(System.in);
        System.out.println("How Many Computer Opponents will be Playing: ");
        totalPlayers = sc.nextInt()+1;
        
        for (int i = 0; i < 4; i ++)
        {
            addCard(cardsAdded , suspect , "Suspect");
            addCard(cardsAdded , weapon , "Weapon");
            addCard(cardsAdded , location , "Location");
        }
        Model newGame = new Model(totalPlayers,suspect,weapon,location);
        newGame.startGame();
    }
    
    private static void addCard(ArrayList<String> cardsAdded , ArrayList<Card> list , String type )
    {
        String value = getAlphaString();
        if(!cardsAdded.contains(value))
        {
            cardsAdded.add(value);
            list.add(new Card(type,value));
        }
        else 
        {
            addCard(cardsAdded , list , type);
        }
    }
    
    
    private static String getAlphaString()
    {
  
        // chose a Character random from this String
        String AlphaString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  
        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(5);
  
        for (int i = 0; i < 5; i++) {
  
            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                = (int)(AlphaString.length()
                        * Math.random());
  
            // add Character one by one in end of sb
            sb.append(AlphaString
                          .charAt(index));
        }
  
        return sb.toString();
    }

}
