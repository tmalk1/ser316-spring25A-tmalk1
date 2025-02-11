import java.util.stream.Stream; // Moved before JUnit imports

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals; // Static imports go last
import static org.junit.jupiter.api.Assertions.assertTrue;

class BlackBoxGiven {

    // Method to supply different game instances to parameterized tests
    static Stream<Arguments> provideGuessingGameInstances() {
        return Stream.of(
                Arguments.of(new Game0()),
                Arguments.of(new Game1()),
                Arguments.of(new Game2()),
                Arguments.of(new Game3()),
                Arguments.of(new Game4())
        );
    }

    @ParameterizedTest
    @MethodSource("provideGuessingGameInstances")
    @DisplayName("Test Correct Guess")
    void testCorrectGuess(Game game) {
        game.initGame("lion", "Dr. M");
        double response = game.makeGuess("lion");
        assertEquals(0.0, response, 0.0);
        assertEquals(14, game.getPoints());
        assertEquals(1, game.getGameStatus());
    }

    @ParameterizedTest
    @MethodSource("provideGuessingGameInstances")
    @DisplayName("Test Incorrect Guess")
    void testIncorrectGuess(Game game) {
        game.initGame("lion", "Dr. M");
        double response = game.makeGuess("bear");
        assertEquals(2.0, response, 0.0);
        assertEquals(11, game.getPoints());
        assertEquals(0, game.getGameStatus());
    }

    @ParameterizedTest
    @MethodSource("provideGuessingGameInstances")
    @DisplayName("Test Partial Match")
    void testPartialMatch(Game game) {
        game.initGame("lion", "Dr. M");
        double response = game.makeGuess("liar");
        assertEquals(3.0, response, 0.0);
        assertEquals(12, game.getPoints());
        assertEquals(0, game.getGameStatus());
    }

    @ParameterizedTest
    @MethodSource("provideGuessingGameInstances")
    @DisplayName("Test Too Short Guess")
    void testTooShortGuess(Game game) {
        game.initGame("lion", "Dr. M");
        double response = game.makeGuess("li");
        assertEquals(2.2, response, 0.0);
        assertTrue(game.getPoints() < 14);
        assertEquals(0, game.getGameStatus());
    }

    @ParameterizedTest
    @MethodSource("provideGuessingGameInstances")
    @DisplayName("Test Too Long Guess")
    void testTooLongGuess(Game game) {
        game.initGame("lion", "Dr. M");
        double response = game.makeGuess("lioncub");
        assertEquals(2.1, response, 0.0);
        assertTrue(game.getPoints() < 14);
        assertEquals(0, game.getGameStatus());
    }

    @ParameterizedTest
    @MethodSource("provideGuessingGameInstances")
    @DisplayName("Test Repeated Guess")
    void testRepeatGuess(Game game) {
        game.initGame("lion", "Dr. M");
        game.makeGuess("lion");
        double response = game.makeGuess("lion");
        assertEquals(4.0, response, 0.0);
        assertTrue(game.getPoints() < 14);
        assertEquals(1, game.getGameStatus());
    }

    @ParameterizedTest
    @MethodSource("provideGuessingGameInstances")
    @DisplayName("Test Invalid Characters")
    void testInvalidCharacters(Game game) {
        game.initGame("lion", "Dr. M");
        double response = game.makeGuess("l!0n");
        assertEquals(4.1, response, 0.0);
        assertTrue(game.getPoints() < 14);
        assertEquals(0, game.getGameStatus());
    }

    @ParameterizedTest
    @MethodSource("provideGuessingGameInstances")
    @DisplayName("Test Single Letter in Word")
    void testLetterInWord(Game game) {
        game.initGame("lion", "Dr. M");
        double response = game.makeGuess("o");
        assertEquals(1.1, response, 0.0);
        assertTrue(game.getPoints() > 0);
        assertEquals(0, game.getGameStatus());
    }

    @ParameterizedTest
    @MethodSource("provideGuessingGameInstances")
    @DisplayName("Test Single Letter Not in Word")
    void testLetterNotInWord(Game game) {
        game.initGame("lion", "Dr. M");
        double response = game.makeGuess("z");
        assertEquals(1.0, response, 0.0);
        assertTrue(game.getPoints() > 0);
        assertEquals(0, game.getGameStatus());
    }

    @ParameterizedTest
    @MethodSource("provideGuessingGameInstances")
    @DisplayName("Test Maximum Attempts")
    void testMaxAttempts(Game game) {
        game.initGame("lion", "Dr. M");
        for (int i = 0; i < 10; i++) {
            game.makeGuess("bear");
        }
        double response = game.makeGuess("bear");
        assertEquals(5.0, response, 0.0);
        assertEquals(2, game.getGameStatus());
    }

    @ParameterizedTest
    @MethodSource("provideGuessingGameInstances")
    @DisplayName("Test Post-Game Guess")
    void testPostGameGuess(Game game) {
        game.initGame("lion", "Dr. M");
        game.makeGuess("lion");
        double response = game.makeGuess("bear");
        assertEquals(5.1, response, 0.0);
        assertEquals(1, game.getGameStatus());
    }
}
