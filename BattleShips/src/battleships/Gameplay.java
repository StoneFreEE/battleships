package battleships;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

// Manages gameplay actions
public class Gameplay {

    User user;
    UserDatabase database;
    AIEnemy enemy;
    boolean unique;
    Scanner scanner = new Scanner(System.in);
    FileManager fileManager = new FileManager();

    public Gameplay(User user, AIEnemy enemy, UserDatabase database) {
        this.user = user;
        this.enemy = enemy;
        this.database = database;
        this.unique = false;
    }

    //initialise AIEnemy's board and player's board,
    // also prints save/load prompts
    public void startGame(int boardLength, int[] shipLengths) throws IOException {
        ArrayList<Ship> ships = new ArrayList<>();
        // Prompt for load file board
        do {
            fileManager.printLoadSave(this.user, shipLengths, ships);
        } while (ships.isEmpty());
        this.enemy.initBoard(boardLength, shipLengths);
    }

    // Takes one turn
    public void takeTurn() {
        this.printBoardDisplay();

        this.playerFireCannon();
        // Decrement for each turn taken
        this.user.setScore(user.getScore() - 10);
        this.enemyFireCannon();
    }

  /*  private String promptYesOrNo(String question) {
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

    private ArrayList<Ship> loadBoard(String filename, int boardLength) throws FileNotFoundException, IOException {
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
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    private void saveBoard(String filename) throws FileNotFoundException, IOException {
        try (PrintWriter pw = new PrintWriter(new FileOutputStream("./resources/" + filename + ".txt", false))) {
            // Output file
            for (Ship ship : this.user.ships) {
                String origin = this.user.translatePoint(ship.origin);
                String end = this.user.translatePoint(ship.endPoint);
                pw.println(origin + " " + end + " " + ship.length);
            }
        }
    } */

    // player fires cannon at enemy
    private void playerFireCannon() {
        Point userInput;
        Boolean repeat;

        // ask player what coordinate to fire cannon at
        do {
            System.out.println("\nEnemy's Ships Remaining: " + (this.enemy.ships.size() - this.enemy.shipsSunk) + "/" + this.enemy.ships.size());
            System.out.println("Your Ships Remaining: " + (this.user.ships.size() - this.user.shipsSunk) + "/" + this.user.ships.size());
            System.out.println("");

            do {
                System.out.println("Pick a coordinate to fire your cannon (Eg. C4): ");
                userInput = enemy.board.parsePoint(scanner.nextLine());
                if (userInput == null) {
                    System.out.println("Invalid Point, try again\n");
                }
            } while (userInput == null);

            repeat = false;
            // if input has already been shot at, prompt user to reinput
            if (this.enemy.board.isMiss(userInput) || this.enemy.board.isHit(userInput)) {
                repeat = true;
                System.out.println("You've already shot at this point!");
            }
        } while (repeat);

        this.enemy.board.fireAt(userInput);
        if (this.enemy.board.isHit(userInput)) {
            System.out.println("You got a hit !!\n");
            this.user.setScore(user.getScore() + 50);
        } else {
            System.out.println("You missed!!");
        }
    }

    // enemy fires cannon at player
    private void enemyFireCannon() {
        Random rand = new Random();
        Point point = new Point();
        do {
            point = new Point(rand.nextInt(Board.BOARD_SIZE), rand.nextInt(Board.BOARD_SIZE));
        } while (this.user.board.isHit(point) || this.user.board.isMiss(point));

        this.user.board.fireAt(point);
        
        String coordinate = this.enemy.translatePoint(point);

        System.out.println("Enemy Shot: " + coordinate);

        boolean isHit = this.user.board.isHit(point);

        if (isHit) {
            System.out.println("Enemy got a hit !!\n");
        } else {
            System.out.println("Enemy missed!!");
        }
        System.out.println("");

    }

    // prints double board display
    public void printBoardDisplay() {
        System.out.println("         || YOUR BOARD ||                        || ENEMY BOARD ||\n");

        // print top of board
        System.out.print("    ");
        for (int i = 1; i <= Board.BOARD_SIZE; i++) {
            System.out.print(String.format("%2d", i) + " ");
        }
        System.out.print("\t");

        // print top of enemy board
        System.out.print("    ");
        for (int i = 1; i <= Board.BOARD_SIZE; i++) {
            System.out.print(String.format("%2d", i) + " ");
        }
        System.out.println("");

        // print board
        char letter1 = 'A';
        char letter2 = 'A';
        for (int i = 0; i < Board.BOARD_SIZE; i++) {
            // print letter coordinates
            System.out.print(letter1++ + "   ");

            for (int j = 0; j < Board.BOARD_SIZE; j++) {
                if (this.user.board.cells[i][j] == States.MISS.ordinal()) {
                    System.out.print(" # ");
                } else if (this.user.board.cells[i][j] == States.HIT.ordinal()) {
                    System.out.print(" X ");
                } else if (this.user.board.cells[i][j] == States.SHIP.ordinal()) {
                    System.out.print(" O ");
                } else {
                    System.out.print(" . ");
                }
                if (j == Board.BOARD_SIZE - 1) {
                    System.out.print("\t");
                }
            }

            // print other board display
            System.out.print(letter2++ + "   ");
            for (int j = 0; j < Board.BOARD_SIZE; j++) {
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
        System.out.println("Score: " + this.user.getScore());

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
        } // Update user in database
        else {
            this.database.updateUser(this.user);
        }

        try (PrintWriter pw = new PrintWriter(new FileOutputStream("./resources/scores.txt", false))) {
            // Output file
            Iterator it = database.getUsers().descendingIterator();
            while (it.hasNext()) {
                pw.println(it.next());
            }
        }
    }
}
