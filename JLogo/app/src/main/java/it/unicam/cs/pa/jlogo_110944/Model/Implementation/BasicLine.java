package it.unicam.cs.pa.jlogo_110944.Model.Implementation;

import it.unicam.cs.pa.jlogo_110944.Model.Line;
import it.unicam.cs.pa.jlogo_110944.Model.RGBColor;

import java.util.Objects;

/**
 * Basic implementation of the Line interface.
 *
 * @param <L> location type
 */
public class BasicLine<L> implements Line<L> {

    private final L start;
    private final L finish;
    private final int size;
    private final RGBColor color;

    /**
     * Constructor method.
     *
     * @param start  start of the line
     * @param finish end of the line
     * @param size   size of the line
     * @param color  color of the line
     * @throws NullPointerException if the start,finish or the color are null
     */
    public BasicLine(L start, L finish, int size, RGBColor color) {
        this.start = Objects.requireNonNull(start, "The start cannot be null");
        this.finish = Objects.requireNonNull(finish, "The finish cannot be null");
        this.color = Objects.requireNonNull(color, "The color cannot be null");
        this.size = size;
    }

    /**
     * Constructor method.
     *
     * @param start  start of the line
     * @param finish end of the line
     * @throws NullPointerException if the start or the finish are null
     */
    public BasicLine(L start, L finish) {
        this.start = Objects.requireNonNull(start, "The start cannot be null");
        this.finish = Objects.requireNonNull(finish, "The finish cannot be null");
        this.size = 1;
        this.color = new BasicRGBColor((byte) 0, (byte) 0, (byte) 0);
    }

    @Override
    public L getStart() {
        return this.start;
    }

    @Override
    public L getFinish() {
        return this.finish;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public RGBColor getColor() {
        return this.color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasicLine<?> basicLine = (BasicLine<?>) o;
        return (start.equals(basicLine.start) && finish.equals(basicLine.finish)) || (start.equals(basicLine.finish) && finish.equals(basicLine.start));
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, finish);
    }

}
