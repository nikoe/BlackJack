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
 *
 * @author Niko
 */
public class HandValueHolder {
    
    private List<Integer> possibleHandValues = new ArrayList<>();
    
    public List<Integer> getPossibleHandValues() {
        return Collections.unmodifiableList(possibleHandValues);
    }
    
    public void addHandValue(int handValue) {
        this.possibleHandValues.add(handValue);
    }
}
