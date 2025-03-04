import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private JFrame frame;
    private JTextField guessField;
    private JTextArea outputArea;
    private JLabel pointsLabel;
    private Game game;

    public Main() {
        // Initialize Game
        String playerName = JOptionPane.showInputDialog("Enter Player Name:");
        if (playerName == null || playerName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Player name is required!", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        game = Game.createGame(playerName);

        // Initialize UI
        initializeGUI();
    }

    private void initializeGUI() {
        frame = new JFrame("Word Guessing Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 350);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Points Display
        pointsLabel = new JLabel("Points: " + game.getPoints(), SwingConstants.CENTER);
        pointsLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        frame.add(pointsLabel, gbc);

        // Output Area
        outputArea = new JTextArea(8, 30);
        outputArea.setEditable(false);
        outputArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        JScrollPane scrollPane = new JScrollPane(outputArea);
        gbc.gridy = 1;
        frame.add(scrollPane, gbc);

        // Input Field
        guessField = new JTextField(15);
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        frame.add(guessField, gbc);

        // Buttons
        JButton guessButton = new JButton("Guess");
        JButton restartButton = new JButton("Restart");

        gbc.gridx = 1;
        frame.add(guessButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        frame.add(restartButton, gbc);

        // Button Actions
        guessButton.addActionListener(new GuessHandler());
        restartButton.addActionListener(e -> restartGame());

        // Final UI Adjustments
        frame.setLocationRelativeTo(null); // Center window
        frame.setVisible(true); // Ensure UI appears
    }

    private void restartGame() {
        String playerName = JOptionPane.showInputDialog("Enter Player Name:");
        if (playerName != null && !playerName.trim().isEmpty()) {
            game = Game.createGame(playerName);
            outputArea.setText("");
            pointsLabel.setText("Points: " + game.getPoints());
        }
    }

    private class GuessHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String guess = guessField.getText().trim();
            if (guess.isEmpty())
                return;

            double result = game.makeGuess(guess);

            if (result == 0.0) {
                outputArea.append("Correct! You won!\n");
            } else if (result == 5.0) {
                outputArea.append("Game Over! You lost!\n");
            } else {
                outputArea.append("Wrong guess! Try again.\n");
            }

            pointsLabel.setText("Points: " + game.getPoints());
            guessField.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}
