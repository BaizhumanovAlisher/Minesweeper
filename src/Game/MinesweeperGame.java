package Game;

import Game.Helper.*;

public class MinesweeperGame {
    private GameData gameData;
    private UserInputData userInputData;
    private PrintField printField;
    public void startGame() {
        try {
            beginGame();
            middleGame();
            endGame();
        } catch (DefeatGameException ignored) {
            printField.printField();
            System.out.println("You defeat.");
        } catch (QuitGameException ignored) {
            System.out.println("Exiting the game");
        }
    }

    private void beginGame() {
        System.out.println("""
                Hello. It's Minesweeper.
                Set the field size and amount mines:""");
        int[] initialUserData = UserInputData.getInitialUserData();

        initializationGameData(initialUserData);
        initializationAdditionalComponent();
    }

    private void middleGame() {
        int countFreeCell = gameData.getHeightField() * gameData.getWidthField() - gameData.getAmountMines();

        do {
            printField.printField();

            int[] coordinates = userInputData.getUserData();
            int row = coordinates[0];
            int col = coordinates[1];

            countFreeCell -= printField.watchCell(row, col);
        } while (countFreeCell > -1);

        printField.printField();
    }

    private void endGame() {
        System.out.println("You've won. My congratulations!");
    }

    private void initializationAdditionalComponent() {
        InitializationField f = new InitializationField();
        f.initializationField(gameData);

        userInputData = new UserInputData(gameData);
        printField = new PrintField(gameData);
    }

    private void initializationGameData(int[] initialUserData) {
        int height = initialUserData[0];
        int width = initialUserData[1];
        int amountMines = initialUserData[2];

        gameData = new GameData(height, width, amountMines);
    }
}
