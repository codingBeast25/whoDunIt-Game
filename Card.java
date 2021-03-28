import java.util.*;

public class Card
{
    private String type;
    private String value;
    
    public Card(String type,String value)
    {
        this.type = type;
        this.value = value;
    }
    
    public String getType()
    {
        return type;
    }
    
    public String getValue()
    {
        return value;
    }
    
    @Override
    public String toString()
    {
        return value;
    }
    
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
