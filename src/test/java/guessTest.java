import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class guessTest {
    
    private Game game;
    
    @BeforeEach
    public void setUp() {
        game = new Game();
        game.initGame("lion", "Dr. M"); 
    }
    @Test
    @DisplayName("Test Correct Guess")
    void testCorrectGuess()
    
    {
        double response = game.makeGuess("lion");
        assertEquals(0.0, response, 0.0);
        assertEquals(1, game.getGameStatus()); // اللعبة فازت
    }

    @Test
    @DisplayName("Test Invalid Characters")
    void testInvalidCharacters() 
    {
        double response = game.makeGuess("l!0n");
        assertEquals(4.1, response, 0.0);
        assertTrue(game.getPoints() < 14);
        assertEquals(0, game.getGameStatus());
    }

    @Test
    @DisplayName("Test Too Short Guess")
    void testTooShortGuess() 
    {
        double response = game.makeGuess("li");
        assertEquals(2.2, response, 0.0);
        assertTrue(game.getPoints() < 14);
        assertEquals(0, game.getGameStatus());
    }

    @Test
    @DisplayName("Test Too Long Guess")
    void testTooLongGuess() 
    {
        double response = game.makeGuess("lioncub");
        assertEquals(2.1, response, 0.0);
        assertTrue(game.getPoints() < 14);
        assertEquals(0, game.getGameStatus());
    }

    @Test
    @DisplayName("Test Partial Match")
    void testPartialMatch() 
    {
        double response = game.makeGuess("liar");
        assertEquals(3.0, response, 0.0);
        assertEquals(0, game.getGameStatus()); 
    }

    @Test
    @DisplayName("Test Post-Game Guess")
    void testPostGameGuess()
     {
        game.makeGuess("lion"); 
        double response = game.makeGuess("bear"); 
        assertEquals(5.1, response, 0.0);
        assertEquals(1, game.getGameStatus()); 
    }
}

