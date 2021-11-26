package it.unicam.cs.pa.jlogo_110944.Model.Implementation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CartesianLocationTest {

    private List<CartesianLocation> locations;

    @BeforeEach
    void init() {
        this.locations = new ArrayList<>();
        for (int i = 0; i < 1000; i++)
            this.locations.add(new CartesianLocation(i + 1, -(i + 1)));
    }

    @Test
    void getXTest() {
        List<CartesianLocation> testLocations1 = new ArrayList<>();
        List<CartesianLocation> testLocations2 = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            testLocations1.add(new CartesianLocation(i + 1, -(i + 1)));
            testLocations2.add(new CartesianLocation(-(i + 2), (i + 2)));
            assertEquals(this.locations.get(i).getX(), testLocations1.get(i).getX());
            assertNotEquals(this.locations.get(i).getX(), testLocations2.get(i).getX());
        }
    }

    @Test
    void getYTest() {
        List<CartesianLocation> testLocations1 = new ArrayList<>();
        List<CartesianLocation> testLocations2 = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            testLocations1.add(new CartesianLocation(i + 1, -(i + 1)));
            testLocations2.add(new CartesianLocation(-(i + 1), i + 1));
            assertEquals(this.locations.get(i).getY(), testLocations1.get(i).getY());
            assertNotEquals(this.locations.get(i).getY(), testLocations2.get(i).getY());
        }
    }
}
