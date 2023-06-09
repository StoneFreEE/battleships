/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battleshipsGUI;

import java.awt.BorderLayout;
import java.awt.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author oliver
 */
public class PanelLeaderboard extends JPanel{
    private JTable leaderboardTable;
    private DefaultTableModel tableModel;
    private Object[][] users;
    private JPanel titlePanel;
    private JLabel titleLabel;
    private JPanel tablePanel;
    private JPanel buttonPanel;
    private JButton backButton;
    
    public PanelLeaderboard(Object[][] users) {
        System.out.println("leaderboard reached");
        // Initialize and customize GUI components
        this.users = users;
        // Set layout manager
        setBackground(Color.GREEN);

        setLayout(null);
        setVisible(true);
        
        // Create the title label
        titlePanel = new JPanel();
        titlePanel.setBounds(100, 20, 600, 150);
        titlePanel.setBackground(Color.GREEN);
        
        titleLabel = new JLabel("Leaderboard");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);
        
        // Create the leaderboard table
        tablePanel = new JPanel();
        tablePanel.setBounds(100, 200, 600, 400);
        tablePanel.setBackground(Color.GREEN);
        
        tableModel = new DefaultTableModel();
        leaderboardTable = new JTable(tableModel);
        
        // Add table columns
        tableModel.addColumn("Rank");
        tableModel.addColumn("Player");
        tableModel.addColumn("Score");
        
        // Add the table to a scroll pane for scrolling if necessary
        JScrollPane scrollPane = new JScrollPane(leaderboardTable);
        // Add the scroll pane to the panel
        tablePanel.add(scrollPane);
        
         // Set table properties
        leaderboardTable.setFont(new Font("Arial", Font.PLAIN, 16));
        leaderboardTable.setRowHeight(users.length);
        
        buttonPanel = new JPanel();
        buttonPanel.setBounds(300, 650, 200, 100);
        buttonPanel.setBackground(Color.GREEN);
        
        backButton = new JButton("BACK");
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.LIGHT_GRAY);
        
        // Set the preferred size of the button
        backButton.setPreferredSize(new Dimension(200, 50));
        backButton.setFont(new Font("Menlo", Font.PLAIN, 24));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("back");
            }
        });
        buttonPanel.add(backButton);
        
        // Retrieve and display leaderboard data
        updateLeaderboard(users);
        
        add(titlePanel);
        add(tablePanel);
        add(buttonPanel);
    }
    
    public void updateLeaderboard(Object[][] users) {
        // Clear the existing table data
        tableModel.setRowCount(0);
        
        // Populate the table with the retrieved data
        for (Object[] rowData : users) {
            tableModel.addRow(rowData);
        }
    }
    
    // Add other methods and event listeners as needed
}
