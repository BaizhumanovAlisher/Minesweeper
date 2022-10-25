package Game.Cells;

public class CellNumber extends Cell {
    private final int amountNearMines;

    public CellNumber(int row, int col, int amountNearMines) {
        super(row, col);
        this.amountNearMines = amountNearMines;
    }

    public String getSymbol() {
        return Integer.toString(amountNearMines);
    }
}
