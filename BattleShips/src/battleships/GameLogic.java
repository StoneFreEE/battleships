/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battleships;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author 64272
 */
// Manages gameplay logic
public class GameLogic {

    User user;
    Database database;
    AIEnemy enemy;
    boolean unique;
    Scanner scanner = new Scanner(System.in);

    // arraylists to store points fired at..
    ArrayList<Point> enemyMissedPoints;
    ArrayList<Point> enemyHitPoints;

    public GameLogic(User user, AIEnemy enemy, Database database) {
        this.user = user;
        this.enemy = enemy;
        this.enemyMissedPoints = new ArrayList<>();
        this.enemyHitPoints = new ArrayList<>();
        this.database = database;
        this.unique = false;
    }

    //initialise AIEnemy's playerBoard and player's playerBoard
    public void startGame(int boardLength, int[] shipLengths) throws IOException {
        ArrayList<Ship> ships = new ArrayList<Ship>();
        // Prompt for load file board
        do {
            String loadFileResponse = promptYesOrNo("Would you like to load a board file? (y/n)");
            if (loadFileResponse.equals("y")) {
                System.out.println("Filename: ");
                String loadFilename = scanner.nextLine();
                ships = loadBoard(loadFilename, boardLength);
                if (ships != null) {
                    this.user.initLoadFile(boardLength, shipLengths, ships);
                }
                else {
                    System.out.println("Invalid filename");
                }
            }
            else {
                this.user.initBoard(boardLength, shipLengths);
                String saveFileResponse = promptYesOrNo("Would you like to save this board as a file? (y/n)");
                if (saveFileResponse.equals("y")) {
                    // SAVE
                    System.out.println("Filename: ");
                    String saveFilename = scanner.nextLine();
                    saveBoard(saveFilename);
                }
            }
        }while (ships == null);
        this.enemy.initBoard(boardLength, shipLengths);
    }
    
    public String promptYesOrNo(String question) {
        String userLoadFile = "";
        // Loop until valid reponse
        do {
            System.out.println(question);
            userLoadFile = scanner.nextLine().toLowerCase();
            if (!userLoadFile.equals("y") && !userLoadFile.equals("n")) {
                System.out.println("Invalid Response, try again");
            }
        } while (!userLoadFile.equals("y") && !userLoadFile.equals("n"));
        
        return userLoadFile;
    }
    
    public ArrayList<Ship> loadBoard(String filename, int boardLength) throws FileNotFoundException, IOException {
        try (BufferedReader inStream = new BufferedReader(new FileReader("./resources/" + filename + ".txt"))) {
            String line = null;
            this.user.board = new Board(boardLength);
            // HashMap to store key/value pairs of origin and end points
            ArrayList<Ship> ships = new ArrayList<Ship>();
            
            // Load user textfile
            while ((line = inStream.readLine()) != null) {
                    // Split between whitespace
                    String[] split = line.split(" ");
                    Point firstPoint = this.user.board.parsePoint(split[0]);
                    Point endPoint = this.user.board.parsePoint(split[1]);
                    int length = Integer.parseInt(split[2]);
                    Ship ship = new Ship(length, firstPoint, endPoint);
                    ships.add(ship);
            }
            return ships;
        }
        catch (FileNotFoundException e) {
            return null;
        }
    }
    
    public void saveBoard(String filename) throws FileNotFoundException, IOException {
        try (PrintWriter pw = new PrintWriter(new FileOutputStream("./resources/" + filename + ".txt", false))) {
                // Output file
                for (Ship ship : this.user.ships) {
                    String origin = this.user.translatePoint(ship.origin);
                    String end = this.user.translatePoint(ship.endPoint);
                    pw.println(origin + " " + end + " " + ship.length);
                }
            }
    }

