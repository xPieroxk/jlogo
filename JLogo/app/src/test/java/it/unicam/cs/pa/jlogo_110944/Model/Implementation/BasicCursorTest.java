package it.unicam.cs.pa.jlogo_110944.Model.Implementation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BasicCursorTest {

    private List<BasicCursor<CartesianLocation>> cursors;
    private boolean plot = false;

    @BeforeEach
    void init() {
        cursors = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            this.plot = !plot;
            BasicCursor<CartesianLocation> cursor = new BasicCursor<>(new CartesianLocation(i + 1, -(i + 1)));
            cursor.setDirection(i % 360);
            cursor.setLineColor(new BasicRGBColor((byte) ((i + 1) % 255), (byte) ((i + 1) % 255), (byte) ((i + 1) % 255)));
            cursor.setPlot(plot);
            cursor.setLineSize(i + 1);
            this.cursors.add(cursor);
        }
    }

    @Test
    void constructorTest() {
        assertThrows(NullPointerException.class, () -> new BasicCursor<>(null));
    }

    @Test
    void getPositionTest() {
        List<BasicCursor<CartesianLocation>> testCursors1 = new ArrayList<>();
        List<BasicCursor<CartesianLocation>> testCursors2 = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            BasicCursor<CartesianLocation> c1 = new BasicCursor<>(new CartesianLocation(i + 1, -(i + 1)));
            BasicCursor<CartesianLocation> c2 = new BasicCursor<>(new CartesianLocation(i + 2, -(i + 2)));
            testCursors1.add(c1);
            testCursors2.add(c2);
            assertEquals(testCursors1.get(i).getPosition(), this.cursors.get(i).getPosition());
            assertNotEquals(testCursors2.get(i).getPosition(), this.cursors.get(i).getPosition());
        }
    }

    @Test
    void getDirectionTest() {
        List<BasicCursor<CartesianLocation>> testCursors1 = new ArrayList<>();
        List<BasicCursor<CartesianLocation>> testCursors2 = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            BasicCursor<CartesianLocation> c1 = new BasicCursor<>(new CartesianLocation(0, 0));
            BasicCursor<CartesianLocation> c2 = new BasicCursor<>(new CartesianLocation(0, 0));
            testCursors1.add(c1);
            testCursors2.add(c2);
            c1.setDirection(i % 360);
            c2.setDirection((i + 1) % 360);
            assertEquals(testCursors1.get(i).getDirection(), this.cursors.get(i).getDirection());
            assertNotEquals(testCursors2.get(i).getDirection(), this.cursors.get(i).getDirection());
        }
    }

    @Test
    void getLineColorTest() {
        List<BasicCursor<CartesianLocation>> testCursors1 = new ArrayList<>();
        List<BasicCursor<CartesianLocation>> testCursors2 = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            BasicCursor<CartesianLocation> c1 = new BasicCursor<>(new CartesianLocation(0, 0));
            BasicCursor<CartesianLocation> c2 = new BasicCursor<>(new CartesianLocation(0, 0));
            testCursors1.add(c1);
            testCursors2.add(c2);
            c1.setLineColor(new BasicRGBColor((byte) ((i + 1) % 255), (byte) ((i + 1) % 255), (byte) ((i + 1) % 255)));
            c2.setLineColor(new BasicRGBColor((byte) ((i + 2) % 255), (byte) ((i + 2) % 255), (byte) ((i + 2) % 255)));
            assertEquals(testCursors1.get(i).getLineColor(), this.cursors.get(i).getLineColor());
            assertNotEquals(testCursors2.get(i).getLineColor(), this.cursors.get(i).getLineColor());
        }
    }

    @Test
    void getPlotTest() {
        boolean plotTest = false;
        List<BasicCursor<CartesianLocation>> testCursors1 = new ArrayList<>();
        List<BasicCursor<CartesianLocation>> testCursors2 = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            plotTest = !plotTest;
            BasicCursor<CartesianLocation> c1 = new BasicCursor<>(new CartesianLocation(0, 0));
            BasicCursor<CartesianLocation> c2 = new BasicCursor<>(new CartesianLocation(0, 0));
            testCursors1.add(c1);
            testCursors2.add(c2);
            c1.setPlot(plotTest);
            c2.setPlot(!plotTest);
            assertEquals(testCursors1.get(i).getPlot(), this.cursors.get(i).getPlot());
            assertNotEquals(testCursors2.get(i).getPlot(), this.cursors.get(i).getPlot());
        }
    }

    @Test
    void getLineSizeTest() {
        List<BasicCursor<CartesianLocation>> testCursors1 = new ArrayList<>();
        List<BasicCursor<CartesianLocation>> testCursors2 = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            BasicCursor<CartesianLocation> c1 = new BasicCursor<>(new CartesianLocation(0, 0));
            BasicCursor<CartesianLocation> c2 = new BasicCursor<>(new CartesianLocation(0, 0));
            testCursors1.add(c1);
            testCursors2.add(c2);
            c1.setLineSize(i + 1);
            c2.setLineSize(i + 2);
            assertEquals(testCursors1.get(i).getLineSize(), this.cursors.get(i).getLineSize());
            assertNotEquals(testCursors2.get(i).getLineSize(), this.cursors.get(i).getLineSize());
        }
    }

    @Test
    void setPositionTest() {
        List<BasicCursor<CartesianLocation>> testCursors1 = new ArrayList<>();
        List<BasicCursor<CartesianLocation>> testCursors2 = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            BasicCursor<CartesianLocation> c1 = new BasicCursor<>(new CartesianLocation(i + 1, -(i + 1)));
            BasicCursor<CartesianLocation> c2 = new BasicCursor<>(new CartesianLocation(i + 2, -(i + 2)));
            testCursors1.add(c1);
            testCursors2.add(c2);
            assertEquals(testCursors1.get(i).getPosition(), this.cursors.get(i).getPosition());
            assertNotEquals(testCursors2.get(i).getPosition(), this.cursors.get(i).getPosition());
        }
    }

    @Test
    void setDirectionTest() {
        BasicCursor<CartesianLocation> c = new BasicCursor<>(new CartesianLocation(0, 0));
        assertThrows(IllegalArgumentException.class, () -> c.setDirection(-1));
        assertThrows(IllegalArgumentException.class, () -> c.setDirection(361));
        List<BasicCursor<CartesianLocation>> testCursors1 = new ArrayList<>();
        List<BasicCursor<CartesianLocation>> testCursors2 = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            BasicCursor<CartesianLocation> c1 = new BasicCursor<>(new CartesianLocation(0, 0));
            BasicCursor<CartesianLocation> c2 = new BasicCursor<>(new CartesianLocation(0, 0));
            testCursors1.add(c1);
            testCursors2.add(c2);
            c1.setDirection(i % 360);
            c2.setDirection((i + 1) % 360);
            assertEquals(testCursors1.get(i).getDirection(), this.cursors.get(i).getDirection());
            assertNotEquals(testCursors2.get(i).getDirection(), this.cursors.get(i).getDirection());
        }
    }

    @Test
    void setLineColorTest() {
        BasicCursor<CartesianLocation> c = new BasicCursor<>(new CartesianLocation(0, 0));
        assertThrows(NullPointerException.class, () -> c.setLineColor(null));
        List<BasicCursor<CartesianLocation>> testCursors1 = new ArrayList<>();
        List<BasicCursor<CartesianLocation>> testCursors2 = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            BasicCursor<CartesianLocation> c1 = new BasicCursor<>(new CartesianLocation(0, 0));
            BasicCursor<CartesianLocation> c2 = new BasicCursor<>(new CartesianLocation(0, 0));
            testCursors1.add(c1);
            testCursors2.add(c2);
            c1.setLineColor(new BasicRGBColor((byte) ((i + 1) % 255), (byte) ((i + 1) % 255), (byte) ((i + 1) % 255)));
            c2.setLineColor(new BasicRGBColor((byte) ((i + 2) % 255), (byte) ((i + 2) % 255), (byte) ((i + 2) % 255)));
            assertEquals(testCursors1.get(i).getLineColor(), this.cursors.get(i).getLineColor());
            assertNotEquals(testCursors2.get(i).getLineColor(), this.cursors.get(i).getLineColor());
        }
    }

    @Test
    void setPlotTest() {
        boolean plotTest = false;
        List<BasicCursor<CartesianLocation>> testCursors1 = new ArrayList<>();
        List<BasicCursor<CartesianLocation>> testCursors2 = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            plotTest = !plotTest;
            BasicCursor<CartesianLocation> c1 = new BasicCursor<>(new CartesianLocation(0, 0));
            BasicCursor<CartesianLocation> c2 = new BasicCursor<>(new CartesianLocation(0, 0));
            testCursors1.add(c1);
            testCursors2.add(c2);
            c1.setPlot(plotTest);
            c2.setPlot(!plotTest);
            assertEquals(testCursors1.get(i).getPlot(), this.cursors.get(i).getPlot());
            assertNotEquals(testCursors2.get(i).getPlot(), this.cursors.get(i).getPlot());
        }
    }

    @Test
    void setLineTest() {
        List<BasicCursor<CartesianLocation>> testCursors1 = new ArrayList<>();
        List<BasicCursor<CartesianLocation>> testCursors2 = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            BasicCursor<CartesianLocation> c1 = new BasicCursor<>(new CartesianLocation(0, 0));
            BasicCursor<CartesianLocation> c2 = new BasicCursor<>(new CartesianLocation(0, 0));
            testCursors1.add(c1);
            testCursors2.add(c2);
            c1.setLineSize(i + 1);
            c2.setLineSize(i + 2);
            assertEquals(testCursors1.get(i).getLineSize(), this.cursors.get(i).getLineSize());
            assertNotEquals(testCursors2.get(i).getLineSize(), this.cursors.get(i).getLineSize());
        }
    }

}
