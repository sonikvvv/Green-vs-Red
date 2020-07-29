package green_vs_red;

import java.util.Scanner;

public class Main {

    /**
     * This method checks if the entered numbers are in allowed limits.
     * 
     * @param x For row.
     * @param y For column.
     * @return {@code true} if x and y are within the permitted limits, else returns
     *         {@code false}.
     */
    public static boolean checkBounds(int x, int y) {
        final int maxSize = 1000;
        final int minSize = 0;

        if (x <= maxSize && y <= maxSize || x > minSize && y > minSize)
            return true;
        return false;
    }

    /**
     * Returns true if and only if this {@code String} contains the specified sequence of
     * {@code char} values.
     * 
     * @param str       The {@code String} to search in.
     * @param subString The {@code String} to search in {@code str}.
     * @return {@code true} if this string contains {@code str}, {@code false} otherwise.
     */
    public static boolean containsIgnoreCase(String str, String subString) {
        return str.toLowerCase().contains(subString.toLowerCase());
    }

    /**
     * This method checks if the coordinates of the cell are within the allowed
     * limits.
     * 
     * @param x  Cell"s row.
     * @param y  Cell"s colimn.
     * @param x1 Limits for rows entered by the user.
     * @param y1 Limits for columns entered by the user.
     * @return {@code true} if {@code x} and {@code y} are within the permitted limits, else returns
     *         {@code false}.
     */
    public static boolean checkCellBounds(int x, int y, int x1, int y1) {
        if (x1 < x && x1 >= 0 && y1 < y && y1 >= 0)
            return true;
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean allRandomFlag = false;
        boolean enableColorFlag = false;

        int xBound = 0;
        int yBound = 0;

        int cellX = 0;
        int cellY = 0;

        int turns = 0;

        int result = 0;

        // User input for grid size.
        while (true) {
            System.out.print("Enter grid size: ");
            try {
                String gridSize = scanner.nextLine();

                if (containsIgnoreCase(gridSize, "color")) {
                    enableColorFlag = true;
                    System.out.println("Color enabled!");
                } else if (containsIgnoreCase(gridSize, "random")) {
                    allRandomFlag = true;
                    System.err.println("All random enabled!");
                } else {
                    String[] parts = gridSize.split("\\D++");
                    if (checkBounds(Integer.valueOf(parts[0]), Integer.valueOf(parts[1]))) {
                        xBound = Integer.valueOf(parts[0]);
                        yBound = Integer.valueOf(parts[1]);
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("Error! Enter number between 1 and 1000.");
            }
        }

        // Creating grid object.
        Grid grid0 = new Grid(xBound, yBound);
        grid0.GenerationZero(allRandomFlag, enableColorFlag);
        System.out.print(grid0);

        // User input for cell coordinates and turns.
        while (true) {
            System.out.print("Enter cell coordinates and turns: ");
            try {
                String gridSize = scanner.nextLine();
                String[] parts = gridSize.split("\\D++");
                if (checkCellBounds(xBound, yBound, Integer.valueOf(parts[0]), Integer.valueOf(parts[1]))) {
                    cellX = Integer.valueOf(parts[0]);
                    cellY = Integer.valueOf(parts[1]);
                    turns = Integer.valueOf(parts[2]);
                    break;
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Error! Enter 3 numbers!");
            } catch (Exception e) {
                System.out.println("Error! Enter cell coordinates inside the grid.");
            }
        }

        // Going through the generations.
        for (int i = 0; i <= turns; i++) {
            if (grid0.CheckCellStatus(cellX, cellY) == 1)
                result++;
            grid0 = grid0.NextGeneration();
        }

        // Printing result.
        System.out.println("Result: " + result);

        scanner.close();
    }
}