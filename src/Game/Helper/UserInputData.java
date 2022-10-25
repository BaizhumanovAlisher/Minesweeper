package Game.Helper;

import Game.Cells.Cell;

import java.util.Scanner;

public class UserInputData {
    private final GameData gameData;
    private final static Scanner sc = new Scanner(System.in);

    public UserInputData(GameData gameData) {
        this.gameData = gameData;
    }

    public static void checkQuit(String s) {
        if (s.equalsIgnoreCase("quit")) {
            throw new QuitGameException();
        }
    }

    public static int[] getInitialUserData() {
        do {
            String str = sc.nextLine();
            checkQuit(str);

            try {
                String[] array = str.split(" ");
                int height = Integer.parseInt(array[0]);
                int width = Integer.parseInt(array[1]);
                int amountMines = Integer.parseInt(array[2]);

                if (!checkInitialData(height, width, amountMines) || array.length != 3) {
                    continue;
                }

                return formationResult(height, width, amountMines);
            } catch (Exception ignored) {}

            System.out.println("You entered the data incorrectly. Try again.");
        } while (true);
    }

    private static boolean checkInitialData(int height, int width, int amountMines) {
        if (height < 2 || width < 2) {
            System.out.println("The field sizes are too small. Try again.");
            return false;
        }

        if (height > 99 || width > 99) {
            System.out.println("The field sizes are too large. Try again.");
            return false;
        }

        if (amountMines >= height * width) {
            System.out.println("There are too many bombs on the field. Try again.");
            return false;
        }

        if (amountMines < 1) {
            System.out.println("There are too few bombs on the field. Try again.");
            return false;
        }

        return true;
    }

    public int[] getUserData() {
        do {
            String str = sc.nextLine();
            checkQuit(str);

            try {
                String[] array = str.split(" ");
                int row = Integer.parseInt(array[0]) - 1;
                int col = Integer.parseInt(array[1]) - 1;

                if (!checkInitialData(row, col) || array.length != 2) {
                    continue;
                }

                return formationResult(row, col);
            } catch (Exception ignored) {}

            System.out.println("You entered the data incorrectly. Try again.");
        } while (true);
    }

    private static int[] formationResult(int height, int width, int amountMines) {
        int[] result = new int[3];
        result[0] = height;
        result[1] = width;
        result[2] = amountMines;
        return result;
    }

    private boolean checkInitialData(int row, int col) {
        if (row < 0 || row >= gameData.getHeightField()) {
            System.out.println("You entered the row incorrectly. Try again.");
            return false;
        }

        if (col < 0 || col >= gameData.getWidthField()) {
            System.out.println("You entered the col incorrectly. Try again.");
            return false;
        }

        return true;
    }

    private int[] formationResult(int row, int col) {
        int[] result = new int[2];
        result[0] = row;
        result[1] = col;
        return result;
    }
}
