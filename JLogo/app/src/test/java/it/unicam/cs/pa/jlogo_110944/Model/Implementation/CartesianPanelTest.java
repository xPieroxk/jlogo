package it.unicam.cs.pa.jlogo_110944.Model.Implementation;

import it.unicam.cs.pa.jlogo_110944.Model.Line;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class CartesianPanelTest {

    private BasicCursor<CartesianLocation> cursor1;
    private BasicCursor<CartesianLocation> cursor2;
    private BasicCursor<CartesianLocation> cursor3;
    private CartesianPanel<BasicCursor<CartesianLocation>> panel1;
    private CartesianPanel<BasicCursor<CartesianLocation>> panel2;
    private CartesianPanel<BasicCursor<CartesianLocation>> panel3;
    private List<BasicLine<CartesianLocation>> lines;
    private List<BasicArea<CartesianLocation>> areas;

    @BeforeEach
    void init() {
        cursor1 = new BasicCursor<>(new CartesianLocation(250, 250));
        cursor2 = new BasicCursor<>(new CartesianLocation(0, 0));
        cursor3 = new BasicCursor<>(new CartesianLocation(100, 100));
        panel1 = new CartesianPanel<>(500, 500, cursor1);
        panel2 = new CartesianPanel<>(500, 500, cursor2);
        panel3 = new CartesianPanel<>(200, 200, cursor3);
        this.lines = new ArrayList<>();
        for (int i = 0; i < 100; i++)
            this.lines.add(new BasicLine<>(new CartesianLocation(i, i), new CartesianLocation(-i, -i)));
        this.areas = new ArrayList<>();
        for (int i = 0; i < 100; i++)
            this.areas.add(new BasicArea<>(List.of(this.lines.get(i)), new BasicRGBColor((byte) i, (byte) i, (byte) i)));
    }

    @Test
    void constructorTest() {
        assertThrows(NullPointerException.class, () -> new CartesianPanel<>(100, 100, null));
        assertThrows(IllegalArgumentException.class, () -> new CartesianPanel<>(-100, 100, cursor1));
        assertThrows(IllegalArgumentException.class, () -> new CartesianPanel<>(100, -100, cursor1));
    }

    @Test
    void addLineTest() {
        Set<Line<CartesianLocation>> linesTest = new HashSet<>();
        for (int i = 0; i < 50; i++) {
            linesTest.add(this.lines.get(i));
            this.panel1.addLine(this.lines.get(i));
            assertTrue(linesTest.containsAll(this.panel1.getLines()));
        }
    }

    @Test
    void addArea() {
        for (BasicArea<CartesianLocation> area : this.areas) {
            this.panel1.addArea(area);
            assertNotEquals(this.panel1, this.panel2);
            this.panel2.addArea(area);
            assertEquals(this.panel1, this.panel2);
        }
    }

    @Test
    void containsLineTest() {
        assertThrows(NullPointerException.class, () -> this.panel1.getLine(null, new CartesianLocation(0, 0)));
        assertThrows(NullPointerException.class, () -> this.panel1.getLine(new CartesianLocation(0, 0), null));
        for (int i = 1; i < 100; i++) {
            this.panel1.addLine(this.lines.get(i));
            assertNotNull(this.panel1.getLine(this.lines.get(i).getStart(), this.lines.get(i).getFinish()));
        }
    }

    @Test
    void resetTest() {
        panel1.addLine(this.lines.get(1));
        panel1.reset();
        assertEquals(panel1, panel2);
        panel2.addLine(this.lines.get(1));
        assertNotEquals(panel1, panel2);
        panel2.reset();
        assertEquals(panel1, panel2);
    }

    @Test
    void getHomeTest() {
        assertEquals(panel1.getHome(), panel2.getHome());
        assertNotEquals(panel1.getHome(), panel3.getHome());
    }


    @Test
    void moveCursorTest() {
        panel1.moveCursor(10);
        panel2.moveCursor(10);
        assertEquals(panel1, panel2);
        assertNotEquals(panel1, panel3);
    }

    @Test
    void getLinesTest() {
        Set<Line<CartesianLocation>> linesTest = new HashSet<>();
        for (int i = 0; i < 50; i++) {
            linesTest.add(this.lines.get(i));
            this.panel1.addLine(this.lines.get(i));
            assertTrue(linesTest.containsAll(this.panel1.getLines()));
        }
    }

    @Test
    void getAreasTest() {
        List<BasicArea<CartesianLocation>> areasTest = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            areasTest.add(this.areas.get(i));
            panel1.addArea(this.areas.get(i));
            assertEquals(areasTest, panel1.getAreas());
        }
    }

    @Test
    void setScreenColorTest() {
        assertThrows(NullPointerException.class, () -> panel1.setScreenColor(null));
        panel1.setScreenColor(new BasicRGBColor((byte) 10, (byte) 10, (byte) 10));
        assertEquals(panel1.getScreenColor(), new BasicRGBColor((byte) 10, (byte) 10, (byte) 10));
        panel1.setScreenColor(new BasicRGBColor((byte) 100, (byte) 100, (byte) 100));
        assertNotEquals(panel1.getScreenColor(), new BasicRGBColor((byte) 10, (byte) 10, (byte) 10));
        assertEquals(panel1.getScreenColor(), new BasicRGBColor((byte) 100, (byte) 100, (byte) 100));
    }

    @Test
    void setFillColorTest() {
        assertThrows(NullPointerException.class, () -> panel1.setFillColor(null));
        panel1.setFillColor(new BasicRGBColor((byte) 10, (byte) 10, (byte) 10));
        assertEquals(panel1.getFillColor(), new BasicRGBColor((byte) 10, (byte) 10, (byte) 10));
        panel1.setFillColor(new BasicRGBColor((byte) 100, (byte) 100, (byte) 100));
        assertNotEquals(panel1.getFillColor(), new BasicRGBColor((byte) 10, (byte) 10, (byte) 10));
        assertEquals(panel1.getFillColor(), new BasicRGBColor((byte) 100, (byte) 100, (byte) 100));
    }

    @Test
    void getScreenColorTest() {
        panel1.setScreenColor(new BasicRGBColor((byte) 10, (byte) 10, (byte) 10));
        assertEquals(panel1.getScreenColor(), new BasicRGBColor((byte) 10, (byte) 10, (byte) 10));
        panel1.setScreenColor(new BasicRGBColor((byte) 100, (byte) 100, (byte) 100));
        assertNotEquals(panel1.getScreenColor(), new BasicRGBColor((byte) 10, (byte) 10, (byte) 10));
        assertEquals(panel1.getScreenColor(), new BasicRGBColor((byte) 100, (byte) 100, (byte) 100));
    }

    @Test
    void getFillColorTest() {
        panel1.setFillColor(new BasicRGBColor((byte) 10, (byte) 10, (byte) 10));
        assertEquals(panel1.getFillColor(), new BasicRGBColor((byte) 10, (byte) 10, (byte) 10));
        panel1.setFillColor(new BasicRGBColor((byte) 100, (byte) 100, (byte) 100));
        assertNotEquals(panel1.getFillColor(), new BasicRGBColor((byte) 10, (byte) 10, (byte) 10));
        assertEquals(panel1.getFillColor(), new BasicRGBColor((byte) 100, (byte) 100, (byte) 100));
    }

    @Test
    void rotateCursorTest() {
        panel1.rotateCursor(20);
        assertEquals(panel1.getCursor().getDirection(), 20);
        panel1.rotateCursor(340);
        assertEquals(panel1.getCursor().getDirection(), 0);
        assertEquals(panel1.getCursor().getDirection(), panel2.getCursor().getDirection());
        panel1.rotateCursor(-180);
        assertEquals(panel1.getCursor().getDirection(), 180);
    }

    @Test
    void resultTest() {
        List<String> resultString1 = new ArrayList<>();
        resultString1.add("Screen color: 255 255 255");
        resultString1.add("Fill color: 255 255 255");
        resultString1.add("Pen color: 0 0 0");
        resultString1.add("Pen size: 1");
        resultString1.add("Areas found: 0");
        this.panel2.addArea(this.areas.get(0));
        List<String> resultString2 = new ArrayList<>();
        resultString2.add("Screen color: 255 255 255");
        resultString2.add("Fill color: 255 255 255");
        resultString2.add("Pen color: 0 0 0");
        resultString2.add("Pen size: 1");
        resultString2.add("Areas found: 1");
        resultString2.add("Area: 0");
        resultString2.add("\tColor: 0 0 0");
        resultString2.add("\tvertex {x=0.0, y=0.0}");
        assertEquals(panel1.result(), resultString1);
        assertEquals(panel2.result(), resultString2);
    }

    @Test
    void getCursorTest() {
        assertEquals(panel1.getCursor(), cursor1);
        assertEquals(panel2.getCursor(), cursor2);
        assertEquals(panel3.getCursor(), cursor3);
        assertNotEquals(panel1.getCursor(), cursor3);
        panel2.rotateCursor(20);
        assertNotEquals(panel2.getCursor(), cursor1);
        assertNotEquals(panel3.getCursor(), cursor2);
    }

}
