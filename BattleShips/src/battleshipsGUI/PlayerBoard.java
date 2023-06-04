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
    private boolean isSelfGridListener = true;
    private Point firstPoint = new Point(0,0);
    private Board board;
    private User user;
    private JPanel currentPanel = new JPanel();
    private Point secondNextPoint = new Point(0,0);
    private JPanel secondNextCell = null;
    private Point thirdNextPoint = new Point(0,0);
    private JPanel thirdNextCell = null;

    public PlayerBoard(int[] shipLengths) {
        super(shipLengths);
        board = new Board();
    }
    
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

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++)
            {
                if(board.cells[i][j] == States.SHIP.ordinal()){
                    int x = numberToPanel(i);
                    int y = numberToPanel(j);

                    Point p = new Point(x,y);
                    getJPanel(p);
                    currentPanel.setBackground(Color.CYAN);
                }
               if(board.cells[i][j] == States.NONE.ordinal()){
                   int x = numberToPanel(i);
                   int y = numberToPanel(j);
                   
                   System.out.println(x + " " + y);
                   Point p = new Point(Math.abs(x),Math.abs(y));
                   getJPanel(p);
                   currentPanel.setBackground(Color.BLUE);

                }
               if(board.cells[i][j] == States.HIT.ordinal()){
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
        JPanel firstCell = new JPanel();
        firstCell.setBorder(BorderFactory.createLineBorder(Color.blue, 2));
        firstCell.setPreferredSize(new Dimension(20, 20)); // for demo purposes only
        firstCell.setBackground(Color.black);

        firstCell.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(isSelfGridListener) {
                    firstPoint = firstCell.getLocation();
                    double xPos = (firstPoint.getX()/20+1);
                    int x = (int) xPos;
                    double yPos = (firstPoint.getY()/20+1);
                    int y = (int) yPos;

                    double xPos2 = (firstPoint.getX()/20+2);
                    int x2 = (int) xPos2;
                    double yPos2 = (firstPoint.getY()/20+1);
                    int y2 = (int) yPos2;

                    double xPos3 = (firstPoint.getX()/20+3);
                    int x3 = (int) xPos3;
                    double yPos3 = (firstPoint.getY()/20+1);
                    int y3 = (int) yPos3;
                    

                    System.out.print("\nLocation (X: " + x + " Y: " + y + ")");

                    secondNextPoint = new Point((int)(firstPoint.getX()+20),(int)(firstPoint.getY()));
                    thirdNextPoint = new Point((int)(firstPoint.getX()+40),(int)(firstPoint.getY()));
                    Coordinate a = new Coordinate(x,y);
                    Coordinate b = new Coordinate(x2,y2);
                    Coordinate c = new Coordinate(x3,y3);

                    getComp2(secondNextPoint);
                    getComp3(thirdNextPoint);

                }
            }
        });
        return firstCell;
    }
    
    public int numberToPanel(int s){
        int temp = (s-1)*20;
        return temp;
    }
    
    public void getJPanel(Point newPoint){
        currentPanel = this.getComponentAt(newPoint);
    }
    
    public void getComp2(Point newPoint){
        secondNextCell = this.getComponentAt(newPoint);
    }
    public void getComp3(Point newPoint){
        thirdNextCell = this.getComponentAt(newPoint);
    }
    
    
    public void setSelfGridListener (boolean selfGridListener){
        this.isSelfGridListener = selfGridListener;
    }
    
    
    public boolean getSelfGridListener() {
        return isSelfGridListener;
    }
    
}
