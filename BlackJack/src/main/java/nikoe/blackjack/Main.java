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
import java.util.List;
import java.util.Scanner;
import nikoe.blackjack.logic.HandValueHolder;
import nikoe.blackjack.logic.players.Human;
import nikoe.blackjack.logic.Seat;
import nikoe.blackjack.logic.cards.BlackJackDeck;
import nikoe.blackjack.logic.cards.Card;
import nikoe.blackjack.logic.cards.Hand;
import nikoe.blackjack.logic.cards.Rank;
import nikoe.blackjack.logic.cards.Suit;
import nikoe.blackjack.logic.players.Dealer;
import nikoe.blackjack.logic.players.Player;
import nikoe.blackjack.util.PropertyReader;

public class Main {

    static PropertyReader props = new PropertyReader("game.properties");
    static BlackJackDeck deck = new BlackJackDeck(Integer.parseInt(props.getProperty("deck.numberOfDecks", "6")));
    static Scanner scanner = new Scanner(System.in);
    static Seat[] seats = new Seat[Integer.parseInt(props.getProperty("table.numberOfSeats", "6"))];
    static Player dealer = new Dealer("Dealer");

    public static void main(String[] args) {
        
        
    }

}
