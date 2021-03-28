import java.util.*;
public class Computer implements IPlayer
{
    private int numOfPlayers;
    private int myIndex;
    private ArrayList<Card> suspects;
    private ArrayList<Card> locations;
    private ArrayList<Card> weapons;
    private ArrayList<Card> myCards;
    private ArrayList<Card> remainingCards = new ArrayList<Card> ();
    public void setUp( int numPlayers, int index, ArrayList<Card> ppl,ArrayList<Card> places, ArrayList<Card> weapons)
    {
        numOfPlayers = numPlayers;
        myIndex = index;
        suspects = ppl;
        locations = places;
        this.weapons = weapons;
        myCards = new ArrayList<Card> ();
        remainingCards.addAll(suspects);
        remainingCards.addAll(weapons);
        remainingCards.addAll(locations);
    }

    public void setCard (Card c)
    {
        myCards.add(c);
        remainingCards.remove(c);
    }

    public int getIndex()
    {
        return myIndex;
    }

    public Card canAnswer(Guess g, IPlayer ip)
    {
        Card retCard = null;
        ArrayList<Card> guessedCards = g.getGuessCards();
        ArrayList<Card> temp = new ArrayList<Card> ();
        for(Card curr: guessedCards)
        {
            if(myCards.contains(curr))
                temp.add(curr);
        }
        if(temp.size() > 1)
        {
            Collections.shuffle(temp);
            retCard = temp.get(0);
        }
        else if(temp.size() == 1)
        {
            retCard = temp.get(0);
        }
        return retCard;
    }

    public Guess getGuess()
    {
        Card suspectCard = null;
        Card weaponCard = null;
        Card locationCard = null;
        boolean isSuggestion = true;
        Collections.shuffle(remainingCards);
       for(Card curr : remainingCards)
       {
           if(curr.getType().equals("Suspect") && suspectCard == null)
               suspectCard = new Card("Suspect",curr.getValue());
           else if(curr.getType().equals("Location") && locationCard == null)
               locationCard = new Card("Location",curr.getValue());
           else if(curr.getType().equals("Weapon") && weaponCard == null)
               weaponCard = new Card("Weapon",curr.getValue());
       }
       if(remainingCards.size() == 3)
           isSuggestion = false;
       return new Guess(suspectCard,weaponCard,locationCard,isSuggestion);
    }

    public void receiveInfo(IPlayer ip, Card c)
    {
        if(c != null)
        {
            remainingCards.remove(c);
            System.out.println("Player "+ip.getIndex()+ " answered.");
        }
        else
        {
            System.out.println("No one could answer.");
        }
    }
    
    @Override
    public boolean equals(Object p)
    {
        boolean ret = false;
        if(p instanceof Computer)
        {
            Computer player = (Computer) p;
            if(this.getIndex() == player.getIndex())
                ret = true;
        }
        return ret;
    }
}
