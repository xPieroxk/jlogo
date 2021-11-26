package it.unicam.cs.pa.jlogo_110944.Model.Implementation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BasicRGBColorTest {

    private List<BasicRGBColor> colors;


    @BeforeEach
    void init() {
        this.colors = new ArrayList<>();
        for (int i = 0; i < 1000; i++)
            this.colors.add(new BasicRGBColor((byte) ((i + 1) % 255), (byte) ((i + 1) % 255), (byte) ((i + 1) % 255)));
    }

    @Test
    void getRedTest() {
        List<BasicRGBColor> testColors1 = new ArrayList<>();
        List<BasicRGBColor> testColors2 = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            testColors1.add(new BasicRGBColor((byte) ((i + 1) % 255), (byte) ((i + 1) % 255), (byte) ((i + 1) % 255)));
            testColors2.add(new BasicRGBColor((byte) ((i + 2) % 255), (byte) ((i + 2) % 255), (byte) ((i + 2) % 255)));
            assertEquals(this.colors.get(i).getRed(), testColors1.get(i).getRed());
            assertNotEquals(this.colors.get(i).getRed(), testColors2.get(i).getRed());
        }
    }

    @Test
    void getGreenTest() {
        List<BasicRGBColor> testColors1 = new ArrayList<>();
        List<BasicRGBColor> testColors2 = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            testColors1.add(new BasicRGBColor((byte) ((i + 1) % 255), (byte) ((i + 1) % 255), (byte) ((i + 1) % 255)));
            testColors2.add(new BasicRGBColor((byte) ((i + 2) + 1 % 255), (byte) ((i + 2) % 255), (byte) ((i + 2) % 255)));
            assertEquals(this.colors.get(i).getGreen(), testColors1.get(i).getGreen());
            assertNotEquals(this.colors.get(i).getGreen(), testColors2.get(i).getGreen());
        }
    }

    @Test
    void getBlueTest() {
        List<BasicRGBColor> testColors1 = new ArrayList<>();
        List<BasicRGBColor> testColors2 = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            testColors1.add(new BasicRGBColor((byte) ((i + 1) % 255), (byte) ((i + 1) % 255), (byte) ((i + 1) % 255)));
            testColors2.add(new BasicRGBColor((byte) ((i + 2) % 255), (byte) ((i + 2) % 255), (byte) ((i + 2) % 255)));
            assertEquals(this.colors.get(i).getBlue(), testColors1.get(i).getBlue());
            assertNotEquals(this.colors.get(i).getBlue(), testColors2.get(i).getBlue());
        }
    }

}
