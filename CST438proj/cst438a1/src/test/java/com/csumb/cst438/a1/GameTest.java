package com.csumb.cst438.a1;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test of Game class
 * @author david wisneski
 * @veraion 1.0
 */
public class GameTest {
    
    public GameTest() {
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

    /**
     * Test of getState method, of class Game.
     * at start of game, state should be 1.
     * a correct guess will not change the state
     * an incorrect guess will increase state by 1
     */
    @org.junit.Test
    public void testGetState() {
        System.out.println("getState");
        Game instance = new Game();
        int expResult = 1;
        int result = instance.getState();
        assertEquals(expResult, result);
        instance.playGame(instance.selectAChar());
        result = instance.getState();
        assertEquals(expResult, result);
        instance.playGame(instance.selectNoChar(true));
        result = instance.getState();
        assertEquals(expResult+1, result);
    }

    /**
     * Test of getWord method, of class Game.
     */
    @org.junit.Test
    public void testGetWord() {
        System.out.println("getWord");
        Game instance = new Game();
        String expResult = "computer";
        String result = instance.getWord();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDisplayWord method, of class Game.
     */
    @org.junit.Test
    public void testGetDisplayWord() {
        System.out.println("getDisplayWord");
        Game instance = new Game();
        String expResult = instance.getDisplayWord();
        String result = instance.getDisplayWord();
        assertEquals(expResult, result);
        instance.playGame(instance.selectAchar()); // Refer to selectAChar method
        result = instance.getDisplayWord();
        assertEquals(instance.getDisplayWord(), result);

    }

    /**
     * Test of startNewGame method, of class Game.
     */
    @org.junit.Test
    public void testStartNewGame() {
        System.out.println("startNewGame");
        Game instance = new Game();
        instance.startNewGame();
        instance.playGame(instance.selectAChar()); // Refer to selectAChar method
        instance.playGame(instance.selectNoChar()); // Refer to selectNoChar method
        instance.startNewGame();
        int result = instance.getState();
        assertEquals(1,result);
 
    }

    /**
     * Test of playGame method, of class Game.
     *   correct guess should return 0 , or 1 when game is won
     *   incorrect guess should return 2, or 3 when game is lost
     */
    @org.junit.Test
    public void testPlayGame() {
        System.out.println("playGame");
        Game instance = new Game();
        char guess = instance.selectAChar();
        int expResult = 0;
        int result = instance.playGame(guess);
        assertEquals(expResult, result);
        result = instance.playGame(instance.selectAChar(true));
        assertEquals(2, result);
        result = instance.playGame(instance.selectAChar(false));
        assertEquals(2, result);
        result = instance.playGame(instance.selectAChar(true));
        assertEquals(2, result);
        result = instance.playGame(instance.selectAChar(false));
        assertEquals(2,result);
        result = instance.playGame(instance.selectAChar(true));
        assertEquals(2,result);
        result = instance.playGame(instance.selectAChar(true));
        assertEquals(3,result);
 
        instance.startNewGame();
        result = instance.playGame('c');
        assertEquals(0,result);
        result = instance.playGame('o');
        assertEquals(0,result);
        result = instance.playGame('m');
        assertEquals(0,result);
        result = instance.playGame('p');
        assertEquals(0,result);
        result = instance.playGame('u');
        assertEquals(0,result);
        result = instance.playGame('t');
        assertEquals(0,result);
        result = instance.playGame('e');
        assertEquals(0,result);
        result = instance.playGame('r');
        assertEquals(1,result);
    }
    
}
