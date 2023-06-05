/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battleshipsGUI;

/**
 *
 * @author oliver
 */
public class BattleShipMain {
    public static void main(String[] args) {
        Model model = new Model();
        View view = new View(model);
        Controller controller = new Controller(model, view);
        model.setController(controller);
        
        System.out.println(model.getShipLengths().length);
        view.setVisible(true);
    }
}
