/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battleshipsGUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.*;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

/**
 * The View class represents the GUI view of the Battleships game. It extends
 * the JFrame class and implements the Observer and KeyListener interfaces. It
 * displays the start screen, game screens, leaderboard, and handles user
 * interactions.
 */
public class View extends JFrame implements Observer, KeyListener {

    /**
     * The model object associated with the view.
     */
    private Model model;

    // Title
    public JPanel titlePanel;
    public JLabel titleLabel;
    public JButton startButton;
    public JPanel buttonPanel;
    public Font titleFont = new Font("Menlo", Font.BOLD, 80);

    // Name
    public JPanel namePanel;
    public JTextField textField;
    public JButton enterButton;

    // button font
    private Font buttonFont = new Font("Menlo", Font.PLAIN, 24);

    // Game
    public PanelLoadBoard loadBoardPanel;
    public PanelSaveBoard saveBoardPanel;
    public PanelPlaceShip placeShipPanel;
    public FrameGame frameGame;
    public User user;
    public GridPlayer grid;
    public FrameGameOver gameOverPanel;
    public String winner;

    // Score
    private PanelLeaderboard leaderboardPanel;

    private Controller controller;

    private Container con;

    /**
     * Constructs a View object with the specified model.
     *
     * @param model The model object to associate with the view.
     */
    public View(Model model) {
        this.model = model;
        initStartScreen();
        addKeyListener(this);
        setFocusable(true);

    }

    /**
     * Initializes the start screen of the game.
     */
    public void initiateStartScreen() {
        if (frameGame != null) {
            frameGame.dispose();
        }
        if (gameOverPanel != null) {
            gameOverPanel.dispose();
        }
        con.removeAll();
        initStartScreen();
        con.revalidate();
        con.repaint();
    }

    /**
     * Initializes the start screen of the game.
     */
    public void initStartScreen() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        getContentPane().setBackground(Color.BLACK);
        setLayout(null);
        setVisible(true);
        con = getContentPane();
        // title 
        titlePanel = new JPanel();
        titlePanel.setBounds(100, 100, 600, 150);
        titlePanel.setBackground(Color.BLACK);

        titleLabel = new JLabel("BATTLESHIPS");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(titleFont);

        titlePanel.add(titleLabel);

        JLabel infoLabel = new JLabel("press esc to return to the start menu");
        infoLabel.setForeground(Color.WHITE);
        infoLabel.setFont(new Font("Menlo", Font.PLAIN, 18));

        titlePanel.add(infoLabel);

        // start button
        buttonPanel = new JPanel();
        buttonPanel.setBounds(300, 400, 200, 100);
        buttonPanel.setBackground(Color.BLACK);

