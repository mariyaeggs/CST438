package com.csumb.cst438.a1;

import static com.csumb.cst438.a1.MyHttpServer.RESOURCE_DIR;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Game contains logic to play hangman game. Includes code to generate new
 * words, process user guess, determine win or lose.
 *
 * @author David, Mariya Eggensperger (modifications)
 * @version 1.0
 */
public class Game {

    private Random generator;
    private int state;
    //  1 means no incorrect guesses, or start of game
    //  2 means 1 incorrect guess.
    //  3 means 2 incorrect guesses.
    //  4 means 3 incorrect guesses.
    //  5 means 4 incorrect guesses.
    //  6 means 5 incorrect guesses.
    //  7 means 6 incorrect guesses, user has lost game

    private String word;   // the word to be guessed
    private StringBuffer displayWord; // part of the word (if any) to show user
    private ArrayList<String> wordlist;  // list of words

    public Game() {
        word = "computer";
        createDisplayWord();
        state = 1; // no incorrect guesses, or start of game state
        wordlist = null;
        generator = new Random();
    }

    public int getState() {
        return state;
    }

    public String getWord() {
        return word;
    }

    public String getDisplayWord() {
        return displayWord.toString();
    }

    public void startNewGame() {
        state = 1;
        word = randomWord();
        createDisplayWord();

    }

    /*
     * Process a user guess return page saying game over user win or lost, or
     * return page with updated game display
     * return 0 = continue game, good guess
     *        1 = good guess.  Win game.
     *        2 = bad guess.  continue game
     *        3 = bad guess.  Lost game.
     */
    public int playGame(char guess) {
        boolean correctGuess = updateDisplayWord(guess);
        if (correctGuess == false) {
            state++;
            if (state == 7) {
                // user has lost game
                return 3;
            } else {
                return 2; // bad guess, continue
            }
        } else if (displayWord.indexOf("_") >= 0) {
            return 0; // continue game, with good guess
        } else {
            return 1; // all characters has been guessed, user has won game.
        }
    }

    /**
     * Update display word to show any occurrences of guess There is a space
     * character between each letter, and any letters remaining to be guessed
     * are displayed as underscore
     *
     * @param guess
     * @return true if at least one match, false if guess is incorrect
     */
    private boolean updateDisplayWord(char guess) {
        boolean correctGuess = false;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guess) {
                displayWord.replace(2 * i, 2 * i + 1, Character.toString(guess));
                correctGuess = true;
            }
        }
        return correctGuess;

    }

    /**
     * Create the display version of word with letters replaced by underscore
     * for letter remaining to be guessed
     *
     * @param word
     * @return
     */
    private void createDisplayWord() {
        displayWord = new StringBuffer("_");
        for (int i = 1; i < word.length(); i++) {
            displayWord.append(" _");
        }

    }

    /**
     * Generate random word.
     *
     * @param wordlist array
     * @return a word from the list
     */
    private String randomWord() {
        try {
            if (wordlist == null) {
                wordlist = new ArrayList<String>();
                // read in word list
                Scanner infile = new Scanner(new File(RESOURCE_DIR + "wordlist.txt"));
                while (infile.hasNextLine()) {
                    wordlist.add(infile.nextLine());
                }
                infile.close();
            }
            int t = generator.nextInt(wordlist.size());
            return wordlist.get(t);

        } catch (Exception e) {
            System.out.println("Error randomWord: reading wordlist. " + e.getMessage());
            System.exit(0);
            return null; // to keep compiler happy
        }
    }

    /**
     * Assignment Part 1 modification Returns a random character from a String
     * word.
     *
     * @param word
     * @return word.charAt(index)
     */
    public char selectAChar() {
        try {
            // Random random = new Random();
            int index = generator.nextInt(word.length()); // Where 'word' is String word
            return word.charAt(index);
        } catch (Exception e) {
            System.out.println("Error. Char could not be returned.");
            System.exit(0);
            return ' '; // to keep compiler happy
        }

    }

    /**
     * Assignment Part 1 modification Returns a random lowercase character from
     * a String word; otherwise, returns a random uppercase character from
     * String word.
     *
     * @param word
     * @return lowercase/uppercase word
     */
    public char selectNoChar(boolean toLowerCaseWord) {
        try {
            Random random = new Random();
            char character = (char) (random.nextInt(26) + 'a');
            char charToLower = character;
            Character.toUpperCase(character);
            if (word.indexOf(charToLower) > -1 || word.indexOf(character) > -1) {
                return selectNoChar(toLowerCaseWord);
            } else {
                if (toLowerCaseWord) {
                    return charToLower;
                } else {
                    return character;
                }
            }
        } catch (Exception e) {
            System.out.println("Error. Method could not extract random lowercase/uppercase characters.");
            System.exit(0);
            return ' '; // to keep compiler happy
        }

    }

}
