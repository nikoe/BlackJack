/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikoe.blackjack.logic.players;

import nikoe.blackjack.logic.cards.Card;
import nikoe.blackjack.logic.cards.Hand;
import nikoe.blackjack.logic.cards.Rank;
import nikoe.blackjack.logic.cards.Suit;
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
public class DealerTest {
    
    Dealer dealer = new Dealer("Test");
    
    public DealerTest() {
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
    public void addHandTest() {
        addHand();
        assertEquals(1, this.dealer.getHands().size());
    }
    
    @Test
    public void clearHands() {
        addHand();
        this.dealer.clearHands();
        assertEquals(0, this.dealer.getHands().size());
    }
    
    private void addHand() {
        Hand h = new Hand();
        h.addCard(new Card(Rank.ACE, Suit.CLUBS, null));
        this.dealer.addHand(h);        
    }
}