        JButton startButton = new JButton("START");
        startButton.setBackground(Color.BLACK);
        startButton.setForeground(Color.BLACK);
        // Set the preferred size of the button
        startButton.setPreferredSize(new Dimension(200, 50));
        startButton.setFont(buttonFont);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action to perform when the Start button is clicked
                promptName();
            }
        });
        buttonPanel.add(startButton);

        con.add(titlePanel);
        con.add(buttonPanel);
    }

    /**
     * Sets the controller object associated with the view.
     *
     * @param controller The controller object to associate with the view.
     */
    public void setController(Controller controller) {
        this.controller = controller;
    }

    /**
     * Starts the game by displaying the load board panel.
     */
    public void startGame() {
        con.removeAll();

        // load board
        loadBoardPanel = new PanelLoadBoard(controller);
        con.add(loadBoardPanel);
        con.revalidate();
        con.repaint();
        setFocusable(true);

    }

    /**
     * Prompts the user to save their board configuration.
     *
     * @param grid The GridPlayer object representing the player's board.
     */
    public void promptSave(GridPlayer grid) {
        System.out.println("Save");
        con.removeAll();

        // save board
        saveBoardPanel = new PanelSaveBoard(controller, grid);
        con.add(saveBoardPanel);
        con.revalidate();
        con.repaint();
    }

    /**
     * Initializes the board for placing ships with the specified ship lengths.
     *
     * @param shipLengths An array of ship lengths.
     */
    public void initiateBoard(int[] shipLengths) {
        con.removeAll();
        placeShipPanel = new PanelPlaceShip(controller, shipLengths, user);
        con.add(placeShipPanel);
        con.revalidate();
        con.repaint();
        setFocusable(true);

    }

    /**
     * Starts the game by displaying the game frame.
     */
    public void playGame() {
        con.removeAll();
        frameGame = new FrameGame(controller, model);
        controller.linkFrametoGrid(frameGame);

        con = frameGame.getContentPane();
        con.revalidate();
        con.repaint();
        dispose();
        setFocusable(true);

    }

    /**
     * Displays the game over screen with the winner and score.
     *
     * @param winner The name of the winner.
     * @param score The score of the game.
     */
    public void gameOver(String winner, int score) {
        frameGame.dispose();
        con.removeAll();

        gameOverPanel = new FrameGameOver(controller, winner, score);

        con = gameOverPanel.getContentPane();
        con.revalidate();
        con.repaint();
        setFocusable(true);

    }

    /**
     * Displays the leaderboard with the specified user data.
     *
     * @param users A 2D array representing the user data.
     */
    public void displayLeaderboard(Object[][] users) {
        if (leaderboardPanel != null) {
            con.remove(leaderboardPanel);
        }
        leaderboardPanel = new PanelLeaderboard(users, gameOverPanel);
        con.add(leaderboardPanel);
        con.revalidate();
        con.repaint();
        setFocusable(true);
    }

    /**
     * Updates the view based on changes in the model.
     */
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Object[][]) {
            // Handle score update
            Object[][] users = (Object[][]) arg;
        } else if (arg instanceof Boolean) {
            boolean valid = (boolean) arg;
            if (!valid) {
                displayErrorPlacementMessage();
            }
        } else if (arg instanceof User) {
            user = (User) arg;
            if (placeShipPanel != null) {
                placeShipPanel.updateGrid(user);
            }
        } else if (arg instanceof Coordinate[]) {
            Coordinate[] points = (Coordinate[]) arg;
            placeShipPanel.displayPossiblePoints(points);
        } else if (arg instanceof Board) {
            Board board = (Board) arg;
            this.grid = new GridPlayer(controller, user);
            grid.updateGrid(user.board);
            controller.playFromLoad();
        } else if (arg instanceof String) {
            String argument = (String) arg;
            if (argument.equals("invalidboard")) {
                displayErrorBoard();
            } else if (argument.contains("existingboard")) {
                String boardName = argument.substring("existingboard".length());
                displayOverwritePrompt(boardName);
            } else if (argument.equals("playgame")) {
                controller.playGame();
            }
        }
    }

    /**
     * Displays an error message when the board is not found.
     */
    public void displayErrorBoard() {
        JOptionPane.showMessageDialog(this,
                "Could not find board.",
                "Error",
                JOptionPane.PLAIN_MESSAGE);
        controller.startGame();
    }

    /**
     * Displays a prompt to overwrite an existing board.
     *
     * @param boardName The name of the existing board.
     */
    public void displayOverwritePrompt(String boardName) {
        int option = JOptionPane.showConfirmDialog(this,
                "Overwrite existing board?",
                "Existing Board",
                JOptionPane.YES_NO_OPTION);
        if (option == 0) {
            controller.updateBoard(boardName);
        } else if (option == 1) {
            promptSave(grid);
        }
    }

    /**
     * Retrieves the GridPlayer object representing the player's board.
     *
     * @return The GridPlayer object.
     */
    public GridPlayer getGrid() {
        return this.grid;
    }

    /**
     * Prompts the user to enter their name before starting the game.
     */
    private void promptName() {
        // name input panel
        namePanel = new JPanel();
        namePanel.setBounds(250, 400, 300, 150);
        namePanel.setBackground(Color.BLACK);

        // Get board name
        textField = new JTextField("Name...");
        textField.setBackground(Color.BLACK);
        textField.setForeground(Color.WHITE);
        textField.setFont(buttonFont);
        textField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textField.setText(""); // Clear the text field when it is clicked
            }
        });

        namePanel.add(textField);

        // enter button
        enterButton = new JButton("Enter");
        enterButton.setPreferredSize(new Dimension(200, 50));
        enterButton.setFont(buttonFont);

        namePanel.add(enterButton);
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = textField.getText();
                if (!name.equals("")) {
                    controller.setName(name);
                    namePanel.setVisible(false);
                    controller.startGame();
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Empty name",
                            "Error",
                            JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

        // Toggle to enterBoard panel
        buttonPanel.setVisible(false);
        namePanel.setVisible(true);
        add(namePanel);
        setFocusable(true);

    }

    /**
     * Displays an error message for invalid ship placement.
     */
    public void displayErrorPlacementMessage() {
        placeShipPanel.displayErrorPlacementMessage();
    }

    /**
     * Handles the key pressed event, specifically the ESC key to return to the
     * start menu.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            initiateStartScreen();
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
}
