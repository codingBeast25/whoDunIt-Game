import java.util.*;

public class Guess
{
    private Card who;
    private Card what;
    private Card where;
    
    private boolean isSuggestion;
    
    public Guess(Card who,Card what,Card where,boolean isSuggestion)
    {
        this.who = who;
        this.what = what;
        this.where = where;
        this.isSuggestion = isSuggestion;
    }
    
    public ArrayList<Card> getGuessCards()
    {
        ArrayList<Card> retList = new ArrayList<Card> ();
        retList.add(who);
        retList.add(what);
        retList.add(where);
        return retList;
    }
    
    public boolean isItSuggestion()
    {
        return isSuggestion;
    }
    
    public String toString()
    {
        return who.getValue() + " in " + where.getValue() + " with " + what.getValue(); 
    }
}
