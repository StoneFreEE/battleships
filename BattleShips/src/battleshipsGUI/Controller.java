/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battleshipsGUI;

/**
 * The controller class that mediates between the model and the view. It handles
 * user input and updates the model and view accordingly. Implements the logic
 * and functionality of the Battleships game.
 *
 * @author oliver
 */
public class Controller {

    private Model model;
    private View view;

    /**
     * Constructs a Controller object.
     *
     * @param model the Model object
     * @param view the View object
     */
    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        this.view.setController(this);

        this.model.addObserver(view);
    }

    /**
     * Starts the game by displaying the start screen.
     */
    public void startGame() {
        view.startGame();
    }

    /**
     * Plays the game by initializing the player's game grid and displaying the
     * game screen.
     */
    public void playGame() {
        System.out.println("Initialising finished");
        setPlayerGameGrid();
        view.playGame();
    }

    /**
     * Plays the game from a loaded board by initializing the player's game grid
     * from the provided grid and displaying the game screen.
     */
    public void playFromLoad() {
        System.out.println("Loaded board");
        loadPlayerGameGrid(view.getGrid());
        view.playGame();
    }

    /**
     * Loads the player's game grid from the provided grid.
     *
     * @param grid the GridPlayer object representing the player's game grid
     */
    public void loadPlayerGameGrid(GridPlayer grid) {
        model.initPlayerGrid(grid);
    }

    /**
     * Loads the board with the given board name from the model.
     *
     * @param boardName the name of the board to load
     */
    public void loadBoard(String boardName) {
        // Set board to loadedboard
        model.setBoardName(boardName);
        model.loadBoard();

    }

    /**
     * Displays the leaderboard in the view.
     */
    public void displayLeaderboard() {
        view.displayLeaderboard(model.getUsers());
    }

    /**
     * Initializes the enemy's game grid by letting the player choose ship
     * placement.
     */
    public void setEnemyGrid() {
        // let player choose ship placement and save it on a gamegrid object
        model.initEnemyGrid();
    }

    /**
     * Links the FrameGame object to the player's game grid in the model.
     *
     * @param panel the FrameGame object representing the game screen
     */
    public void linkFrametoGrid(FrameGame panel) {
        model.linkPaneltoGrid(panel);
    }

    /**
     * Updates the turn by performing the enemy's firing action.
     */
    public void updateTurn() {
        model.enemyFireCannon();
    }

    /**
     * Sets the player's game grid by letting the player choose ship placement.
     */
    public void setPlayerGameGrid() {
        // let player choose ship placement and save it on a gamegrid object
        GridPlayer grid = view.placeShipPanel.getGrid();
        model.initPlayerGrid(grid);
    }

    /**
     * Sets the name of the player by checking uniqueness and updating the
     * model.
     *
     * @param name the name of the player
     */
    public void setName(String name) {
        model.checkUnique(name);
        model.setName(name);
    }

    /**
     * Updates the score of the game.
     */
    public void updateScore() {
        model.updateScore();
    }

    /**
     * Updates the player's score.
     */
    public void updatePlayerScore() {
        model.updatePlayerScore();
    }

    /**
     * Initiates the game board by displaying the board initiation screen.
     */
    public void initiateBoard() {
        view.initiateBoard(model.getShipLengths());
    }

    /**
     * Sets the origin coordinate for ship placement.
     *
     * @param coordinate the origin coordinate
     */
    public void setOrigin(Coordinate coordinate) {
        model.setOrigin(coordinate);

    }

    /**
     * Initiates the start screen by displaying it in the view.
     */
    public void initiateStartScreen() {
        view.initiateStartScreen();
    }

    /**
     * Sets the end coordinate for ship placement.
     *
     * @param coordinate the end coordinate
     */
    public void setEnd(Coordinate coordinate) {
        model.setEnd(coordinate);
    }

    /**
     * Handles the game over event by updating the score, displaying the game
     * over screen, and updating the model with the score.
     *
     * @param winner the winner of the game
     * @param score the score achieved in the game
     */
    public void gameOver(String winner, int score) {

        model.updateScore();
        view.gameOver(winner, score);
    }

    /**
     * Checks if the ship placement is valid for the given coordinate and ship
     * length.
     *
     * @param coordinate the coordinate to check
     * @param shipLength the length of the ship
     * @return true if the placement is valid, false otherwise
     */
    public boolean checkValid(Coordinate coordinate, int shipLength) {
        return model.checkValid(coordinate, shipLength);
    }

    /**
     * Sets the score for the game.
     *
     * @param score the score to set
     */
    public void setScore(int score) {
        model.setScore(score);
    }

    /**
     * Saves the current game board with the given board name.
     *
     * @param boardName the name of the board to save
     */
    public void saveBoard(String boardName) {
        model.insertBoard(boardName, model.getUser());
    }

    /**
     * Updates the existing game board with the given board name.
     *
     * @param boardName the name of the board to update
     */
    public void updateBoard(String boardName) {
        model.updateBoard(boardName, model.getUser());
    }

    /**
     * Prompts the view to save the current game board with the provided grid.
     *
     * @param grid the GridPlayer object representing the current game grid
     */
    public void promptSave(GridPlayer grid) {
        view.promptSave(grid);
    }
}
