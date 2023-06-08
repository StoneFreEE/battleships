/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battleshipsGUI;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author oliver
 */
public class PanelPlaceShip extends JPanel {

    private Controller controller;

    private JPanel boardPanel;
    private JPanel diagnosticPanel;
    private int index = 0;

    private JLabel shipLengthLabel;
    private JLabel shipLengthNumberLabel;
    private GridPlayer grid;
    private User user;

    private int[] shipLengths;

    private JPanel errorPanel;
    private JLabel errorLabel;

    private boolean playGameCalled = false; // Flag to track if playGame() has been called

    public PanelPlaceShip(Controller controller, int[] shipLengths, User user) {
        this.controller = controller;
        this.shipLengths = shipLengths;
        this.user = user;

        setLayout(null);
        setBounds(0, 0, 800, 600);
        setBackground(Color.BLACK);

        diagnosticPanel = new JPanel();
        diagnosticPanel.setBounds(280, 10, 400, 30);
        diagnosticPanel.setBackground(Color.BLACK);
        diagnosticPanel.setLayout(new GridLayout(1, 2));

        shipLengthLabel = new JLabel("Placing Ship Length:");
        shipLengthLabel.setFont(new Font("Munlo", Font.PLAIN, 16));
        shipLengthLabel.setHorizontalAlignment(SwingConstants.CENTER);
        shipLengthLabel.setForeground(Color.WHITE);
        diagnosticPanel.add(shipLengthLabel);

        shipLengthNumberLabel = new JLabel(shipLengths[index] + "");
        shipLengthNumberLabel.setFont(new Font("Munlo", Font.PLAIN, 16));
        shipLengthNumberLabel.setForeground(Color.WHITE);
        diagnosticPanel.add(shipLengthNumberLabel);

        errorPanel = new JPanel();
        errorPanel.setBounds(260, 50, 250, 30);
        errorPanel.setBackground(Color.BLACK);

        errorLabel = new JLabel("");
        errorLabel.setFont(new Font("Munlo", Font.PLAIN, 16));
        errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        errorLabel.setForeground(Color.RED);
        errorPanel.add(errorLabel);

        boardPanel = new JPanel();
        boardPanel.setBounds(135, 90, 500, 500);
        boardPanel.setBackground(Color.BLACK);

        //playerBoard = new PlayerBoard(shipLengths);
        //playerBoard.draw();
        grid = new GridPlayer(controller, user);
        grid.setShipLength(shipLengths[index]);
        boardPanel.add(grid);

        add(diagnosticPanel);
        add(boardPanel);
        add(errorPanel);
        revalidate();
        repaint();
    }

    public void displayPossiblePoints(Coordinate[] points) {
        grid.displayPossiblePoints(points);
    }

    public void updateGrid(User user) {
        errorLabel.setText("");
        grid.updateGrid(user.board);

        if (index >= shipLengths.length - 1 && !playGameCalled) {
            // All ships have been placed
            playGameCalled = true; // Set the flag to true to prevent further calls
            controller.playGame();
            return;
        }

        index++; // Increment the index to move to the next ship length

        // Check if the next ship length is valid
        if (index < shipLengths.length) {
            grid.setShipLength(shipLengths[index]);
            shipLengthNumberLabel.setText(shipLengths[index] + "");
        }
    }

    public GridPlayer getGrid() {
        System.out.println("Hello");
        return grid;
    }

    public void displayErrorPlacementMessage() {
        errorLabel.setText("Invalid point");
        System.out.println("invalid");
    }
}
