package it.unicam.cs.pa.jlogo_110944.Model;

/**
 * Identifies the cursor in the Logo programming.
 *
 * @param <L> location type
 */
public interface Cursor<L> {

    /**
     * Gets the cursor position.
     *
     * @return cursor position
     */
    L getPosition();

    /**
     * Gets the cursor direction.
     *
     * @return cursor direction
     */
    int getDirection();

    /**
     * Gets the color of the cursor line.
     *
     * @return color of the cursor line
     */
    RGBColor getLineColor();

    /**
     * Returns a boolean which indicates if the cursor can generate lines
     * when moving.
     *
     * @return true if the cursor generates a line when moving, false otherwise
     */
    boolean getPlot();

    /**
     * Gets the pen size of the cursor.
     *
     * @return the pen size of the cursor
     */
    int getLineSize();

    /**
     * Set the cursor position.
     *
     * @param location the new cursor position
     * @throws NullPointerException if the location is null
     */
    void setPosition(L location);

    /**
     * Set the cursor direction.
     *
     * @param direction the new cursor direction
     * @throws IllegalArgumentException if the direction is not between 0 and 360
     */
    void setDirection(int direction);

    /**
     * Set the color of the line that the cursor generates when it moves.
     *
     * @param color color of the line
     * @throws NullPointerException if the color is null
     */
    void setLineColor(RGBColor color);

    /**
     * Set the plot of the cursor, true if it writes, false otherwise.
     *
     * @param b true if the cursor generates a line when moving, false otherwise
     */
    void setPlot(boolean b);

    /**
     * Set the pensize of the cursor
     *
     * @param size the new pensize
     * @throws IllegalArgumentException if the size is less than 1
     */
    void setLineSize(int size);
}
