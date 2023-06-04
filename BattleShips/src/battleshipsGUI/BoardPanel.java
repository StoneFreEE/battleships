/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battleshipsGUI;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author oliver
 */
public abstract class BoardPanel extends JPanel{
    private JPanel temp;
    JPanel self;
    int[] shipLengths;
    
    public BoardPanel(int[] shipLengths) {
        this.shipLengths = shipLengths;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        self = new JPanel();
        self.setLayout(new GridLayout(0,10));

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                temp = getCell();
                self.add(temp);
            }
        }
        this.add(self);
    }
    
    //return the cell that selected at point p
    public JPanel getComponentAt(Point p) {
        Component comp = null;
        for (Component child : self.getComponents()) {
            if (child.getBounds().contains(p)) {
                comp = child;
            }
        }
        return (JPanel)comp;
    }

    protected abstract JPanel getCell();
}
