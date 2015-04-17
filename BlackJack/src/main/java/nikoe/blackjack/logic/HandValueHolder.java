/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikoe.blackjack.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class for holding handvalues
 * @author Niko
 */
public class HandValueHolder {
    
    private List<Integer> possibleHandValues = new ArrayList<>();
    
    /**
     * Returns possible values for a hand
     * @return List<Integer>
     */
    public List<Integer> getPossibleHandValues() {
        return Collections.unmodifiableList(possibleHandValues);
    }
    
    /**
     * Adds handvalue for a hand
     * @param handValue
     */
    public void addHandValue(int handValue) {
        this.possibleHandValues.add(handValue);
    }
}
