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
import nikoe.blackjack.util.PropertyReader;

public class Main {
    
    public static void main(String[] args) {
        
        PropertyReader props = new PropertyReader("game.properties");
        
        int decks = Integer.parseInt(props.getProperty("deck.numberOfDecks"));
        
        BlackJackDeck deck = new BlackJackDeck(decks);
        
        System.out.println(deck.cardsLeft());
        
        
    }
    
}
