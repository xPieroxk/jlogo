package it.unicam.cs.pa.jlogo_110944.Model;


/**
 * Listener of events produced by a panel.
 *
 * @param <L> location type
 */
public interface PanelUpdateListener<L> {

    /**
     * This method is called when the angle of the cursor is changed.
     *
     * @param angle the new angle of the cursor
     */
    void fireAngleChanged(int angle);

    /**
     * This method is called when a line is added to the panel.
     *
     * @param line the added line
     */
    void fireAddLine(Line<L> line);

    /**
     * This method is called when the cursor moves.
     *
     * @param location the new location of the cursor
     */
    void fireCursorMoved(L location);

    /**
     * This method is called when a closed area is found.
     *
     * @param area the area found
     */
    void fireAreaFound(Area<L> area);

    /**
     * This method is called when all lines and closed areas are removed from the panel.
     */
    void fireClearScreen();

    /**
     * This method is called when the color of the panel screen is changed.
     *
     * @param color the new color of the screen
     */
    void fireSetScreenColor(RGBColor color);

    /**
     * This method is called when a new panel is created.
     *
     * @param width  the width of the panel
     * @param height the height of the panel
     */
    void firePanelChanged(int width, int height);
}
