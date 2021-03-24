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
    
    public String toString()
    {
        return value;
    }
}
