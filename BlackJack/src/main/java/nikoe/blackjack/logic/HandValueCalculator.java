/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikoe.blackjack.logic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import nikoe.blackjack.logic.cards.Card;
import nikoe.blackjack.logic.cards.Rank;

/**
 * THIS CLASS CALCULATES VALUE FOR BLACKJACK HAND
 * @author Niko
 */
public class HandValueCalculator {

    private static final Map<Rank, Integer> CARDVALUES = new HashMap<Rank, Integer>();

    static {
        intializeCardValues();
    }

    private static void intializeCardValues() {
        CARDVALUES.put(Rank.ACE, 1);
        CARDVALUES.put(Rank.DEUCE, 2);
        CARDVALUES.put(Rank.THREE, 3);
        CARDVALUES.put(Rank.FOUR, 4);
        CARDVALUES.put(Rank.FIVE, 5);
        CARDVALUES.put(Rank.SIX, 6);
        CARDVALUES.put(Rank.SEVEN, 7);
        CARDVALUES.put(Rank.EIGHT, 8);
        CARDVALUES.put(Rank.NINE, 9);
        CARDVALUES.put(Rank.TEN, 10);
        CARDVALUES.put(Rank.JACK, 10);
        CARDVALUES.put(Rank.QUEEN, 10);
        CARDVALUES.put(Rank.KING, 10);
    }

    private int getCardValue(Card card, boolean aceIsEleven) {
        int value;
        Rank rank = card.getRank();

        if (rank.equals(Rank.ACE) && aceIsEleven) {
            value = 11;
        } else {
            value = CARDVALUES.get(rank);
        }

        return value;
    }

    /**
     * Returns value of hand in a HandValueHolder
     * If Hand contains ace HandValueHolder usually contains two handvalues example 6 and 16
     * @param hand
     * @return HandValueHolder
     */
    public HandValueHolder getHandValue(List<Card> hand) {
        int value1 = 0, value2 = 0;
        boolean isFirstAce = true;
        for (Card card : hand) {
            value1 += getCardValue(card, false);
            if (card.isAce() && isFirstAce) {
                isFirstAce = false;
                value2 += getCardValue(card, true);
            } else {
                value2 += getCardValue(card, false);
            }
        }

        HandValueHolder values = new HandValueHolder();

        values.addHandValue(value1);

        if (value1 != value2) {
            values.addHandValue(value2);
        }

        return values;
    }

    /**
     * Returns integer value of hand, if hand contains two values returnd bigger one which is 21 or under
     * @param hand
     * @return
     */
    public int getFinalHandValue(List<Card> hand) {
        HandValueHolder values = getHandValue(hand);

        if (values.getPossibleHandValues().size() == 1) {
            return values.getPossibleHandValues().get(0);
        } else {
            int value1 = values.getPossibleHandValues().get(0);
            int value2 = values.getPossibleHandValues().get(1);

            if (value1 == value2) {
                return value1;
            }

            if (Math.max(value1, value2) > 21) {
                return Math.min(value1, value2);
            } else {
                return Math.max(value1, value2);
            }
        }
    }

    /**
     * Checks if hand is a blackjack
     * @param hand
     * @return
     */
    public boolean isBlackJack(List<Card> hand) {
        if (hand.size() != 2) {
            return false;
        }
        
        int value = getCardValue(hand.get(0), true) + getCardValue(hand.get(1), true);

        if (value == 21) {
            return true;
        } else {
            return false;
        }
    }
}
