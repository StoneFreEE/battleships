/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battleshipsGUI;

import java.awt.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 64272
 */
public class DBManager {

    private Connection conn;
    private String url = "jdbc:derby://localhost:1527/BattleShipDB;create=true";
    private String dbusername = "pdc";
    private String dbpassword = "pdc";

    public DBManager() {
        dbSetup();
    }

    private void dbSetup() {
        try {
            conn = DriverManager.getConnection(url, dbusername, dbpassword);
            createUserInfoTable();
            createBoardsTable();
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void createUserInfoTable() {
        try {
            Statement statement = conn.createStatement();
            String tableName = "UserInfo";
            if (!checkTableExisting(tableName)) {
                statement.executeUpdate("CREATE TABLE " + tableName + " (name VARCHAR(32), score INT)");
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void createBoardsTable() {
        try {
            Statement statement = conn.createStatement();
            String tableName = "Boards";
            if (!checkTableExisting(tableName)) {
                statement.executeUpdate("CREATE TABLE " + tableName + " (boardname VARCHAR(32),"
                        + " origin1 VARCHAR(3), end1 VARCHAR(3), length1 INT,"
                        + " origin2 VARCHAR(3), end2 VARCHAR(3), length2 INT,"
                        + " origin3 VARCHAR(3), end3 VARCHAR(3), length3 INT,"
                        + " origin4 VARCHAR(3), end4 VARCHAR(3), length4 INT,"
                        + " origin5 VARCHAR(3), end5 VARCHAR(3), length5 INT)");
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean checkTableExisting(String newTableName) {
        boolean flag = false;
        try {
            String[] types = {"TABLE"};
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rsDBMeta = dbmd.getTables(null, null, null, types);
            while (rsDBMeta.next()) {
                String tableName = rsDBMeta.getString("TABLE_NAME");
                if (tableName.equalsIgnoreCase(newTableName)) {
                    flag = true;
                    break;
                }
            }
            if (rsDBMeta != null) {
                rsDBMeta.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }

    public void addUser(String name, int score) {
        try {
            // Check if the user already exists in the database
            if (checkUserExists(name)) {
                System.out.println("User already exists in the database.");
                return; // Skip adding the user
            }

            Statement statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO UserInfo (name, score) VALUES ('" + name + "', " + score + ")");
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean checkUserExists(String username) {
        try {
            String sql = "SELECT * FROM UserInfo WHERE name = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public User getUser(String username) {
        try {
            String sql = "SELECT * FROM users WHERE username = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("username");
                int score = resultSet.getInt("score");
                return new User(name, score);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object[][] getUsersByScore(int limit) {
        try {
            String sql = "SELECT * FROM users ORDER BY score DESC LIMIT ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, limit);
            ResultSet resultSet = statement.executeQuery();

            // Get the number of rows returned
            resultSet.last();
            int rowCount = resultSet.getRow();
            resultSet.beforeFirst();

            // Create the 2D array to hold the users
            Object[][] users = new Object[rowCount][2];

            // Iterate over the result set and populate the array
            int i = 0;
            while (resultSet.next()) {
                String name = resultSet.getString("username");
                int score = resultSet.getInt("score");
                users[i][0] = name;
                users[i][1] = score;
                i++;
            }

            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateUserScore(String name, int score) {
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate("UPDATE UserInfo SET score = " + score + " WHERE name = '" + name + "'");
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    public int getUserScore(String name) {
        int score = 0;
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT score FROM UserInfo WHERE name = '" + name + "'");
            if (rs.next()) {
                score = rs.getInt("score");
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return score;
    }

    public void addBoard(String boardName, Ship[] ships) {
        try {
            Statement statement = conn.createStatement();
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO Boards (boardname");
            for (int i = 1; i <= 5; i++) {
                sb.append(", origin").append(i).append(", end").append(i).append(", length").append(i);
            }
            sb.append(") VALUES ('").append(boardName).append("'");
            for (int i = 0; i < 5; i++) {
                Ship ship = ships[i];
                sb.append(", '").append(ship.origin).append("', '").append(ship.endPoint).append("', ").append(ship.length);
            }
            sb.append(")");
            statement.executeUpdate(sb.toString());
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void removeBoard(String boardName) {
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate("DELETE FROM Boards WHERE boardname = '" + boardName + "'");
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Ship[] getBoard(String boardName) {
        Ship[] ships = new Ship[5];
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Boards WHERE boardname = '" + boardName + "'");
            if (rs.next()) {
                for (int i = 0; i < 5; i++) {
                    String originStr = rs.getString("origin" + (i + 1));
                    String endStr = rs.getString("end" + (i + 1));
                    int length = rs.getInt("length" + (i + 1));

                    // Parse the coordinates from the strings
                    Coordinate origin = parseCoordinate(originStr);
                    Coordinate end = parseCoordinate(endStr);

                    ships[i] = new Ship(length, origin, end);
                }
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ships;
    }

    private Coordinate parseCoordinate(String coordinateStr) {
        int x = coordinateStr.charAt(0) - 'A';
        int y = Integer.parseInt(coordinateStr.substring(1)) - 1;
        return new Coordinate(x, y);
    }
}
