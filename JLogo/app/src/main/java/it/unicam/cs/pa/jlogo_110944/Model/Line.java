package it.unicam.cs.pa.jlogo_110944.Model;

/**
 * Identifies a line between two locations in the panel.
 *
 * @param <L> location type
 */
public interface Line<L> {

    /**
     * Get the thickness of the line.
     *
     * @return the thickness of the line
     */
    int getSize();

    /**
     * Get the color of the line
     *
     * @return the color of the line
     */
    RGBColor getColor();

    /**
     * Get the start of the line.
     *
     * @return the start of the line
     */
    L getStart();

    /**
     * Get the end of the line.
     *
     * @return the end of the line
     */
    L getFinish();

}
