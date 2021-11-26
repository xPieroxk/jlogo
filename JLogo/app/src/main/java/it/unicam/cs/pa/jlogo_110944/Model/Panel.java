package it.unicam.cs.pa.jlogo_110944.Model;

import java.util.List;

/**
 * Identifies a panel on which the cursor moves.
 *
 * @param <L> location type
 * @param <C> cursor type
 */
public interface Panel<L, C extends Cursor<L>> {

    /**
     * Adds a line to the panel.
     *
     * @param line the line to be added
     * @throws NullPointerException if the line is null
     */
    void addLine(Line<L> line);

    /**
     * Adds an area to the panel.
     *
     * @param line lines of the area
     * @throws NullPointerException if the area is null
     */
    void addArea(Area<L> line);

    /**
     * Gets the line that has the given locations.
     *
     * @param location1 an end point of the line
     * @param location2 an end point of the line
     * @return a line that contains the given locations or null if the line was not found
     * @throws NullPointerException if one of the locations is null
     */
    Line<L> getLine(L location1, L location2);

    /**
     * Moves the cursor forward or backward from its current position.
     *
     * @param distance the distance
     * @return the new position of the cursor
     */
    L moveCursor(int distance);

    /**
     * Rotates the cursor.
     *
     * @param degrees the degrees to be added
     */
    void rotateCursor(int degrees);

    /**
     * Gets the panel lines that can create a closed area, in the order in which they were added.
     *
     * @return the panel lines that can create a closed area
     */
    List<Line<L>> getLines();

    /**
     * Gets all found areas.
     *
     * @return areas found
     */
    List<Area<L>> getAreas();

    /**
     * Erases all shapes and closed areas.
     */
    void reset();

    /**
     * Gets the home location of the panel
     *
     * @return the home location of the panel
     */
    L getHome();

    /**
     * Moves the Cursor to the home location.
     */
    void moveToHome();

    /**
     * Sets the screen color.
     *
     * @param color the new color of the screen
     * @throws NullPointerException if the color is null
     */
    void setScreenColor(RGBColor color);

    /**
     * Sets the fill color.
     *
     * @param color the new fill color
     * @throws NullPointerException if the color is null
     */
    void setFillColor(RGBColor color);

    /**
     * Gets the screen color.
     *
     * @return the screen color
     */
    RGBColor getScreenColor();

    /**
     * Gets the fill color.
     *
     * @return the fill color
     */
    RGBColor getFillColor();

    /**
     * Gets the result of the executed commands.
     *
     * @return a list containing the result of the executed commands
     */
    List<String> result();

    /**
     * Get the cursor of this panel.
     *
     * @return the cursor
     */
    C getCursor();

    /**
     * Add a PanelUpdateListener to the listener list. The listener is registered for all properties.
     * The same listener object may be added more than once, and will be called as many times as it is added.
     * If listener is null, no exception is thrown and no action is taken.
     *
     * @param listener the PanelUpdateListener to be added
     */
    void addPanelUpdateListener(PanelUpdateListener<L> listener);

    /**
     * Remove a PanelUpdateListener from the listener list. This removes a PropertyChangeListener that
     * was registered for all properties. If listener was added more than once to the same event source, it
     * will be notified one less time after being removed. If listener is null, or was never added, no
     * exception is thrown and no action is taken.
     *
     * @param listener the PanelUpdateListener to be removed
     */
    void removePanelUpdateListener(PanelUpdateListener<L> listener);
}
