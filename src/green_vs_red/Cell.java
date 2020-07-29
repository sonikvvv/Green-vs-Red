package green_vs_red;

/**
 * The {@code Cell} class hods the cell's {@code status} and cell's
 * {@code type}.
 * <p>
 * Constructors: Default {@link #Cell()} and explicit
 * {@link #Cell(int, CellType)}.
 * <p>
 * Methods: {@link #getStatus()}, {@link #getType()}, {@link #setStatus(int)},
 * {@link #setType(CellType)}, {@link #setEnableColor(boolean)} and
 * {@link #toString()}.
 * 
 * @param status      This variable holds the cell's status witch can be
 *                    {@code 1} for green or {@code 0} for red.
 * @param type        This variable holds the cell's type from {@link CellType}
 *                    enum.
 * @param enableColor This variables holds the cell's function to enable
 *                    printing with color. If {@code enableColor = true}
 *                    {@link #toString()} method returns {@code String} with
 *                    colored letters, otherwise returns {@code String} with
 *                    white letters.
 * 
 * @author Veselin Nikolov
 * 
 * @see green_vs_red.CellType
 * @see green_vs_red.Colors
 */
public class Cell {
    private int status;
    private CellType type;
    private boolean enableColor = false;

    /**
     * Default constructor for {@code Cell} class.
     * <p>
     * Default values: for variable {@code status} is 0 and for {@code type} is
     * {@link CellType#INSIDE}.
     * 
     * @param status   This variable can be {@code 1} for green or {@code 0} for
     *                 red.
     * @param cellType This variable holds the cell's type from {@link CellType}
     *                 enum.
     * 
     * @see green_vs_red.CellType
     */
    public Cell() {
        this.status = 0;
        this.type = CellType.INSIDE;
    }

    /**
     * Explicit constructor for {@code Cell} class.
     * 
     * @param status   This variable can be {@code 1} for green or {@code 0} for
     *                 red.
     * @param cellType This variable holds the cell's type from {@link CellType}
     *                 enum.
     * 
     * @see green_vs_red.CellType
     */
    public Cell(final int status, final CellType cellType) {
        this.status = status;
        this.type = cellType;
    }

    /**
     * This method returns cell's {@code status}.
     * 
     * @return {@code int} Returns cell's status (0 or 1) as {@code int}.
     */
    public int getStatus() {
        return this.status;
    }

    /**
     * This method returns cell's {@code type}.
     * 
     * @return Returns cell's type as {@link CellType}.
     * 
     * @see green_vs_red.CellType
     */
    public CellType getType() {
        return this.type;
    }

    /**
     * This method sets cell's {@code status}.
     * 
     * @param status Sets the status of a cell (0 or 1).
     */
    public void setStatus(final int status) {
        this.status = status;
    }

    /**
     * This method sets cell's {@code type}.
     * 
     * @param type Sets the type of a cell.
     * 
     * @see green_vs_red.CellType
     */
    public void setType(final CellType type) {
        this.type = type;
    }

    /**
     * This method sets cell's {@code enableColor}.
     * 
     * @param enableColor {@code true} for print with color, {@code false} for
     *                    colorless.
     */
    public void setEnableColor(final boolean enableColor) {
        this.enableColor = enableColor;
    }

    /**
     * This method returns {@code String} with color if {@code enableColor = true}, else
     * {@code enableColor = false} colorless.
     * <p>
     * Using the enum {@link Colors}.
     * 
     * @param enableColor If equals true enables color usage.
     * @return Returns object of class {@code String} with cell status.
     * 
     * @see green_vs_red.Colors
     */
    @Override
    public String toString() {
        if (enableColor == true)
            if (this.status == 1)
                return Colors.GREEN.getColor() + this.status + Colors.RESET.getColor();
            else
                return Colors.RED.getColor() + this.status + Colors.RESET.getColor();
        else
            return this.status + "";
    }
}