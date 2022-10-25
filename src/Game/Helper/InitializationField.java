package Game.Helper;

import Game.Cells.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InitializationField {
    private List<List<Integer>> listCoordinates;
    private GameData gameData;
    private Field field;

    public void initializationField(GameData gameData) {
        this.gameData = gameData;
        this.field = gameData.getField();

        creatingListCoordinates();

        fillingMines();

        fillingOtherCell();
    }

    private void creatingListCoordinates() {
        listCoordinates = new ArrayList<>();

        for (int i = 0; i < gameData.getHeightField(); i++) {
            for (int j = 0; j < gameData.getWidthField(); j++) {
                List<Integer> newArray = new ArrayList<>();
                newArray.add(i);
                newArray.add(j);
                listCoordinates.add(newArray);
            }
        }

        Collections.shuffle(listCoordinates);
    }

    private void fillingMines() {
        for (int i = 0; i < gameData.getAmountMines(); i++) {
            List<Integer> coordinates = listCoordinates.get(i);
            int row = coordinates.get(0);
            int col = coordinates.get(1);
            Cell cellMine = new CellMine(row, col);

            field.setCell(cellMine, row, col);
        }
    }

    private void fillingOtherCell(){
        for (int i = gameData.getAmountMines(); i < listCoordinates.size(); i++) {
            int row = listCoordinates.get(i).get(0);
            int col = listCoordinates.get(i).get(1);
            int amountNearMines = countingNearMines(row, col);

            if (amountNearMines != 0) {
                Cell cellNumber = new CellNumber(row, col, amountNearMines);
                field.setCell(cellNumber, row, col);
            } else {
                Cell cellEmpty = new CellEmpty(row, col);
                field.setCell(cellEmpty, row, col);
            }
        }
    }

    private int countingNearMines(int row, int col) {
        int countNearMines = 0;

        List<Cell> nearCells = field.getListNearCells(row, col);

        for (Cell cell : nearCells) {
            if (cell instanceof CellMine) {
                countNearMines++;
            }
        }

        return countNearMines;
    }
}
