/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battleshipsGUI;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author oliver
 */
public class LeaderboardPanel extends JPanel{
    private JTable leaderboardTable;
    private DefaultTableModel tableModel;
    private Object[][] users;
    
    public LeaderboardPanel(Object[][] users) {
        // Initialize and customize GUI components
        this.users = users;
        // Set layout manager
        setLayout(new BorderLayout());
        setFont(new Font("Arial", Font.PLAIN, 16));
        
        // Create the title label
        JLabel titleLabel = new JLabel("Leaderboard");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);
        
        // Create the leaderboard table
        tableModel = new DefaultTableModel();
        leaderboardTable = new JTable(tableModel);
        
        // Add table columns
        tableModel.addColumn("Rank");
        tableModel.addColumn("Player");
        tableModel.addColumn("Score");
        
        // Add the table to a scroll pane for scrolling if necessary
        JScrollPane scrollPane = new JScrollPane(leaderboardTable);
        // Add the scroll pane to the panel
        add(scrollPane, BorderLayout.CENTER);
        
         // Set table properties
        leaderboardTable.setFont(new Font("Arial", Font.PLAIN, 16));
        leaderboardTable.setRowHeight(users.length);
        
        // Retrieve and display leaderboard data
        updateLeaderboard(users);
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
