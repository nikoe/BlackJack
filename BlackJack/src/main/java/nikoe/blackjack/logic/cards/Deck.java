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

    /**
     * Cards that are available for dealing
     */
    protected Stack<Card> availableCards = new Stack<Card>();

    /**
     * Cards that are already dealt from the deck
     */
    protected List<Card> dealtCards = new ArrayList<Card>();

    public Deck() {
    }

    /**
     * Method for shuffling the Deck
     */
    public void shuffle() {
        Collections.shuffle(availableCards, new SecureRandom());
    }

    /**
     *
     * @return Returns a Card from the top of the deck
     * @throws DeckEmptyException
     */
    public Card dealCard() throws DeckEmptyException {
        if (this.availableCards.isEmpty()) {
            throw new DeckEmptyException("No cards left!");
        }

        Card card = this.availableCards.pop();
        this.dealtCards.add(card);

        return card;

    }

    /**
     *
     * @return Returns the count of how many cards is available from the deck
     */
    public int cardsLeft() {
        return this.availableCards.size();
    }

    /**
     * Resets the deck by putting all dealt cards back to availablecards ant
     * then shuffles
     */
    public void reset() {
        this.availableCards.addAll(this.dealtCards);
        this.dealtCards.clear();
        this.shuffle();
    }

    /**
     *
     * @return Returns list of dealt cards
     */
    public List<Card> getDealtCards() {
        return this.dealtCards;
    }

    /**
     *
     * @return Returns the count of dealt cards
     */
    public int getNumberOfDealtCards() {
        return this.dealtCards.size();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Card card : this.availableCards) {
            sb.append(card + System.getProperty("line.separator"));
        }

        return sb.toString();
    }

}
