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
import java.util.Scanner;

/**
 *
 * @author magur
 */
public class FileManager {

    Scanner scanner = new Scanner(System.in);

    public void printLoadSave(User user, int[] shipLengths, ArrayList<Ship> ships) throws FileNotFoundException, IOException {
        String loadFileResponse = promptYesOrNo("Would you like to load a board file? (y/n)");
        if (loadFileResponse.equals("y")) {
            System.out.println("Filename: ");
            String loadFilename = scanner.nextLine();
            loadBoard(loadFilename, Board.BOARD_SIZE, user, ships);
            if (!ships.isEmpty()) {
                user.initLoadFile(Board.BOARD_SIZE, shipLengths, ships);
            } else {
                System.out.println("Invalid filename");
            }
        } else {
            user.initBoard(Board.BOARD_SIZE, shipLengths, ships);
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

    private void loadBoard(String filename, int boardLength, User user, ArrayList<Ship> ships) throws FileNotFoundException, IOException {
        try (BufferedReader inStream = new BufferedReader(new FileReader("./resources/" + filename + ".txt"))) {
            String line = null;
            user.board= new Board(boardLength);
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
}
