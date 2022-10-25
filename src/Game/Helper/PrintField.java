package Game.Helper;

import Game.Cells.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PrintField {
    private final Field field;
    private final GameData gameData;

    public PrintField(GameData gameData) {
        this.gameData = gameData;
        this.field = gameData.getField();
    }

    public void printField() {
        printSeparator();

        for (int row = 0; row < gameData.getHeightField(); row++) {
            for (int col = 0; col < gameData.getWidthField(); col++) {
                Cell cell = field.getCell(row, col);
                System.out.print(cell.watchCell());
            }

            System.out.println();
        }

        printSeparator();
    }

    private void printSeparator() {
        for (int i = 0; i < gameData.getWidthField(); i++) {
            System.out.print("=");
        }
        System.out.println();
    }

    public int watchCell(int row, int col) {
        int countFreeCell = 0;
        Cell cell = field.getCell(row, col);

        if (cell instanceof CellMine) {
            watchMines();
            throw new DefeatGameException();
        } else if (cell instanceof CellNumber) {
            cell.becomeAware();
            countFreeCell++;
        } else if (cell instanceof CellEmpty){
            int countCellEmpty = watchEmptyCell(cell);
            countFreeCell += countCellEmpty;
        }

        return countFreeCell;
    }

    private void watchMines() {
        for (int row = 0; row < gameData.getHeightField(); row++) {
            for (int col = 0; col < gameData.getWidthField(); col++) {
                Cell cell = field.getCell(row, col);

                if (cell instanceof CellMine) {
                    cell.becomeAware();
                }
            }
        }
    }

    private int watchEmptyCell(Cell startCell) {
        int countEmptyCell = 1;

        List<Cell> passedCell = new ArrayList<>();
        Stack<Cell> stackNearCell = new Stack<>();
        stackNearCell.add(startCell);

        while (!stackNearCell.empty()) {
            Cell mainCell = stackNearCell.pop();
            List<Cell> nearCell = field.getListNearCells(mainCell.getRow(), mainCell.getCol());

            for (Cell cell : nearCell) {
                if (!passedCell.contains(cell)) {
                    if (cell instanceof CellEmpty) {
                        stackNearCell.add(cell);
                    }

                    cell.becomeAware();
                    countEmptyCell++;
                    passedCell.add(cell);
                }
            }
        }

        return countEmptyCell;
    }
}
