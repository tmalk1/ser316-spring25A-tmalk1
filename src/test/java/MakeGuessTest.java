import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MakeGuessTest {

    private Game game;

    @BeforeEach
    public void setUp() {
        game = Game.createGame("Dr. M"); 
    }

    @Test
    @DisplayName("Test Correct Guess")
    void testCorrectGuess() {
        String answer = game.getAnswer();
        double response = game.makeGuess(answer);
        assertEquals(0.0, response, 0.0);
        assertEquals(1, game.getGameStatus());
    }
}
