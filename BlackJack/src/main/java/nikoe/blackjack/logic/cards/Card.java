package nikoe.blackjack.logic.cards;

import java.awt.Image;
import java.util.Objects;

/**
 *
 * @author Niko
 *
 * Class for one single card
 *
 */
public class Card implements Comparable{

    /*
     Rank and suit for card
     */
    private final Rank rank;
    private final Suit suit;
    private final Image img;

    /**
     *
     * @param rank
     * @param suit
     */
    public Card(Rank rank, Suit suit, Image img) {
        this.rank = rank;
        this.suit = suit;
        this.img = img;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + Objects.hashCode(this.rank);
        hash = 43 * hash + Objects.hashCode(this.suit);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Card other = (Card) obj;
        if (this.rank != other.rank) {
            return false;
        }
        if (this.suit != other.suit) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Object o) {
        Card com = (Card) o;
        if(this.rank != com.getRank() && this.getSuit() != com.getSuit()) {
            return 1;
        }else {
            return 0;
        }
    }
    
    public Image getImage() {
        return this.img;
    }
}
