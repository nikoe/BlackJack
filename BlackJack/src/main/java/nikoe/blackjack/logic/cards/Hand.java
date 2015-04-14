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
    private boolean isReady;
    private boolean isBusted;
    
    public Hand() {
        this.cards = new ArrayList<>();
        this.doubled = false;
        this.splitted = false;
        this.isReady = false;
        this.isBusted = false;
    }
    
    public void clear() {
        this.cards.clear();
    }
    
    public boolean getIsReady() {
        return this.isReady;
    }
    
    public void setReady() {
        this.isReady = true;
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
        this.checkIfBusted();
    }
    
    public boolean getIsBusted() {
        return this.isBusted;
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
    
    private void checkIfBusted() {
        if(getFinalHandValue() > 21) {
            this.isBusted = true;
        }
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
