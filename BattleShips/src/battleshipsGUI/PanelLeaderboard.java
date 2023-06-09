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
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 * The panel that displays the leaderboard. It contains a table with ranks,
 * player names, and scores. Implements the graphical user interface for the
 * leaderboard. Provides methods to update and display the leaderboard data.
 * Contains a back button to return to the game over screen.
 *
 * @author oliver
 */
public class PanelLeaderboard extends JPanel {

    private JTable leaderboardTable;
    private DefaultTableModel tableModel;
    private Object[][] users;
    private JPanel titlePanel;
    private JLabel titleLabel;
    private JPanel tablePanel;
    private JPanel buttonPanel;
    private JButton backButton;

    /**
     * Constructs a PanelLeaderboard object.
     *
     * @param users the leaderboard data as a two-dimensional object array
     * @param frameGameOver the FrameGameOver object to return to the game over
     * screen
     */
    public PanelLeaderboard(Object[][] users, FrameGameOver frameGameOver) {
        System.out.println("leaderboard reached");
        // Initialize and customize GUI components
        this.users = users;
        // Set layout manager
        setBounds(0, 0, 800, 600);
        setBackground(Color.BLACK);

        setLayout(null);

        // Create the title label
        titlePanel = new JPanel();
        titlePanel.setBounds(50, 10, 700, 100);
        titlePanel.setBackground(Color.BLACK);

        titleLabel = new JLabel("LEADERBOARD");
        titleLabel.setFont(new Font("Menlo", Font.BOLD, 80));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);

        // Create the leaderboard table
        tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBounds(100, 120, 600, 340);
        tablePanel.setBackground(Color.BLACK);

        tableModel = new DefaultTableModel();
        leaderboardTable = new JTable(tableModel);

        // Add table columns
        tableModel.addColumn("RANK");
        tableModel.addColumn("PLAYER");
        tableModel.addColumn("SCORE");

        // Add the table to a scroll pane for scrolling if necessary
        JScrollPane scrollPane = new JScrollPane(leaderboardTable);
        scrollPane.setBounds(100, 120, 600, 340);
        scrollPane.setBackground(Color.BLACK);
        // Set the scroll pane's properties
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        // Add the scroll pane to the panel
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        // Set table properties
        leaderboardTable.setFont(new Font("Menlo", Font.PLAIN, 16));
        JTableHeader header = leaderboardTable.getTableHeader();
        header.setFont(new Font("Menlo", Font.BOLD, 24));
        leaderboardTable.setRowHeight(30);
        leaderboardTable.setEnabled(false);

        // Disable column reordering
        leaderboardTable.getTableHeader().setReorderingAllowed(false);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        leaderboardTable.setDefaultRenderer(Object.class, centerRenderer);
        // Get the table header and set the custom renderer
        header.setDefaultRenderer(centerRenderer);

        leaderboardTable.setGridColor(Color.BLACK);
        leaderboardTable.setBackground(Color.BLACK);
        leaderboardTable.setForeground(Color.WHITE);
        header.setBackground(Color.BLACK);
        header.setForeground(Color.WHITE);

        // Create a custom border for the table
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2); // Customize the color and thickness
        // Set the custom border on the table
        leaderboardTable.setBorder(border);

        buttonPanel = new JPanel();
        buttonPanel.setBounds(300, 480, 200, 90);
        buttonPanel.setBackground(Color.BLACK);

        backButton = new JButton("BACK");
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.BLACK);

        // Set the preferred size of the button
        backButton.setPreferredSize(new Dimension(200, 50));
        backButton.setFont(new Font("Menlo", Font.PLAIN, 24));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                frameGameOver.setAllVisible(true);
                frameGameOver.revalidate();
                frameGameOver.repaint();
            }
        });
        buttonPanel.add(backButton);

        // Retrieve and display leaderboard data
        updateLeaderboard(users);

        add(titlePanel);
        add(tablePanel);
        add(buttonPanel);

        setVisible(true);
    }

    /**
     * Updates the leaderboard table with new data.
     *
     * @param users the leaderboard data as a two-dimensional object array
     */
    public void updateLeaderboard(Object[][] users) {
        // Clear the existing table data
        tableModel.setRowCount(0);

        // Populate the table with the retrieved data
        for (Object[] rowData : users) {
            tableModel.addRow(rowData);
        }
    }
}
