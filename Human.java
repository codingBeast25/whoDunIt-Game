import java.util.*;

public class Human implements IPlayer
{
    private int numOfPlayers;
    private int myIndex;
    private ArrayList<Card> suspects;
    private ArrayList<Card> locations;
    private ArrayList<Card> weapons;
    private ArrayList<Card> myCards;
    public void setUp( int numPlayers, int index, ArrayList<Card> ppl,ArrayList<Card> places, ArrayList<Card> weapons)
    {
        numOfPlayers = numPlayers;
        myIndex = index;
        suspects = ppl;
        locations = places;
        this.weapons = weapons;
        myCards = new ArrayList<Card> ();
    }

    public void setCard (Card c)
    {
        System.out.println("You recieved the " + c.getType() + " card "+c.getValue()); 
        myCards.add(c);
    }

    public int getIndex()
    {
        return myIndex;
    }

    public Card canAnswer(Guess g, IPlayer ip)
    {
        ArrayList<Card> guessedCards = g.getGuessCards();
        ArrayList<Card> temp = new ArrayList<Card> ();
        Card retCard = null;
        for(Card curr: guessedCards)
        {
            if(myCards.contains(curr))
                temp.add(curr);
        }
        if(temp.size() > 1)
        {
            Scanner sc= new Scanner(System.in);
            System.out.println("Player "+ip.getIndex()+ " asked you about " + g.toString() + ". Which do you show?");
            for(int i = 0; i < temp.size(); i ++){
                System.out.println((i+1) + ". " + temp.get(i).getValue());
            }
            int choice = sc.nextInt();
            retCard = temp.get(choice-1);
        }
        else if ( temp.size() == 1 )
        {
            retCard = temp.get(0);
        }
        
        return retCard;
    }

    public Guess getGuess()
    {
        Scanner sc= new Scanner(System.in);
        Card who;
        Card where;
        Card what;
        boolean isSuggestion = true;
        int choice;
        String choiceOfSugg;
        int i = 0;
        System.out.println("Which Person do you want to suggest?");
        for(Card curr : suspects)
            System.out.println(++i + ". "+curr.getValue());
        choice = sc.nextInt();
        who = new Card("Suspect",suspects.get(choice-1).getValue());
        
        i = 0;
        System.out.println("Which Weapon do you want to suggest?");
        for(Card curr : weapons)
            System.out.println(++i + ". " + curr.getValue());
        choice = sc.nextInt();
        what = new Card("Weapon",weapons.get(choice-1).getValue());
        
        i = 0;
        System.out.println("Which Location do you want to suggest?");
        for(Card curr : locations)
            System.out.println(++i + ". " + curr.getValue());
        choice = sc.nextInt();
        where = new Card("Location",locations.get(choice-1).getValue());
        
        System.out.println("Is it an Accusation ?[Y/N] : ");
        choiceOfSugg = sc.next();
        if(choiceOfSugg.equals("Y") || choiceOfSugg.equals("y"))
            isSuggestion = false;
        else if(choiceOfSugg.equals("N") || choiceOfSugg.equals("n"))
            isSuggestion = true;
            
        return new Guess(who,what,where,isSuggestion);
    }

    public void receiveInfo(IPlayer ip, Card c)
    {
        if(c != null)
        {
            System.out.println("Player "+ip.getIndex()+ " refuted your suggestion by showing you "+c.getValue()+".");
        }
        else
            System.out.println("No one could refute your suggestion.");
        
    }
    
    @Override
    public boolean equals(Object p)
    {
        boolean ret = false;
        if(p instanceof Human)
        {
            Human player = (Human) p;
            if(this.getIndex() == player.getIndex())
                ret = true;
        }
        return ret;
    }
}
