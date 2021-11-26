package it.unicam.cs.pa.jlogo_110944.Controller.Implementation;

import it.unicam.cs.pa.jlogo_110944.Model.Cursor;
import it.unicam.cs.pa.jlogo_110944.Model.Implementation.BasicCursor;
import it.unicam.cs.pa.jlogo_110944.Model.Implementation.CartesianLocation;
import it.unicam.cs.pa.jlogo_110944.Model.Implementation.CartesianPanel;
import it.unicam.cs.pa.jlogo_110944.Model.Panel;
import it.unicam.cs.pa.jlogo_110944.Model.SyntaxErrorException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class BasicControllerTest {

    private BasicController controller;

    @BeforeEach
    void init() {
        controller = new BasicController();
    }

    @Test
    void newPanelTest() {
        assertThrows(IllegalArgumentException.class, () -> controller.newPanel(-10, 100));
        assertThrows(IllegalArgumentException.class, () -> controller.newPanel(100, -10));
        assertThrows(IllegalArgumentException.class, () -> controller.newPanel(-10, -100));
        controller.newPanel(500, 500);
        Panel<CartesianLocation, Cursor<CartesianLocation>> panel1 = new CartesianPanel<>(500, 500, new BasicCursor<>(new CartesianLocation(250, 250)));
        assertEquals(controller.getPanel(), panel1);
        controller.newPanel(1000, 1000);
        Panel<CartesianLocation, Cursor<CartesianLocation>> panel2 = new CartesianPanel<>(1000, 1000, new BasicCursor<>(new CartesianLocation(250, 250)));
        assertEquals(controller.getPanel(), panel2);
    }

    @Test
    void loadCommandsTest() {
        assertThrows(NullPointerException.class, () -> controller.loadCommands(null));
        assertThrows(IllegalStateException.class, () -> controller.loadCommands("FORWARD 90"));
        controller.newPanel(100, 100);
        controller.loadCommands("FORWARD 90");
        assertThrows(IllegalStateException.class, () -> controller.loadCommands("FORWARD 90"));
    }

    @Test
    void loadFromFileTest() {
        assertThrows(NullPointerException.class, () -> controller.loadFromFile(null));
        assertThrows(IllegalStateException.class, () -> controller.loadFromFile(new File(Objects.requireNonNull(getClass().getResource("/testLogo.txt")).toURI()).getAbsolutePath()));
        controller.newPanel(100, 100);
        controller.loadCommands("FORWARD 90");
        assertThrows(IllegalStateException.class, () -> controller.loadFromFile(new File(Objects.requireNonNull(getClass().getResource("/testLogo.txt")).toURI()).getAbsolutePath()));
    }

    @Test
    void executeCommandTest() throws SyntaxErrorException {
        assertThrows(IllegalStateException.class, () -> controller.executeCommand());
        controller.newPanel(500, 500);
        assertThrows(IllegalStateException.class, () -> controller.executeCommand());
        controller.loadCommands("TEST 90");
        assertThrows(SyntaxErrorException.class, () -> controller.executeCommand());
        controller.newPanel(500, 500);
        controller.loadCommands("FORWARD 90 BACKWARD 90 LEFT 90 RIGHT 90 " +
                "SETSCREENCOLOR 255 255 255 SETFILLCOLOR 255 255 255 " +
                "SETPENSIZE 10 SETPENCOLOR 255 255 255 HOME CLEARSCREEN " +
                "REPEAT 4 [ FORWARD 90 ] PENDOWN PENUP");
        assertEquals(controller.executeCommand(), List.of("FORWARD", "90"));
        assertEquals(controller.executeCommand(), List.of("BACKWARD", "90"));
        assertEquals(controller.executeCommand(), List.of("LEFT", "90"));
        assertEquals(controller.executeCommand(), List.of("RIGHT", "90"));
        assertEquals(controller.executeCommand(), List.of("SETSCREENCOLOR", "255", "255", "255"));
        assertEquals(controller.executeCommand(), List.of("SETFILLCOLOR", "255", "255", "255"));
        assertEquals(controller.executeCommand(), List.of("SETPENSIZE", "10"));
        assertEquals(controller.executeCommand(), List.of("SETPENCOLOR", "255", "255", "255"));
        assertEquals(controller.executeCommand(), List.of("HOME"));
        assertEquals(controller.executeCommand(), List.of("CLEARSCREEN"));
        assertEquals(controller.executeCommand(), List.of("REPEAT", "4", "[", "FORWARD", "90", "]"));
        assertEquals(controller.executeCommand(), List.of("PENDOWN"));
        assertEquals(controller.executeCommand(), List.of("PENUP"));
    }

    @Test
    void hasCommandTest() throws SyntaxErrorException {
        assertFalse(controller.hasCommand());
        controller.newPanel(100, 100);
        controller.loadCommands("FORWARD 90");
        assertTrue(controller.hasCommand());
        controller.executeCommand();
        assertFalse(controller.hasCommand());
    }

    @Test
    void resultTest() throws SyntaxErrorException {
        assertThrows(IllegalStateException.class, () -> controller.result());
        List<String> resultString1 = new ArrayList<>();
        resultString1.add("Screen color: 255 255 255");
        resultString1.add("Fill color: 255 255 255");
        resultString1.add("Pen color: 0 0 0");
        resultString1.add("Pen size: 1");
        resultString1.add("Areas found: 0");
        controller.newPanel(500, 500);
        assertEquals(controller.result(), resultString1);
        List<String> resultString2 = new ArrayList<>();
        resultString2.add("Screen color: 255 255 255");
        resultString2.add("Fill color: 255 255 255");
        resultString2.add("Pen color: 0 0 0");
        resultString2.add("Pen size: 1");
        resultString2.add("Areas found: 1");
        resultString2.add("Area: 0");
        resultString2.add("\tColor: 255 255 255");
        resultString2.add("\tvertex {x=250.0, y=250.0}");
        resultString2.add("\tvertex {x=270.0, y=270.0}");
        resultString2.add("\tvertex {x=250.0, y=270.0}");
        controller.loadCommands("REPEAT 4 [ FORWARD 20 LEFT 90 ]");
        controller.executeCommand();
        assertNotEquals(controller.result(), resultString2);
    }

    @Test
    void exportCommandsTest() throws URISyntaxException, SyntaxErrorException, IOException {
        File exportFile = new File(Objects.requireNonNull(getClass().getResource("/exportTest.txt")).toURI());
        Files.write(exportFile.toPath(), "".getBytes());//delete previous content
        String commandString = "FORWARD 90 LEFT 90";
        this.controller.newPanel(500, 500);
        this.controller.loadCommands(commandString);
        this.controller.executeCommand();
        this.controller.executeCommand();
        assertThrows(NullPointerException.class, () -> this.controller.exportCommands(null));
        this.controller.exportCommands(exportFile);
        List<String> commandList = new ArrayList<>();
        Files.lines(Path.of(exportFile.getAbsolutePath())).forEach(l -> Collections.addAll(commandList, l.split("\\s+")));
        assertEquals(commandList, List.of("FORWARD", "90", "LEFT", "90"));
    }

    @Test
    void getPanelTest() {
        assertNull(controller.getPanel());
        controller.newPanel(500, 500);
        Panel<CartesianLocation, Cursor<CartesianLocation>> panel1 = new CartesianPanel<>(500, 500, new BasicCursor<>(new CartesianLocation(250, 250)));
        assertEquals(controller.getPanel(), panel1);
        controller.newPanel(1000, 1000);
        Panel<CartesianLocation, Cursor<CartesianLocation>> panel2 = new CartesianPanel<>(1000, 1000, new BasicCursor<>(new CartesianLocation(250, 250)));
        assertEquals(controller.getPanel(), panel2);
        controller.newPanel(2500, 2500);
        Panel<CartesianLocation, Cursor<CartesianLocation>> panel3 = new CartesianPanel<>(2500, 2500, new BasicCursor<>(new CartesianLocation(250, 250)));
        assertEquals(controller.getPanel(), panel3);
        controller.newPanel(200, 200);
        assertNotEquals(controller.getPanel(), panel3);
    }

}
