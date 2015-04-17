package nikoe.blackjack.logic.cards;

import java.util.ArrayList;
import java.util.List;
import nikoe.blackjack.logic.HandValueCalculator;
import nikoe.blackjack.logic.HandValueHolder;

/**
 * Class for Hand
 * Hand contains Cards
 * @author Niko
 */
public class Hand {
    
    private static final HandValueCalculator handValueCalculator = new HandValueCalculator();
    private List<Card> cards;
    private boolean splitted;
    private boolean doubled;
    private boolean isReady;
    private boolean isBusted;
    
    /**
     * Constructor
     */
    public Hand() {
        this.cards = new ArrayList<>();
        this.doubled = false;
        this.splitted = false;
        this.isReady = false;
        this.isBusted = false;
    }
    
    /**
     * Clears all cards
     */
    public void clear() {
        this.cards.clear();
    }
    
    /**
     * Returns if hand is ready/standed
     * @return
     */
    public boolean getIsReady() {
        return this.isReady;
    }
    
    /**
     * Sets hand ready/standed
     */
    public void setReady() {
        this.isReady = true;
    }
    
    /**
     * Returns if hand is splitted
     * @return
     */
    public boolean isSplitted() {
        return this.splitted;
    }
    
    /**
     * Returnd if hand is doubled
     * @return
     */
    public boolean isDoubled() {
        return this.doubled;
    }
    
    /**
     *
     * @param splitted
     */
    public void setSplitted(boolean splitted) {
        this.splitted = splitted;
    }
    
    /**
     *
     * @param doubled
     */
    public void setDoubled(boolean doubled) {
        this.doubled = doubled;
    }
    
    /**
     * Returns list of cards
     * @return
     */
    public List<Card> getCards() {
        return this.cards;
    }
    
    /**
     * Adds card to hand
     * @param card
     */
    public void addCard(Card card) {
        this.cards.add(card);
        this.checkIfBusted();
    }
    
    /**
     *
     * @return
     */
    public boolean getIsBusted() {
        return this.isBusted;
    }
    
    /**
     * Returnd final value for hand
     * @return
     */
    public int getFinalHandValue() {
        return handValueCalculator.getFinalHandValue(this.cards);
    }
    
    /**
     * Returnd HandValueHolder which contains all possible values of hand
     * @return
     */
    public HandValueHolder getHandValue() {
        return handValueCalculator.getHandValue(this.cards);
    }
    
    /**
     *
     * @return
     */
    public boolean isBlackJack() {
        return handValueCalculator.isBlackJack(this.cards);
    }
    
    private void checkIfBusted() {
        if(getFinalHandValue() > 21) {
            this.isBusted = true;
        }
    }
    
    /**
     *
     */
    public void splitHand() {
        this.setSplitted(true);
        //TODO
    }
    
    /**
     *
     */
    public void doubleHand() {
        this.setDoubled(true);
        //TODO
    }
}
