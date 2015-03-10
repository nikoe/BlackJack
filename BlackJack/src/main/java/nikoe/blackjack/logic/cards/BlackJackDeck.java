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
public class BlackJackDeck extends Deck {

    private int numberOfDecks;

    public BlackJackDeck(int numberOfDecks) {
        this.numberOfDecks = numberOfDecks;
        this.initDeck();
        this.shuffle();
    }

    private void initDeck() {
        for (int i = 1; i <= this.numberOfDecks; i++) {
            for (Rank rank : Rank.values()) {
                for (Suit suit : Suit.values()) {
                    Card card = new Card(rank, suit);
                    this.availableCards.add(card);
                }
            }
        }
    }

}
