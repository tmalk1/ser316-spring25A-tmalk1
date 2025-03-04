import java.io.*;
import java.nio.file.*;
import java.util.*;

enum GameStatus {
    IN_PROGRESS, WON, LOST
}

class Game {
    private int points;
    private String playerName;
    private String answer;
    private int attempts;
    private GameStatus gameStatus;
    private final Set<String> guesses = new HashSet<>();
    private static final Random RANDOM = new Random();
    private static final String LEADERBOARD_FILE = "leaderboard.txt";
    private static final Map<String, Integer> leaderboard = new HashMap<>();
    private static final List<String> WORDS = Arrays.asList("dog", "horse", "pony", "cat", "lion", "bear", "lioncub");

    private Game(String playerName, String answer) {
        this.playerName = playerName;
        this.answer = answer.toLowerCase(Locale.ROOT);
        this.points = 10;
        this.attempts = 0; // ✅ التأكد من تهيئة attempts
        this.gameStatus = GameStatus.IN_PROGRESS; // ✅ استخدام Enum بدلاً من int
        loadLeaderboard();
    }

    public static Game createGame(String playerName) {
        return new Game(playerName, getUniqueWord());
    }

    public String getAnswer() {
        return this.answer;
    }

    public GameStatus getGameStatus() { 
        return this.gameStatus;
    }

    public int getPoints() {
        return this.points;
    }

    public double makeGuess(String guess) {
        if (gameStatus != GameStatus.IN_PROGRESS)
            return 5.1;

        if (!guess.matches("[a-zA-Z]+")) {
            points -= 3;
            return 4.1; 
        }
        if (!guesses.add(guess)) {
            points -= 2;
            return 4.0; 
        }
        if (guess.equalsIgnoreCase(answer)) {
            gameStatus = GameStatus.WON;
            points += 14;
            return 0.0; 
        }
        attempts++;
        if (attempts >= 10) {
            gameStatus = GameStatus.LOST;
            return 5.0; 
        }
        return 2.0; 
    }

    private static String getUniqueWord() {
        List<String> shuffledWords = new ArrayList<>(WORDS);
        Collections.shuffle(shuffledWords);
        return shuffledWords.get(0); 
    }

    private void loadLeaderboard() {
        try {
            if (!Files.exists(Paths.get(LEADERBOARD_FILE))) { 
                Files.createFile(Paths.get(LEADERBOARD_FILE));
            }
            Files.lines(Paths.get(LEADERBOARD_FILE))
                    .map(line -> line.split(":"))
                    .forEach(parts -> leaderboard.put(parts[0], Integer.parseInt(parts[1])));
        } catch (IOException ignored) {
        }
    }

    public void updateLeaderboard() {
        leaderboard.put(playerName, Math.max(leaderboard.getOrDefault(playerName, 0), points));
        try {
            Files.write(Paths.get(LEADERBOARD_FILE),
                    () -> leaderboard.entrySet().stream()
                            .<CharSequence>map(e -> e.getKey() + ":" + e.getValue())
                            .iterator());
        } catch (IOException ignored) {
        }
    }

    public void displayLeaderboard() {
        System.out.println("🏆 Leaderboard:");
        leaderboard.entrySet().stream()
                .sorted((a, b) -> Integer.compare(b.getValue(), a.getValue()))
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
    }
}
