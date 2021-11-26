package it.unicam.cs.pa.jlogo_110944.Model.Implementation;

import it.unicam.cs.pa.jlogo_110944.Model.RGBColor;

import java.util.Objects;

/**
 * Basic implementation of the RGBColor interface
 */
public class BasicRGBColor implements RGBColor {
    private final byte red;
    private final byte green;
    private final byte blue;

    /**
     * Constructor method.
     *
     * @param red   the red value
     * @param green the green value
     * @param blue  the blue value
     */
    public BasicRGBColor(byte red, byte green, byte blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    @Override
    public byte getRed() {
        return this.red;
    }

    @Override
    public byte getGreen() {
        return this.green;
    }

    @Override
    public byte getBlue() {
        return this.blue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasicRGBColor that = (BasicRGBColor) o;
        return red == that.red && green == that.green && blue == that.blue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(red, green, blue);
    }

}
