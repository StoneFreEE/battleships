/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battleshipsGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Represents a panel for loading a game board.
 * Extends JPanel.
 */
public class PanelLoadBoard extends JPanel {

    public JLabel loadBoardTitle;
    public JPanel titlePanel;

    public JLabel loadBoardLabel;
    public JPanel buttonPanel;
    public JButton yesButton;
    public JButton noButton;

    public JPanel enterBoardPanel;
    public JTextField textField;
    public String boardName;
    public JButton cancelButton;
    public JButton enterButton;

    public Controller controller;

    public Font buttonFont = new Font("Menlo", Font.PLAIN, 24);
    public Font titleFont = new Font("Menlo", Font.BOLD, 80);

    public PanelPlaceShip initiateBoardPanel;
/**
     * Constructs a PanelLoadBoard object with the specified controller.
     *
     * @param controller The controller object associated with the game.
     */
    public PanelLoadBoard(Controller controller) {
        this.controller = controller;

        setLayout(null);
        setBounds(0, 0, 800, 600);
        setBackground(Color.BLACK);

        titlePanel = new JPanel();
        titlePanel.setBounds(100, 100, 600, 150);
        titlePanel.setBackground(Color.BLACK);

        loadBoardTitle = new JLabel("LOAD BOARD?");
        loadBoardTitle.setFont(titleFont);
        loadBoardTitle.setForeground(Color.WHITE);
        titlePanel.add(loadBoardTitle);

        // Add buttons
        buttonPanel = new JPanel();
        buttonPanel.setBounds(300, 400, 200, 100);
        buttonPanel.setBackground(Color.BLACK);

        yesButton = new JButton("Yes");
        yesButton.setForeground(Color.BLACK);
        yesButton.setFont(buttonFont);

        buttonPanel.add(yesButton);
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                promptBoard();
            }
        });

        noButton = new JButton("No");
        noButton.setForeground(Color.BLACK);
        noButton.setFont(buttonFont);

        buttonPanel.add(noButton);
        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // direct user to board initiating panel
                controller.initiateBoard();
            }
        });

        add(buttonPanel);
        add(titlePanel);
    }
/**
     * Retrieves the name of the loaded board.
     *
     * @return The name of the loaded board.
     */
    public String getBoardName() {
        return this.boardName;
    }

    private void promptBoard() {
        enterBoardPanel = new JPanel();
        enterBoardPanel.setBounds(250, 400, 300, 150);
        enterBoardPanel.setBackground(Color.BLACK);

        // Get board name
        textField = new JTextField("Board Name...");
        textField.setFont(new Font("Menlo", Font.PLAIN, 15));
        textField.setBackground(Color.BLACK);
        textField.setForeground(Color.WHITE);
        textField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textField.setText(""); // Clear the text field when it is clicked
            }
        });

        enterBoardPanel.add(textField);

        enterButton = new JButton("Enter");
        enterButton.setFont(new Font("Menlo", Font.PLAIN, 15));
        enterBoardPanel.add(enterButton);
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boardName = textField.getText();
                System.out.println(boardName);
                // Send user to game
                controller.loadBoard(boardName);
                removeAll();
            }
        });

        cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Menlo", Font.PLAIN, 15));
        enterBoardPanel.add(cancelButton);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Toggle to button panel
                enterBoardPanel.setVisible(false);
                buttonPanel.setVisible(true);
            }
        });
        add(enterBoardPanel);

        // Toggle to enterBoard panel
        buttonPanel.setVisible(false);
        enterBoardPanel.setVisible(true);
    }
}
