/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikoe.blackjack.logic.cards;

import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ekni
 */
public class CardTest {

    public CardTest() {
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
    public void cardsAreEqual() {
        Card c1 = new Card(Rank.ACE, Suit.DIAMONDS, null);
        Card c2 = new Card(Rank.ACE, Suit.DIAMONDS, null);
        assertEquals(true, c1.equals(c2));
    }

    @Test
    public void cardsAreNotEqual() {
        Card c1 = new Card(Rank.ACE, Suit.HEARTS, null);
        Card c2 = new Card(Rank.ACE, Suit.DIAMONDS, null);
        assertEquals(false, c1.equals(c2));
    }

    @Test
    public void cardsAreNotEqual2() {
        Card c1 = new Card(Rank.FIVE, Suit.HEARTS, null);
        Card c2 = new Card(Rank.ACE, Suit.HEARTS, null);
        assertEquals(false, c1.equals(c2));
    }

    @Test
    public void testEquals() {
        Card c1 = new Card(Rank.FIVE, Suit.HEARTS, null);
        assertEquals(false, c1.equals(null));
    }

    @Test
    public void testEquals2() {
        Card c1 = new Card(Rank.FIVE, Suit.HEARTS, null);
        assertEquals(false, c1.equals(new Object()));
    }

    @Test
    public void testgetSuit() {
        Card c1 = new Card(Rank.FIVE, Suit.HEARTS, null);
        assertEquals(Suit.HEARTS, c1.getSuit());
    }

    @Test
    public void testToString() {
        Card c1 = new Card(Rank.FIVE, Suit.HEARTS, null);
        assertEquals("FIVE OF HEARTS", c1.toString());
    }

    @Test
    public void testCompare1() {
        Card c1 = new Card(Rank.FIVE, Suit.HEARTS, null);
        Card c2 = new Card(Rank.FIVE, Suit.HEARTS, null);
        assertEquals(0, c1.compareTo(c2));
    }

    @Test
    public void testCompare2() {
        Card c1 = new Card(Rank.FIVE, Suit.HEARTS, null);
        Card c2 = new Card(Rank.FIVE, Suit.SPADES, null);
        assertEquals(1, c1.compareTo(c2));
    }
    
    @Test
    public void testCompare3() {
        Card c1 = new Card(Rank.TEN, Suit.HEARTS, null);
        Card c2 = new Card(Rank.FIVE, Suit.SPADES, null);
        assertEquals(1, c1.compareTo(c2));
    }
    
    @Test
    public void testHashCode() {
        Card c1 = new Card(Rank.TEN, Suit.HEARTS, null);
        Card c2 = new Card(Rank.FIVE, Suit.SPADES, null);
        assertNotSame(c1.hashCode(), c2.hashCode());
    }
}
