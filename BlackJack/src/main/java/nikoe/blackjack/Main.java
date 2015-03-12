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
        System.out.print("How many seats?: ");
        int seatcount = Integer.parseInt(scanner.nextLine());
        if (seatcount > Integer.parseInt(props.getProperty("table.numberOfSeats", "6"))) {
            seatcount = Integer.parseInt(props.getProperty("table.numberOfSeats", "6"));
            System.out.println("Maximun seatcount is: " + seatcount);
        }
        System.out.println("");
        for (int i = 0; i < seatcount; i++) {
            seats[i] = new Seat(i + 1);
            System.out.print("Name for player in seat number " + (i + 1) + ": ");
            String name = scanner.nextLine();
            if (name.equals("") || name == null) {
                name = "Seat #" + (i + 1);
            }
            seats[i].setPlayer(new Human(name));
        }

        System.out.println("");
        System.out.println("");
        System.out.println("So players are:");
        for (Seat seat : seats) {
            if(seat != null)
            System.out.println(seat.getPlayer().getName());
        }
        System.out.println("");
        System.out.println("HAVE FUN!!!");

        while (true) {
            System.out.println("new round: [r], exit game: [q]");
            String command = scanner.nextLine();

            if (command.equals("r")) {
                newRound();
            }

            if (command.equals("q")) {
                break;
            }

        }
    }

    public static void newRound() {
        System.out.println("NEW ROUND!");
        
        for(int i = 0; i < 2; i++) {
            for(Seat seat : seats) {
                if(seat != null) {
                    List<Hand> hands = seat.getPlayer().getHands();
                    if(hands.size() == 0) {
                        seat.getPlayer().addHand(new Hand());
                    }
                    hands.get(0).addCard(deck.dealCard());
                }
            }
            
            if(i == 0) {
                List<Hand> hand = dealer.getHands();
                hand.get(0).addCard(deck.dealCard());
            }
        }
        
        System.out.println("DEALER HAS: " + dealer.getHand(0).getCards().toString() + " VALUE: " + dealer.getHand(0).getFinalHandValue());
        System.out.println("");
        
        for(Seat seat : seats) {
            if(seat != null) {
                while(true) {

                    if(seat.getPlayer().getHand(0).getFinalHandValue() < 22) {
                        System.out.println(seat.getPlayer().getName()+ "HAS: " +seat.getPlayer().getHand(0).getCards().toString() + " VALUE: " + seat.getPlayer().getHand(0).getFinalHandValue());
                        System.out.println(seat.getPlayer().getName()+ "....Hit [h] or Stand [s] ?");
                        String command = scanner.nextLine();

                        if(command.equals("h")) {
                            seat.getPlayer().getHand(0).addCard(deck.dealCard());
                        }

                        if(command.equals("s")) {
                            break;
                        }
                    }else {
                        System.out.println("BUST! " + seat.getPlayer().getHand(0).getCards().toString() + "VALUE: " + seat.getPlayer().getHand(0).getFinalHandValue());
                        break;
                    }
                }
                System.out.println("");
            }
        }
        
        System.out.println("");
        
        while(dealer.getHand(0).getFinalHandValue() < 17) {
            dealer.getHand(0).addCard(deck.dealCard());
        }
        
        System.out.println("DEALER FINAL HAND: " + dealer.getHand(0).getCards().toString() + " VALUE: " + dealer.getHand(0).getFinalHandValue());
        System.out.println("");
        for(Seat seat : seats) {
            if(seat != null) {
                if(seat.getPlayer().getHand(0).getFinalHandValue() <= 21) {
                    if(dealer.getHand(0).getFinalHandValue() > 21) {
                        System.out.println(seat.getPlayer().getName()+" WINS!!");
                    }else {
                        if(seat.getPlayer().getHand(0).getFinalHandValue() > dealer.getHand(0).getFinalHandValue()) {
                            System.out.println(seat.getPlayer().getName()+" WINS!!");
                        }else {
                            System.out.println(seat.getPlayer().getName()+ " LOOSES!!");
                        }
                    }
                }else {
                    System.out.println(seat.getPlayer().getName()+ " LOOSES!!");
                }
            }
        }
        
        endRound();
        
        System.out.println("");
        
    }
    
    public static void endRound() {
        for(Seat seat : seats) {
            if(seat != null) {
                seat.getPlayer().clearHands();
                seat.getPlayer().addHand(new Hand());
            }
        }
        
        dealer.clearHands();
        dealer.addHand(new Hand());
    }

}
