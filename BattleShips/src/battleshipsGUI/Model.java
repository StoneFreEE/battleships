/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battleshipsGUI;

import java.sql.*;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Observable;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Model class represents the data and business logic of the Battleships
 * game. It interacts with the database, manages user information, game boards,
 * and coordinates communication between the various components of the game.
 */
public class Model extends Observable {

    // db setup
    private Connection conn;
    private String url = "jdbc:derby://localhost:1527/BattleShipDB;create=true";
    private String dbusername = "pdc";
    private String dbpassword = "pdc";
    private String username = null;
    private String password = null;
    private int score;
    private boolean uniqueName;

    private int prevScore;

    // enemy user setup
    private User user;
    private AIEnemy enemy;
    private Coordinate lastShot = new Coordinate(0, 0);

    private Object[][] users;

    // board setup ?
    private Board board;
    private String boardName;
    private int[] shipLengths = {2, 3, 3, 4, 5};
    private Ship currentShip;

    //
    private PanelPlaceShip placeShipPanel;

    private GridPlayer playerGrid;
    private GridEnemy enemyGrid;

    private Controller controller;

    /**
     * Constructs a Model object and sets up the database.
     */
    public Model() {
        dbsetup();
    }

    /**
     * Retrieves the list of users from the database.
     *
     * @return The list of users as a two-dimensional array.
     */
    public Object[][] getUsers() {
        return users;
    }

    /**
     * Sets up the database tables if they don't exist.
     */
    public void dbsetup() {
        try {
            conn = DriverManager.getConnection(url, dbusername, dbpassword);
            Statement statement = conn.createStatement();

            String tableName = "UserInfo";
            if (!checkTableExisting(tableName)) {
                statement.executeUpdate("CREATE TABLE " + tableName + " (name VARCHAR(32), score INT)");
            }

            String tableName2 = "Boards";
            if (!checkTableExisting(tableName2)) {
                statement.executeUpdate("CREATE TABLE " + tableName2 + " (boardname VARCHAR(32),"
                        + " origin1 VARCHAR(3), end1 VARCHAR(3), length1 INT,"
                        + " origin2 VARCHAR(3), end2 VARCHAR(3), length2 INT,"
                        + " origin3 VARCHAR(3), end3 VARCHAR(3), length3 INT,"
                        + " origin4 VARCHAR(3), end4 VARCHAR(3), length4 INT,"
                        + " origin5 VARCHAR(3), end5 VARCHAR(3), length5 INT)");
            }

            statement.close();
        } catch (Throwable e) {
            System.out.println("error");
        }
    }

