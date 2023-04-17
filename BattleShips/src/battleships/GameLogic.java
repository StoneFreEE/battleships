/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battleships;


/**
 *
 * @author 64272
 */
// Manages gameplay logic
public class GameLogic {
    Player player;
    AIEnemy enemy;

    public GameLogic(Player player, AIEnemy enemy){
        this.player = player;
        this.enemy = enemy;
    }
    
    //initialise AIEnemy's board and player's board
    public void startGame(int boardLength,int[]shipLengths) {
        
        this.player.testingInitBoard(boardLength, shipLengths);
        this.enemy.initBoard(boardLength, shipLengths);
        
        // TESTING
        this.player.board.printBoard();
        this.enemy.board.printBoard();
    }
    
    // TODO create function for takin turns firing cannon player.firecannon next enemy.firecannon etcetc
}

