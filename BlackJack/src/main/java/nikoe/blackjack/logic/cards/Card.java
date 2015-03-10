package nikoe.blackjack.logic.cards;

/**
 *
 * @author Niko
 *
 * Class for one single card
 *
 */
public class Card {

    /*
     Rank and suit for card
     */
    private final Rank rank;
    private final Suit suit;

    /**
     *
     * @param rank
     * @param suit
     */
    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    /**
     *
     * @return rank of a card
     */
    public Rank getRank() {
        return rank;
    }

    /**
     *
     * @return suit of a card
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     *
     * @return returns true if a card is an ace otherwise returns false
     */
    public boolean isAce() {
        return rank.equals(Rank.ACE);
    }

    public String toString() {
        return this.rank.name() + " OF " + this.suit.name();
    }

}
