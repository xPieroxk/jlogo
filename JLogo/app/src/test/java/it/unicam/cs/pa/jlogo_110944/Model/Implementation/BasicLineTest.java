package it.unicam.cs.pa.jlogo_110944.Model.Implementation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BasicLineTest {

    private List<BasicLine<CartesianLocation>> lines;

    @BeforeEach
    void init() {
        this.lines = new ArrayList<>();
        for (int i = 0; i < 1000; i++)
            this.lines.add(new BasicLine<>(new CartesianLocation(i + 1, i + 1), new CartesianLocation(-(i + 1), -(i + 1)), i + 1, new BasicRGBColor((byte) ((i + 1) % 255), (byte) ((i + 1) % 255), (byte) ((i + 1) % 255))));
    }

    @Test
    void constructorTest() {
        assertThrows(NullPointerException.class, () -> new BasicLine<>(null, new CartesianLocation(0, 0), 2, new BasicRGBColor((byte) 0, (byte) 0, (byte) 0)));
        assertThrows(NullPointerException.class, () -> new BasicLine<>(new CartesianLocation(0, 0), null, 2, new BasicRGBColor((byte) 0, (byte) 0, (byte) 0)));
        assertThrows(NullPointerException.class, () -> new BasicLine<>(new CartesianLocation(0, 0), new CartesianLocation(0, 0), 2, null));
    }

    @Test
    void getStartTest() {
        List<BasicLine<CartesianLocation>> testLines1 = new ArrayList<>();
        List<BasicLine<CartesianLocation>> testLines2 = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            testLines1.add(new BasicLine<>(new CartesianLocation(i + 1, i + 1), new CartesianLocation(-(i + 1), -(i + 1)), i, new BasicRGBColor((byte) (i % 255), (byte) (i % 255), (byte) (i % 255))));
            testLines2.add(new BasicLine<>(new CartesianLocation(-(i + 2), -(i + 2)), new CartesianLocation(-(i + 2), -(i + 2)), i, new BasicRGBColor((byte) (i % 255), (byte) (i % 255), (byte) (i % 255))));
            assertEquals(this.lines.get(i).getStart(), testLines1.get(i).getStart());
            assertNotEquals(this.lines.get(i).getStart(), testLines2.get(i).getStart());
        }
    }

    @Test
    void getFinishTest() {
        List<BasicLine<CartesianLocation>> testLines1 = new ArrayList<>();
        List<BasicLine<CartesianLocation>> testLines2 = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            testLines1.add(new BasicLine<>(new CartesianLocation(i, i), new CartesianLocation(-(i + 1), -(i + 1)), i, new BasicRGBColor((byte) (i % 255), (byte) (i % 255), (byte) (i % 255))));
            testLines2.add(new BasicLine<>(new CartesianLocation(-i, -i), new CartesianLocation((i + 2), (i + 2)), i, new BasicRGBColor((byte) (i % 255), (byte) (i % 255), (byte) (i % 255))));
            assertEquals(this.lines.get(i).getFinish(), testLines1.get(i).getFinish());
            assertNotEquals(this.lines.get(i).getFinish(), testLines2.get(i).getFinish());
        }
    }

    @Test
    void getSizeTest() {
        List<BasicLine<CartesianLocation>> testLines1 = new ArrayList<>();
        List<BasicLine<CartesianLocation>> testLines2 = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            testLines1.add(new BasicLine<>(new CartesianLocation(i, i), new CartesianLocation(-i, -i), i + 1, new BasicRGBColor((byte) (i % 255), (byte) (i % 255), (byte) (i % 255))));
            testLines2.add(new BasicLine<>(new CartesianLocation(-i, -i), new CartesianLocation(i, i), i + 2, new BasicRGBColor((byte) (i % 255), (byte) (i % 255), (byte) (i % 255))));
            assertEquals(this.lines.get(i).getSize(), testLines1.get(i).getSize());
            assertNotEquals(this.lines.get(i).getSize(), testLines2.get(i).getSize());
        }
    }

    @Test
    void getColorTest() {
        List<BasicLine<CartesianLocation>> testLines1 = new ArrayList<>();
        List<BasicLine<CartesianLocation>> testLines2 = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            testLines1.add(new BasicLine<>(new CartesianLocation(i, i), new CartesianLocation(-i, -i), i, new BasicRGBColor((byte) ((i + 1) % 255), (byte) ((i + 1) % 255), (byte) ((i + 1) % 255))));
            testLines2.add(new BasicLine<>(new CartesianLocation(-i, -i), new CartesianLocation(i, i), i + 1, new BasicRGBColor((byte) ((i + 2) % 255), (byte) ((i + 2) % 255), (byte) ((i + 2) % 255))));
            assertEquals(this.lines.get(i).getColor(), testLines1.get(i).getColor());
            assertNotEquals(this.lines.get(i).getColor(), testLines2.get(i).getColor());
        }
    }

}
