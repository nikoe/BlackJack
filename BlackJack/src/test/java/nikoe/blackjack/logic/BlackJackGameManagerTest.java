/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikoe.blackjack.logic;

import nikoe.blackjack.logic.cards.Card;
import nikoe.blackjack.logic.cards.Hand;
import nikoe.blackjack.logic.cards.Rank;
import nikoe.blackjack.logic.cards.Suit;
import nikoe.blackjack.logic.players.Dealer;
import nikoe.blackjack.logic.players.Human;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Niko
 */
public class BlackJackGameManagerTest {

    private BlackJackGameManager manager;

    public BlackJackGameManagerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testInit() {
        initManager();
        assertEquals(this.manager.getGameState(), GameState.IDLE);
        assertNotEquals(this.manager.getSeats().size(), 0);
    }

    @Test
    public void testGameStatePlaceBets() {
        initManager();
        this.manager.placeBets();
        assertEquals(this.manager.getGameState(), GameState.PLACEBETS);
    }

    @Test
    public void testCanNotStartRoundWithoutBets1() {
        initManager();
        this.manager.startNewRound();
        assertEquals(this.manager.getGameState(), GameState.IDLE);
    }

    @Test
    public void testCanNotStartRoundWithoutBets2() {
        initManager();
        this.manager.placeBets();
        this.manager.startNewRound();
        assertEquals(this.manager.getGameState(), GameState.PLACEBETS);
    }

    @Test
    public void testCanotHitCardInWrongState() {
        initManager();
        initPlayer(1);
        this.manager.hitCard();
        Human h = getHumanFromSeat(1);
        assertEquals(h.getHands().size(), 0);
    }

    @Test
    public void testCanotGetSeatDontExsists() {
        initManager();
        assertNull(this.manager.getSeat(100));
    }

    @Test
    public void testGetDealerSeat() {
        initManager();
        assertNotNull(this.manager.getDealerSeat());
        Seat s = this.manager.getDealerSeat();
        assertEquals(s.hasPlayer(), true);

    }

    @Test
    public void testDealerDontHaveCardsInInit() {
        initManager();
        Seat s = this.manager.getDealerSeat();
        Dealer d = (Dealer) s.getPlayer();
        assertEquals(d.getHands().size(), 0);
    }

    @Test
    public void testSeatsHasNotPlayersInInit() {
        initManager();
        assertEquals(this.manager.seatsHasPlayers(), false);
    }

    @Test
    public void testSeatsHasPlayersIfSetted() {
        initManager();
        initPlayer(1);
        assertEquals(this.manager.seatsHasPlayers(), true);
    }

    @Test
    public void testGetSeats() {
        initManager();
        assertNotEquals(this.manager.getSeats().size(), 0);
    }

    @Test
    public void testReleaseSeat() {
        initManager();
        initPlayer(1);
        this.manager.getSeat(1).release();
        assertNull(this.manager.getSeat(1).getPlayer());
    }

    @Test
    public void testReleaseSeat2() {
        initManager();
        initPlayer(1);
        this.manager.releaseSeat(1);
        assertEquals(null, this.getHumanFromSeat(1));
    }
    
    @Test
    public void testStartNewRoundPlayerHasTwoCards() {
        initManager();
        initPlayer(1);
        initRound();
        Human h = this.getHumanFromSeat(1);
        assertEquals(h.getHands().get(0).getCards().size(), 2);
    }

    @Test
    public void testStartNewRoundDealerHasOneCard() {
        initManager();
        initPlayer(1);
        initRound();
        Dealer d = (Dealer) this.manager.getDealerSeat().getPlayer();
        assertEquals(d.getHands().get(0).getCards().size(), 1);
    }

    @Test
    public void testStartNewRoundGameState() {
        initManager();
        initPlayer(1);
        initRound();
        assertEquals(this.manager.getGameState(), GameState.ROUNDACTIVE);
    }

    @Test
    public void testCardHitToPlayer() {
        initManager();
        initPlayer(1);
        initRound();
        this.manager.hitCard();
        Human h = this.getHumanFromSeat(1);
        assertEquals(h.getHands().get(0).getCards().size(), 3);
    }

    @Test
    public void testDontHaveCardsIfNotBet() {
        initManager();
        initPlayer(1);
        initPlayer(2);
        Human h = this.getHumanFromSeat(1);
        h.setBet(10.0);
        this.manager.placeBets();
        this.manager.startNewRound();
        Human h2 = this.getHumanFromSeat(2);
        assertEquals(h2.getHands().get(0).getCards().size(), 0);
    }

    @Test
    public void testSeatPlaying() {
        initManager();
        initPlayer(1);
        initPlayer(2);
        this.manager.placeBets();
        Human h = this.getHumanFromSeat(2);
        h.setBet(10.00);
        this.manager.startNewRound();
        assertEquals(this.manager.getSeatPlaying(), 2);
    }

