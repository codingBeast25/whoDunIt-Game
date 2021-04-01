/*
 * Name: Kabir Bhakta  Student Number: 7900098
 * Purpose: This class implements IPlayer interface for computer player.
 */

import java.util.*;
public class Computer implements IPlayer
{
    private int numOfPlayers; //total players in the game
    private int myIndex;     //this players index
    private ArrayList<Card> suspects;  //suspect list
    private ArrayList<Card> locations; //loaction list
    private ArrayList<Card> weapons;   //weapon list
    private ArrayList<Card> myCards;   //cards this players hold
    private ArrayList<Card> remainingCards = new ArrayList<Card> (); //this list keeps track of how many and which cards are out in the game.
    private boolean lastGuess = false;    //no one answered this player's last guess
    private Guess myLastGuess;

    //Empty Constructor
    public Computer() { }

    //initializes all players with the data provided
    public void setUp( int numPlayers, int index, ArrayList<Card> ppl,ArrayList<Card> places, ArrayList<Card> weapons)
    {
        numOfPlayers = numPlayers;
        myIndex = index;
        suspects = ppl;
        locations = places;
        this.weapons = weapons;
        myCards = new ArrayList<Card> ();
        remainingCards.addAll(suspects); //for now add all the cards in remainng cards lsit
        remainingCards.addAll(weapons);
        remainingCards.addAll(locations);
    }

    //this indicates this player has been dealt this card and removes it from remaninng cards list.
    public void setCard (Card c)
    {
        myCards.add(c);
        remainingCards.remove(c);
    }

    //returns this players index
    public int getIndex()
    {
        return myIndex;
    }

    //This method answers to the guess made by ip
    public Card canAnswer(Guess g, IPlayer ip)
    {
        Card retCard = null;
        ArrayList<Card> guessedCards = g.getGuessCards();
        ArrayList<Card> temp = new ArrayList<Card> ();   //temporary list to store all the cards this player have in common with guessed cards
        for(Card curr: guessedCards)
        {
            if(myCards.contains(curr))
                temp.add(curr);
        }

        //if player have more than one card in common just randomly select one card and show it
        if(temp.size() > 1)
        {
            Collections.shuffle(temp);
            retCard = temp.get(0);
        }
        //if only one card then show that
        else if(temp.size() == 1)
        {
            retCard = temp.get(0);
        }
        return retCard;
    }

    //this player's turn to make a guess
    public Guess getGuess()
    {
        Card suspectCard = null; //suspect guess
        Card weaponCard = null;  //weapon guess
        Card locationCard = null; //location guess
        boolean isSuggestion = true; //isit a suggestion?

        //if during my last guess no one answered then accuse directly
        if(lastGuess)
        {
            isSuggestion = false;
            suspectCard = myLastGuess.getGuessCards().get(0);
            weaponCard = myLastGuess.getGuessCards().get(1);
            locationCard = myLastGuess.getGuessCards().get(2);
            return new Guess(suspectCard,weaponCard,locationCard,isSuggestion);
        }
        //randomly select one card from each category that this player dosent have and is in a list of remaming cards that player doesnt know
        else{
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
            myLastGuess = new Guess(suspectCard,weaponCard,locationCard,isSuggestion);
            return myLastGuess;
        }
    }

    //gets information from some player ip that he does or dosent have a gard this player guessed
    public void receiveInfo(IPlayer ip, Card c)
    {
        //if some player ip showed this player a card then remove it from remainingCards that player havent seen
        if(c != null)
        {
            remainingCards.remove(c);
            System.out.println("Player "+ip.getIndex()+ " answered.");
        }
        //if no player can show the card then this players guess was correct next time accuse
        else
        {
            lastGuess = true;
            System.out.println("No one could answer.");
        }
    }

    //overrides the objects equals method and checks the computer object by matching the index
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
