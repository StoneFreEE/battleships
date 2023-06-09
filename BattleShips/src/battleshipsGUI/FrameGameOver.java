package battleshipsGUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Represents the game over screen displayed when the game ends.
 */
public class FrameGameOver extends JFrame implements KeyListener {

    private Controller controller;
    private JPanel titlePanel;
    private JLabel gameOverTitle;
    private JLabel scoreLabel;
    private Font buttonFont = new Font("Menlo", Font.PLAIN, 24);
    private Font titleFont = new Font("Menlo", Font.BOLD, 80);

    private JPanel buttonPanel;
    private JButton leaderboardBtn;
    private JButton playAgainBtn;

    private int score;
    private String winner;

    public FrameGameOver(Controller controller, String winner, int score) {
        this.controller = controller;
        this.winner = winner;
        this.score = score;
        addKeyListener(this);
        controller.setScore(score);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        getContentPane().setBackground(Color.BLACK);
        setLayout(null);
        setVisible(true);

        // Create the title panel
        titlePanel = new JPanel();
        titlePanel.setBounds(100, 100, 600, 150);
        titlePanel.setBackground(Color.BLACK);

        gameOverTitle = new JLabel(winner + " WINS");
        gameOverTitle.setFont(titleFont);
        gameOverTitle.setForeground(Color.WHITE);
        titlePanel.add(gameOverTitle);

        scoreLabel = new JLabel("SCORE: " + this.score);
        scoreLabel.setFont(buttonFont);
        scoreLabel.setForeground(Color.WHITE);
        titlePanel.add(scoreLabel);

        // Create the button panel
        buttonPanel = new JPanel();
        buttonPanel.setBounds(300, 400, 200, 100);
        buttonPanel.setBackground(Color.BLACK);

        // Create the leaderboard button
        leaderboardBtn = new JButton("Leaderboard");
        leaderboardBtn.setForeground(Color.BLACK);
        leaderboardBtn.setFont(buttonFont);

        
        buttonPanel.add(leaderboardBtn);
        leaderboardBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setAllVisible(false);
                controller.displayLeaderboard();
            }
        });
        buttonPanel.add(leaderboardBtn);

        // Create the play again button
        playAgainBtn = new JButton("Play Again");
        playAgainBtn.setForeground(Color.BLACK);
        playAgainBtn.setFont(buttonFont);
        playAgainBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.initiateStartScreen();
                dispose();
            }
        });
        buttonPanel.add(playAgainBtn);

        // Add components to the frame
        add(buttonPanel);
        add(titlePanel);
        setFocusable(true);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            dispose();
            controller.initiateStartScreen();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used, but needs to be implemented
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Not used, but needs to be implemented
    }
    
    public void setAllVisible(boolean visibile) {
        titlePanel.setVisible(visibile);
        buttonPanel.setVisible(visibile);
    }
}
