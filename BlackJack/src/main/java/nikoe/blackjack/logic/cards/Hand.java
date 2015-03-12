package nikoe.blackjack.logic.cards;

import java.util.ArrayList;
import java.util.List;
import nikoe.blackjack.logic.HandValueCalculator;
import nikoe.blackjack.logic.HandValueHolder;

/**
 *
 * @author Niko
 */
public class Hand {
    
    private static final HandValueCalculator handValueCalculator = new HandValueCalculator();
    private List<Card> cards;
    private boolean splitted;
    private boolean doubled;
    
    public Hand() {
        this.cards = new ArrayList<>();
        this.doubled = false;
        this.splitted = false;
    }
    
    public void clear() {
        this.cards.clear();
    }
    
    public boolean isSplitted() {
        return this.splitted;
    }
    
    public boolean isDoubled() {
        return this.doubled;
    }
    
    public void setSplitted(boolean splitted) {
        this.splitted = splitted;
    }
    
    public void setDoubled(boolean doubled) {
        this.doubled = doubled;
    }
    
    public List<Card> getCards() {
        return this.cards;
    }
    
    public void addCard(Card card) {
        this.cards.add(card);
    }
    
    public int getFinalHandValue() {
        return handValueCalculator.getFinalHandValue(this.cards);
    }
    
    public HandValueHolder getHandValue() {
        return handValueCalculator.getHandValue(this.cards);
    }
    
    public boolean isBlackJack() {
        return handValueCalculator.isBlackJack(this.cards);
    }
    
    public void splitHand() {
        this.setSplitted(true);
        //TODO
    }
    
    public void doubleHand() {
        this.setDoubled(true);
        //TODO
    }
    
}
