package nikoe.blackjack.logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
 * This class is the "core" of this application. It handles all of the games
 * flow
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

    /**
     * Constructor
     */
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

    /**
     * Method for adding a player to specific seat
     *
     * @param playerName
     * @param seatNumber
     */
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

    /**
     * Getter for geting a seat by seatnumber
     *
     * @param seatNumber
     * @return
     */
    public Seat getSeat(int seatNumber) {
        if (!seatExists(seatNumber)) {
            return null;
        }
        return this.seats.get(seatNumber - 1);
    }

    /**
     * Getter for geting a dealers seat
     *
     * @return
     */
    public Seat getDealerSeat() {
        return this.dealerSeat;
    }

    /**
     * Method to check if there any seat seated
     *
     * @return
     */
    public boolean seatsHasPlayers() {
        boolean b = false;
        for (Seat s : this.getSeats()) {
            if (s.hasPlayer()) {
                b = true;
                break;
            }
        }
        return b;
    }

    /**
     * Getter for getting a list for seats
     *
     * @return
     */
    public List<Seat> getSeats() {
        return Collections.unmodifiableList(seats);
    }

    /**
     * Method for realeasing a seat by seatnumber
     *
     * @param seatNumber
     */
    public void releaseSeat(int seatNumber) {
        if (seatExists(seatNumber)) {
            for (Seat seat : this.seats) {
                if (seat.getSeatNumber() == seatNumber) {
                    seat.release();
                }
            }
        }
    }

    /**
     * Setter for setting UI
     *
     * @param ui
     */
    public void setUi(GamePanel ui) {
        this.ui = ui;
    }

    private void repaintAll() {
        if (this.ui != null) {
            this.ui.repaintAll();
        }
    }

    /**
     * Method for starting new round. Round starts only if GameState is IDLE.
     * This deal starting cards for players
     */
    public void startNewRound() {
        if (this.state == GameState.IDLE) {
            clearHands();
            checkDeck();

            if (!isBetsSetted()) {
                return;
            }

            for (Seat seat : this.seats) {
                if (seat.hasPlayer()) {
                    this.seatPlaying = seat.getSeatNumber();
                    break;
                }
            }
            this.state = GameState.ROUNDACTIVE;
            dealCards();
            repaintAll();
        }
    }

    private boolean isBetsSetted() {
        int bets = 0;

        for (Seat seat : this.seats) {
            if (seat.hasPlayer()) {
                Human h = (Human) seat.getPlayer();
                if (h.getBet() > 0) {
                    bets++;
                }
            }
        }

        return bets > 0;
    }

    private void dealCards() {
        this.dealer.addHand(new Hand());
        for (Seat seat : this.seats) {
            if (seat.hasPlayer()) {
                seat.getPlayer().addHand(new Hand());
            }
        }
        for (int i = 0; i < 2; i++) {
            for (Seat seat : this.seats) {
                Human human = (Human) seat.getPlayer();
                if (seat.hasPlayer() && human.getBet() > 0 ) {
                    Hand h = human.getHands().get(0);
                    h.addCard(this.deck.dealCard());
                }
            }
            //Only first card to dealer
            if (i == 0) {
                this.dealer.getHands().get(0).addCard(this.deck.dealCard());
            }
        }
    }

    private void checkDeck() {
        double cardsInDeck = this.deck.getNumberOfDecks() * 52;
        double cardsLeft = this.deck.getAvailableCards().size();
        double percentage;
        percentage = cardsLeft / cardsInDeck * 100.0;
        if (percentage < 12.0) {
            this.deck.reset();
        }
    }

    private void clearHands() {
        for (Seat s : this.seats) {
            if (s.hasPlayer()) {
                s.getPlayer().clearHands();
            }
        }
        this.dealer.clearHands();
    }

    /**
     * Method for taking card to player. BlackJackGameManager has propety to
     * hold information about seat playing
     */
    public void hitCard() {
        Seat s = this.getSeat(seatPlaying);
        if (s.hasPlayer()) {
            Hand h = s.getPlayer().getHands().get(0);
            h.addCard(this.deck.dealCard());
            if (h.getIsBusted()) {
                this.activeHandStand();
            }
        }
        repaintAll();
    }

    /**
     * Method for stand current hand
     */
    public void activeHandStand() {
        Hand curHand = this.getSeat(seatPlaying).getPlayer().getHands().get(0);
        curHand.setReady();
        if (isPlayerLeftBehind()) {
            for (int i = this.seatPlaying + 1; i <= this.seats.size(); i++) {
                if (this.getSeat(i).hasPlayer()) {
                    this.seatPlaying = i;
                    break;
                }
            }
        } else {
            this.state = GameState.DEALTODEALER;
            repaintAll();
            dealToDealer();
        }
    }

    private void dealToDealer() {
        if (!isAllBusted()) {
            Hand h = this.dealer.getHands().get(0);
            while (h.getFinalHandValue() < 17) {
                h.addCard(this.deck.dealCard());
            }
            h.setReady();
        }
        endRound();
    }

    private boolean isAllBusted() {
        boolean allPlayersBusted = true;
        for (Seat s : this.seats) {
            if (s.hasPlayer()) {
                for (Hand h : s.getPlayer().getHands()) {
                    if (h.getFinalHandValue() <= 21) {
                        return false;
                    }
                }
            }
        }

        return allPlayersBusted;
    }

    private void payWins() {
        int dealerScore = this.dealer.getHands().get(0).getFinalHandValue();

        for (Seat s : this.seats) {
            if (s.hasPlayer()) {
                Human h = (Human) s.getPlayer();
                int handScore = h.getHands().get(0).getFinalHandValue();
                double bet = h.getBet();
                if (handScore < 22) {
                    if (dealerScore < 22) {
                        if (handScore > dealerScore) {
                            double win;
                            if (h.getHands().get(0).isBlackJack()) {
                                win = (bet * 1.5) + bet;
                            } else {
                                win = (bet * 1.0) + bet;
                            }
                            h.setMoney(h.getMoney() + win);
                        } else if (handScore == dealerScore) {
                            h.setMoney(h.getMoney() + bet);
                        }
                    } else {
                        double win;
                        if (h.getHands().get(0).isBlackJack()) {
                            win = (bet * 1.5) + bet;
                        } else {
                            win = (bet * 1.0) + bet;
                        }
                        h.setMoney(h.getMoney() + win);
                    }

                    h.setBet(0);
                } else {
                    h.setBet(0);
                }
            }
        }

    }

    private void endRound() {
        payWins();
        this.state = GameState.IDLE;
        repaintAll();
    }

    private boolean isPlayerLeftBehind() {
        boolean b = false;
        if (this.seatPlaying == this.seats.size()) {
            return false;
        }
        for (int i = this.seatPlaying + 1; i <= this.seats.size(); i++) {
            if (this.getSeat(i).hasPlayer()) {
                Human h = (Human) this.getSeat(i).getPlayer();
                if(h.getBet() > 0) {
                    b = true;
                    break;
                }
            }
        }
        return b;
    }

    /**
     * Method for getting current gamestate
     *
     * @return
     */
    public GameState getGameState() {
        return this.state;
    }
}
