import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

/**
 * Class for handling game logic for a Hangman game.
 * The game starts with a score of 10, and points are adjusted based on guesses.
 * The game is lost when the user makes 10 incorrect guesses without finding the word.
 */
public class Game {

    /** Holds the points for the game. */
    private int points;
    
    /** Holds the player's name. */
    private String playerName;

    /** Holds the answer for the current game. */
    private String answer;

    /** The path to the file holding the leaderboard. */
    private static final String LEADERBOARD_FILE = "leaderboard.txt";
    
    /** The number of attempts made by the player. */
    private int attempts;

    /** The status of the game: {0 - In progress, 1 - Game won, 2 - Game over}. */
    private int gameStatus;

    /** List of all guessed letters and words. */
    private final ArrayList<String> guesses = new ArrayList<>();

    /** List of all answers from makeGuess that were already returned. */
    private final ArrayList<Double> previousResults = new ArrayList<>();

    /** Reusable Random instance to avoid creating multiple objects. */
    private static final Random RANDOM = new Random(); // SER316 TASK 2 SPOT-BUGS FIX

    /**
     * Constructs a new game with a random word.
     * 
     * @param playerName The name of the player.
     */
    public Game(String playerName) {
        this.playerName = playerName;
        this.answer = getRandomWord(); // SER316 TASK 2 SPOT-BUGS FIX
        this.points = 5;
        this.gameStatus = 0;
    }

    /**
     * Constructs a new game with a given word and player name.
     * 
     * @param fixedWord The predefined word for the game.
     * @param playerName The name of the player.
     */
    public Game(String fixedWord, String playerName) {
        this.playerName = playerName;
        this.answer = fixedWord;
        this.points = 10;
        this.gameStatus = 0;
    }

    /**
     * Constructs a new game with no arguments, initializing with default values.
     */
    public Game() {
        this("", "");
    }

    /**
     * Initializes the game with a given answer and player name, clearing previous guesses.
     * 
     * @param answer The correct word for the game.
     * @param playerName The name of the player.
     */
    public void initGame(String answer, String playerName) {
        this.playerName = playerName;
        this.answer = answer;
        this.gameStatus = 0;
        this.guesses.clear();
        this.previousResults.clear();
        this.points = 10;
    }

    /**
     * Gets the name of the player.
     * 
     * @return The player's name.
     */
    public String getPlayerName() {
        return this.playerName;
    }

    /**
     * Gets the answer in lowercase.
     * 
     * @return The correct answer in lowercase.
     */
    public String getAnswer() {
        return this.answer.toLowerCase(Locale.ROOT); // SER316 TASK 2 SPOT-BUGS FIX
    }

    /**
     * Gets the current status of the game.
     * 
     * @return The game status.
     */
    public int getGameStatus() {
        return this.gameStatus;
    }

    /**
     * Sets the points for the game.
     * 
     * @param points The score to set.
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Gets the current points of the player.
     * 
     * @return The player's score.
     */
    public int getPoints() {
        return this.points;
    }

    /**
     * Counts the number of correctly guessed letters in the answer.
     * 
     * @return The count of correctly guessed letters.
     */
    public int countCorrectLetters() {
        int result = 0;
        for (char letter : this.answer.toCharArray()) {
            if (guesses.contains(String.valueOf(letter))) {
                result++;
            }
        }
        return result;
    }

    /**
     * Counts occurrences of a specific letter in the answer.
     * 
     * @param letter The letter to count.
     * @return The number of times the letter appears in the answer.
     */
    public int countLetters(char letter) {
        int count = 0;
        for (char c : this.answer.toCharArray()) {
            if (c == letter) {
                count++;
            }
        }
        return count;
    }

    /**
     * Checks if the player's guess is correct and updates the game state accordingly.
     * 
     * @param guess The player's guess.
     * @return A double representing the result of the guess.
     */
    public double makeGuess(String guess) {
        if (gameStatus != 0) {
            return 5.1;
        }

        if (guess.equalsIgnoreCase(answer)) {
            gameStatus = 1;
            points += 14;
            return 0.0;
        }

        if (!guess.matches("[a-zA-Z]+")) {
            points -= 3;
            return 4.1;
        }

        if (guesses.contains(guess)) {
            points -= 2;
            return 4.0;
        }

        guesses.add(guess);
        attempts++;

        if (guess.length() < answer.length()) {
            points -= 1;
            return 2.2;
        } else if (guess.length() > answer.length()) {
            points -= 1;
            return 2.1;
        }

        int correctLetters = countCorrectLetters();
        if (correctLetters > 0) {
            points += 3;
            return 3.0;
        }

        points -= 3;

        if (attempts >= 10) {
            gameStatus = 2;
            return 5.0;
        }

        return 2.0;
    }

    /**
     * Returns a random word from a predefined list.
     *
     * @return A random word.
     */
    private static String getRandomWord() { // SER316 TASK 2 SPOT-BUGS FIX
        String[] animals = {"dog", "horse", "pony", "cat", "lion", "bear", "lioncub"};
        return animals[RANDOM.nextInt(animals.length)];
    }
}
