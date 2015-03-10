/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikoe.blackjack.logic.cards;

/**
 *
 * @author Niko
 */
public class Card {

    private final Rank rank;
    private final Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public boolean isAce() {
        return rank.equals(Rank.ACE);
    }
    
    public String toString() {
        return this.rank.name() + " OF " + this.suit.name();
    }
    
}
