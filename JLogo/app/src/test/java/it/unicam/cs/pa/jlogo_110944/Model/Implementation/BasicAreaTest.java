package it.unicam.cs.pa.jlogo_110944.Model.Implementation;

import it.unicam.cs.pa.jlogo_110944.Model.Area;
import it.unicam.cs.pa.jlogo_110944.Model.Line;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BasicAreaTest {

    private List<BasicArea<CartesianLocation>> areas;
    private List<Line<CartesianLocation>> lines;

    @BeforeEach
    void init() {
        this.areas = new ArrayList<>();
        this.lines = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            BasicLine<CartesianLocation> line = new BasicLine<>(new CartesianLocation(i, i), new CartesianLocation(-i, -i));
            this.lines.add(line);
            this.areas.add(new BasicArea<>(List.of(line), new BasicRGBColor((byte) ((i + 1) % 255), (byte) ((i + 1) % 255), (byte) ((i + 1) % 255))));
        }
    }

    @Test
    void getLinesTest() {
        List<Area<CartesianLocation>> testAreas = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            testAreas.add(new BasicArea<>(List.of(this.lines.get(i)), new BasicRGBColor((byte) ((i + 1) % 255), (byte) ((i + 1) % 255), (byte) ((i + 1) % 255))));
            assertEquals(this.areas.get(i).getLines(), testAreas.get(i).getLines());
        }
    }

    @Test
    void getColorTest() {
        List<Area<CartesianLocation>> testAreas = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            testAreas.add(new BasicArea<>(List.of(this.lines.get(i)), new BasicRGBColor((byte) ((i + 1) % 255), (byte) ((i + 1) % 255), (byte) ((i + 1) % 255))));
            assertEquals(this.areas.get(i).getColor(), testAreas.get(i).getColor());
        }
    }
}
