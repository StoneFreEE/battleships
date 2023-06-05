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
        System.out.println("Initialising finished");
        setPlayerGameGrid();
        view.playGame();
    }

    public void loadBoard() {
        // Set board to loadedboard
        model.setBoardName(view.loadBoardPanel.getBoardName());

    }

    public void setEnemyGrid() {
        // let player choose ship placement and save it on a gamegrid object
        model.initEnemyGrid();
    }

    public void setPlayerGameGrid() {
        // let player choose ship placement and save it on a gamegrid object
        model.initPlayerGrid(view.placeShipPanel.getGrid());
    }

    public void setName(String name) {
        //model.checkUnique(name);
        model.setName(name);
    }

    public void updateScore() {
        model.updateScore();
    }

    public void initiateBoard() {
        view.initiateBoard(model.getShipLengths());
    }

    public void setOrigin(Coordinate coordinate) {
        model.setOrigin(coordinate);

    }

    public void setEnd(Coordinate coordinate) {
        model.setEnd(coordinate);
    }

    public boolean checkValid(Coordinate coordinate, int shipLength) {
        return model.checkValid(coordinate, shipLength);
    }
}
