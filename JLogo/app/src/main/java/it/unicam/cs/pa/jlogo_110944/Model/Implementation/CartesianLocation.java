package it.unicam.cs.pa.jlogo_110944.Model.Implementation;

import java.util.Objects;

/**
 * Identifies a Cartesian coordinate.
 */
public final class CartesianLocation {
    private final double x;
    private final double y;

    /**
     * Constructor method.
     *
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public CartesianLocation(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the x-coordinate.
     *
     * @return the x-coordinate
     */
    public double getX() {
        return this.x;
    }

    /**
     * Gets the y-coordinate.
     *
     * @return the y-coordinate
     */
    public double getY() {
        return this.y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartesianLocation that = (CartesianLocation) o;
        return Double.compare(that.x, x) == 0 && Double.compare(that.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "{x=" + x +
                ", y=" + y +
                '}';
    }
}
