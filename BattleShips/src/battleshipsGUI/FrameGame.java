package battleshipsGUI;

import java.awt.*;
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
    private JLabel playerResultLabel;
    private JLabel enemyClickLabel;
    private JLabel enemyResultLabel;

    private Controller controller;
    private Model model;

    private int turnCounter = 1;

    public FrameGame(Controller controller, Model model) {
        this.controller = controller;
        this.model = model;

        // set enemy grid
        controller.setEnemyGrid();

        // Create a new JFrame for the game window
        gameFrame = new JFrame();
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setSize(1000, 600);
        gameFrame.setLayout(new BorderLayout());

        // Create a panel for the top section
        JPanel topPanel = new JPanel(new GridLayout(4, 1));

        // Create a panel for the first row
        JPanel row1Panel = new JPanel(new BorderLayout());
        turnCounterLabel = new JLabel("Turn: " + turnCounter);
        turnCounterLabel.setFont(new Font("Arial", Font.BOLD, 16));
        row1Panel.add(turnCounterLabel, BorderLayout.WEST);
        topPanel.add(row1Panel);

        // Create a panel for the second row
        JPanel row2Panel = new JPanel(new BorderLayout());
        playerClickLabel = new JLabel("Player clicked cell: ");
        playerClickLabel.setFont(new Font("Arial", Font.BOLD, 16));
        row2Panel.add(playerClickLabel, BorderLayout.CENTER);
        playerResultLabel = new JLabel(" test");
        playerResultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        row2Panel.add(playerResultLabel, BorderLayout.EAST);
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
        enemyResultLabel = new JLabel(" test");
        enemyResultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        row4Panel.add(enemyResultLabel, BorderLayout.EAST);
        topPanel.add(row4Panel);

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
    }

    public void updateUserClickLabel(String cell) {
        playerClickLabel.setText("Player clicked cell: " + cell);
    }

    // player's turn is over, next is AI turn
    public void updateTurn() {
        turnCounter++;
        turnCounterLabel.setText("Turn: " + turnCounter);
        controller.updateTurn();
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
        playerResultLabel.setText(result);
    }

    public void updateEnemyResultLabel(String result) {
        enemyResultLabel.setText(result);
    }
}
