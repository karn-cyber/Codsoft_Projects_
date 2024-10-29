@ -0,0 +1,89 @@
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGameGUI extends JFrame {
    private JTextField guessInput;
    private JLabel feedbackLabel;
    private JButton guessButton, playAgainButton;
    private int randomNumber, attempts, maxAttempts;
    private Random random;

    public NumberGuessingGameGUI() {
        setTitle("Number Guessing Game");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        random = new Random();
        randomNumber = random.nextInt(100) + 1;
        attempts = 0;
        maxAttempts = 5;
        
        JLabel instructionLabel = new JLabel("Guess a number between 1 and 100:");
        instructionLabel.setBounds(30, 20, 300, 20);
        add(instructionLabel);
        
        guessInput = new JTextField();
        guessInput.setBounds(30, 50, 100, 30);
        add(guessInput);
        
        feedbackLabel = new JLabel("You have 5 attempts.");
        feedbackLabel.setBounds(30, 90, 300, 20);
        add(feedbackLabel);
        
        guessButton = new JButton("Guess");
        guessButton.setBounds(150, 50, 100, 30);
        add(guessButton);
        
        playAgainButton = new JButton("Play Again");
        playAgainButton.setBounds(150, 120, 100, 30);
        playAgainButton.setEnabled(false);
        add(playAgainButton);
        
        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int guess = Integer.parseInt(guessInput.getText());
                attempts++;
                if (guess == randomNumber) {
                    feedbackLabel.setText("Correct! You've won in " + attempts + " attempts.");
                    guessButton.setEnabled(false);
                    playAgainButton.setEnabled(true);
                } else if (guess < randomNumber) {
                    feedbackLabel.setText("Too low! " + (maxAttempts - attempts) + " attempts left.");
                } else {
                    feedbackLabel.setText("Too high! " + (maxAttempts - attempts) + " attempts left.");
                }
                
                if (attempts >= maxAttempts && guess != randomNumber) {
                    feedbackLabel.setText("Out of attempts! The correct number was " + randomNumber);
                    guessButton.setEnabled(false);
                    playAgainButton.setEnabled(true);
                }
            }
        });
        
        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });
    }
    
    private void resetGame() {
        randomNumber = random.nextInt(100) + 1;
        attempts = 0;
        guessInput.setText("");
        feedbackLabel.setText("You have 5 attempts.");
        guessButton.setEnabled(true);
        playAgainButton.setEnabled(false);
    }
    
    public static void main(String[] args) {
        NumberGuessingGameGUI game = new NumberGuessingGameGUI();
        game.setVisible(true);
    }
}
