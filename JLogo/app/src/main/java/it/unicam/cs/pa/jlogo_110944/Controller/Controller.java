package it.unicam.cs.pa.jlogo_110944.Controller;

import it.unicam.cs.pa.jlogo_110944.Model.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Application controller.
 *
 * @param <L> location type
 * @param <C> cursor type
 */
public interface Controller<L, C extends Cursor<L>> {

    /**
     * Creates a new Panel and empty the command list if one was loaded previously.
     *
     * @param width  width of the panel
     * @param height height of the panel
     * @throws IllegalArgumentException if the width or the height are negative
     */
    void newPanel(int width, int height);

    /**
     * Load commands.
     *
     * @param commands commands to load
     * @throws NullPointerException  if the commands are null
     * @throws IllegalStateException if there is already a list of commands loaded or if a panel has not yet been created
     */
    void loadCommands(String commands);

    /**
     * Load commands from a file
     *
     * @param path the path of the file
     * @throws IllegalStateException if there is already a list of commands loaded or if a panel has not yet been created
     * @throws NullPointerException  if the path is null
     * @throws IOException           if an I/O error occurs
     */
    void loadFromFile(String path) throws IOException;

    /**
     * Consumes a command from the list of loaded commands
     *
     * @return arguments of the executed command
     * @throws IllegalStateException if a panel has not yet been created or if there are no commands to execute
     * @throws SyntaxErrorException  if one of the loaded commands has a wrong syntax
     */
    List<String> executeCommand() throws SyntaxErrorException;

    /**
     * Determines if there are still commands to execute.
     *
     * @return true if there are still commands to execute, false otherwise
     */
    boolean hasCommand();

    /**
     * Gets the result of the executed commands.
     *
     * @return a list containing the result of the executed commands
     * @throws IllegalStateException if a panel has not yet been created
     */
    List<String> result();

    /**
     * Exports the commands to a file.
     *
     * @param file the file on which to write the commands
     * @throws NullPointerException if the file is null
	 * @throws IOException if an I/O error occurs
     */
    void exportCommands(File file) throws IOException;

    /**
     * Gets the panel.
     *
     * @return the panel, or null if no panel has been created yet
     */
    Panel<L, C> getPanel();

    /**
     * Adds a listener on the controller.
     *
     * @param listener the listener to add
     */
    void addListener(PanelUpdateListener<L> listener);

    /**
     * Removes a listener from the controller.
     *
     * @param listener the listener to remove
     */
    void removeListener(PanelUpdateListener<L> listener);
}
