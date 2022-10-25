package Game.Cells;

public abstract class Cell {
    private boolean isAware = false;
    private final int row;
    private final int col;

    protected Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public String watchCell() {
        if (isAware) {
            return this.getSymbol();
        } else {
            return "?";
        }
    }

    public void becomeAware() {
        isAware = true;
    }

    public abstract String getSymbol();

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
