| ID # | Location (File and Line Number) | Problem Description                                    | Problem Category        | Severity      |
|------|---------------------------------|--------------------------------------------------------|-------------------------|---------------|
| 1    | Game.java (Line 47)             | `getName()` method returns answer instead of the name.  | FD (Functional Defect)  | High          |
| 2    | Game.java (Line 68)             | `gameStatus` should be an enum instead of an int.       | CS (Coding Standard)    | Medium        |
| 3    | Game.java (Line 120)            | `countCorrectLetters()` prints instead of returning the result. | FD (Functional Defect) | Medium        |
| 4    | Game.java (Line 203)            | `setRandomWord()` has unnecessary hardcoded values.     | MD (Maintainability Defect) | Medium        |
| 5    | Game.java (Line 250)            | `makeGuess()` lacks input validation before processing. | CG (Code Guideline Violation) | High          |
| 6    | Main.java (Line 10)             | `System.out.println()` should use logging.              | CS (Coding Standard)    | Low           |
| 7    | MakeGuessTest.java (Line 15)    | The test for `correctGuess()` does not check game status. | FD (Functional Defect) | Medium        |
