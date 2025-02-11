import java.util.Scanner;

/**
 * This is the main class that initializes and runs the game.
 */
public class Main {
    static Scanner scanner = new Scanner(System.in);

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
    }
}
