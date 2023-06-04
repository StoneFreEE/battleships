/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battleshipsGUI;

import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Set;
import javax.swing.*;

/**
 *
 * @author oliver
 */
public class GridPanel extends JPanel {
    private Board board;
    private boolean placing;
    private Controller controller;
    private int shipLength;

    public GridPanel(Controller controller, User user) {
        this.controller = controller;
        this.placing = true;
        this.board = user.board;
        setLayout(new GridLayout(board.cells.length, board.cells.length));
        setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                JPanel pan = new JPanel();

                pan.setEnabled(true);
                pan.setPreferredSize(new Dimension(45, 45));
                pan.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                pan.setBackground(Color.BLACK);
                pan.addMouseListener(new GridListener(j, i));

                pan.setName(j+""+i);
                add(pan);
            }
        }
    }

    public void setShipLength(int shipLength) {
        this.shipLength = shipLength;
    }

    public void displayPossiblePoints(Coordinate[] points) {
        for (Coordinate point : points) {
            int x = point.getX();
            int y = point.getY();
            JPanel pan = getComponentAt(x, y);
            pan.setBackground(Color.YELLOW);
        }
    }

    public void updateGrid(User user) {
        this.board = user.board;
        user.board.printBoard();
        this.placing = true;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                JPanel pan = getComponentAt(j, i);
                if (board.cells[i][j] == States.SHIP.ordinal()) {
                    pan.setBackground(Color.BLUE);
                }
                else if (board.cells[i][j] == States.NONE.ordinal()) {
                    pan.setBackground(Color.BLACK);
                }
            }
        this.placing = false;
        }
    }
    
    public JPanel getComponentAt(int x, int y) {
        Component comp = null;
        String pointString = x + "" + y;
        for (Component child : getComponents()) {
            if (child.getName().equals(pointString)) {
                comp = child;
            }
        }
        return (JPanel)comp;
    }
    
    private class GridListener extends MouseAdapter {
        private int x;
        private int y;
        
        public GridListener(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            JPanel clickedBox = (JPanel)e.getSource();
            Coordinate point = new Coordinate(x, y);
            System.out.println(point.getX() + "" + point.getY());
            if (placing) {
                    if (controller.checkValid(point, shipLength)) {
                        placing = false;             
                        clickedBox.setBackground(Color.BLUE);
                        controller.setOrigin(point);  
                    }
            }
            else {
                if (clickedBox.getBackground() == Color.YELLOW) {
                    controller.setEnd(point);
                    placing = true;
                    
                }
            }
        }
    }
}
    
   
