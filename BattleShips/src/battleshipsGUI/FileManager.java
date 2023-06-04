package battleshipsGUI;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class handles file input and output for the Battleship game. It provides methods for saving and loading both
 * the game board and user data.
 */
public class FileManager {

    private boolean unique;
    private final Scanner scanner = new Scanner(System.in);

    /** 
     * METHODS FOR BOARD SAVE / LOAD
    */
    
    /**
     * Prompts the user to load a board file and loads the file if the user chooses to do so.If the user does not choose
 to load a file, initializes the board and prompts the user to save the board as a file.
     * 
     * @param user The user playing the game
     * @param shipLengths An array of the lengths of the ships to be placed on the board
     */
    public void printLoadSave(User user, int[] shipLengths){
        // Prompt user to load
        String loadFileResponse = promptYesOrNo("Would you like to load a board file? (y/n)");
        if (loadFileResponse.equals("y")) {
            // Prompt filename
            System.out.println("Filename: ");
            String loadFilename = scanner.nextLine();
            loadBoard(loadFilename, user);
            // Check valid filename
            if (!user.ships.isEmpty()) {
                user.initLoadDatabase();
            } else {
                System.out.println("Invalid filename");
            }
        } else {
            user.initBoard(shipLengths);
            user.board.printBoard();
            
            String saveFileResponse = promptYesOrNo("Would you like to save this board as a file? (y/n)");
            if (saveFileResponse.equals("y")) {
                // SAVE
                System.out.println("Filename: ");
                String saveFilename = scanner.nextLine();
                saveBoard(saveFilename, user);
            }
        }
    }

    /**
     * Prompts the user with a yes or no question and returns the user's response.
     * 
     * @param question The yes or no question to be asked
     * @return The user's response
     */
    private String promptYesOrNo(String question) {
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

     /**
     * Loads a board from a file with the specified filename.
     * 
     * @param filename The name of the file to be loaded
     * @param user The user playing the game
     * @param ships An ArrayList of Ship objects representing the ships on the board
     * @throws FileNotFoundException If the file to be loaded cannot be found
     * @throws IOException If an error occurs while reading the input file
     */
    private void loadBoard(String filename, User user){
        try (BufferedReader inStream = new BufferedReader(new FileReader("./resources/" + filename + ".txt"))) {
            String line = "";
            user.board = new Board();
            // Load user textfile
            while ((line = inStream.readLine()) != null) {
                // Split between whitespace
                String[] split = line.split(" ");
                Coordinate firstPoint = user.board.parsePoint(split[0]);
                Coordinate endPoint = user.board.parsePoint(split[1]);
                int length = Integer.parseInt(split[2]);
                Ship ship = new Ship(length, firstPoint, endPoint);
                user.ships.add(ship);
            }
        } catch (FileNotFoundException e) {
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Saves the board configuration of a user's ships to a text file.
     * 
     * @param filename the name of the file to be created/overwritten
     * @param user the User object whose ship configuration is to be saved
     * @throws FileNotFoundException if the file cannot be found
     * @throws IOException if an I/O error occurs while writing to the file
     */
    private void saveBoard(String filename, User user) {
        try (PrintWriter pw = new PrintWriter(new FileOutputStream("./resources/" + filename + ".txt", false))) {
            // Output file
            for (Ship ship : user.ships) {
                String origin = Coordinate.translatePoint(ship.origin);
                String end = Coordinate.translatePoint(ship.endPoint);
                pw.println(origin + " " + end + " " + ship.length);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * METHODS FOR USER SAVE / LOAD
    */
    
    /**
    * Loads a user from a text file and returns a new User object.
    * 
    * @param user the User object to be loaded
    * @param database the UserDatabase containing all the users
    * @return the loaded User object
    */
    public User load(User user, UserDatabase database) {
        try (BufferedReader inStream = new BufferedReader(new FileReader("./resources/scores.txt"))) {
            String line = null;

            // Load user textfile
            while ((line = inStream.readLine()) != null) {
                String[] split = line.split(" ");
                User current = new User(split[0], Integer.parseInt(split[1]));
                database.getUsers().add(current);
            }

            // Get username
            System.out.println("Enter your name: ");
            String name = scanner.nextLine();

            if ((user = database.checkUnique(name)) == null) {
                this.unique = true;
            }
            return new User(name);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
    * Saves the User object to a text file and updates the UserDatabase accordingly.
    * 
    * @param user the User object to be saved
    * @param database the UserDatabase containing all the users
    * @throws FileNotFoundException if the file cannot be found
    */
    public void saveFile(User user, UserDatabase database) throws FileNotFoundException {
        // Add new user to database
        if (this.unique == true) {
            database.getUsers().add(user);
        } // Update user in database
        else {
            database.updateUser(user);
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
