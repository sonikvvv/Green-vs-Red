package green_vs_red;

/**
 * The {@code Colors} enum holds the color codes for fun terminal.
 * 
 * <p>
 * Methods: {@link #getColor}.
 * 
 * @author Veselin Nikolov
 */
public enum Colors {
    /**
     * @param RED   Red color.
     */
    RED("\033[0;31m"),
    /**
     * @param GREEN Green color.
     */
    GREEN("\033[0;32m"),
    /**
     * @param RESET Resets the color to white.
     */
    RESET("\033[0m");

    /**
     * @param color Holds the color code as {@code String}.
     */
    private String color;

    Colors(final String color) {
        this.color = color;
    }

    /**
     * Returns the color code as {@code String}.
     * 
     * @return Color code.
     */
    public String getColor() {
        return this.color;
    }
}