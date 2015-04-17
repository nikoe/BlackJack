package nikoe.blackjack.logic.players;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import nikoe.blackjack.logic.HandValueCalculator;
import nikoe.blackjack.logic.HandValueHolder;
import nikoe.blackjack.logic.cards.Card;
import nikoe.blackjack.logic.cards.Hand;

/**
 * ABSTRACT PLAYER CLASS
 * @author Niko
 */
public abstract class Player {

    private List<Hand> hands;
    private String name;

    /**
     *
     * @param name
     */
    public Player(String name) {
        this.hands = new ArrayList<>();
        this.name = name;
    }

    /**
     * Clears all players hands
     */
    public void clearHands() {
        this.hands.clear();
    }

    /**
     * Adds hand for player
     * @param hand
     */
    public void addHand(Hand hand) {
        this.hands.add(hand);
    }

    /**
     *
     * @return
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Returns all player hands in a list
     * @return
     */
    public List<Hand> getHands() {
        return Collections.unmodifiableList(this.hands);
    }

}
