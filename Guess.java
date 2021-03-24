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
    
    public Card getWho()
    {
        return who;
    }
    
    public Card getWhere()
    {
        return where;
    }
    
    public Card getWhat()
    {
        return what;
    }
    
    public boolean isItSuggestion()
    {
        return isSuggestion;
    }
    
}
