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
 *
 * @author oliver
 */
public class View extends JFrame implements Observer, KeyListener {

    private Model model;

    // Title
    public JPanel titlePanel;
    public JLabel titleLabel;
    public JButton startButton;
    private JPanel buttonPanel;
    private Font titleFont = new Font("Menlo", Font.BOLD, 80);

    // Name
    private JPanel namePanel;
    private JTextField textField;
    private JButton enterButton;

    // button font
    private Font buttonFont = new Font("Menlo", Font.PLAIN, 24);

    // Game
    public PanelLoadBoard loadBoardPanel;
    public PanelSaveBoard saveBoardPanel;
    public PanelPlaceShip placeShipPanel;
    public FrameGame frameGame;
    private User user;
    private GridPlayer grid;
    private FrameGameOver gameOverPanel;
    private String winner;

    // Score
    private PanelLeaderboard leaderboardPanel;

    private Controller controller;

    private Container con;

    public View(Model model) {
        this.model = model;
        initStartScreen();
        addKeyListener(this);
        setFocusable(true);

    }

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

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void startGame() {
        con.removeAll();

        // load board
        loadBoardPanel = new PanelLoadBoard(controller);
        con.add(loadBoardPanel);
        con.revalidate();
        con.repaint();
        setFocusable(true);

    }

    public void promptSave(GridPlayer grid) {
        System.out.println("Save");
        con.removeAll();

        // save board
        saveBoardPanel = new PanelSaveBoard(controller, grid);
        con.add(saveBoardPanel);
        con.revalidate();
        con.repaint();
    }

    public void initiateBoard(int[] shipLengths) {
        con.removeAll();
        placeShipPanel = new PanelPlaceShip(controller, shipLengths, user);
        con.add(placeShipPanel);
        con.revalidate();
        con.repaint();
        setFocusable(true);

    }

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

    public void gameOver(String winner, int score) {
        frameGame.dispose();
        con.removeAll();

        gameOverPanel = new FrameGameOver(controller, winner, score);

        con = gameOverPanel.getContentPane();
        con.revalidate();
        con.repaint();
        setFocusable(true);

    }

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

    public void displayErrorBoard() {
        JOptionPane.showMessageDialog(this,
                "Could not find board.",
                "Error",
                JOptionPane.PLAIN_MESSAGE);
        controller.startGame();
    }

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

    public GridPlayer getGrid() {
        return this.grid;
    }

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
                }
                else {
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

    public void displayErrorPlacementMessage() {
        placeShipPanel.displayErrorPlacementMessage();
    }

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