    // player fires cannon at enemy
    public void playerFireCannon() {
        Point userInput;
        Boolean repeat;

        // ask player what coordinate to fire cannon at
        do {
            System.out.println("\nEnemy's Ships Remaining: " + (this.enemy.ships.size() - this.enemy.shipsSunk) + "/" + this.enemy.ships.size());
            System.out.println("Your Ships Remaining: " + (this.user.ships.size() - this.user.shipsSunk) + "/" + this.user.ships.size());
            System.out.println("");
            
            do {
                System.out.println("Pick a coordinate to fire your cannon (Eg. C4): ");
                String tempUserInput = scanner.nextLine();
                userInput = enemy.board.parsePoint(tempUserInput);
                if (userInput == null) {
                    System.out.println("Invalid Point, try again\n");
                }
            } while (userInput == null);
            
            repeat = false;
            // if input x, y is greater than length or less than 0 or is an already dead ship, prompt user to reinput
            if (this.enemy.board.isMiss(userInput) || this.enemy.board.isHit(userInput)) {
                repeat = true;
            }
            System.out.println("");
        } while (repeat);

        this.enemy.board.setDead(userInput);
        if (this.enemy.board.isHit(userInput)) {
            System.out.println("You got a hit !!\n");
            this.user.setScore(user.getScore() + 50);
        }
        else {
            System.out.println("You missed!!");
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
        
        String coordinate = enemy.translatePoint(shootPoint);
        
        System.out.println("Enemy Shot: " + coordinate);
        this.user.board.setDead(shootPoint);
        isHit = this.user.board.isHit(shootPoint);

        if (isHit) {
            this.enemyHitPoints.add(shootPoint);
            System.out.println("Enemy got a hit !!\n");
        } else {
            System.out.println("Enemy missed!!");
            this.enemyMissedPoints.add(shootPoint);
        }
        System.out.println("");
    }

    // helper methods for ai fire cannon
    private Point getNextPoint(Point hitPoint) {
        Point nextPoint = null;
        int x = hitPoint.getX();
        int y = hitPoint.getY();
        if (x > 0 && !enemyHitPoints.contains(new Point(x - 1, y))) {
            nextPoint = new Point(x - 1, y); //check up
        } else if (x < this.user.board.getLength() - 1 && !enemyHitPoints.contains(new Point(x + 1, y))) {
            nextPoint = new Point(x + 1, y); //check down
        } else if (y > 0 && !enemyHitPoints.contains(new Point(x, y - 1))) {
            nextPoint = new Point(x, y - 1); //check left
        } else if (y < this.user.board.getLength() - 1 && !enemyHitPoints.contains(new Point(x, y + 1))) {
            nextPoint = new Point(x, y + 1); //check right
        }
        return nextPoint;
    }

    private Point getRandomPoint() {
        Random rand = new Random();
        Point point = new Point();
        do {
            point = new Point(rand.nextInt(this.user.board.getLength()), rand.nextInt(this.user.board.getLength()));
        } while (enemyHitPoints.contains(point) || enemyMissedPoints.contains(point));
        return point;
    }

    // Takes one turn
    public void takeTurn() {
        System.out.println("         || YOUR BOARD ||                        || ENEMY BOARD ||\n");
        this.printBoardDisplay();

        this.playerFireCannon();
        // Decrement for each turn taken
        this.user.setScore(user.getScore() - 10);
        this.enemyFireCannon();
    }

    // prints double board display
    public void printBoardDisplay() {

        // print top of board
        System.out.print("    ");
        for (int i = 1; i <= this.user.board.getLength(); i++) {
            System.out.print(String.format("%2d", i) + " ");
        }
        System.out.print("\t");

        // print top of enemy board
        System.out.print("    ");
        for (int i = 1; i <= this.user.board.getLength(); i++) {
            System.out.print(String.format("%2d", i) + " ");
        }
        System.out.println("");

        // print board
        char letter1 = 'A';
        char letter2 = 'A';
        for (int i = 0; i < this.user.board.getLength(); i++) {
            // print letter coordinates
            System.out.print(letter1++ + "   ");

            for (int j = 0; j < this.user.board.getLength(); j++) {
                if (this.user.board.cells[i][j] == States.MISS.ordinal()) {
                    System.out.print(" # ");
                } else if (this.user.board.cells[i][j] == States.HIT.ordinal()) {
                    System.out.print(" X ");
                } else if (this.user.board.cells[i][j] == States.SHIP.ordinal()) {
                    System.out.print(" O ");
                } else {
                    System.out.print(" . ");
                }
                if (j == this.user.board.getLength() - 1) {
                    System.out.print("\t");
                }
            }

            // print other board display
            System.out.print(letter2++ + "   ");
            for (int j = 0; j < this.user.board.getLength(); j++) {
                if (this.enemy.board.cells[i][j] == States.MISS.ordinal()) {
                    System.out.print(" # ");
                } else if (this.enemy.board.cells[i][j] == States.HIT.ordinal()) {
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
        return (this.user.checkLose());
    }
    
    public void load() throws FileNotFoundException, IOException {
        try (BufferedReader inStream = new BufferedReader(new FileReader("./resources/scores.txt"))) {
            String line = null;

            // Load user textfile
            while ((line = inStream.readLine()) != null) {
                    String[] split = line.split(" ");
                    User current = new User(split[0], Integer.parseInt(split[1]));
                    this.database.getUsers().add(current);
            }
            
            // Get username
            System.out.println("Enter your name: ");
            String name = scanner.nextLine();
            // Check if user in database
            if ((this.user = this.database.checkUnique(name)) == null) {
                this.unique = true;
            }
            this.user = new User(name);
        }
    }
    
    public void saveFile(User user) throws FileNotFoundException {
         // Add new user to database
            if (this.unique == true) {
                this.database.getUsers().add(this.user);
            }
            // Update user in database
            else {
                this.database.updateUser(this.user);
            }
            
            try (PrintWriter pw = new PrintWriter(new FileOutputStream("./resources/scores.txt", false))) {
                // Output file
                Iterator it = database.getUsers().descendingIterator();
                while(it.hasNext()) {
                    pw.println(it.next());
                }
            }
    }
}
