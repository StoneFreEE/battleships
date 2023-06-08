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
 *
 * @author oliver
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
    private DBManager dbManager;

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

    public Model() {
        dbManager = new DBManager();
    }

    public Object[][] getUsers() {
        return users;
    }

    public void checkUnique(String username) {
        boolean userCheck = false;
        // Check if the user exists in the database
        if (dbManager.checkUserExists(username)) {
            user = dbManager.getUser(username);
        } else {
            // If the user does not exist, create a new user and add it to the database
            user = new User(username);
            dbManager.addUser(username, 500);
        }
    }

    public void updatePlayerScore() {
        this.playerGrid.getUser().setScore(this.playerGrid.getUser().getScore() + 50);
    }

    private boolean checkTableExisting(String newTableName) {
        boolean flag = false;
        try {
            System.out.println("check existing tables.... ");
            String[] types = {"TABLE"};
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rsDBMeta = dbmd.getTables(null, null, null, null);
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int[] getShipLengths() {
        return this.shipLengths;
    }

    public void updateScore(int limit) {
        dbManager.updateUserScore(username, score);

        users = dbManager.getUsersByScore(limit);
        setChanged();
        notifyObservers(users);
    }

    /* public void updateScore() {
        try {
            Statement statement = conn.createStatement();

            ResultSet rs = statement.executeQuery("SELECT score FROM UserInfo WHERE name = '" + username + "'");
            if (score > rs.getInt(0)) {
                statement.executeUpdate("UPDATE UserInfo SET score=" + score + " WHERE name='" + username + "'");
                System.out.println(username + " " + score);
            }
            rs = statement.executeQuery("SELECT COUNT(*) FROM UserInfo ");
            int totalUsers = (int) rs.getObject(0);
            users = new Object[totalUsers][3];

            rs = statement.executeQuery("SELECT name, score FROM UserInfo " + "ORDER BY score DESC, name ASC");
            int row = 0;
            while (rs.next()) {
                users[row][0] = row + 1;
                users[row][1] = rs.getObject(0);
                users[row][2] = rs.getObject(1);
                row++;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        setChanged();
        notifyObservers(users);
    }
     */
    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    // set grid with ship placements
    public void initPlayerGrid(GridPlayer grid) {
        playerGrid = grid; // Retrieve the GridPlayer object from PanelPlaceShip
        playerGrid.startShootingPhase();
    }

    public void initEnemyGrid() {
        initEnemy();
        enemyGrid = new GridEnemy(controller, this, this.enemy, shipLengths);
    }

    public GridPlayer getPlayerGrid() {
        return this.playerGrid; // Retrieve the GridPlayer object from PanelPlaceShip
    }

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

    public void linkPaneltoGrid(FrameGame panel) {
        this.playerGrid.setGamePanel(panel);
        this.enemyGrid.setGamePanel(panel);
    }

    // update grid before returning
    public GridEnemy getEnemyGrid() {
        this.enemyGrid.updateGrid(enemy.board);
        return this.enemyGrid; // Retrieve the GridPlayer object from PanelPlaceShip
    }

    public void updateScoreDB() {
        dbManager.updateUserScore(username, score);
    }

    public void setName(String name) {
        this.user = new User(name);
        this.username = name;
        setChanged();
        notifyObservers(user);
        addUserToDatabase(name); // Add the user to the database
    }

    public void initEnemy() {
        this.enemy = new AIEnemy();
        this.enemy.initBoard(shipLengths);
    }

    public void loadBoard() {
        try {
            Statement statement = conn.createStatement();

            ResultSet rs = statement.executeQuery("SELECT "
                    + "origin1, end1, length1,"
                    + "origin2 end2 length2,"
                    + "origin3 end3 length3,"
                    + "origin4 end4 length4,"
                    + "origin5 end5 length5,"
                    + "FROM Boards "
                    + "WHERE boardname = '" + boardName + "';");

            if (!rs.next()) {
                for (int i = 0; i <= 12; i += 3) {
                    Coordinate firstPoint = user.board.parsePoint(rs.getString(i));
                    Coordinate endPoint = user.board.parsePoint(rs.getString(i + 1));
                    Ship ship = new Ship(rs.getInt(i + 2), firstPoint, endPoint);
                    user.ships.add(ship);
                }

                user.initLoadDatabase();
            } else {
                //invalid
                boolean invalid = true;
                setChanged();
                notifyObservers(invalid);

            }

        } catch (SQLException ex) {
            Logger.getLogger(Model.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setOrigin(Coordinate coordinate) {
        HashSet<Coordinate> possiblePoints = user.board.checkPossible(currentShip);
        Coordinate[] coordinates = convertToPrimitive(possiblePoints);

        setChanged();
        notifyObservers(coordinates);
    }

    public void setEnd(Coordinate coordinate) {

        currentShip.endPoint = coordinate;
        user.board.placeShip(currentShip);
        user.ships.add(currentShip);
        setChanged();
        notifyObservers(user);
    }

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

    private void addUserToDatabase(String name) {
        dbManager.addUser(name, score);
        /*  try {
            Statement statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO UserInfo (name, score) VALUES ('" + name + "', 0)");
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }

    public boolean checkValid(Coordinate coordinate, int shipLength) {
        boolean valid = true;
        currentShip = new Ship(shipLength, coordinate);
        valid = user.board.isFree(coordinate) && user.board.isSpace(currentShip);
        setChanged();
        notifyObservers(valid);
        return valid;
    }
}
