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
public class GridEnemy extends JPanel implements GridPanel {

    private Board board;
    private AIEnemy enemy;
    private Controller controller;
    private Model model;
    private FrameGame panelGame;
    private GridStates gridState;

    private boolean startPhase = true;

    public GridEnemy(Controller controller, Model model, AIEnemy enemy, int[] shipLengths) {
        this.controller = controller;
        this.model = model;
        this.enemy = enemy;
        this.board = enemy.board;
        this.gridState = GridStates.SHOOTINGAT;

        initializeGrid();
    }

    private void initializeGrid() {
        setLayout(new GridLayout(board.cells.length, board.cells.length));
        setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                JPanel pan = createGridPanel(j, i);
                add(pan);
            }
        }
    }

    private JPanel createGridPanel(int x, int y) {
        JPanel pan = new JPanel();
        pan.setEnabled(true);
        pan.setPreferredSize(new Dimension(45, 45));
        pan.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        pan.setBackground(Color.BLACK);
        pan.addMouseListener(new GridListener(x, y));
        pan.setName(x + "" + y);
        return pan;
    }

    public GridEnemy(Controller controller, Model model, AIEnemy enemy) {
        this.board = enemy.board;
        this.enemy = enemy;

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
                pan.addMouseListener(new GridEnemy.GridListener(j, i));

                pan.setName(j + "" + i);
                add(pan);
            }
        }

        this.gridState = GridStates.SHOOTINGAT;
        this.model = model;
    }

    public void setGamePanel(FrameGame panel) {
        this.panelGame = panel;
    }

    public AIEnemy getEnemy() {
        return this.enemy;
    }

    @Override
    public void updateGrid(Board board) {
        this.board = board;
        // Customize how the enemy grid is updated based on the user's board
        for (int i = 0; i < board.cells.length; i++) {
            for (int j = 0; j < board.cells[i].length; j++) {
                JPanel pan = getComponentAt(j, i);
                int cellState = board.cells[i][j];
                if (cellState == GridCellStates.NONE.ordinal()) {
                    pan.setBackground(Color.BLACK);
                } else if (cellState == GridCellStates.MISS.ordinal()) {
                    pan.setBackground(Color.GRAY);
                } else if (cellState == GridCellStates.HIT.ordinal()) {
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
            if (gridState == GridStates.NOCLICK) {
                return;
            }
            JPanel clickedBox = (JPanel) e.getSource();
            Coordinate point = new Coordinate(x, y);

            if (gridState == GridStates.SHOOTINGAT) {
                String clickedCell = Coordinate.translatePoint(point);

                panelGame.updateUserClickLabel(clickedCell);

                // if successfully fires at point with no errors
                if (board.fireAt(point)) {
                    if (board.isMiss(point)) {
                        clickedBox.setBackground(Color.GRAY);
                        panelGame.updatePlayerResultLabel("MISS");
                    } else {
                        clickedBox.setBackground(Color.RED);
                        panelGame.updatePlayerResultLabel("HIT");
                        controller.updatePlayerScore();
                    }
                    panelGame.updateShipsRemaining();
                    panelGame.updateTurn();
                    panelGame.updateErrorLabel(true);
                } else {
                    panelGame.updateErrorLabel(false);
                }

            }
        }
    }

    public Board getBoard() {
        return this.board;
    }

    public void startShootingPhase() {
        gridState = GridStates.SHOOTINGAT;
        startPhase = true;
    }
}
