/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battleships;

/**
 *
 * @author 64272
 */
public class BoardDisplay {

    private Board gameBoard;
    
    public BoardDisplay(Board board){
        this.gameBoard = board;
    }
    
    public void printBoard() {

        // print top of board
        System.out.print("    ");
        for (int i = 1; i <= this.gameBoard.getX(); i++) {
            System.out.print(String.format("%2d", i) + " ");
        }
        System.out.println("\n");

        // print board
        char letter = 'A';
        for (int[] row : this.gameBoard.cells) {
            // print letter coordinates
            System.out.print(letter++ + "   ");
            for (int element : row) {
                System.out.print(String.format("%2d", element) + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

}
