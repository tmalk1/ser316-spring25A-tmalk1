import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Word Guessing Game!");
        System.out.print("Enter your name: ");
        String playerName = scanner.nextLine();

        Game game = Game.createGame(playerName);
      

        while (game.getGameStatus() == GameStatus.IN_PROGRESS){
            System.out.print("Enter your guess: ");
            String guess = scanner.nextLine();

            double result = game.makeGuess(guess);

            if (result == 0.0) {
                System.out.println("🎉 Congratulations! You won! Your score: " + game.getPoints());
                break;
            } else if (result == 5.0) {
                System.out.println("😢 Game over! The correct word was: " + game.getAnswer());
                break;
            } else {
                System.out.println("❌ Incorrect guess! Current score: " + game.getPoints());
            }
        }

        game.updateLeaderboard(); 
        game.displayLeaderboard();

        scanner.close();
    }
}
