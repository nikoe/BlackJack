package nikoe.blackjack.logic.players;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import nikoe.blackjack.logic.HandValueCalculator;
import nikoe.blackjack.logic.HandValueHolder;
import nikoe.blackjack.logic.cards.Card;
import nikoe.blackjack.logic.cards.Hand;

/**
 *
 * @author Niko
 */
public abstract class Player {

    private List<Hand> hands;
    private String name;

    public Player(String name) {
        this.hands = new ArrayList<>();
        this.name = name;
    }

    public void clearHands() {
        this.hands.clear();
    }

    public void addHand(Hand hand) {
        this.hands.add(hand);
    }

    public String getName() {
        return this.name;
    }
    
    public List<Hand> getHands() {
        return Collections.unmodifiableList(this.hands);
    }

}
