package it.unicam.cs.pa.jlogo_110944.Model.Implementation;

import it.unicam.cs.pa.jlogo_110944.Model.Area;
import it.unicam.cs.pa.jlogo_110944.Model.Line;
import it.unicam.cs.pa.jlogo_110944.Model.RGBColor;

import java.util.List;
import java.util.Objects;

/**
 * Basic implementation of the Area interface
 *
 * @param <L> location type
 */
public class BasicArea<L> implements Area<L> {

    private final List<Line<L>> lines;
    private final RGBColor color;

    /**
     * Constructor method.
     *
     * @param lines lines that make up the closed area
     * @param color the color of the area
     * @throws NullPointerException if the lines or the color are null
     */
    public BasicArea(List<Line<L>> lines, RGBColor color) {
        this.lines = Objects.requireNonNull(lines, "The lines cannot be null");
        this.color = Objects.requireNonNull(color, "The color cannot be null");
    }

    @Override
    public List<Line<L>> getLines() {
        return this.lines;
    }

    @Override
    public RGBColor getColor() {
        return this.color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasicArea<?> basicArea = (BasicArea<?>) o;
        return lines.equals(basicArea.lines) && color.equals(basicArea.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lines, color);
    }
}
