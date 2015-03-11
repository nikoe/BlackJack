package nikoe.blackjack.logic.players;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import nikoe.blackjack.logic.HandValueCalculator;
import nikoe.blackjack.logic.HandValueHolder;
import nikoe.blackjack.logic.cards.Card;

/**
 *
 * @author Niko
 */

public abstract class Player {
    
    private static final HandValueCalculator handValueCalculator = new HandValueCalculator();
    private List<Card> hand;
    private String name;
    
    public Player(String name) {
        this.hand = new ArrayList<>();
        this.name = name;
    }
    
    public void addCard(Card card) {
        this.hand.add(card);
    }
    
    public void clearHand() {
        this.hand.clear();
    }
    
    public List<Card> getHand() {
        return Collections.unmodifiableList(this.hand);
    }
    
    public int getFinalHandValue() {
        return handValueCalculator.getFinalHandValue(this.hand);
    }
    
    public HandValueHolder getHandValue() {
        return handValueCalculator.getHandValue(hand);
    }
    
    public boolean isBlackJack() {
        return handValueCalculator.isBlackJack(this.hand);
    }
}
