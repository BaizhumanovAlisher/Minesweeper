package Game.Helper;

import Game.Cells.Cell;

import java.util.ArrayList;
import java.util.List;

public class Field {
    private final Cell[][] field;
    private final GameData gameData;

    public Field(GameData gameData) {
        this.gameData = gameData;
        field = new Cell[gameData.getHeightField()][gameData.getWidthField()];
    }

    public Cell getCell(int row, int col) {
        return field[row][col];
    }

    public void setCell(Cell cell, int row, int col) {
        field[row][col] = cell;
    }

    public List<Cell> getListNearCells(int row, int col) {
        List<Cell> nearCells = new ArrayList<>();

        boolean isLeft = col == 0;
        boolean isRight = col == gameData.getWidthField() - 1;
        boolean isLower = row == 0;
        boolean isUpper = row == gameData.getHeightField() - 1;

        if (!isLeft && !isLower) {
            nearCells.add(field[row - 1][col - 1]);
        }

        if (!isLower) {
            nearCells.add(field[row - 1][col]);
        }

        if (!isLower && !isRight) {
            nearCells.add(field[row - 1][col + 1]);
        }

        if (!isLeft) {
            nearCells.add(field[row][col - 1]);
        }

        if (!isRight) {
            nearCells.add(field[row][col + 1]);
        }

        if (!isUpper && !isLeft) {
            nearCells.add(field[row + 1][col - 1]);
        }

        if (!isUpper) {
            nearCells.add(field[row + 1][col]);
        }

        if (!isUpper && !isRight) {
            nearCells.add(field[row + 1][col + 1]);
        }

        return nearCells;
    }
}
