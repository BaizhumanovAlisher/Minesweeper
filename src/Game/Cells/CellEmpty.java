package Game.Cells;

public class CellEmpty extends Cell {
    public CellEmpty(int row, int col) {
        super(row, col);
    }

    @Override
    public String getSymbol() {
        return "-";
    }
}
