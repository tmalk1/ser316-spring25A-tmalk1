import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GuessTest {

    private Game game;

    @BeforeEach
    public void setUp() {
        game = Game.createGame("Dr. M");
    }

    @Test
    @DisplayName("Test Invalid Characters")
    void testInvalidCharacters() {
        double response = game.makeGuess("l!0n");
        assertEquals(4.1, response, 0.0);
        assertTrue(game.getPoints() < 14);
        assertEquals(0, game.getGameStatus());
    }

    @Test
    @DisplayName("Test Too Short Guess")
    void testTooShortGuess() {
        double response = game.makeGuess("li");
        assertEquals(2.2, response, 0.0);
        assertTrue(game.getPoints() < 14);
        assertEquals(0, game.getGameStatus());
    }

    @Test
    @DisplayName("Test Too Long Guess")
    void testTooLongGuess() {
        double response = game.makeGuess("lioncub");
        assertEquals(2.1, response, 0.0);
        assertTrue(game.getPoints() < 14);
        assertEquals(0, game.getGameStatus());
    }
}
