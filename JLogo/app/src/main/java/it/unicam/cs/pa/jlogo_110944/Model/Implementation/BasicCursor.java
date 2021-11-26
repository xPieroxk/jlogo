package it.unicam.cs.pa.jlogo_110944.Model.Implementation;

import it.unicam.cs.pa.jlogo_110944.Model.Cursor;
import it.unicam.cs.pa.jlogo_110944.Model.RGBColor;

import java.util.Objects;

/**
 * Basic implementation of the Cursor interface
 *
 * @param <L> location type
 */
public class BasicCursor<L> implements Cursor<L> {

    private L position;
    private int direction;
    private RGBColor lineColor;
    private boolean plot;
    private int lineSize;

    /**
     * Constructor method.
     *
     * @param location the location of the cursor
     * @throws NullPointerException if the location is null
     */
    public BasicCursor(L location) {
        this.position = Objects.requireNonNull(location,"The location cannot be null");
        this.direction = 0;
        this.lineColor = new BasicRGBColor((byte) 0, (byte) 0, (byte) 0);
        this.plot = true;
        this.lineSize = 1;
    }

    @Override
    public L getPosition() {
        return this.position;
    }

    @Override
    public int getDirection() {
        return this.direction;
    }

    @Override
    public RGBColor getLineColor() {
        return this.lineColor;
    }

    @Override
    public boolean getPlot() {
        return this.plot;
    }

    @Override
    public int getLineSize() {
        return this.lineSize;
    }

    @Override
    public void setPosition(L location) {
        if (location == null)
            throw new NullPointerException("The location cannot be null");
        this.position = location;
    }

    @Override
    public void setDirection(int direction) {
        if (direction < 0 || direction > 360)
            throw new IllegalArgumentException("The direction must be between 0 and 360");
        this.direction = direction;
    }

    @Override
    public void setLineColor(RGBColor color) {
        if (color == null)
            throw new NullPointerException("The color cannot be null");
        this.lineColor = color;
    }

    @Override
    public void setPlot(boolean b) {
        this.plot = b;
    }

    @Override
    public void setLineSize(int size) {
        this.lineSize = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasicCursor<?> that = (BasicCursor<?>) o;
        return direction == that.direction && plot == that.plot && lineSize == that.lineSize && position.equals(that.position) && lineColor.equals(that.lineColor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, direction, lineColor, plot, lineSize);
    }
}
