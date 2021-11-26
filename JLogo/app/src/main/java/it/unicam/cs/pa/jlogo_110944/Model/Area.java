package it.unicam.cs.pa.jlogo_110944.Model;

import java.util.List;

/**
 * Identifies a closed area.
 *
 * @param <L> location type
 */
public interface Area<L> {

    /**
     * Get the lines that make up this area.
     *
     * @return the lines that make up this area
     */
    List<Line<L>> getLines();

    /**
     * Get the color of the closed area.
     *
     * @return the color of the closed area
     */
    RGBColor getColor();
}
