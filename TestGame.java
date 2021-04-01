// CLASS: TestGame...
//
// Author:  Kabir Bhakta
// Student number: 7900098
//
// Purpose:
//          Only for testing the computer logic
//-----------------------------------------

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;


public class TestGame {
    Computer c1;
    Computer c2;
    Human h;
    ArrayList<Card> pplNames;
    ArrayList<Card> weaponsNames;
    ArrayList<Card> locationsNames;
    ArrayList<Card> deck;
    ArrayList<Card> solution;

    @BeforeEach
    public void init(){
        //Players
        c1 = new Computer();
        c2 = new Computer();
        h = new Human();
        //all cards
        pplNames = new ArrayList<Card>();
        weaponsNames = new ArrayList<Card>();
        locationsNames = new ArrayList<Card>();
        pplNames.add(new Card("Suspect","Professor Plum"));
        pplNames.add(new Card("Suspect","Mrs White"));
        pplNames.add(new Card("Suspect","Colonel Mustard"));
        weaponsNames.add(new Card("Weapon","Candlestick"));
        weaponsNames.add(new Card("Weapon","Wrench"));
        locationsNames.add(new Card("Location","Study"));
        locationsNames.add(new Card("Location","Hall"));
        locationsNames.add(new Card("Location","Billiard Room"));

        //create deck
        deck = new ArrayList<Card>();
        deck.addAll(pplNames);
        deck.addAll(locationsNames);
        deck.addAll(weaponsNames);

        //select solution
        Collections.shuffle(pplNames);
        Collections.shuffle(locationsNames);
        Collections.shuffle(weaponsNames);
        solution = new ArrayList<Card>();
        solution.add(pplNames.get(0));
        solution.add(weaponsNames.get(0));
        solution.add(locationsNames.get(0));

        //remove the solution cards from the deck
        Collections.shuffle(deck);
        deck.removeAll(solution);

        //setup all the players
        c1.setUp(2,1,pplNames,locationsNames,weaponsNames);
        c2.setUp(2,2,pplNames,locationsNames,weaponsNames);
        h.setUp(3,3,pplNames,locationsNames,weaponsNames);

    }

    @Test
    //Tests the 1st Case
    public void test1(){
        Guess newGuess = new Guess(pplNames.get(1),weaponsNames.get(0), locationsNames.get(1),true );
        //currently c1 doesn't have any card on him so should return null
        assertNull (c1.canAnswer(newGuess,c2),"Should be null because c1 dosent have any card on him");

    }

    @Test
    //Tests 2nd Case
    public void test2(){
        Guess newGuess = new Guess(pplNames.get(1),weaponsNames.get(0), locationsNames.get(1),true );
        //give c1 a card
        c1.setCard(weaponsNames.get(0));
        //c1 has a card that c2 requested so it shouldnt be null
        assertNotNull(c1.canAnswer(newGuess,c2) ,"should not be null because c1 has a card that c2 guessed");
    }

    @Test
    //tests 3rd case
    public void test3(){
        Guess newGuess = new Guess(pplNames.get(1),weaponsNames.get(0), locationsNames.get(1),true );
        c1.setCard(weaponsNames.get(0));
        c1.setCard(pplNames.get(1));
        Card ret = c1.canAnswer(newGuess,c2);
        //c1 should have returned one of the two cards
        if(ret.equals(weaponsNames.get(0)))
            assertEquals(weaponsNames.get(0),ret);
        else
            assertEquals(pplNames.get(1),ret);
        assertNotNull(c1.canAnswer(newGuess,c2) ,"should not be null because c1 has a card that c2 guessed");
    }

    @Test
    //tests 4th case
    public void test4(){
        c1.setCard(pplNames.get(1));
        c1.setCard(weaponsNames.get(0));
        c1.setCard(locationsNames.get(1));
        Guess myGuess = c1.getGuess();
        ArrayList<Card> guessCards= myGuess.getGuessCards();

        //Should not contains any of the following cards
        assertFalse(guessCards.contains(pplNames.get(1)));
        assertFalse(guessCards.contains(weaponsNames.get(0)));
        assertFalse(guessCards.contains(locationsNames.get(1)));
    }

    @Test
    //Tests 5th case
    public void test5(){
    //we have already removed the solution cards from the deck so just give all the cards from the remaming deck
        for(int i = 0; i < deck.size();i++)
            c2.setCard(deck.get(i));
        Guess myGuess = c2.getGuess();
        ArrayList<Card> guessCards = myGuess.getGuessCards();
        //it will accuse correctly
        assertFalse(myGuess.isItSuggestion());
        assertEquals(guessCards,solution,"Should be equal");
    }

    @Test
    //Tests 6th Case
    public void test6(){
        //give first 4 cards to c1
        c1.setCard(deck.get(0));
        c1.setCard(deck.get(1));
        c1.setCard(deck.get(2));
        c1.setCard(deck.get(3));
        //give the only card left from the deck to c2
        c2.setCard(deck.get(4));

        Guess myGuess = c1.getGuess();
        //it will be suggestion because remaining cards are 4 which c1 dosent know about
        assertTrue(myGuess.isItSuggestion());

        //c2 has the the other card other than solution cards
        Card ret = c2.canAnswer(myGuess,c1);
        c1.receiveInfo(c2,ret);
        Guess newGuess = c1.getGuess();
        //C1 will accuse
        assertFalse(newGuess.isItSuggestion(),"Should be false as it is accusation");
    }

    @Test
    public void test7(){
        //remove solution cards from each category cards
        pplNames.removeAll(solution);
        locationsNames.removeAll(solution);
        weaponsNames.removeAll(solution);

        //give any two cards to human player
        h.setCard(weaponsNames.get(0));
        h.setCard(locationsNames.get(0));
        Guess cGuess = new Guess(pplNames.get(0),weaponsNames.get(0), locationsNames.get(0),true );
        //c1 ask the human player about all the guessed cards and human has 2 of them
        Card ret = h.canAnswer(cGuess,c1);
        //if human player has two cards then ask
        if(!ret.equals(locationsNames.get(0)))
            assertEquals(weaponsNames.get(0),ret);
        else
            assertEquals(locationsNames.get(0),ret);
    }
}
