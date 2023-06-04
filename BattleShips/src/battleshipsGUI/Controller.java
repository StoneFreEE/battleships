/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battleshipsGUI;

/**
 *
 * @author oliver
 */
public class Controller {
    private Model model;
    private View view;
    
    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        this.view.setController(this);
        
        this.model.addObserver(view);
    }
    
    public void startGame() {
        view.startGame();
    }
    
    public void playGame() {
        view.playGame();
    }
    
    public void loadBoard() {
        // Set board to loadedboard
        model.setBoardName(view.loadBoardPanel.getBoardName());
        
    }
    
    public void setName(String name) {
        //model.checkUnique(name);
    }
    public void updateScore() {
        model.updateScore();
    }
    
    public void initiateBoard() {
        view.initiateBoard(model.getShipLengths());
    }
}
