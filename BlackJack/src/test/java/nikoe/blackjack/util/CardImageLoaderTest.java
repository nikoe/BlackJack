/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikoe.blackjack.util;

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
public class CardImageLoaderTest {
    
    CardImageLoader loader = new CardImageLoader();
    public CardImageLoaderTest() {
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
    public void notNull() {
        assertNotNull(this.loader.getCardImage(Rank.ACE, Suit.CLUBS));
    }
}
