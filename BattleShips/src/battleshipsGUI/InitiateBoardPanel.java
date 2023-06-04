/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battleshipsGUI;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author oliver
 */
public class InitiateBoardPanel extends JPanel {
    private Controller controller;
    
    private JPanel boardPanel;
    private JPanel diagnosticPanel;
    private int index = 0;
    
    private JLabel shipLengthLabel;
    private JLabel shipLengthNumberLabel;
    
    private int[] shipLengths;
    
    private PlayerBoard playerBoard;
    
    public InitiateBoardPanel(Controller controller, int[] shipLengths) {
        this.controller = controller;
        this.shipLengths = shipLengths;
        
        setLayout(null);
        setBounds(0, 0, 800, 600);
        setBackground(Color.BLACK);
        
        diagnosticPanel = new JPanel();
        diagnosticPanel.setBounds(275, 15, 250, 50);
        diagnosticPanel.setBackground(Color.BLACK);
        diagnosticPanel.setLayout(new GridLayout(1,2));
        
        shipLengthLabel = new JLabel("Ship Length:");
        shipLengthLabel.setFont(new Font("Munlo", Font.PLAIN, 16));
        shipLengthLabel.setForeground(Color.WHITE);
        diagnosticPanel.add(shipLengthLabel);
        
        shipLengthNumberLabel = new JLabel(shipLengths[index] + "");
        shipLengthNumberLabel.setFont(new Font("Munlo", Font.PLAIN, 16));
        shipLengthNumberLabel.setForeground(Color.WHITE);
        diagnosticPanel.add(shipLengthNumberLabel);
        
        boardPanel = new JPanel();
        boardPanel.setBounds(175, 100, 450, 450);
        boardPanel.setBackground(Color.WHITE);
        
        playerBoard = new PlayerBoard(shipLengths);
        playerBoard.draw();
        
        add(diagnosticPanel);
        add(boardPanel);
    }
}
