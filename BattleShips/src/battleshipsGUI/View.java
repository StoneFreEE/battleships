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
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

/**
 *
 * @author oliver
 */
public class View extends JFrame implements Observer{
    // Title
    public JPanel titlePanel;
    public JLabel titleLabel;
    public JButton startButton;
    private JPanel buttonPanel;
    private Font titleFont = new Font("Menlo", Font.BOLD, 80);
    
    // Game
    public LoadBoardPanel loadBoardPanel;
    
    // Score
    private LeaderboardPanel leaderboardPanel;
    
    private Controller controller;
    
    private Container con;
       
    public View() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        
        getContentPane().setBackground(Color.BLACK);
        setLayout(null);
        setVisible(true);   
        con = getContentPane();
        
        titlePanel = new JPanel();
        titlePanel.setBounds(100, 100, 600, 150);
        titlePanel.setBackground(Color.BLACK);
        
        titleLabel = new JLabel("BATTLESHIPS");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(titleFont);
        
        titlePanel.add(titleLabel);

        buttonPanel = new JPanel();
        buttonPanel.setBounds(300, 400, 200, 100);
        buttonPanel.setBackground(Color.BLACK);
        
        JButton startButton = new JButton("START");
        startButton.setBackground(Color.BLACK);
        startButton.setForeground(Color.BLACK);
        
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action to perform when the Start button is clicked
                controller.startGame();
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
        titlePanel.setVisible(false);
        buttonPanel.setVisible(false);
        
        // load board
        loadBoardPanel = new LoadBoardPanel(controller);
        add(loadBoardPanel);
    }
    
    public void playGame() {
        // Create game grid
    }

    public void displayLeaderboard(Object[][] users) {
        leaderboardPanel = new LeaderboardPanel(users);
        getContentPane().removeAll();
        add(leaderboardPanel);
        revalidate();
        repaint();
    }
    
    @Override
    public void update(Observable o, Object arg) {
       if (arg instanceof Object[][]) {
            // Handle score update
            Object[][] users = (Object[][]) arg;
            displayLeaderboard(users);
        }
    }
}
