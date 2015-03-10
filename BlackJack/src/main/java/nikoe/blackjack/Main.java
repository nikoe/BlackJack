/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikoe.blackjack;

/**
 *
 * @author Niko
 */
import nikoe.blackjack.logic.cards.BlackJackDeck;

public class Main {
    
    public static void main(String[] args) {
        
        BlackJackDeck deck = new BlackJackDeck(2);
        
        System.out.println(deck);
        
    }
    
}
