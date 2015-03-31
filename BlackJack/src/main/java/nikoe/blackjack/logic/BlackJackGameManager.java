/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikoe.blackjack.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import nikoe.blackjack.logic.cards.BlackJackDeck;
import nikoe.blackjack.logic.cards.Hand;
import nikoe.blackjack.logic.players.Dealer;
import nikoe.blackjack.logic.players.Human;
import nikoe.blackjack.logic.players.Player;
import nikoe.blackjack.ui.GamePanel;
import nikoe.blackjack.util.PropertyReader;

/**
 *
 * @author Niko
 */
public class BlackJackGameManager {

    private List<Seat> seats;
    private Seat dealerSeat;
    private Dealer dealer;
    private PropertyReader props;
    private BlackJackDeck deck;
    private GameState state;
    private GamePanel ui;
    
    
    private int seatPlaying;
    
    public BlackJackGameManager() {
        this.seats = new ArrayList<>();
        this.dealer = new Dealer("Dealer");
        this.props = new PropertyReader("game.properties");
        this.state = GameState.IDLE;
        this.seatPlaying = 0;
        initSeats();
        initDeck();
    }

    private void initSeats() {
        int maxSeatAmount = Integer.parseInt(this.props.getProperty("table.numberOfSeats", "4"));
        if (maxSeatAmount > 4) {
            maxSeatAmount = 4;
        }
        for (int i = 1; i <= maxSeatAmount; i++) {
            this.seats.add(new Seat(i));
        }
        this.dealerSeat = new Seat(0);
        this.dealerSeat.setPlayer(this.dealer);
    }

    private void initDeck() {
        this.deck = new BlackJackDeck(Integer.parseInt(this.props.getProperty("deck.numberOfDecks", "6")));
    }

    public void addPlayerToSeat(String playerName, int seatNumber) {
        if (seatExists(seatNumber)) {
            for (Seat seat : this.seats) {
                if (seat.getSeatNumber() == seatNumber) {
                    if (!seat.hasPlayer()) {
                        seat.setPlayer(new Human(playerName));
                    }
                }
            }
        }
    }

    private boolean seatExists(int seatNumber) {
        if (seatNumber > this.seats.size()) {
            return false;
        }
        if (seatNumber < 0) {
            return false;
        }

        return true;
    }

    public Seat getSeat(int seatNumber) {
        if (!seatExists(seatNumber)) {
            return null;
        }
        return this.seats.get(seatNumber-1);
    }
    
    public Seat getDealerSeat() {
        return this.dealerSeat;
    }
    
    public boolean seatsHasPlayers() {
        boolean b = false;
        for(Seat s : this.getSeats()) {
            if(s.hasPlayer()) {
                b = true;
                break;
            }
        }
        return b;
    }

    public List<Seat> getSeats() {
        return Collections.unmodifiableList(seats);
    }

    public void releaseSeat(int seatNumber) {
        if (seatExists(seatNumber)) {
            for (Seat seat : this.seats) {
                if (seat.getSeatNumber() == seatNumber) {
                    seat.release();
                }
            }
        }
    }
    
    public void setUi(GamePanel ui) {
        this.ui = ui;
    }
    
    private void repaintAll() {
        if(this.ui != null) {
            this.ui.repaintAll();
        }
    }
    
    public void startNewRound() {
        if(this.state == GameState.IDLE) {
            this.seatPlaying = 1;
            this.state = GameState.ROUNDACTIVE;
            dealCards();
            repaintAll();
        }
    }
    
    private void dealCards() {
        this.dealer.addHand(new Hand());
        for(Seat seat : this.seats) {
            if(seat.hasPlayer()) {
                seat.getPlayer().addHand(new Hand());
            }
        }
        for(int i = 0; i < 2; i++) {
            for(Seat seat : this.seats) {
                if(seat.hasPlayer()) {
                    Hand h = seat.getPlayer().getHands().get(0);
                    h.addCard(this.deck.dealCard());
                }
            }
            //Only first card to dealer
            if(i == 0) {
                this.dealer.getHands().get(0).addCard(this.deck.dealCard());
            }
        }
    }
    
    public void hitCard() {
        Seat s = this.getSeat(seatPlaying);
        if(s.hasPlayer()) {
            Hand h = s.getPlayer().getHands().get(0);
            h.addCard(this.deck.dealCard());
        }
        
        repaintAll();
    }
    
    public void activeHandStand() {
        if(isPlayerLeftBehind()) {
            for(int i = this.seatPlaying+1; i <= this.seats.size(); i++) {
                if(this.getSeat(i).hasPlayer()) {
                    this.seatPlaying = i;
                    break;
                }
            }
        }else {
            this.state = GameState.DEALTODEALER;
            repaintAll();
            dealToDealer();
            System.out.println("DEAL TO DEALER");
        }
    }
    
    private void dealToDealer() {

            Hand h = this.dealer.getHands().get(0);
            while(h.getFinalHandValue() < 17) {
                h.addCard(this.deck.dealCard());
            }
            repaintAll();
            //endRound();
    }
    
    private void endRound() {
            for(Seat s : this.seats) {
                if(s.hasPlayer()) {
                    s.getPlayer().clearHands();
                }
            }
            this.dealer.clearHands();
            this.state = GameState.IDLE;
            repaintAll();
    }
    
    private boolean isPlayerLeftBehind() {
        boolean b = false;
        if(this.seatPlaying == this.seats.size()) return false;
        for(int i = this.seatPlaying+1; i <= this.seats.size(); i++) {
            if(this.getSeat(i).hasPlayer()) {
                b = true;
                break;
            }
        }
        return b;
    }
    
    public GameState getGameState() {
        return this.state; 
    }
}
