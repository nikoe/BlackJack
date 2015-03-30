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
    
    public GameState getGameState() {
        return this.state;
    }
}
