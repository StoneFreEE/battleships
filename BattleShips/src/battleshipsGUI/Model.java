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

    private User user;
    private AIEnemy enemy;

    private Object[][] users;

    // board setup ?
    private Board board;
    private String boardName;
    private int[] shipLengths = {2, 3, 3, 4, 5};
    private Ship currentShip;

    //
    private PlaceShipPanel placeShipPanel;

    private GameGrid playerGrid;
    private EnemyGrid enemyGrid;

    private Controller controller;

    public Model() {
        dbsetup();
    }

    public void dbsetup() {
        try {
            conn = DriverManager.getConnection(url, dbusername, dbpassword);
            System.out.println("HI");
            Statement statement = conn.createStatement();

            String tableName = "UserInfo";
            if (!checkTableExisting(tableName)) {
                statement.executeUpdate("CREATE TABLE " + tableName + " (name VARCHAR(32), score INT)");
            }

            String tableName2 = "Boards";
            if (!checkTableExisting(tableName2)) {
                statement.executeUpdate("CREATE TABLE " + tableName2 + " (boardname, VARCHAR(32),"
                        + " origin1 VARCHAR(3), end1 VARCHAR(3), length1 INT,"
                        + " origin2 VARCHAR(3), end2 VARCHAR(3), length2 INT,"
                        + " origin3 VARCHAR(3), end3 VARCHAR(3), length3 INT,"
                        + " origin4 VARCHAR(3), end4 VARCHAR(3), length4 INT,"
                        + " origin5 VARCHAR(3), end5 VARCHAR(3), length5 INT,"
                );
            }

            statement.close();
        } catch (Throwable e) {
            System.out.println("error");
        }
    }

    public void checkUnique(String username) {
        boolean userCheck = false;
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT name, score FROM UserInfo "
                    + "WHERE name = '" + username + "'");
            if (!rs.next()) {
                System.out.println("no such user");
                statement.executeUpdate("INSERT INTO UserInfo "
                        + "VALUES('" + username + "', 500)");
                user = new User(username);
            } else {
                String name = rs.getString(0);
                int score = rs.getInt(1);
                user = new User(name, score);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    public void updateScore() {
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

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    // set grid with ship placements
    public void initPlayerGrid(GameGrid grid) {
        playerGrid = grid; // Retrieve the GameGrid object from PlaceShipPanel
        playerGrid.startShootingPhase();
    }

    public void initEnemyGrid() {
        initEnemy();
        enemyGrid = new EnemyGrid(controller, this, this.enemy, shipLengths);
    }

    public GameGrid getPlayerGrid() {
        return this.playerGrid; // Retrieve the GameGrid object from PlaceShipPanel
    }

    // update grid before returning
    public EnemyGrid getEnemyGrid() {
        this.enemyGrid.updateGrid(enemy);
        return this.enemyGrid; // Retrieve the GameGrid object from PlaceShipPanel
    }

    public void setName(String name) {
        this.user = new User(name);
        setChanged();
        notifyObservers(user);
    }
    
    public void initEnemy(){
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

    public boolean checkValid(Coordinate coordinate, int shipLength) {
        boolean valid = true;
        currentShip = new Ship(shipLength, coordinate);
        valid = user.board.isFree(coordinate) && user.board.isSpace(currentShip);
        setChanged();
        notifyObservers(valid);
        return valid;
    }
}
