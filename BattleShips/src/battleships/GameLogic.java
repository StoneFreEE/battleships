/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battleships;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author 64272
 */
// Manages gameplay logic
public class GameLogic {

    Player player;
    AIEnemy enemy;

    // arraylists to store points fired at..
    ArrayList<Point> enemyMissedPoints;
    ArrayList<Point> enemyHitPoints;

    public GameLogic(Player player, AIEnemy enemy) {
        this.player = player;
        this.enemy = enemy;
        this.enemyMissedPoints = new ArrayList<>();
        this.enemyHitPoints = new ArrayList<>();
    }

    //initialise AIEnemy's playerBoard and player's playerBoard
    public void startGame(int boardLength, int[] shipLengths) {
        this.player.testingInitBoard(boardLength, shipLengths);
        this.enemy.initBoard(boardLength, shipLengths);
    }

    // player fires cannon at enemy
    public void playerFireCannon() {
        Scanner scanner = new Scanner(System.in);
        Point userInput;
        Boolean repeat;

        // ask player what coordinate to fire cannon at
        do {
            System.out.println("\nEnemy's Ships Remaining: " + (this.enemy.ships.size() - this.enemy.shipsSunk) + "/" + this.enemy.ships.size());
            System.out.println("Your Ships Remaining: " + (this.player.ships.size() - this.player.shipsSunk) + "/" + this.player.ships.size());
            System.out.println("");
            System.out.println("Pick a coordinate to fire your cannon (Eg. C4): ");
            String tempuserInput = scanner.nextLine();
            userInput = enemy.board.parsePoint(tempuserInput);
            if (userInput != null) {
                repeat = false;
                // if input x, y is greater than length or less than 0 or is an already dead ship, prompt user to reinput
                if (userInput.x > this.enemy.board.getLength() - 1 || userInput.y > this.enemy.board.getLength() - 1
                        || userInput.x < 0 || userInput.y < 0
                        || this.enemy.board.isDeadNone(userInput)
                        || this.enemy.board.isHit(userInput)) {
                    repeat = true;
                }
            } else {
                repeat = true;
            }
            System.out.println("");
        } while (repeat);

        this.enemy.board.setDead(userInput);
        if (this.enemy.board.isHit(userInput)) {
            System.out.println("You got a hit !!\n");
        }
    }

    // enemy fires cannon at player
    public void enemyFireCannon() {
        Point shootPoint = new Point();
        Boolean isHit;
        if (!enemyHitPoints.isEmpty()) { //if there are hits on the board
            Point hitPoint = enemyHitPoints.get(0); //choose the first hit point to start hunting around
            Point nextPoint = getNextPoint(hitPoint); //get the next point to shoot at
            while (nextPoint != null && enemyHitPoints.contains(nextPoint)) { //keep hunting in that direction until a miss or the end of the ship is found
                hitPoint = nextPoint;
                nextPoint = getNextPoint(hitPoint);
            }
            if (nextPoint != null) { //if the end of the ship was found, backtrack to the original hit point and repeat the process for the other direction
                hitPoint = enemyHitPoints.get(0);
                nextPoint = getNextPoint(hitPoint);
                while (nextPoint != null && enemyHitPoints.contains(nextPoint)) {
                    hitPoint = nextPoint;
                    nextPoint = getNextPoint(hitPoint);
                }
            }
            shootPoint = (nextPoint != null) ? nextPoint : getRandomPoint(); //if all adjacent points to the hit point have been shot at, choose another hit point and repeat the process
        } else {
            shootPoint = getRandomPoint(); //if there are no hits on the board, randomly shoot at a point on the board
        }

        this.player.playerBoard.setDead(shootPoint);
        isHit = this.player.playerBoard.isHit(shootPoint);

        if (isHit) {
            this.enemyHitPoints.add(shootPoint);
            System.out.println("Enemy got a hit !!\n");
        } else {
            this.enemyMissedPoints.add(shootPoint);
        }
    }

    // helper methods for ai fire cannon
    private Point getNextPoint(Point hitPoint) {
        Point nextPoint = null;
        int x = hitPoint.x;
        int y = hitPoint.y;
        if (x > 0 && !enemyHitPoints.contains(new Point(x - 1, y))) {
            nextPoint = new Point(x - 1, y); //check up
        } else if (x < this.player.playerBoard.getLength() - 1 && !enemyHitPoints.contains(new Point(x + 1, y))) {
            nextPoint = new Point(x + 1, y); //check down
        } else if (y > 0 && !enemyHitPoints.contains(new Point(x, y - 1))) {
            nextPoint = new Point(x, y - 1); //check left
        } else if (y < this.player.playerBoard.getLength() - 1 && !enemyHitPoints.contains(new Point(x, y + 1))) {
            nextPoint = new Point(x, y + 1); //check right
        }
        return nextPoint;
    }

    private Point getRandomPoint() {
        Random rand = new Random();
        Point point = new Point();
        do {
            point = new Point(rand.nextInt(this.player.playerBoard.getLength()), rand.nextInt(this.player.playerBoard.getLength()));
        } while (enemyHitPoints.contains(point) || enemyMissedPoints.contains(point));
        return point;
    }

    // Takes one turn
    public void takeTurn() {
        System.out.println("         || YOUR BOARD ||                        || ENEMY BOARD ||\n");
        //this.player.playerBoard.printBoard();
        this.printBoardDisplay();
        //       this.enemy.board.printBoard();
        //this.enemy.board.printEnemyBoard();

        this.playerFireCannon();
        this.enemyFireCannon();
    }

    // prints double board display
    public void printBoardDisplay() {

        // print top of board
        System.out.print("    ");
        for (int i = 1; i <= this.player.playerBoard.getLength(); i++) {
            System.out.print(String.format("%2d", i) + " ");
        }
        System.out.print("\t");

        // print top of enemy board
        System.out.print("    ");
        for (int i = 1; i <= this.player.playerBoard.getLength(); i++) {
            System.out.print(String.format("%2d", i) + " ");
        }
        System.out.println("");

        // print board
        char letter1 = 'A';
        char letter2 = 'A';
        for (int i = 0; i < this.player.playerBoard.getLength(); i++) {
            // print letter coordinates
            System.out.print(letter1++ + "   ");

            for (int j = 0; j < this.player.playerBoard.getLength(); j++) {
                if (this.player.playerBoard.cells[i][j] == States.DEADNONE.ordinal()) {
                    System.out.print(" # ");
                } else if (this.player.playerBoard.cells[i][j] == States.DEADSHIP.ordinal()) {
                    System.out.print(" X ");
                } else if (this.player.playerBoard.cells[i][j] == States.SHIP.ordinal()) {
                    System.out.print(" O ");
                } else {
                    System.out.print(" . ");
                }
                if (j == this.player.playerBoard.getLength() - 1) {
                    System.out.print("\t");
                }
            }

            // print other board display
            System.out.print(letter2++ + "   ");
            for (int j = 0; j < this.player.playerBoard.getLength(); j++) {
                if (this.enemy.board.cells[i][j] == States.DEADNONE.ordinal()) {
                    System.out.print(" # ");
                } else if (this.enemy.board.cells[i][j] == States.DEADSHIP.ordinal()) {
                    System.out.print(" X ");
                } else {
                    System.out.print(" . ");
                }

            }
            System.out.println("");

        }
    }

    public boolean checkPlayerWin() {
        return (this.enemy.checkLose());
    }

    public boolean checkEnemyWin() {
        return (this.player.checkLose());
    }

}
