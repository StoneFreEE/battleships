/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battleshipsGUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author 64272
 */
public class FrameGameOver extends JFrame {

    Controller controller;
    private JPanel titlePanel;
    private JLabel gameOverTitle;
    private JLabel scoreLabel;
    private Font buttonFont = new Font("Menlo", Font.PLAIN, 24);
    private Font titleFont = new Font("Menlo", Font.BOLD, 80);

    private JPanel buttonPanel;
    private JButton leaderboardBtn;
    private JButton playAgainbtn;

    private int score;
    private String winner;

    public FrameGameOver(Controller controller, String playerWin, int score) {
        this.controller = controller;
        this.winner = playerWin;
        this.score = score;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        getContentPane().setBackground(Color.BLACK);
        setLayout(null);
        setVisible(true);
        
        titlePanel = new JPanel();
        titlePanel.setBounds(100, 100, 600, 150);
        titlePanel.setBackground(Color.BLACK);

        gameOverTitle = new JLabel(winner + " WINS");
        gameOverTitle.setFont(titleFont);
        gameOverTitle.setForeground(Color.WHITE);
        titlePanel.add(gameOverTitle);
        
        scoreLabel = new JLabel("SCORE: "+ this.score);
        scoreLabel.setFont(buttonFont);
        scoreLabel.setForeground(Color.WHITE);
        titlePanel.add(scoreLabel);
        

        // Add buttons
        buttonPanel = new JPanel();
        buttonPanel.setBounds(300, 400, 200, 100);
        buttonPanel.setBackground(Color.BLACK);

        leaderboardBtn = new JButton("Leaderboard");
        leaderboardBtn.setForeground(Color.BLACK);
        leaderboardBtn.setFont(buttonFont);
        
        buttonPanel.add(leaderboardBtn);
        leaderboardBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // promptBoard();
                titlePanel.setVisible(false);
                buttonPanel.setVisible(false);
                controller.displayLeaderboard();
            }
        });

        playAgainbtn = new JButton("Play Again");
        playAgainbtn.setForeground(Color.BLACK);
        playAgainbtn.setFont(buttonFont);

        buttonPanel.add(playAgainbtn);
        playAgainbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // direct user to board initiating panel
                // controller.initiateBoard();
                controller.initiateStartScreen();
                dispose();
            }
        });

        add(buttonPanel);
        add(titlePanel);
    }

}
