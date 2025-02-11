import java.util.Scanner;
import java.nio.charset.StandardCharsets;

/**
 * This is the main class that initializes and runs the game.
 */
public class Main {
    
    // SER316 TASK 2 SPOT-BUGS FIX: Explicitly specify UTF-8 encoding to avoid reliance on default encoding
    static Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8); 

    /**
     * The main method that starts the game.
     * It initializes a Game instance and makes a sample guess.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Just some calls
        System.out.println("Getting started");
        Game game = new Game("Student");
        System.out.println("Current word: " + game.getAnswer());
        System.out.println(game.makeGuess("a"));
        System.out.println("Automatic guess a");

        // SER316 TASK 2 SPOT-BUGS FIX: Close scanner to prevent resource leak
        scanner.close();
    }
}
