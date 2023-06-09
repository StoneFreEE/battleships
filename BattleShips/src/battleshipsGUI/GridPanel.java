/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battleshipsGUI;

/**
 * An interface representing a grid panel that displays the game board.
 * Provides methods to update the grid and set the game panel.
 * 
 * Implementing classes are responsible for displaying the game board and handling user interactions.
 * They should update the grid when changes occur and communicate with the game panel for further actions.
 * 
 * The interface is used in the BattleshipsGUI application.
 * 
 * @author 64272
 */
public interface GridPanel {
    
    /**
     * Updates the grid panel with the provided game board.
     *
     * @param board the Board object representing the game board
     */
    void updateGrid(Board board);
    
    /**
     * Sets the game panel to the specified FrameGame object.
     *
     * @param panel the FrameGame object representing the game panel
     */
    void setGamePanel(FrameGame panel);
}
