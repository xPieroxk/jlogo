package it.unicam.cs.pa.jlogo_110944.Controller.Implementation;

import it.unicam.cs.pa.jlogo_110944.Controller.Controller;
import it.unicam.cs.pa.jlogo_110944.Model.*;
import it.unicam.cs.pa.jlogo_110944.Model.Implementation.BasicCursor;
import it.unicam.cs.pa.jlogo_110944.Model.Implementation.CartesianLocation;
import it.unicam.cs.pa.jlogo_110944.Model.Implementation.CartesianPanel;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Collections;

/**
 * Basic implementation of the Controller interface.
 */
public class BasicController implements Controller<CartesianLocation, Cursor<CartesianLocation>> {
    private Panel<CartesianLocation, Cursor<CartesianLocation>> panel;
    private final List<PanelUpdateListener<CartesianLocation>> listeners;
    private List<String> loadedCommands;
    private List<String> commandsToExport;

    /**
     * Constructor method.
     */
    public BasicController() {
        this.loadedCommands = new ArrayList<>();
        this.listeners = new ArrayList<>();
        this.commandsToExport = new ArrayList<>();
    }

    @Override
    public void newPanel(int width, int height) {
        Panel<CartesianLocation, Cursor<CartesianLocation>> newPanel = new CartesianPanel<>(width, height, new BasicCursor<>(new CartesianLocation((double) width / 2, (double) height / 2)));
        if (this.panel == null)
            listeners.forEach(newPanel::addPanelUpdateListener);
        else
            switchListeners(this.panel, newPanel);
        newPanel(newPanel, width, height);
    }

    @Override
    public void loadCommands(String commands) {
        Objects.requireNonNull(commands, "Commands must be not null");
        canLoad();
        Collections.addAll(this.loadedCommands, commands.split("\\s+"));
    }

    @Override
    public void loadFromFile(String path) throws IOException {
        Objects.requireNonNull(path, "The path cannot be null");
        canLoad();
        Files.lines(Path.of(path)).forEach(l -> Collections.addAll(this.loadedCommands, l.split("\\s+")));
	}

    @Override
    public List<String> executeCommand() throws SyntaxErrorException {
        if (this.panel == null)
            throw new IllegalStateException("No panel has been created yet");
        if (!hasCommand())
            throw new IllegalStateException("There are no commands to execute");
        return executeCommand(this.loadedCommands.get(0));
    }

    @Override
    public boolean hasCommand() {
        return this.loadedCommands.size() != 0;
    }

    @Override
    public List<String> result() {
        if (this.panel == null)
            throw new IllegalStateException("No panel has been created yet");
        return this.panel.result();
    }

    @Override
    public void exportCommands(File file) throws IOException {
        Objects.requireNonNull(file, "The file cannot be null");
        for (String command : this.commandsToExport)
            Files.write(file.toPath(), command.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
    }

    @Override
    public Panel<CartesianLocation, Cursor<CartesianLocation>> getPanel() {
        return this.panel;
    }

    @Override
    public void addListener(PanelUpdateListener<CartesianLocation> listener) {
        listeners.add(listener);
        if (this.panel != null)
            this.panel.addPanelUpdateListener(listener);
    }

    @Override
    public void removeListener(PanelUpdateListener<CartesianLocation> listener) {
        listeners.remove(listener);
        if (this.panel != null)
            this.panel.removePanelUpdateListener(listener);
    }

    /**
     * Removes all listeners from the old panel and adds them to the new panel.
     */
    private void switchListeners(Panel<CartesianLocation, Cursor<CartesianLocation>> panel1, Panel<CartesianLocation, Cursor<CartesianLocation>> panel2) {
        listeners.forEach(l -> {
            panel1.removePanelUpdateListener(l);
            panel2.addPanelUpdateListener(l);
        });
    }

    /**
     * Sets the new panel.
     */
    private void newPanel(Panel<CartesianLocation, Cursor<CartesianLocation>> newPanel, int width, int height) {
        this.panel = newPanel;
        synchronized (this) { //notifies all listeners that a new panel has been created
            listeners.forEach(l -> l.firePanelChanged(width, height));
        }
        this.loadedCommands = new ArrayList<>();
        this.commandsToExport = new ArrayList<>();
    }

    /**
     * Given a name, executes the command that corresponds to it.
     */
    private List<String> executeCommand(String name) throws SyntaxErrorException {
        List<String> result;
        switch (name) {
            case "FORWARD":
                result = LogoCommand.forward(this.panel, this.loadedCommands);
                break;
            case "BACKWARD":
                result = LogoCommand.backward(this.panel, this.loadedCommands);
                break;
            case "LEFT":
                result = LogoCommand.left(this.panel, this.loadedCommands);
                break;
            case "RIGHT":
                result = LogoCommand.right(this.panel, this.loadedCommands);
                break;
            case "CLEARSCREEN":
                result = LogoCommand.clearScreen(this.panel, this.loadedCommands);
                break;
            case "HOME":
                result = LogoCommand.home(this.panel, this.loadedCommands);
                break;
            case "PENUP":
                result = LogoCommand.penUp(this.panel, this.loadedCommands);
                break;
            case "PENDOWN":
                result = LogoCommand.penDown(this.panel, this.loadedCommands);
                break;
            case "SETPENCOLOR":
                result = LogoCommand.setPenColor(this.panel, this.loadedCommands);
                break;
            case "SETFILLCOLOR":
                result = LogoCommand.setFillColor(this.panel, this.loadedCommands);
                break;
            case "SETSCREENCOLOR":
                result = LogoCommand.setScreenColor(this.panel, this.loadedCommands);
                break;
            case "SETPENSIZE":
                result = LogoCommand.setPenSize(this.panel, this.loadedCommands);
                break;
            case "REPEAT":
                result = LogoCommand.repeat(this.panel, this.loadedCommands);
                break;
            default:
                throw new SyntaxErrorException("The syntax is wrong");
        }
        this.loadedCommands = this.loadedCommands.subList(result.size(), this.loadedCommands.size());
        commandsToExport.add(String.join(" ", result) + "\n");
        return result;
    }

    /**
     * Determines if it is possible to load commands, if it is possible nothing happens, otherwise an IllegalStateException exception is thrown.
     */
    private void canLoad() {
        if (this.panel == null)
            throw new IllegalStateException("No panel has been created yet");
        if (this.loadedCommands.size() != 0)
            throw new IllegalStateException("There is already a list of commands loaded");
    }
}
