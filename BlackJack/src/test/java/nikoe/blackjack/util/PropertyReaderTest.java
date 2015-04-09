/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikoe.blackjack.util;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ekni
 */
public class PropertyReaderTest {
    
    PropertyReader props = new PropertyReader("game.properties");
    
    public PropertyReaderTest() {
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
    public void notNull() {
        assertNotNull(this.props.getProperty("deck.numberOfDecks", "5"));
    }
    
    @Test
    public void testDefault() {
        assertEquals("6", this.props.getProperty("test", "6"));
    }

}
