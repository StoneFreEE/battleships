package battleshipsGUI;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.*;

/**
 * Represents the main game panel. Displays player and enemy grids along with
 * additional labels.
 */
public class FrameGame extends JFrame {

    private JFrame gameFrame;
    private JLabel userClickLabel;
    private JLabel errorLabel;
    private JLabel enemyTargetLabel;
    private JLabel turnCounterLabel;
    private JLabel playerClickLabel;
    private JLabel shipCellsRemainingLabel;
    private JLabel playerResultLabel;
    private JLabel enemyClickLabel;
    private JLabel enemyResultLabel;

    private Controller controller;
    private Model model;

    private int enemyShipsRemaining;
    private int turnCounter = 1;
    private int score;

    public FrameGame(Controller controller, Model model) {
        this.controller = controller;
        this.model = model;

        setFocusable(true);

        // set enemy grid
        controller.setEnemyGrid();

        this.enemyShipsRemaining = this.model.getEnemyGrid().getEnemy().ships.size() - this.model.getEnemyGrid().getEnemy().shipsSunk;
        this.score = this.model.getPlayerGrid().getUser().getScore();

// Create a new JFrame for the game window
        gameFrame = new JFrame();
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setSize(1000, 600);
        gameFrame.setLayout(new BorderLayout());

        // Create a panel for the top section
        JPanel topPanel = new JPanel(new GridLayout(5, 1));

        // Create a panel for the first row
        JPanel row1Panel = new JPanel(new BorderLayout());
        turnCounterLabel = new JLabel("Turn: " + turnCounter + "   Score: " + score);
        turnCounterLabel.setFont(new Font("Arial", Font.BOLD, 20));
        row1Panel.add(turnCounterLabel, BorderLayout.CENTER);
        shipCellsRemainingLabel = new JLabel("Enemy Ships Remaining: " + enemyShipsRemaining);
        shipCellsRemainingLabel.setFont(new Font("Arial", Font.BOLD, 20));
        row1Panel.add(shipCellsRemainingLabel, BorderLayout.EAST);

        topPanel.add(row1Panel);

        // Create a panel for the second row
        JPanel row2Panel = new JPanel(new BorderLayout());
        playerClickLabel = new JLabel("Player targeted cell: ");
        playerClickLabel.setFont(new Font("Arial", Font.BOLD, 16));
        row2Panel.add(playerClickLabel, BorderLayout.WEST);
        playerResultLabel = new JLabel("    ");
        playerResultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        row2Panel.add(playerResultLabel, BorderLayout.CENTER);
        topPanel.add(row2Panel);

        // create panel for third row
        JPanel row3Panel = new JPanel(new BorderLayout());
        errorLabel = new JLabel(" ");
        errorLabel.setFont(new Font("Arial", Font.BOLD, 16));
        errorLabel.setForeground(Color.RED);
        row3Panel.add(errorLabel, BorderLayout.WEST);
        topPanel.add(row3Panel);

        // Create a panel for the fourth row
        JPanel row4Panel = new JPanel(new BorderLayout());
        enemyClickLabel = new JLabel("Enemy targeted cell: ");
        enemyClickLabel.setFont(new Font("Arial", Font.BOLD, 16));
        row4Panel.add(enemyClickLabel, BorderLayout.WEST);
        enemyResultLabel = new JLabel("     ");
        enemyResultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        row4Panel.add(enemyResultLabel, BorderLayout.CENTER);
        topPanel.add(row4Panel);

        // Create a panel for the fifth row
        JPanel row5Panel = new JPanel(new BorderLayout());
        JLabel playerLabel = new JLabel("Player Board");
        playerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        row5Panel.add(playerLabel, BorderLayout.WEST);
        JLabel enemyLabel = new JLabel("Enemy Board");
        enemyLabel.setFont(new Font("Arial", Font.BOLD, 16));
        row5Panel.add(enemyLabel, BorderLayout.EAST);
        topPanel.add(row5Panel);

        // Add the top panel to the game frame
        gameFrame.add(topPanel, BorderLayout.NORTH);

        // Create panels for player and enemy grids
        JPanel playerPanel = new JPanel(new BorderLayout());
        JPanel enemyPanel = new JPanel(new BorderLayout());

        // Add player and enemy grids to the panels
        playerPanel.add(this.model.getPlayerGrid(), BorderLayout.CENTER);
        enemyPanel.add(this.model.getEnemyGrid(), BorderLayout.CENTER);

        // Add the panels to the game frame
        gameFrame.add(playerPanel, BorderLayout.WEST);
        gameFrame.add(enemyPanel, BorderLayout.EAST);

        // Set the game frame as visible
        gameFrame.setVisible(true);
        // Register the KeyEventDispatcher to capture key events
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE && e.getID() == KeyEvent.KEY_PRESSED) {
                    gameFrame.dispose();  // Close the FrameGame window
                    controller.initiateStartScreen();
                }
                return false;
            }
        });

    }

    public void updateUserClickLabel(String cell) {
        playerClickLabel.setText("Player targeted cell: " + cell);
    }

    // player's turn is over, next is AI turn
    public void updateTurn() {
        model.getPlayerGrid().getUser().setScore(model.getPlayerGrid().getUser().getScore() - 10);
        this.score = model.getPlayerGrid().getUser().getScore();
        // Decrement the score by 10 after each turn
        turnCounter++;
        turnCounterLabel.setText("Turn: " + turnCounter + "   Score: " + score);
        controller.updateTurn();
        checkWinner();

    }

    public void incrementScore() {
        score += 50;
    }

    public void updateErrorLabel(boolean isValid) {
        if (isValid) {
            errorLabel.setText(" ");
        } else {
            errorLabel.setText("Invalid point");
        }
    }

    public void updateEnemyTargetLabel(String cell) {
        enemyClickLabel.setText("Enemy targeted cell: " + cell);
    }

    public void updatePlayerResultLabel(String result) {
        playerResultLabel.setText("        " + result);
    }

    public void updateShipsRemaining() {
        this.model.getEnemyGrid().getEnemy().checkLose();
        this.enemyShipsRemaining = this.model.getEnemyGrid().getEnemy().ships.size() - this.model.getEnemyGrid().getEnemy().shipsSunk;
        shipCellsRemainingLabel.setText("Enemy Ships Remaining: " + enemyShipsRemaining);
    }

    public void updateEnemyResultLabel(String result) {
        enemyResultLabel.setText("        " + result);
    }

    // check if enemy or player has won and return the corresponding string
    public String checkWin() {
        // if enemy loses
        if (this.model.getEnemyGrid().getEnemy().checkLose()) {
            gameFrame.setVisible(false);

            return "PLAYER";
        } else if (this.model.getPlayerGrid().getUser().checkLose()) {
            gameFrame.setVisible(false);
            return "ENEMY";
        } else {
            return "nowin";
        }
    }

    public void checkWinner() {
        String winner = checkWin();
        if (winner.equals("PLAYER") || winner.equals("ENEMY")) {
            controller.gameOver(winner, score);
        }
    }

}
