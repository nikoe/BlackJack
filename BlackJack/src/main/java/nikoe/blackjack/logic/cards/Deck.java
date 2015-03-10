/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikoe.blackjack.logic.cards;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author Niko
 */
public abstract class Deck {

    protected Stack<Card> availableCards = new Stack<Card>();
    protected List<Card> dealtCards = new ArrayList<Card>();

    public Deck() {
    }

    public void shuffle() {
        Collections.shuffle(availableCards, new SecureRandom());
    }

    public Card dealCard() throws DeckEmptyException {
        if (this.availableCards.isEmpty()) {
            throw new DeckEmptyException("No cards left!");
        }

        Card card = this.availableCards.pop();
        this.dealtCards.add(card);

        return card;

    }

    public int cardsLeft() {
        return this.availableCards.size();
    }

    public void reset() {
        this.availableCards.addAll(this.dealtCards);
        this.dealtCards.clear();
        this.shuffle();
    }

    public List<Card> getDealtCards() {
        return this.dealtCards;
    }

    public int getNumberOfDealtCards() {
        return this.dealtCards.size();
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        for(Card card : this.availableCards) {
            sb.append(card + System.getProperty("line.separator"));
        }
        
        return sb.toString();
    }
    
}
