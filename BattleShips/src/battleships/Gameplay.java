package battleships;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * The Game class represents the Battleship game and handles the game logic.
 */
public class Gameplay {

    User user;
    UserDatabase database;
    AIEnemy enemy;
    Scanner scanner = new Scanner(System.in);
    FileManager fileManager = new FileManager();
    private Point lastShot = new Point(0, 0);

    /**
     * Constructor for the Game class.
     * @param user the user playing the game.
     * @param enemy the ai enemy playing the game.
     * @param database the database object used to store user data.
     */
    public Gameplay(User user, AIEnemy enemy, UserDatabase database) {
        this.user = user;
        this.enemy = enemy;
        this.database = database;
    }

    /**
     * Initialise AIEnemy's board and player's board, also prints save/load prompts
     *
     * @param boardLength the length of the board
     * @param shipLengths an array of ship lengths
     */
    public void startGame(int boardLength, int[] shipLengths) {
        // Prompt for load file board
        do {
            fileManager.printLoadSave(this.user, shipLengths);
        } while (this.user.ships.isEmpty());
        this.enemy.initBoard(shipLengths);
    }

    /**
     * Takes one turn
     */
    public void takeTurn() {
        this.printBoardDisplay();

        this.playerFireCannon();
        // Decrement for each turn taken
        this.user.setScore(user.getScore() - 10);
        this.enemyFireCannon();
    }

    /**
     * Player fires cannon at enemy
     */
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
            System.out.println("\nYou got a hit !!\n");
            this.user.setScore(user.getScore() + 50);
        } else {
            System.out.println("\nYou missed!!\n");
        }
    }

    /**
     * Enemy fires cannon at player
     */
    private void enemyFireCannon() {
        Random rand = new Random();
        Point point = new Point();

        // If havent hit a ship yet, randomly shoot at the board
        if (user.board.isMiss(lastShot)) {
            do {
                point = new Point(rand.nextInt(Board.BOARD_SIZE), rand.nextInt(Board.BOARD_SIZE));
            } while (this.user.board.isHit(point) || this.user.board.isMiss(point));
        } else {
            // If hit a ship last turn, prioritize shooting the surrounding areas
            int x = lastShot.getX();
            int y = lastShot.getY();

            // Check up
            if (y > 0 && !this.user.board.isHit(new Point(x, y - 1)) && !this.user.board.isMiss(new Point(x, y - 1))) {
                point = new Point(x, y - 1);
            } // Check down
            else if (y < Board.BOARD_SIZE - 1 && !this.user.board.isHit(new Point(x, y + 1)) && !this.user.board.isMiss(new Point(x, y + 1))) {
                point = new Point(x, y + 1);
            } // Check left
            else if (x > 0 && !this.user.board.isHit(new Point(x - 1, y)) && !this.user.board.isMiss(new Point(x - 1, y))) {
                point = new Point(x - 1, y);
            } // Check right
            else if (x < Board.BOARD_SIZE - 1 && !this.user.board.isHit(new Point(x + 1, y)) && !this.user.board.isMiss(new Point(x + 1, y))) {
                point = new Point(x + 1, y);
            } // If all surrounding areas have already been shot, randomly shoot at the board
            else {
                do {
                    point = new Point(rand.nextInt(Board.BOARD_SIZE), rand.nextInt(Board.BOARD_SIZE));
                } while (this.user.board.isHit(point) || this.user.board.isMiss(point));
            }
        }

        this.user.board.fireAt(point);
        lastShot = point;

        String coordinate = Point.translatePoint(point);
        System.out.println("Enemy Shot: " + coordinate);

        boolean isHit = this.user.board.isHit(point);
        if (isHit) {
            System.out.println("Enemy got a hit !!\n");
        } else {
            System.out.println("Enemy missed!!");
        }
        System.out.println("");
    }

    /**
     * Method that prints the current state of the game board.
     */
    public void printBoardDisplay() {
        System.out.println("         || YOUR BOARD ||                        "
                + "|| ENEMY BOARD ||\n");

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

    /**
    * Checks if the player has won the game.
    *
    * @return true if the enemy has lost the game, false otherwise.
    */
    public boolean checkPlayerWin() {
        return (this.enemy.checkLose());
    }

    /**
    * Checks if the enemy has won the game.
    *
    * @return true if the player has lost the game, false otherwise.
    */
    public boolean checkEnemyWin() {
        return (this.user.checkLose());
    }

    /**
    * Loads the user data from the file.
    *
    * @throws FileNotFoundException if the file is not found.
    * @throws IOException           if there is an error while reading the file.
    */
    public void load() throws FileNotFoundException, IOException {
        this.user = this.fileManager.load(this.user, this.database);
    }

    /**
    * Saves the user data to a file.
    *
    * @param user the User object to save.
    * @throws FileNotFoundException if the file is not found.
    */
    public void saveFile(User user) throws FileNotFoundException {
        this.fileManager.saveFile(this.user, this.database);
    }
}
