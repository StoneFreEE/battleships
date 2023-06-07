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
 * @author 64272
 */
public class EnemyGrid extends JPanel {

    private Board board;
    private Controller controller;
    private Model model;
    private int[] shipLengths;
    public GridStates gridState;
    private FrameGame panelGame;
    
    private boolean startPhase = true;

    public EnemyGrid(Controller controller, Model model, AIEnemy enemy, int[] shipLengths) {
        this.board = enemy.board;

        this.controller = controller;
        setLayout(new GridLayout(board.cells.length, board.cells.length));
        setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                JPanel pan = new JPanel();

                pan.setEnabled(true);
                pan.setPreferredSize(new Dimension(45, 45));
                pan.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                pan.setBackground(Color.BLACK);
                pan.addMouseListener(new EnemyGrid.GridListener(j, i));

                pan.setName(j + "" + i);
                add(pan);
            }
        }

        this.gridState = GridStates.SHOOTINGAT;
        this.model = model;
        this.shipLengths = shipLengths;
    }

    public void setGamePanel(FrameGame panel) {
        this.panelGame = panel;
    }

    public void updateGrid(AIEnemy enemy) {
        // Customize how the enemy grid is updated based on the user's board
        // For example, you can use different colors or symbols to represent ship, miss, or empty cells
        enemy.board.printBoard();
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
            board.printBoard();
            if (gridState == GridStates.NOCLICK) {
                return;
            }
            JPanel clickedBox = (JPanel) e.getSource();
            Coordinate point = new Coordinate(x, y);

            System.out.println(point.getX() + "" + point.getY());

            if (gridState == GridStates.SHOOTINGAT) {
                String clickedCell = Coordinate.translatePoint(point);

                panelGame.updateUserClickLabel(clickedCell);

                // if successfully fires at point with no errors
                if (board.fireAt(point)) {
                    if (board.isMiss(point)) {
                        clickedBox.setBackground(Color.GRAY);
                    } else {
                        clickedBox.setBackground(Color.RED);
                    }
                    panelGame.updateTurn();
                    panelGame.updateErrorLabel(true);
                } else{
                    panelGame.updateErrorLabel(false);
                }

            }
        }
    }

    public void startShootingPhase() {
        gridState = GridStates.SHOOTINGAT;
        startPhase = true;
    }
}
