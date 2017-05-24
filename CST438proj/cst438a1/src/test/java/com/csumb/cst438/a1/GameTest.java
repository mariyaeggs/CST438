package com.csumb.cst438.a1;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test of Game class
 *
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
     * Test of getState method, of class Game. at start of game, state should be
     * 1. a correct guess will not change the state an incorrect guess will
     * increase state by 1
     */
    @org.junit.Test
    public void testGetState() {
        System.out.println("getState");
        Game instance = new Game();
        int expResult = 1;
        int result = instance.getState();
        assertEquals(expResult, result);
        instance.playGame(instance.selectAChar()); // Refers to selectAChar();
        result = instance.getState();
        assertEquals(expResult, result);
        instance.playGame(instance.selectNoChar(true));// Refers to selectNoChar();
        result = instance.getState();
        assertEquals(expResult + 1, result);
    }

    /**
     * Test of getWord method, of class Game.
     */
    @org.junit.Test
    public void testGetWord() {
        System.out.println("getWord");
        Game instance = new Game();
        String expResult = instance.getWord(); // Modify expResult from 'computer'
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
        instance.playGame(instance.selectAChar());
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
        instance.playGame(instance.selectAChar()); // Refer to selectAChar()
        instance.playGame(instance.selectNoChar(true)); // Refer to selectNoChar()
        instance.startNewGame();
        int result = instance.getState();
        assertEquals(1, result);

    }

    /**
     * Test of playGame method, of class Game. correct guess should return 0 ,
     * or 1 when game is won incorrect guess should return 2, or 3 when game is
     * lost
     */
    @org.junit.Test
    public void testPlayGame() {
        System.out.println("playGame");
        Game instance = new Game();
        char guess = instance.selectAChar();
        int expResult = 0;
        int result = instance.playGame(guess);
        assertEquals(expResult, result);
        // Refer to selectNoChar() -->
        result = instance.playGame(instance.selectNoChar(true));
        assertEquals(2, result);
        result = instance.playGame(instance.selectNoChar(false));
        assertEquals(2, result);
        result = instance.playGame(instance.selectNoChar(true));
        assertEquals(2, result);
        result = instance.playGame(instance.selectNoChar(false));
        assertEquals(2, result);
        result = instance.playGame(instance.selectNoChar(true));
        assertEquals(2, result);
        result = instance.playGame(instance.selectNoChar(false));
        assertEquals(3, result);

        instance.startNewGame();

        /* Play game loop
        * 0 = continue game, good guess
        * 1 = good guess.  Win game.
        * 2 = bad guess.  continue game
        * 3 = bad guess.  Lost game.
         */
        int index = 0;
        while (- 1 < instance.getDisplayWord().indexOf("_")) {
            result = instance.playGame(instance.getWord().charAt(index));
            if (- 1 < instance.getDisplayWord().indexOf("_")) {
                assertEquals(0, result); // Continue game with good guess
            } else if (-1 == instance.getDisplayWord().indexOf("_")) {
                assertEquals(1, result); // Good guess, win game.
            }
            index++; // Fill all "_" slots
        }

    }

}
