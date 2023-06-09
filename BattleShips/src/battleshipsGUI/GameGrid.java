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
public class GameGrid extends JPanel {

    private Board board;
    private User user;
    private Controller controller;
    private int shipLength;
    public GridStates gridState;
    private FrameGame panelGame;

    private boolean enemyTurn = false;
    private boolean startPhase = false;

    public GameGrid(Controller controller, User user) {
        this.user = user;
        this.controller = controller;
        this.gridState = GridStates.PLACINGSTART;
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

                pan.setName(j + "" + i);
                add(pan);
            }
        }
    }
    
    public void setEnemyTurn(boolean bool){
        this.enemyTurn = bool;
    }
    
    public void setGamePanel(FrameGame panel){
        this.panelGame = panel;
    }

    public User getUser(){
        return this.user;
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
        this.gridState = GridStates.PLACINGSTART;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                JPanel pan = getComponentAt(j, i);
                if (board.cells[i][j] == States.SHIP.ordinal()) {
                    pan.setBackground(Color.BLUE);
                } else if (board.cells[i][j] == States.NONE.ordinal()) {
                    pan.setBackground(Color.BLACK);
                } else if (board.cells[i][j] == States.MISS.ordinal()) {
                    pan.setBackground(Color.GRAY);
                } else if (board.cells[i][j] == States.HIT.ordinal()) {
                    pan.setBackground(Color.RED);
                }
            }
            this.gridState = GridStates.PLACINGEND;
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
        return (JPanel) comp;
    }
    
    public Board getBoard() {
        return this.board;
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
            System.out.println("Grid state : " + gridState);
            if (gridState == GridStates.NOCLICK) {
                return;
            }
            JPanel clickedBox = (JPanel) e.getSource();
            Coordinate point = new Coordinate(x, y);
            System.out.println(point.getX() + "" + point.getY());

            if (gridState == GridStates.PLACINGSTART) {
                if (startPhase) {
                    return;
                }
                if (controller.checkValid(point, shipLength)) {
                    gridState = GridStates.PLACINGEND;
                    clickedBox.setBackground(Color.BLUE);
                    controller.setOrigin(point);
                }
            }
            if (gridState == GridStates.PLACINGEND) {
                if (startPhase) {
                    return;
                }
                if (clickedBox.getBackground() == Color.YELLOW && gridState != GridStates.NOCLICK) {
                    controller.setEnd(point);
                    gridState = GridStates.PLACINGSTART;
                }
            }
            if (gridState == GridStates.SHOOTINGAT) {
                clickedBox.setBackground(Color.RED);
            }
        }
    }

    public void updateEnemyTargetLabel(String cell){
        this.panelGame.updateEnemyTargetLabel(cell);
    }
    
    public void startShootingPhase() {
        gridState = GridStates.NOCLICK;
        startPhase = true;
    }
    
    public void updateEnemyResultLabel(String result){
        this.panelGame.updateEnemyResultLabel(result);
    }
}
