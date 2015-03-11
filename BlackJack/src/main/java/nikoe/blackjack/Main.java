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
import java.util.Scanner;
import nikoe.blackjack.logic.HandValueHolder;
import nikoe.blackjack.logic.Human;
import nikoe.blackjack.logic.Seat;
import nikoe.blackjack.logic.cards.BlackJackDeck;
import nikoe.blackjack.logic.cards.Card;
import nikoe.blackjack.logic.cards.Rank;
import nikoe.blackjack.logic.cards.Suit;
import nikoe.blackjack.util.PropertyReader;

public class Main {
    
    public static void main(String[] args) {
        
        Seat seat1 = new Seat(1);
        seat1.setPlayer(new Human("Niko"));
        
        BlackJackDeck deck = new BlackJackDeck(1);
        
        Scanner scanner = new Scanner(System.in);
        
        while(true) {
            scanner.nextLine();
            
            seat1.getPlayer().addCard(deck.dealCard());
            
            System.out.println(seat1.getPlayer().getHand());
            System.out.println(seat1.getPlayer().getHandValue().getPossibleHandValues());
        }
        
    }
    
}
