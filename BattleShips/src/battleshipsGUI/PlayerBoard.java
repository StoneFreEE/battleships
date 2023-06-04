/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battleshipsGUI;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Iterator;

/**
 *
 * @author oliver
 */
public class PlayerBoard extends BoardPanel {
    private Ship currentShip;
    private Point firstPoint = new Point(0,0);
    private Board board;
    private User user;
    private JPanel currentPanel = new JPanel();

    public PlayerBoard(int[] shipLengths) {
        super(shipLengths);
        board = new Board();
    }
    /*
    public JPanel getOrigin(int index) {
        JPanel firstCell = new JPanel();
        firstCell.setBorder(BorderFactory.createLineBorder(Color.blue, 2));
        firstCell.setPreferredSize(new Dimension(20, 20)); // for demo purposes only
        firstCell.setBackground(Color.black);

        firstCell.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                    firstPoint = firstCell.getLocation();
                    double xPos = (firstPoint.getX()/20+1);
                    int x = (int) xPos;
                    double yPos = (firstPoint.getY()/20+1);
                    int y = (int) yPos;
                    currentShip = new Ship(shipLengths[index], new Coordinate(x, y));
                    System.out.print("\nLocation (X: " + x + " Y: " + y + ")");
                        
                }
        });
        HashSet<Coordinate> possiblePoints = board.checkPossible(currentShip);
        displayPossiblePoints(possiblePoints);
        
        JPanel secondCell = new JPanel();
        secondCell.setBorder(BorderFactory.createLineBorder(Color.blue, 2));
        secondCell.setPreferredSize(new Dimension(20, 20)); // for demo purposes only
        secondCell.setBackground(Color.black);
        
        secondCell.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                    firstPoint = firstCell.getLocation();
                    double xPos = (firstPoint.getX()/20+1);
                    int x = (int) xPos;
                    double yPos = (firstPoint.getY()/20+1);
                    int y = (int) yPos;
                    Coordinate coordinate = new Coordinate(x, y);
                    if (user.contains(possiblePoints, coordinate)) {
                        //valid
                    }
                    else {
                        // ask for new origin
                    }
                    System.out.print("\nLocation (X: " + x + " Y: " + y + ")");
                        
                }
        });
        return firstCell;
    }
    */
    public void displayPossiblePoints(HashSet<Coordinate> possiblePoints) {
        Iterator itr = possiblePoints.iterator();
        while (itr.hasNext()) {
            Coordinate coordinate = (Coordinate)itr.next();
            int x = numberToPanel(coordinate.getX());
            int y = numberToPanel(coordinate.getY());
            Point p = new Point(x, y);
            getJPanel(p);
            
            currentPanel.setBackground(Color.YELLOW);
        }
    }
    
    public void draw() {
        int[][] temp = null;
        temp = board.cells;

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++)
            {
                if(temp[i][j] == States.SHIP.ordinal()){
                    int x = numberToPanel(i);
                    int y = numberToPanel(j);

                    Point p = new Point(x,y);
                    getJPanel(p);
                    currentPanel.setBackground(Color.CYAN);
                }
               if(temp[i][j] == States.NONE.ordinal()){
                   int x = numberToPanel(i);
                   int y = numberToPanel(j);

                   Point p = new Point(Math.abs(x),Math.abs(y));
                   getJPanel(p);

                   currentPanel.setBackground(Color.BLUE);

                }
               if(temp[i][j] == States.HIT.ordinal()){
                    int x = numberToPanel(i);
                    int y = numberToPanel(j);

                    Point p = new Point(x,y);
                    getJPanel(p);
                    currentPanel.setBackground(Color.RED);
                }
               
            }
        }
    }
    
    public JPanel getEnd() {
        JPanel firstCell = new JPanel();
        firstCell.setBorder(BorderFactory.createLineBorder(Color.blue, 2));
        firstCell.setPreferredSize(new Dimension(20, 20)); // for demo purposes only
        firstCell.setBackground(Color.black);

        firstCell.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                    firstPoint = firstCell.getLocation();
                    double xPos = (firstPoint.getX()/20+1);
                    int x = (int) xPos;
                    double yPos = (firstPoint.getY()/20+1);
                    int y = (int) yPos;
                    currentShip.endPoint = new Coordinate(x, y);

                }
        });
        return firstCell;
    }
    
    @Override
    protected JPanel getCell() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public int numberToPanel(int s){
        int temp = (s-1)*20;
        return temp;
    }
    
    public void getJPanel(Point newPoint){
        currentPanel = this.getComponentAt(newPoint);
    }
    
}
