/*
 * Name: Kabir Bhakta  Student Number: 7900098
 * Purpose: This class provides blue print for card object
 */
import java.util.*;

public class Card
{
    private String type;  //type of card: suspect,weapon,location
    private String value; //name of the card
    
    //constructor
    public Card(String type,String value)
    {
        this.type = type;
        this.value = value;
    }
    
    //return the type of card
    public String getType()
    {
        return type;
    }
    
    //return the value of card
    public String getValue()
    {
        return value;
    }
    
    
    @Override
    public String toString()
    {
        return value;
    }
    
    //overrides the equals method of the object by matching type and valueb of the card
    @Override
    public boolean equals(Object c)
    {
        boolean retVal = false;
        if( c instanceof Card) {
            Card temp = (Card) c;
            if ( temp.getValue().equals(this.value))
                retVal = true;
        }
        return retVal;
    }
}
