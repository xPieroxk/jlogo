package it.unicam.cs.pa.jlogo_110944.Model.Implementation;

import it.unicam.cs.pa.jlogo_110944.Model.Line;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Set;

public class GraphAreaFinderTest {

    private CartesianPanel<BasicCursor<CartesianLocation>> panel;

    @BeforeEach
    void init() {
        this.panel = new CartesianPanel<>(500, 500, new BasicCursor<>(new CartesianLocation(250, 250)));
    }

    @Test
    void triangle() {
        BasicLine<CartesianLocation> line1 = new BasicLine<>(new CartesianLocation(250, 250), new CartesianLocation(340, 250));
        BasicLine<CartesianLocation> line2 = new BasicLine<>(new CartesianLocation(340, 250), new CartesianLocation(295, 327.94229));
        BasicLine<CartesianLocation> line3 = new BasicLine<>(new CartesianLocation(295, 327.94229), new CartesianLocation(250, 250));
        BasicArea<CartesianLocation> areaTest = new BasicArea<>(List.of(line1, line2, line3), new BasicRGBColor((byte) 255, (byte) 255, (byte) 255));
        this.panel.addLine(line1);
        this.panel.addLine(line2);
        this.panel.addLine(line3);
        Set<Line<CartesianLocation>> lines = Set.of(line1, line2, line3);
        assertEquals(this.panel.getAreas().get(0).getColor(), new BasicRGBColor((byte) 255, (byte) 255, (byte) 255));
        assertTrue(lines.containsAll(areaTest.getLines()));
    }

    @Test
    void square() {
        BasicLine<CartesianLocation> line1 = new BasicLine<>(new CartesianLocation(250, 250), new CartesianLocation(340, 250));
        BasicLine<CartesianLocation> line2 = new BasicLine<>(new CartesianLocation(340, 250), new CartesianLocation(340, 340));
        BasicLine<CartesianLocation> line3 = new BasicLine<>(new CartesianLocation(340, 340), new CartesianLocation(250, 340));
        BasicLine<CartesianLocation> line4 = new BasicLine<>(new CartesianLocation(250, 340), new CartesianLocation(250, 250));
        BasicArea<CartesianLocation> areaTest = new BasicArea<>(List.of(line1, line2, line3, line4), new BasicRGBColor((byte) 255, (byte) 255, (byte) 255));
        this.panel.addLine(line1);
        this.panel.addLine(line2);
        this.panel.addLine(line3);
        this.panel.addLine(line4);
        Set<Line<CartesianLocation>> lines = Set.of(line1, line2, line3, line4);
        assertEquals(this.panel.getAreas().get(0).getColor(), new BasicRGBColor((byte) 255, (byte) 255, (byte) 255));
        assertTrue(lines.containsAll(areaTest.getLines()));
    }

    @Test
    void pentagon() {
        BasicLine<CartesianLocation> line1 = new BasicLine<>(new CartesianLocation(250, 250), new CartesianLocation(340, 250));
        BasicLine<CartesianLocation> line2 = new BasicLine<>(new CartesianLocation(340, 250), new CartesianLocation(367.81153, 335.59509));
        BasicLine<CartesianLocation> line3 = new BasicLine<>(new CartesianLocation(367.81153, 335.59509), new CartesianLocation(295, 388.49576));
        BasicLine<CartesianLocation> line4 = new BasicLine<>(new CartesianLocation(295, 388.49576), new CartesianLocation(222.18847, 335.59509));
        BasicLine<CartesianLocation> line5 = new BasicLine<>(new CartesianLocation(222.18847, 335.59509), new CartesianLocation(250, 250));
        BasicArea<CartesianLocation> areaTest = new BasicArea<>(List.of(line1, line2, line3, line4, line5), new BasicRGBColor((byte) 255, (byte) 255, (byte) 255));
        this.panel.addLine(line1);
        this.panel.addLine(line2);
        this.panel.addLine(line3);
        this.panel.addLine(line4);
        this.panel.addLine(line5);
        Set<Line<CartesianLocation>> lines = Set.of(line1, line2, line3, line4, line5);
        assertEquals(this.panel.getAreas().get(0).getColor(), new BasicRGBColor((byte) 255, (byte) 255, (byte) 255));
        assertTrue(lines.containsAll(areaTest.getLines()));
    }

    @Test
    void hexagon() {
        BasicLine<CartesianLocation> line1 = new BasicLine<>(new CartesianLocation(250, 250), new CartesianLocation(340, 250));
        BasicLine<CartesianLocation> line2 = new BasicLine<>(new CartesianLocation(340, 250), new CartesianLocation(385, 327.94229));
        BasicLine<CartesianLocation> line3 = new BasicLine<>(new CartesianLocation(385, 327.94229), new CartesianLocation(340, 405.88458));
        BasicLine<CartesianLocation> line4 = new BasicLine<>(new CartesianLocation(340, 405.88458), new CartesianLocation(250, 405.88458));
        BasicLine<CartesianLocation> line5 = new BasicLine<>(new CartesianLocation(250, 405.88458), new CartesianLocation(205, 327.94229));
        BasicLine<CartesianLocation> line6 = new BasicLine<>(new CartesianLocation(205, 327.94229), new CartesianLocation(250, 250));
        BasicArea<CartesianLocation> areaTest = new BasicArea<>(List.of(line1, line2, line3, line4, line5, line6), new BasicRGBColor((byte) 255, (byte) 255, (byte) 255));
        this.panel.addLine(line1);
        this.panel.addLine(line2);
        this.panel.addLine(line3);
        this.panel.addLine(line4);
        this.panel.addLine(line5);
        this.panel.addLine(line6);
        Set<Line<CartesianLocation>> lines = Set.of(line1, line2, line3, line4, line5, line6);
        assertEquals(this.panel.getAreas().get(0).getColor(), new BasicRGBColor((byte) 255, (byte) 255, (byte) 255));
        assertTrue(lines.containsAll(areaTest.getLines()));
    }

}
