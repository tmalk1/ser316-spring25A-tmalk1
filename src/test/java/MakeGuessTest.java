import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals; // Static imports go after regular imports

public class MakeGuessTest {
    
    private Game game = new Game();

    @BeforeEach
    public void setUp() {
        game.initGame("horse", "Zach");
    }
    
    @Test
    @DisplayName("Test correct word guess")
    public void correctGuess() {
        double result = game.makeGuess("horse");
        assertEquals(0, result, 0.0001);
    }
}
