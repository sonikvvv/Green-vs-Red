package green_vs_red;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * The {@code Grid} class hods the the height and widht of the grid.
 * <p>
 * Constructors: Explicit {@link #Grid(int, int)}.
 * <p>
 * Methods: {@link #getX_width()}, {@link #getY_height()}, {@link #getGrid()},
 * {@link #setX_width(int)}, {@link #setY_height(int)}, {@link #setGrid(List)},
 * {@link #addGridRow(List)}, {@link #GenerationZero(boolean, boolean)},
 * {@link #NextGeneration()}, {@link #checkCellNeighbours(Cell, int, int)},
 * {@link #CheckCellStatus(int, int)} and {@link #toString()}.
 * 
 * @param grid     This variale holds list of lists filled with objects from
 *                 class {@link Cell}.
 * @param x_width  This variale holds the grid's width.
 * @param y_height This variale holds the grid's height.
 * 
 * @author Veselin Nikolov
 * 
 * @see green_vs_red.Cell
 * @see green_vs_red.CellType
 */
public class Grid {
    private List<List<Cell>> grid = new ArrayList<>();
    private int x_width;
    private int y_height;

    /**
     * Explicit constructor for {@code Grid} class.
     * 
     * @param x Rows.
     * @param y Columns.
     */
    public Grid(int x, int y) {
        this.x_width = x;
        this.y_height = y;
    }

    /**
     * This method returns the grid's width.
     * 
     * @return {@code int} Grid's width.
     */
    public int getX_width() {
        return x_width;
    }

    /**
     * This method returns the grid's height.
     * 
     * @return {@code int} Grid's height.
     */
    public int getY_height() {
        return y_height;
    }

    /**
     * This method return the whole grid as {@code List<List<Cell>>}.
     * 
     * @return {@code List<List<Cell>>} All grid's rows.
     */
    public List<List<Cell>> getGrid() {
        return grid;
    }

    /**
     * This method sets the grid's width.
     * 
     * @param x_width Grid's width.
     */
    public void setX_width(int x_width) {
        this.x_width = x_width;
    }

    /**
     * This method sets the grid's height.
     * 
     * @param y_height Grid's height.
     */
    public void setY_height(int y_height) {
        this.y_height = y_height;
    }

    /**
     * This method sets the grid. Type: {@code List<List<Cell>>}.
     * 
     * @param grid The whole Grid.
     * 
     * @see green_vs_red.Cell
     */
    public void setGrid(List<List<Cell>> grid) {
        this.grid = grid;
    }

    /**
     * This method adds rows to the grid.
     * 
     * @param cellList Grid row from type {@code List<Cell>}.
     */
    public void addGridRow(List<Cell> cellList) {
        this.grid.add(cellList);
    }

    /**
     * This method generates the Generation Zero of the grid.
     * 
     * @param all_random  {@code boolean} flag to make the whole grid with random
     *                    numbers.
     * @param enableColor {@code boolean} flag to print the cell's with color.
     * 
     * @see green_vs_red.Cell
     * @see green_vs_red.CellType
     */
    public void GenerationZero(boolean all_random, boolean enableColor) {
        Random random = new Random();
        for (int i = 0; i < x_width; i++) {
            List<Cell> cellList = new ArrayList<>();
            for (int j = 0; j < y_height; j++) {
                boolean random_value = random.nextBoolean();

                Cell cell;
                if (random_value == true) {
                    cell = new Cell(1, CellType.getType(i, j, this.x_width, this.y_height));
                    if (enableColor == true)
                        cell.setEnableColor(true);
                    cellList.add(cell);
                } else if (all_random == false && i == 1) {
                    cell = new Cell(1, CellType.getType(i, j, this.x_width, this.y_height));
                    if (enableColor == true)
                        cell.setEnableColor(true);
                    cellList.add(cell);
                } else {
                    cell = new Cell(0, CellType.getType(i, j, this.x_width, this.y_height));
                    if (enableColor == true)
                        cell.setEnableColor(true);
                    cellList.add(cell);
                }
            }
            addGridRow(cellList);
        }
    }

    /**
     * This method returns new object from {@code Grid} class.
     * <ol>
     * <li>Each red cell that is surrounded by exactly 3 or exactly 6 green cells
     * will also become green in the next generation.</li>
     * <li>A red cell will stay red in the next generation if it has either 0, 1, 2,
     * 4, 5, 7 or 8 green neighbors.</li>
     * <li>Each green cell surrounded by 0, 1, 4, 5, 7 or 8 green neighbors will
     * become red in the next generation.</li>
     * <li>A green cell will stay green in the next generation if it has either 2, 3
     * or 6 green neighbors.</li>
     * </ol>
     * 
     * @return {@code Grid} New {@link Grid} object.
     */
    public Grid NextGeneration() {
        int i = 0;
        int j = 0;
        Grid newGrid = new Grid(this.x_width, this.y_height);

        for (List<Cell> list : this.grid) {
            List<Cell> row = new ArrayList<>();
            for (Cell cell : list) {
                int green_neighbours = checkCellNeighbours(cell, i, j);

                switch (green_neighbours) {
                    case 0:
                    case 1:
                    case 4:
                    case 5:
                    case 7:
                    case 8:
                        row.add(new Cell(0, cell.getType()));
                        break;
                    case 3:
                    case 6:
                        row.add(new Cell(1, cell.getType()));
                        break;
                    case 2:
                        row.add(cell);
                        break;
                }
                j++;
            }
            newGrid.addGridRow(row);
            i++;
            j = 0;
        }
        return newGrid;
    }

    /**
     * This method receives object from class {@link Cell}, {@code int} for row and
     * {@code int} for column, culates and returns green neighbors of the cell.
     * <p>
     * This method {@link CellType#getNeighbour(CellType)} returns {@code String}
     * with encrypted coordinates of the cell's neighbors according to its type.
     * 
     * @param cell   Object from class {@link Cell}.
     * @param row    {@code int} for row.
     * @param column {@code int} for column.
     * @return Count of the cell's green neighbours as {@code int}.
     * 
     * @see green_vs_red.Cell
     * @see green_vs_red.CellType
     */
    private int checkCellNeighbours(Cell cell, int row, int column) {
        List<String> neighbours = CellType.getNeighbour(cell.getType());
        List<Integer> coordinates = new ArrayList<>();
        int green = 0;
        for (String string : neighbours) {
            char[] symbols = string.toCharArray();
            for (int k = 0; k < symbols.length; k++) {
                if (symbols[k] == '+')
                    if (k == 0)
                        coordinates.add(row + 1);
                    else
                        coordinates.add(column + 1);
                else if (symbols[k] == '-')
                    if (k == 0)
                        coordinates.add(row - 1);
                    else
                        coordinates.add(column - 1);
                else if (k == 0)
                    coordinates.add(row);
                else
                    coordinates.add(column);
            }
        }

        // Checking all green neighbours of the cell.
        Iterator<Integer> it = coordinates.iterator();
        while (it.hasNext()) {
            List<Cell> tmpList = this.grid.get(it.next());
            Cell tmpCell = tmpList.get(it.next());
            if (tmpCell.getStatus() == 1)
                ++green;
        }

        return green;
    }

    /**
     * This method finds the cell and returns its {@code status}.
     * 
     * @param row    Cell's row coordinate.
     * @param colunm Cell's column coordinate.
     * @return Cell's status as {@code int}.
     * 
     * @see green_vs_red.Cell
     */
    public int CheckCellStatus(int row, int colunm) {
        List<Cell> tmpList = this.grid.get(row);
        Cell tmpCell = tmpList.get(colunm);

        return tmpCell.getStatus();
    }

    /**
     * This method returns the whole grid as {@code String}.
     * 
     * @return The whole grid as {@code String}.
     */
    @Override
    public String toString() {
        String res = "";
        for (List<Cell> list : grid) {
            for (Cell cell : list) {
                res = res + cell.toString();
            }
            res = res + "\n";
        }
        return res;
    }
}