
package nikoe.blackjack.logic;

import junit.framework.Assert;
import nikoe.blackjack.logic.players.Human;
import nikoe.blackjack.logic.players.Player;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Niko
 */
public class SeatTest {
    
    private Human h;
    private Seat s;
    
    public SeatTest() {
        h = new Human("Test");
        s = new Seat(1);
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
    public void testsetPlayer()  {
        s.setPlayer(h);
        assertEquals(s.hasPlayer(), true);
    }
    
    @Test
    public void testreleaseSeat() {
        s.setPlayer(h);
        s.release();
        assertEquals(s.hasPlayer(), false);
    }
    
    @Test
    public void testgetSeatNumber() {
        assertEquals(s.getSeatNumber(), 1);
    }
    
    @Test
    public void testgetPlayer() {
        s.setPlayer(h);
        Assert.assertNotNull(s.getPlayer());
    }
    
    @Test
    public void testgetImage() {
        assertNotNull(s.getImage());
    }
    
    @Test
    public void testNotHasPlayer() {
        assertEquals(s.hasPlayer(), false);
    }
    
}
