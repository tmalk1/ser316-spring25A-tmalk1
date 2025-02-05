import org.junit.Test;
import static org.junit.Assert.*;

public class guessTest {

    @Test
    public void testCorrectWordGuess() {
        Game game = new Game("apple", "Player");
        double result = game.makeGuess("apple");
        assertEquals(0.0, result, 0.001); // 0.0 تعني أن الكلمة صحيحة
        assertEquals(1, game.getGameStatus()); // يجب أن يكون Status = فوز
    }

    @Test
    public void testCorrectLetterGuess() {
        Game game = new Game("apple", "Player");
        double result = game.makeGuess("p");
        assertEquals(1.2, result, 0.001); // 'p' تظهر مرتين
        assertEquals(7, game.getPoints()); // النقاط تصبح 7 (5 + 2)
    }

    @Test
    public void testIncorrectWordSameLength() {
        Game game = new Game("apple", "Player");
        double result = game.makeGuess("grape");
        assertEquals(2.0, result, 0.001); // نفس الطول لكن الكلمة خاطئة
        assertEquals(6, game.getPoints()); // النقاط تصبح 6 (5 + 1)
    }

    @Test
    public void testTooLongWord() {
        Game game = new Game("cat", "Player");
        double result = game.makeGuess("tiger");
        assertEquals(2.1, result, 0.001); // الكلمة طويلة جدًا
        assertEquals(2, game.getPoints()); // النقاط تصبح 2 (5 - 3)
    }

    @Test
    public void testTooShortWord() {
        Game game = new Game("elephant", "Player");
        double result = game.makeGuess("rat");
        assertEquals(2.2, result, 0.001); // الكلمة قصيرة جدًا
        assertEquals(6, game.getPoints()); // النقاط تصبح 6 (8 - 2)
    }

    @Test
    public void testPartiallyCorrectWord() {
        Game game = new Game("banana", "Player");
        double result = game.makeGuess("band");
        assertEquals(3.0, result, 0.001); // الكلمة جزئيًا صحيحة
        assertEquals(7, game.getPoints()); // النقاط تصبح 7 (5 + 2)
    }

    @Test
    public void testAlreadyGuessed() {
        Game game = new Game("zebra", "Player");
        game.makeGuess("z"); // تخمين أول مرة
        double result = game.makeGuess("z"); // تخمين نفس الحرف مرة ثانية
        assertEquals(4.0, result, 0.001); // الحرف سبق تخمينه
        assertEquals(3, game.getPoints()); // النقاط تصبح 3 (5 - 2)
    }

    @Test
    public void testInvalidCharacterInput() {
        Game game = new Game("tiger", "Player");
        double result = game.makeGuess("t1ger");
        assertEquals(4.1, result, 0.001); // الإدخال غير صحيح (يحتوي على رقم)
        assertEquals(2, game.getPoints()); // النقاط تصبح 2 (5 - 3)
    }

    @Test
    public void testMaxGuessesReached() {
        Game game = new Game("giraffe", "Player");
        for (int i = 0; i < 10; i++) {
            game.makeGuess("x"); // 10 تخمينات خاطئة
        }
        double result = game.makeGuess("giraffe"); // التخمين الـ 11
        assertEquals(5.1, result, 0.001); // اللعبة انتهت
        assertEquals(2, game.getGameStatus()); // يجب أن يكون Status = Game Over
    }
}
