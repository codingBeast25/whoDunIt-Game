/*
 * Name: Kabir Bhakta  Student Number: 7900098
 * Purpose: This class provides all information about a players guess.
 */
import java.util.*;

public class Guess
{
    private Card who; //who is the suspect?
    private Card what; //with which weapon?
    private Card where; //where did it take place?
    
    private boolean isSuggestion; //was it accusation or guess
    
    //constructor
    public Guess(Card who,Card what,Card where,boolean isSuggestion)
    {
        this.who = who;
        this.what = what;
        this.where = where;
        this.isSuggestion = isSuggestion;
    }
    
    //returns the guessed cards
    public ArrayList<Card> getGuessCards()
    {
        ArrayList<Card> retList = new ArrayList<Card> ();
        retList.add(who);
        retList.add(what);
        retList.add(where);
        return retList;
    }
    
    //returns whether it was a suggestion or accusation
    public boolean isItSuggestion()
    {
        return isSuggestion;
    }
    
    //prints the guess that player made
    public String toString()
    {
        return who.getValue() + " with " + what.getValue() + " in " + where.getValue()+ "."; 
    }
}
