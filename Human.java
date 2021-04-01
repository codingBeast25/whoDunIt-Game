/*
 * Name: Kabir Bhakta  Student Number: 7900098
 * Purpose: This class implements IPlayer interface for human player.
 */

import java.util.*;

public class Human implements IPlayer
{
    private int numOfPlayers;  //total players in the game
    private int myIndex;       //players index
    private ArrayList<Card> suspects;  //suspect list
    private ArrayList<Card> locations; //locations list
    private ArrayList<Card> weapons;   //weapons list
    private ArrayList<Card> myCards;   //cards this player is holding

    //Empty constructor because all the intialisation of necesarry details are done in setup method
    public Human() { }

    //intitalizes all the data for the human player
    public void setUp( int numPlayers, int index, ArrayList<Card> ppl,ArrayList<Card> places, ArrayList<Card> weapons)
    {
        numOfPlayers = numPlayers;
        myIndex = index;
        suspects = ppl;
        locations = places;
        this.weapons = weapons;
        myCards = new ArrayList<Card> ();
    }

    //player recieves cards c
    public void setCard (Card c)
    {
        System.out.println("You recieved the " + c.getType() + " card "+c.getValue()); 
        myCards.add(c);
    }

    //return players index
    public int getIndex()
    {
        return myIndex;
    }

    //can this player answer ip's guess if yes then return the card or return null
    public Card canAnswer(Guess g, IPlayer ip)
    {
        ArrayList<Card> guessedCards = g.getGuessCards(); //get the cards that ip guessed
        ArrayList<Card> temp = new ArrayList<Card> ();  //temporary list to store all the cards that i have are in common with ip's guessed cards
        Card retCard = null;
        for(Card curr: guessedCards)
        {
            if(myCards.contains(curr))
                temp.add(curr);
        }

        //if player has more than 1 card in common then ask for which card to show
        if(temp.size() > 1)
        {
            Scanner sc= new Scanner(System.in);
            int choice = -1;
            System.out.println("Player "+ip.getIndex()+ " asked you about " + g.toString() + ". Which do you show?");
            for(int i = 0; i < temp.size(); i ++){
                System.out.println((i+1) + ". " + temp.get(i).getValue());
            }
            //loop until valid input entered
            while( choice < 1 || choice > temp.size()){
                try{
                    choice = sc.nextInt();
                }
                catch(NumberFormatException e){
                    System.out.println("Enter valid input.");
                }
            }
            retCard = temp.get(choice-1);
        }
        //return the only card
        else if ( temp.size() == 1 )
        {
            retCard = temp.get(0);
        }

        return retCard;
    }

    //this indicates its player's turn. He will guess now.
    public Guess getGuess()
    {
        Scanner sc= new Scanner(System.in);
        Card who; //suspect
        Card where; //location
        Card what;  //weapon
        boolean isSuggestion = true; //is the guess suggestion?
        int choice = -1;
        String choiceOfSugg;
        int i = 0;
        //get the suspect card
        System.out.println("Which Person do you want to suggest?");
        for(Card curr : suspects)
            System.out.println(++i + ". "+curr.getValue());
        //loop until valid input entered
        while( choice < 1 || choice > suspects.size()){
            try{
                choice = sc.nextInt();
            }
            catch(NumberFormatException e){
                System.out.println("Enter valid input.");
            }
        }
        who = new Card("Suspect",suspects.get(choice-1).getValue());
        
        choice = -1;
        i = 0;
        System.out.println("Which Weapon do you want to suggest?");
        for(Card curr : weapons)
            System.out.println(++i + ". " + curr.getValue());
        
        //loop until valid input
        while( choice < 1 || choice > weapons.size()){
            try{
                choice = sc.nextInt();
            }
            catch(NumberFormatException e){
                System.out.println("Enter valid input.");
            }
        }
        what = new Card("Weapon",weapons.get(choice-1).getValue());

        i = 0;
        choice = -1;
        System.out.println("Which Location do you want to suggest?");
        for(Card curr : locations)
            System.out.println(++i + ". " + curr.getValue());
            
        //loop until valid input
        while( choice < 1 || choice > locations.size()){
            try{
                choice = sc.nextInt();
            }
            catch(NumberFormatException e){
                System.out.println("Enter valid input.");
            }
        }
        where = new Card("Location",locations.get(choice-1).getValue());

        //check if player wants to accuse or suggest
        System.out.println("Is it an Accusation ?[Y/N] : ");
        choiceOfSugg = sc.next();
        if(choiceOfSugg.equals("Y") || choiceOfSugg.equals("y"))
            isSuggestion = false;
        else if(choiceOfSugg.equals("N") || choiceOfSugg.equals("n"))
            isSuggestion = true;

            //return the new guess
        return new Guess(who,what,where,isSuggestion);
    }

    //print the info that this player recieved from ip. 
    public void receiveInfo(IPlayer ip, Card c)
    {
        //player recieved a card from ip
        if(c != null)
        {
            System.out.println("Player "+ip.getIndex()+ " refuted your suggestion by showing you "+c.getValue()+".");
        }
        //no one answered your guess
        else
            System.out.println("No one could refute your suggestion.");

    }

    //overrides the objects equals method and checks the human object by matching the index
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
