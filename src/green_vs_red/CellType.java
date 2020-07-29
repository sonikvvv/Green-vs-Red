package green_vs_red;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The {@code CellType} enum holds all 9 cells types:
 * <p>
 * {@code CTL}, {@code CTR}, {@code CBL}, {@code CBR}, {@code SL}, {@code SR},
 * {@code SB}, {@code ST}, {@code INSIDE}.
 * <p>
 * {@code C} = corner, {@code S} = side, {@code T} = top, {@code B} = bottom,
 * {@code L} = left and {@code R} = right.
 * <p>
 * Methods: {@link #getType} and {@link #getNeighbour}.
 * 
 * @author Veselin Nikolov
 * 
 * @see green_vs_red.Cell
 */
public enum CellType {
    /**
     * @param CTL    Corner Top Left, with 3 possible neighbours.
     */
    CTL,
    /**
     * @param CTR    Corner Top Right, with 3 possible neighbours.
     */
    CTR,
    /**
     * @param CBL    Corner Bottom Left, with 3 possible neighbours.
     */
    CBL,
    /**
     * @param CBR    Corner Bottom Right, with 3 possible neighbours.
     */
    CBR,
    /**
     * @param SL     Side Left, with 5 possible neighbours.
     */
    SL,
    /**
     * @param SR     Side Right, with 5 possible neighbours.
     */
    SR,
    /**
     * @param SB     Side Bottom, with 5 possible neighbours.
     */
    SB,
    /**
     * @param ST     Side Top, with 5 possible neighbours.
     */
    ST,
    /**
     * @param INSIDE Inside, with 8 possible neighbours.
     */
    INSIDE;

    /**
     * This method returns the cell type determined by its coordinates.
     * 
     * @param row    Cell row coordinate.
     * @param col    Cell column coordinate.
     * @param iBound This is the row bound.
     * @param jBound This is the column bound.
     * @return {@link CellType} Returns object from {@code CellType} enum.
     */
    public static CellType getType(int row, int col, int iBound, int jBound) {
        if (row == 0 && col == 0)
            return CTL;
        else if (row == 0 && col == jBound - 1)
            return CTR;
        else if (row == iBound - 1 && col == 0)
            return CBL;
        else if (row == iBound - 1 && col == jBound - 1)
            return CBR;
        else if (row == 0 && col != jBound - 1)
            return ST;
        else if (row == iBound - 1 && col != jBound - 1)
            return SB;
        else if (col == 0 && row != 0 || iBound < iBound - 1)
            return SL;
        else if (col == jBound - 1 && row > 0 && row < iBound - 1)
            return SR;
        else
            return INSIDE;
    }

    /**
     * This method returns a {@code String} that contains encrypted coordinates of the
     * cell's neighbors according to its type.
     * <p>
     * Encrypted coordinates:
     * {@code "--"}, {@code "-0"}, {@code "-+"}, {@code "0-"}, {@code "0+"}, {@code "+-"}, {@code "+0"}, {@code "++" }.
     * <p>
     * {@code +} means {@code +1},
     * <p>
     * {@code -} means {@code -1},
     * <p>
     * {@code 0} means no change.
     * 
     * @param cellType              The cell type.
     * @return {@code List<String>} Returns list of encrypted coordinates of the
     *         neighbours.
     */
    public static List<String> getNeighbour(CellType cellType) {
        final String[] coordinates = { "--", "-0", "-+", "0-", "0+", "+-", "+0", "++" };
        List<String> result = new ArrayList<>();
        switch (cellType) {
            case INSIDE:
                result = Arrays.asList(coordinates);
                break;
            case CTL:
                result.add(coordinates[4]);
                result.add(coordinates[6]);
                result.add(coordinates[7]);
                break;
            case CTR:
                result.add(coordinates[3]);
                result.add(coordinates[5]);
                result.add(coordinates[6]);
                break;
            case CBL:
                result.add(coordinates[1]);
                result.add(coordinates[2]);
                result.add(coordinates[4]);
                break;
            case CBR:
                result.add(coordinates[0]);
                result.add(coordinates[1]);
                result.add(coordinates[3]);
                break;
            case SL:
                result.add(coordinates[1]);
                result.add(coordinates[2]);
                result.add(coordinates[4]);
                result.add(coordinates[6]);
                result.add(coordinates[7]);
                break;
            case SR:
                result.add(coordinates[0]);
                result.add(coordinates[1]);
                result.add(coordinates[3]);
                result.add(coordinates[5]);
                result.add(coordinates[6]);
                break;
            case ST:
                result.add(coordinates[3]);
                result.add(coordinates[4]);
                result.add(coordinates[5]);
                result.add(coordinates[6]);
                result.add(coordinates[7]);
                break;
            case SB:
                result.add(coordinates[0]);
                result.add(coordinates[1]);
                result.add(coordinates[2]);
                result.add(coordinates[3]);
                result.add(coordinates[4]);
                break;
        }
        return result;
    }
}