    /**
     * Checks if the given username is unique in the database.
     *
     * @param username The username to check for uniqueness.
     */
    public void checkUnique(String username) {
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT name, score FROM UserInfo "
                    + "WHERE name = '" + username.toUpperCase() + "'");
            if (!rs.next()) {
                prevScore = 500;
                uniqueName = true;
            } else {
                prevScore = rs.getInt(2);
                uniqueName = false;
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Inserts a board into the database. If the board with the given name
     * already exists, it overwrites the existing board. Notifies the observers
     * after the insertion is complete.
     *
     * @param boardName The name of the board to be inserted.
     * @param user The User object containing the ship placements for the board.
     */
    public void insertBoard(String boardName, User user) {
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT boardname FROM Boards "
                    + "WHERE boardname = '" + boardName + "'");
            if (!rs.next()) {
                String insertScript = "INSERT INTO Boards VALUES('" + boardName + "'";
                for (Ship ship : user.getShipsList()) {
                    insertScript += ", '" + Coordinate.translatePoint(ship.origin) + "', '"
                            + Coordinate.translatePoint(ship.endPoint) + "', "
                            + ship.length;
                }
                insertScript += ")";
                statement.executeUpdate(insertScript);
                String playGame = "playgame";
                setChanged();
                notifyObservers(playGame);
            } else {
                // Overwrite
                System.out.println("Existing database found");
                String existingItem = "existingboard" + boardName;
                setChanged();
                notifyObservers(existingItem);
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Updates the board in the database with the new ship placements. Notifies
     * the observers after the update is complete.
     *
     * @param boardName The name of the board to be updated.
     * @param user The User object containing the new ship placements for the
     * board.
     */
    public void updateBoard(String boardName, User user) {
        try {
            Statement statement = conn.createStatement();
            String updateScript = "UPDATE Boards SET boardname='" + boardName + "'";
            int row = 1;
            for (Ship ship : user.getShipsList()) {
                updateScript += ", origin" + row + " = '" + Coordinate.translatePoint(ship.origin) + "', "
                        + "end" + row + " = '" + Coordinate.translatePoint(ship.endPoint) + "', "
                        + "length" + row + " = " + ship.length;
                row++;
            }
            updateScript += " WHERE boardname = '" + boardName + "'";
            System.out.println(updateScript);
            statement.executeUpdate(updateScript);
            String playGame = "playgame";
            setChanged();
            notifyObservers(playGame);
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Updates the player's score and the score variable.
     */
    public void updatePlayerScore() {
        this.playerGrid.getUser().setScore(this.playerGrid.getUser().getScore() + 50);
        this.score = this.playerGrid.getUser().getScore();
    }

    /**
     * Checks if a table with the given name already exists in the database.
     *
     * @param newTableName The name of the table to check.
     * @return True if the table exists, false otherwise.
     */
    private boolean checkTableExisting(String newTableName) {
        boolean flag = false;
        try {
            System.out.println("check existing tables.... ");
            String[] types = {"TABLE"};
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rsDBMeta = dbmd.getTables(null, null, null, types);
            while (rsDBMeta.next()) {
                String tableName = rsDBMeta.getString("TABLE_NAME");
                if (tableName.compareToIgnoreCase(newTableName) == 0) {
                    System.out.println(tableName + " is there");
                    flag = true;
                }
            }
            if (rsDBMeta != null) {
                rsDBMeta.close();
            }
        } catch (SQLException ex) {
        }
        return flag;
    }

    /**
     * Gets the current score.
     *
     * @return The current score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets the score to the given value.
     *
     * @param score The new score value.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Gets the ship lengths array.
     *
     * @return The array of ship lengths.
     */
    public int[] getShipLengths() {
        return this.shipLengths;
    }

    /**
     * Gets the User object.
     *
     * @return The User object.
     */
    public User getUser() {
        return this.user;
    }

    /**
     * Updates the score in the database based on the current score and
     * username. Retrieves the updated leaderboard and notifies the observers.
     */
    public void updateScore() {
        try {
            Statement statement = conn.createStatement();
            if (uniqueName) {
                System.out.println("unique");
                statement.executeUpdate("INSERT INTO UserInfo VALUES('" + username + "', " + score + ")");
            } else {
                if (score > prevScore) {
                    System.out.println("better than old score");
                    statement.executeUpdate("UPDATE UserInfo SET name='" + username + "', score=" + score + " WHERE name = '" + username + "'");
                }
            }
            ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM UserInfo");
            if (rs.next()) {
                int totalUsers = rs.getInt(1);
                users = new Object[totalUsers][3];
            }

            rs = statement.executeQuery("SELECT name, score FROM UserInfo " + "ORDER BY score DESC, name ASC");
            int row = 0;
            while (rs.next()) {
                users[row][0] = row + 1;
                users[row][1] = rs.getObject(1);
                users[row][2] = rs.getObject(2);
                row++;
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, "Couldn't find user", ex);
        }
        setChanged();
        notifyObservers(users);
    }

    /**
     * Sets the board name.
     *
     * @param boardName The name of the board.
     */
    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    /**
     * Sets the Controller object.
     *
     * @param controller The Controller object.
     */
    public void setController(Controller controller) {
        this.controller = controller;
    }

    /**
     * Initializes the player grid with ship placements.
     *
     * @param grid The GridPlayer object representing the player's grid.
     */
    public void initPlayerGrid(GridPlayer grid) {
        playerGrid = grid;
        playerGrid.startShootingPhase();
    }

    /**
     * Initializes the enemy grid.
     */
    public void initEnemyGrid() {
        initEnemy();
        enemyGrid = new GridEnemy(controller, this, this.enemy, shipLengths);
    }

    /**
     * Gets the player's grid.
     *
     * @return The GridPlayer object representing the player's grid.
     */
    public GridPlayer getPlayerGrid() {
        return this.playerGrid;
    }

    /**
     * Fires the enemy cannon by selecting a coordinate to shoot at. Updates the
     * player's grid and notifies the observers.
     */
    public void enemyFireCannon() {
        Random rand = new Random();
        Coordinate point = new Coordinate();

        // If havent hit a ship yet, randomly shoot at the board
        if (user.board.isMiss(lastShot)) {
            do {
                point = new Coordinate(rand.nextInt(Board.BOARD_SIZE), rand.nextInt(Board.BOARD_SIZE));
            } while (this.user.board.isHit(point) || this.user.board.isMiss(point));
        } else {
            // If hit a ship last turn, prioritize shooting the surrounding areas
            int x = lastShot.x;
            int y = lastShot.y;

            // Check up
            if (y > 0 && !this.user.board.isHit(new Coordinate(x, y - 1)) && !this.user.board.isMiss(new Coordinate(x, y - 1))) {
                point = new Coordinate(x, y - 1);
            } // Check down
            else if (y < Board.BOARD_SIZE - 1 && !this.user.board.isHit(new Coordinate(x, y + 1)) && !this.user.board.isMiss(new Coordinate(x, y + 1))) {
                point = new Coordinate(x, y + 1);
            } // Check left
            else if (x > 0 && !this.user.board.isHit(new Coordinate(x - 1, y)) && !this.user.board.isMiss(new Coordinate(x - 1, y))) {
                point = new Coordinate(x - 1, y);
            } // Check right
            else if (x < Board.BOARD_SIZE - 1 && !this.user.board.isHit(new Coordinate(x + 1, y)) && !this.user.board.isMiss(new Coordinate(x + 1, y))) {
                point = new Coordinate(x + 1, y);
            } // If all surrounding areas have already been shot, randomly shoot at the board
            else {
                do {
                    point = new Coordinate(rand.nextInt(Board.BOARD_SIZE), rand.nextInt(Board.BOARD_SIZE));
                } while (this.user.board.isHit(point) || this.user.board.isMiss(point));
            }
        }

        this.user.board.fireAt(point);
        lastShot = point;

        String coordinate = Coordinate.translatePoint(point);

        boolean isHit = this.user.board.isHit(point);
        if (isHit) {
            this.playerGrid.updateEnemyTargetLabel(coordinate);
            this.playerGrid.updateEnemyResultLabel("HIT");
        } else {
            this.playerGrid.updateEnemyTargetLabel(coordinate);
            this.playerGrid.updateEnemyResultLabel("MISS");
        }
        this.playerGrid.updateGrid(user.board);
    }

    /**
     * Links the game panel to the player and enemy grids.
     *
     * @param panel The game panel to link.
     */
    public void linkPaneltoGrid(FrameGame panel) {
        this.playerGrid.setGamePanel(panel);
        this.enemyGrid.setGamePanel(panel);
    }

    /**
     * Updates the enemy grid before returning it.
     *
     * @return The updated GridEnemy object.
     */
    public GridEnemy getEnemyGrid() {
        this.enemyGrid.updateGrid(enemy.board);
        return this.enemyGrid; // Retrieve the GridPlayer object from PanelPlaceShip
    }

    /**
     * Sets the name of the user.
     *
     * @param name The name to set.
     */
    public void setName(String name) {
        this.username = name.toUpperCase();
        this.user = new User(this.username);
        setChanged();
        notifyObservers(user);
    }

    /**
     * Initializes the enemy AI and its game board.
     */
    public void initEnemy() {
        this.enemy = new AIEnemy();
        this.enemy.initBoard(shipLengths);
    }

    /**
     * Loads the user's game board from the database.
     */
    public void loadBoard() {
        try {
            Statement statement = conn.createStatement();

            ResultSet rs = statement.executeQuery("SELECT "
                    + "origin1, end1, length1,"
                    + "origin2, end2, length2,"
                    + "origin3, end3, length3,"
                    + "origin4, end4, length4,"
                    + "origin5, end5, length5 "
                    + "FROM Boards "
                    + "WHERE boardname = '" + boardName + "'");

            if (rs.next()) {
                for (int i = 1; i <= 13; i += 3) {
                    Coordinate firstPoint = user.board.parsePoint(rs.getString(i));
                    Coordinate endPoint = user.board.parsePoint(rs.getString(i + 1));
                    Ship ship = new Ship(rs.getInt(i + 2), firstPoint, endPoint);
                    user.ships.add(ship);
                }

                user.initLoadDatabase();
                setChanged();
                notifyObservers(user.board);
            } else {
                // Cant find name
                System.out.println("Couldnt find");
                String invalidName = "invalidboard";
                setChanged();
                notifyObservers(invalidName);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Model.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Sets the origin coordinate for placing a ship.
     *
     * @param coordinate The origin coordinate to set.
     */
    public void setOrigin(Coordinate coordinate) {
        HashSet<Coordinate> possiblePoints = user.board.checkPossible(currentShip);
        Coordinate[] coordinates = convertToPrimitive(possiblePoints);

        setChanged();
        notifyObservers(coordinates);
    }

    /**
     * Sets the end coordinate for placing a ship and updates the user's board.
     *
     * @param coordinate The end coordinate to set.
     */
    public void setEnd(Coordinate coordinate) {

        currentShip.endPoint = coordinate;
        user.board.placeShip(currentShip);
        user.ships.add(currentShip);
        setChanged();
        notifyObservers(user);
    }

    /**
     * Converts a HashSet of Coordinate objects to an array of primitive
     * coordinates.
     *
     * @param possiblePoints The HashSet of possible coordinates.
     * @return An array of Coordinate objects.
     */
    public Coordinate[] convertToPrimitive(HashSet<Coordinate> possiblePoints) {
        // instanceof doesn't support in 1.8 hashset so convert to primitive
        Coordinate[] coordinates = new Coordinate[possiblePoints.size()];
        Iterator itr = possiblePoints.iterator();
        int i = 0;
        while (itr.hasNext()) {
            Coordinate coord = (Coordinate) itr.next();
            coordinates[i++] = coord;
        }

        return coordinates;
    }

    /**
     * Checks if a ship placement is valid at the given coordinate with the
     * specified length.
     *
     * @param coordinate The coordinate to check.
     * @param shipLength The length of the ship.
     * @return {@code true} if the placement is valid, {@code false} otherwise.
     */
    public boolean checkValid(Coordinate coordinate, int shipLength) {
        boolean valid = true;
        currentShip = new Ship(shipLength, coordinate);
        valid = user.board.isFree(coordinate) && user.board.isSpace(currentShip);
        setChanged();
        notifyObservers(valid);
        return valid;
    }

}
