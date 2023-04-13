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

    // 2d array to store board
    private int[][] board;
    private int x;
    private int y;

    public BoardDisplay(int x, int y) {
        this.x = x;
        // limit y value to between letters in alphabet
        if (y <= 26) {
            this.board = new int[y][x];
            this.y = y;
        } else{
            this.board = new int[26][x];
            this.y = 26;
        }
    }

    public void printBoard() {

        // print top of board
        System.out.print("    ");
        for (int i = 1; i <= x; i++) {
            System.out.print(i + " ");
        }
        System.out.println("\n");

        // print board
        char letter = 'A';
        for (int[] row : board) {
            // print letter coordinates
            System.out.print(letter + "   ");
            letter++;
            for (int element : row) {
                System.out.print(element + " ");
            }
            System.out.println("");
        }

    }
    
    public int[][] getBoard(){
        return this.board;
    }

}
