/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikoe.blackjack.logic.cards;
import nikoe.blackjack.logic.HandValueHolder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Niko
 */
public class HandTest {
    
    public HandTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void isBlackJack() {
        Hand h = new Hand();
        h.addCard(new Card(Rank.ACE, Suit.SPADES, null));
        h.addCard(new Card(Rank.JACK, Suit.DIAMONDS, null));
        assertEquals(true, h.isBlackJack());
    }
    
    @Test
    public void isNotBlackJack() {
        Hand h = new Hand();
        h.addCard(new Card(Rank.FIVE, Suit.SPADES, null));
        h.addCard(new Card(Rank.JACK, Suit.DIAMONDS, null));
        assertEquals(false, h.isBlackJack());
    }
    
    @Test
    public void isNotBlackJack2() {
        Hand h = new Hand();
        h.addCard(new Card(Rank.FIVE, Suit.SPADES, null));
        h.addCard(new Card(Rank.JACK, Suit.DIAMONDS, null));
        h.addCard(new Card(Rank.TEN, Suit.HEARTS, null));
        assertEquals(false, h.isBlackJack());
    }
    
    @Test
    public void isTwentyOne() {
        Hand h = new Hand();
        h.addCard(new Card(Rank.EIGHT, Suit.CLUBS, null));
        h.addCard(new Card(Rank.THREE, Suit.HEARTS, null));
        h.addCard(new Card(Rank.KING, Suit.CLUBS, null));
        
        assertEquals(21, h.getFinalHandValue());
    }
    
    @Test
    public void testHandValue() {
        Hand h = new Hand();
        h.addCard(new Card(Rank.NINE, Suit.CLUBS, null));
        h.addCard(new Card(Rank.SEVEN, Suit.HEARTS, null));
        
        assertEquals(16, h.getFinalHandValue());
    }
    
    @Test
    public void testHandValue2() {
        Hand h = new Hand();
        h.addCard(new Card(Rank.ACE, Suit.CLUBS, null));
        h.addCard(new Card(Rank.SEVEN, Suit.HEARTS, null));
        
        assertEquals(18, h.getFinalHandValue());
    }
    
    @Test
    public void testHandValue3() {
        Hand h = new Hand();
        h.addCard(new Card(Rank.ACE, Suit.CLUBS, null));
        h.addCard(new Card(Rank.SEVEN, Suit.HEARTS, null));
        h.addCard(new Card(Rank.SEVEN, Suit.HEARTS, null));
        assertEquals(15, h.getFinalHandValue());
    }
    
    @Test
    public void testAce() {
        Hand h = new Hand();
        h.addCard(new Card(Rank.ACE, Suit.SPADES, null));
        h.addCard(new Card(Rank.FIVE, Suit.DIAMONDS, null));
        
        HandValueHolder hvh = h.getHandValue();
        
        assertEquals(2, hvh.getPossibleHandValues().size());
        assertEquals(6, (int)hvh.getPossibleHandValues().get(0));
        assertEquals(16, (int)hvh.getPossibleHandValues().get(1));
    }
    
    @Test
    public void testThreeAces() {
        Hand h = new Hand();
        h.addCard(new Card(Rank.ACE, Suit.SPADES, null));
        h.addCard(new Card(Rank.ACE, Suit.DIAMONDS, null));
        h.addCard(new Card(Rank.ACE, Suit.HEARTS, null));
        
        HandValueHolder hvh = h.getHandValue();
        
        assertEquals(2, hvh.getPossibleHandValues().size());
        assertEquals(3, (int)hvh.getPossibleHandValues().get(0));
        assertEquals(13, (int)hvh.getPossibleHandValues().get(1));
    }
    
    @Test
    public void testAddCard() {
        Hand h = new Hand();
        h.addCard(new Card(Rank.TEN, Suit.DIAMONDS, null));
        assertEquals(1, h.getCards().size());
    }
    
    @Test
    public void testClear() {
        Hand h = new Hand();
        h.addCard(new Card(Rank.TEN, Suit.DIAMONDS, null));
        h.clear();
        assertEquals(0, h.getCards().size());
    }
    
    @Test
    public void testEmptyHandValue() {
        Hand h = new Hand();
        assertEquals(0, h.getFinalHandValue());
    }
    
    @Test
    public void testSetDoubled() {
        Hand h = new Hand();
        h.setDoubled(true);
        assertEquals(true, h.isDoubled());
    }
}
