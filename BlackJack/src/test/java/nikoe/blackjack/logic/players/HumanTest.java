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
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ekni
 */
public class HumanTest {
    
    Human human = new Human("test");
    
    public HumanTest() {
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
        assertEquals(1, this.human.getHands().size());
    }
    
    @Test
    public void clearHands() {
        addHand();
        this.human.clearHands();
        assertEquals(0, this.human.getHands().size());
    }
    
    @Test
    public void testsetMoney() {
        this.human.setMoney(50.4);
        assertEquals(this.human.getMoney(), 50, 4);
    }
    
    @Test
    public void testgetMoney() {
        Assert.assertNotEquals(this.human.getMoney(), 0, 0);
    }
    
    @Test
    public void testgetName() {
        assertEquals(this.human.getName(), "test");
    }
    
    private void addHand() {
        Hand h = new Hand();
        h.addCard(new Card(Rank.ACE, Suit.CLUBS, null));
        this.human.addHand(h);        
    }
    
}
