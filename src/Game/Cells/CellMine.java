package Game.Cells;

public class CellMine extends Cell {
    public CellMine(int row, int col) {
        super(row, col);
    }

    public String getSymbol() {
        return "*";
    }
}
