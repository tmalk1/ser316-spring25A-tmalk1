import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class BlackBoxGiven {

    static Stream<Arguments> provideGames() {
        return Stream.of(
                Arguments.of(Game.createGame("Player1")),
                Arguments.of(Game.createGame("Player2")),
                Arguments.of(Game.createGame("Player3")),
                Arguments.of(Game.createGame("Player4")),
                Arguments.of(Game.createGame("Player5")));
    }

    @ParameterizedTest
    @MethodSource("provideGames")
    @DisplayName("Test Multiple Game Instances")
    void testMultipleGames(Game gameInstance) {
        String answer = gameInstance.getAnswer(); 
        double response = gameInstance.makeGuess(answer);
        assertEquals(0.0, response, 0.0);
        assertEquals(1, gameInstance.getGameStatus());
    }
}
