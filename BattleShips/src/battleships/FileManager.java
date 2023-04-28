package battleships;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class FileManager {

    private boolean unique;
    Scanner scanner = new Scanner(System.in);

    // START OF METHODS FOR BOARD SAVE / LOAD
    // ***************************************
    public void printLoadSave(User user, int[] shipLengths, ArrayList<Ship> ships) throws FileNotFoundException, IOException {
        String loadFileResponse = promptYesOrNo("Would you like to load a board file? (y/n)");
        if (loadFileResponse.equals("y")) {
            System.out.println("Filename: ");
            String loadFilename = scanner.nextLine();
            loadBoard(loadFilename, user, ships);
            if (!ships.isEmpty()) {
                user.initLoadFile(shipLengths, ships);
            } else {
                System.out.println("Invalid filename");
            }
        } else {
            user.initBoard(shipLengths, ships);
            String saveFileResponse = promptYesOrNo("Would you like to save this board as a file? (y/n)");
            if (saveFileResponse.equals("y")) {
                // SAVE
                System.out.println("Filename: ");
                String saveFilename = scanner.nextLine();
                saveBoard(saveFilename, user);
            }
        }
    }

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

    private void loadBoard(String filename, User user, ArrayList<Ship> ships) throws FileNotFoundException, IOException {
        try (BufferedReader inStream = new BufferedReader(new FileReader("./resources/" + filename + ".txt"))) {
            String line = null;
            user.board = new Board();
            // Load user textfile
            while ((line = inStream.readLine()) != null) {
                // Split between whitespace
                String[] split = line.split(" ");
                Point firstPoint = user.board.parsePoint(split[0]);
                Point endPoint = user.board.parsePoint(split[1]);
                int length = Integer.parseInt(split[2]);
                Ship ship = new Ship(length, firstPoint, endPoint);
                ships.add(ship);
            }
        } catch (FileNotFoundException e) {
        }
    }

    private void saveBoard(String filename, User user) throws FileNotFoundException, IOException {
        try (PrintWriter pw = new PrintWriter(new FileOutputStream("./resources/" + filename + ".txt", false))) {
            // Output file
            for (Ship ship : user.ships) {
                String origin = user.translatePoint(ship.origin);
                String end = user.translatePoint(ship.endPoint);
                pw.println(origin + " " + end + " " + ship.length);
            }
        }
    }

    // ***************************************
    // END OF METHODS FOR BOARD SAVE / LOAD
    
    // START OF METHODS FOR USER SAVE / LOAD
    // ***************************************
    
    // loads users textfile and returns new user object
    public User load(User user, UserDatabase database) throws FileNotFoundException, IOException {
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
        }
    }

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

    // ***************************************
    // END OF METHODS FOR USER SAVE / LOAD
}
