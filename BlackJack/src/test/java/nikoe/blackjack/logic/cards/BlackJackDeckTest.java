/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikoe.blackjack.logic.cards;

import java.util.ArrayList;
import java.util.Collections;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Assert;
/**
 *
 * @author Niko
 */
public class BlackJackDeckTest {
    
    BlackJackDeck deck;
    
    public BlackJackDeckTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        deck = new BlackJackDeck(1);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void deckHasCorrectAmountOfCards() {
        deck.reset();
        assertEquals(52, deck.cardsLeft());
    }
    
    @Test(expected = DeckEmptyException.class)
    public void deckGivesExceptionIfNoCardsLeft() {
        deck.reset();
        while(deck.cardsLeft() > 0) {
            deck.dealCard();
        }
        deck.dealCard();
    }
    
    @Test
    public void resetWorkingCorrectly() {
        deck.reset();;
        deckHasCorrectAmountOfCards();
        assertEquals(0, deck.getNumberOfDealtCards());
    }
    
    @Test
    public void cardsLeftGivesCorrectAmount() {
        deck.reset();
        deck.dealCard();
        assertEquals(51, deck.cardsLeft());
    }
    
    @Test
    public void dealtCardsGivesCorrectResult() {
        deck.reset();
        Card c = deck.dealCard();
        assertEquals(1, deck.getNumberOfDealtCards());
        assertEquals(c, deck.getDealtCards().get(0));
    }
    
    @Test
    public void everyCardIsDifferent() {
        deck.reset();
        while(deck.cardsLeft() > 0) {
            Card c = deck.dealCard();
            assertEquals(false, deck.availableCards.contains(c));
        }
    }
}
