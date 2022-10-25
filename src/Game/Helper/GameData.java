package Game.Helper;

public class GameData {
    private final int heightField;
    private final int widthField;
    private final int amountMines;
    private final Field field;

    public GameData(int heightField, int widthField, int amountMines) {
        this.heightField = heightField;
        this.widthField = widthField;
        this.amountMines = amountMines;
        this.field = new Field(this);
    }

    public int getHeightField() {
        return heightField;
    }

    public int getWidthField() {
        return widthField;
    }

    public int getAmountMines() {
        return amountMines;
    }

    public Field getField() {
        return field;
    }
}