    @Test
    public void testActiveHandStand() {
        initManager();
        initPlayer(1);
        initPlayer(2);
        Human h1 = this.getHumanFromSeat(1);
        Human h2 = this.getHumanFromSeat(2);
        this.manager.placeBets();
        h1.setBet(10.0);
        h2.setBet(20.0);
        this.manager.startNewRound();
        this.manager.activeHandStand();
        assertEquals(this.manager.getSeatPlaying(), 2);
    }

    @Test
    public void testActiveHandStandLastPlayer() {
        initManager();
        initPlayer(1);
        initPlayer(2);
        Human h1 = this.getHumanFromSeat(1);
        Human h2 = this.getHumanFromSeat(2);
        this.manager.placeBets();
        h1.setBet(10.0);
        h2.setBet(20.0);
        this.manager.startNewRound();
        this.manager.activeHandStand();
        this.manager.activeHandStand();
        assertEquals(this.manager.getSeatPlaying(), 0);
    }

    @Test
    public void testActiveHandStandLastPlayer2() {
        initManager();
        initPlayer(1);
        initPlayer(2);
        Human h1 = this.getHumanFromSeat(1);
        Human h2 = this.getHumanFromSeat(2);
        this.manager.placeBets();
        h1.setBet(10.0);
        h2.setBet(20.0);
        this.manager.startNewRound();
        this.manager.activeHandStand();
        this.manager.activeHandStand();

        Dealer d = (Dealer) this.manager.getDealerSeat().getPlayer();

        assertNotEquals(d.getHands().get(0).getCards().size(), 1);

    }

    @Test
    public void testPayWinsCorrect() {
        initManager();
        initPlayer(1);
        initPlayer(2);
        Human h1 = this.getHumanFromSeat(1);
        Human h2 = this.getHumanFromSeat(2);
        this.manager.placeBets();
        h1.setBet(10.0);
        h1.setMoney(h1.getMoney() - h1.getBet());
        h2.setBet(10.0);
        h2.setMoney(h2.getMoney() - h2.getBet());
        this.manager.startNewRound();
        this.manager.activeHandStand();

        Dealer d = (Dealer) this.manager.getDealerSeat().getPlayer();
        Hand h = d.getHands().get(0);
        while (h.getFinalHandValue() < 23) {
            h.addCard(new Card(Rank.TEN, Suit.CLUBS, null));
        }

        this.manager.activeHandStand();

        if (h1.getHands().get(0).isBlackJack()) {
            assertEquals(h1.getMoney(), 515, 0);
        } else {
            assertEquals(h1.getMoney(), 510, 0);
        }

    }

    @Test
    public void testDontPayWinIfLoose() {
        initManager();
        initPlayer(1);
        initPlayer(2);
        Human h1 = this.getHumanFromSeat(1);
        Human h2 = this.getHumanFromSeat(2);
        this.manager.placeBets();
        h1.setBet(10.0);
        h1.setMoney(h1.getMoney() - h1.getBet());
        h2.setBet(10.0);
        h2.setMoney(h2.getMoney() - h2.getBet());
        this.manager.startNewRound();
        while(h1.getHands().get(0).getFinalHandValue() < 22) {
            h1.getHands().get(0).addCard(new Card(Rank.TEN, Suit.DIAMONDS, null));
        }
        this.manager.activeHandStand();
        this.manager.activeHandStand();

        Assert.assertEquals(490, h1.getMoney(), 0);

    }
    
    @Test
    public void testActiveHandDouble() {
        initManager();
        initPlayer(1);
        initPlayer(2);
        Human h1 = this.getHumanFromSeat(1);
        Human h2 = this.getHumanFromSeat(2);
        this.manager.placeBets();
        h1.setBet(10.0);
        h1.setMoney(h1.getMoney() - h1.getBet());
        h2.setBet(10.0);
        h2.setMoney(h2.getMoney() - h2.getBet());
        this.manager.startNewRound();
        this.manager.activeHandDouble();
        assertEquals(3, this.getHumanFromSeat(1).getHands().get(0).getCards().size());
        assertEquals(480, this.getHumanFromSeat(1).getMoney(), 0);
    }
    
    @Test
    public void testCannotDoubleIfBetTooMuch() {
        initManager();
        initPlayer(1);
        Human h = this.getHumanFromSeat(1);
        h.setBet(300.0);
        h.setMoney(h.getMoney() - h.getBet());
        this.manager.placeBets();
        this.manager.startNewRound();
        assertEquals(false, this.manager.activeHandCanDouble());
    }
    
    @Test
    public void testAddPlayerToSeat() {
        initManager();
        this.manager.addPlayerToSeat("test", 1);
        assertNotNull(this.getHumanFromSeat(1));
    }

    private void initRound() {
        this.manager.placeBets();
        Human h = this.getHumanFromSeat(1);
        h.setBet(10.0);
        this.manager.startNewRound();
    }

    private void initManager() {
        this.manager = new BlackJackGameManager();
    }

    private void initPlayer(int seatNumber) {
        Seat s = this.manager.getSeat(seatNumber);
        Human h = new Human("test");
        s.setPlayer(h);
    }

    private Human getHumanFromSeat(int seatNumber) {
        return (Human) this.manager.getSeat(seatNumber).getPlayer();
    }

}
