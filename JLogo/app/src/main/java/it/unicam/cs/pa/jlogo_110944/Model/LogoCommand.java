package it.unicam.cs.pa.jlogo_110944.Model;

import it.unicam.cs.pa.jlogo_110944.Model.Implementation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Logo commands
 */
public interface LogoCommand {

    /**
     * Moves the cursor forward.
     * The syntax of the command is: FORWARD &lt;dist&gt;.
     * The dist must be positive
     *
     * @param panel   the panel on which to execute the command
     * @param command list of commands
     * @param <L>     location type
     * @param <C>     panel cursor
     * @return list of string used by this command
     * @throws NullPointerException if the panel or the commands are null
     * @throws SyntaxErrorException if the syntax of the BACKWARD command is wrong
     */
    static <L, C extends Cursor<L>> List<String> forward(Panel<L, C> panel, List<String> command) throws SyntaxErrorException {
        move(panel, command, "FORWARD");
        return command.subList(0, 2);
    }

    /**
     * Moves the cursor backward.
     * The syntax of the command is: BACKWARD &lt;dist&gt;.
     * The dist must be positive.
     *
     * @param panel   the panel on which to execute the command
     * @param command the command to execute
     * @param <L>     location type
     * @param <C>     panel cursor type
     * @return list of string used by this command
     * @throws NullPointerException if the panel or the commands are null
     * @throws SyntaxErrorException if the syntax of the BACKWARD command is wrong
     */
    static <L, C extends Cursor<L>> List<String> backward(Panel<L, C> panel, List<String> command) throws SyntaxErrorException {
        move(panel, command, "BACKWARD");
        return command.subList(0, 2);
    }

    /**
     * Rotates the cursor counter clockwise.
     * The syntax of the command is: LEFT &lt;angle&gt;.
     * The angle must be positive.
     *
     * @param panel   the panel on which to execute the command
     * @param command the command to execute
     * @param <L>     location type
     * @param <C>     panel cursor type
     * @return list of string used by this command
     * @throws NullPointerException if the panel or the commands are null
     * @throws SyntaxErrorException if the syntax of the LEFT command is wrong
     */
    static <L, C extends Cursor<L>> List<String> left(Panel<L, C> panel, List<String> command) throws SyntaxErrorException {
        checkSizeAndName(panel, command, "LEFT", 2);
        panel.rotateCursor(checkPositiveNumber("LEFT", command.get(1)));
        return command.subList(0, 2);
    }

    /**
     * Rotates the cursor clockwise.
     * The syntax of the command is: RIGHT &lt;angle&gt;.
     * The angle must be positive.
     *
     * @param panel   the panel on which to execute the command
     * @param command the command to execute
     * @param <L>     location type
     * @param <C>     panel cursor type
     * @return list of string used by this command
     * @throws NullPointerException if the panel or the commands are null
     * @throws SyntaxErrorException if the syntax of the RIGHT command is wrong
     */
    static <L, C extends Cursor<L>> List<String> right(Panel<L, C> panel, List<String> command) throws SyntaxErrorException {
        checkSizeAndName(panel, command, "RIGHT", 2);
        panel.rotateCursor(-checkPositiveNumber("RIGHT", command.get(1)));
        return command.subList(0, 2);
    }

    /**
     * Cleans the screen.
     * The syntax of the command is: CLEARSCREEN.
     *
     * @param panel   the panel on which to execute the command
     * @param command the command to execute
     * @param <L>     location type
     * @param <C>     panel cursor type
     * @return list of string used by this command
     * @throws NullPointerException if the panel or the commands are null
     * @throws SyntaxErrorException if syntax of the CLEARSCREEN command is wrong
     */
    static <L, C extends Cursor<L>> List<String> clearScreen(Panel<L, C> panel, List<String> command) throws SyntaxErrorException {
        checkSizeAndName(panel, command, "CLEARSCREEN", 1);
        panel.reset();
        return command.subList(0, 1);
    }

    /**
     * Moves the cursor to the home position.
     * The syntax of the command is: HOME.
     *
     * @param panel   the panel on which to execute the command
     * @param command the command to execute
     * @param <L>     location type
     * @param <C>     panel cursor type
     * @return list of string used by this command
     * @throws NullPointerException if the panel or the commands are null
     * @throws SyntaxErrorException if syntax of the HOME command is wrong
     */
    static <L, C extends Cursor<L>> List<String> home(Panel<L, C> panel, List<String> command) throws SyntaxErrorException {
        checkSizeAndName(panel, command, "HOME", 1);
        panel.moveToHome();
        return command.subList(0, 1);
    }

    /**
     * Sets the cursor plot to false
     * The syntax of the command is: PENUP.
     *
     * @param panel   the panel on which to execute the command
     * @param command the command to execute
     * @param <L>     location type
     * @param <C>     panel cursor type
     * @return list of string used by this command
     * @throws NullPointerException if the panel or the commands are null
     * @throws SyntaxErrorException if syntax of the PENUP command is wrong
     */
    static <L, C extends Cursor<L>> List<String> penUp(Panel<L, C> panel, List<String> command) throws SyntaxErrorException {
        checkSizeAndName(panel, command, "PENUP", 1);
        panel.getCursor().setPlot(false);
        return command.subList(0, 1);
    }

    /**
     * Sets the cursor plot to true.
     * The syntax of the command is: PENDOWN.
     *
     * @param panel   the panel on which to execute the command
     * @param command the command to execute
     * @param <L>     location type
     * @param <C>     panel cursor type
     * @return list of string used by this command
     * @throws NullPointerException if the panel or the commands are null
     * @throws SyntaxErrorException if syntax of the PENDOWN command is wrong
     */
    static <L, C extends Cursor<L>> List<String> penDown(Panel<L, C> panel, List<String> command) throws SyntaxErrorException {
        checkSizeAndName(panel, command, "PENDOWN", 1);
        panel.getCursor().setPlot(true);
        return command.subList(0, 1);
    }

    /**
     * Sets the pen color.
     * The syntax of the command is: SETPENCOLOR &lt;byte&gt; &lt;byte&gt; &lt;byte&gt;.
     * The bytes go from 0 to 255 and all together represent a rgb color.
     *
     * @param panel   the panel on which to execute the command
     * @param command the command to execute
     * @param <L>     location type
     * @param <C>     panel cursor type
     * @return list of string used by this command
     * @throws NullPointerException if the panel or the commands are null
     * @throws SyntaxErrorException if syntax of the SETPENCOLOR command is wrong
     */
    static <L, C extends Cursor<L>> List<String> setPenColor(Panel<L, C> panel, List<String> command) throws SyntaxErrorException {
        checkSizeAndName(panel, command, "SETPENCOLOR", 4);
        panel.getCursor().setLineColor(rgb(command));
        return command.subList(0, 4);
    }

    /**
     * Sets the fill color.
     * The syntax of the command is: SETFILLCOLOR &lt;byte&gt; &lt;byte&gt; &lt;byte&gt;.
     * The bytes go from 0 to 255 and all together represent a rgb color.
     *
     * @param panel   the panel on which to execute the command
     * @param command the command to execute
     * @param <L>     location type
     * @param <C>     panel cursor type
     * @return list of string used by this command
     * @throws NullPointerException if the panel or the commands are null
     * @throws SyntaxErrorException if syntax of the SETFILLCOLOR command is wrong
     */
    static <L, C extends Cursor<L>> List<String> setFillColor(Panel<L, C> panel, List<String> command) throws SyntaxErrorException {
        checkSizeAndName(panel, command, "SETFILLCOLOR", 4);
        panel.setFillColor(rgb(command));
        return command.subList(0, 4);
    }

    /**
     * Sets the screen color.
     * The syntax of the command is: SETSCREENCOLOR &lt;byte&gt; &lt;byte&gt; &lt;byte&gt;.
     * The bytes go from 0 to 255 and all together represent a rgb color.
     *
     * @param panel   the panel on which to execute the command
     * @param command the command to execute
     * @param <L>     location type
     * @param <C>     panel cursor type
     * @return list of string used by this command
     * @throws NullPointerException if the panel or the commands are null
     * @throws SyntaxErrorException if syntax of the SETSCREENCOLOR command is wrong
     */
    static <L, C extends Cursor<L>> List<String> setScreenColor(Panel<L, C> panel, List<String> command) throws SyntaxErrorException {
        checkSizeAndName(panel, command, "SETSCREENCOLOR", 4);
        panel.setScreenColor(rgb(command));
        return command.subList(0, 4);
    }

    /**
     * Sets the pen line size.
     * The syntax of the command is: SETPENSIZE &lt;size&gt;.
     * The size must be postive.
     *
     * @param panel   the panel on which to execute the command
     * @param command the command to execute
     * @param <L>     location type
     * @param <C>     panel cursor type
     * @return list of string used by this command
     * @throws NullPointerException if the panel or the commands are null
     * @throws SyntaxErrorException if syntax of the SETPENSIZE command is wrong
     */
    static <L, C extends Cursor<L>> List<String> setPenSize(Panel<L, C> panel, List<String> command) throws SyntaxErrorException {
        checkSizeAndName(panel, command, "SETPENSIZE", 2);
        panel.getCursor().setLineSize(checkPositiveNumber("SETPENSIZE", command.get(1)));
        return command.subList(0, 2);
    }

    /**
     * Repeats a series of commands.
     * The syntax of the command is: REPEAT &lt;num&gt; [cmds].
     *
     * @param panel    the panel on which to execute the command
     * @param commands the commands to execute
     * @param <L>      location type
     * @param <C>      panel cursor type
     * @return list of string used by this command
     * @throws NullPointerException if the panel or the commands are null
     * @throws SyntaxErrorException if the syntax of the REPEAT command is wrong
     */
    static <L, C extends Cursor<L>> List<String> repeat(Panel<L, C> panel, List<String> commands) throws SyntaxErrorException {
        checkSizeAndName(panel, commands, "REPEAT", 5);
        if (!commands.get(2).equals("["))
            throw new SyntaxErrorException("The syntax of the REPEAT command is wrong");
        int n = checkPositiveNumber("REPEAT", commands.get(1));
        int end = findEndBracket(commands);
        if (end == commands.size()) // end bracket not found
            throw new SyntaxErrorException("The syntax of the REPEAT command is wrong");
        repeat(panel, commands.subList(3, end), n);
        return commands.subList(0, ++end);
    }

    /**
     * Executes the commands inside a repeat up to n times.
     */
    private static <L, C extends Cursor<L>> void repeat(Panel<L, C> panel, List<String> commands, int n) throws SyntaxErrorException {
        //make sure that all parameters are valid
        List<String> temp = new ArrayList<>(commands);
        while (n > 0) {
            while (temp.size() != 0) {
                switch (temp.get(0)) {
                    case "FORWARD":
                        temp = temp.subList(LogoCommand.forward(panel, temp).size(), temp.size());
                        break;
                    case "BACKWARD":
                        temp = temp.subList(LogoCommand.backward(panel, temp).size(), temp.size());
                        break;
                    case "LEFT":
                        temp = temp.subList(LogoCommand.left(panel, temp).size(), temp.size());
                        break;
                    case "RIGHT":
                        temp = temp.subList(LogoCommand.right(panel, temp).size(), temp.size());
                        break;
                    case "CLEARSCREEN":
                        temp = temp.subList(LogoCommand.clearScreen(panel, temp).size(), temp.size());
                        break;
                    case "HOME":
                        temp = temp.subList(LogoCommand.home(panel, temp).size(), temp.size());
                        break;
                    case "PENUP":
                        temp = temp.subList(LogoCommand.penUp(panel, temp).size(), temp.size());
                        break;
                    case "PENDOWN":
                        temp = temp.subList(LogoCommand.penDown(panel, temp).size(), temp.size());
                        break;
                    case "SETPENCOLOR":
                        temp = temp.subList(LogoCommand.setPenColor(panel, temp).size(), temp.size());
                        break;
                    case "SETFILLCOLOR":
                        temp = temp.subList(LogoCommand.setFillColor(panel, temp).size(), temp.size());
                        break;
                    case "SETSCREENCOLOR":
                        temp = temp.subList(LogoCommand.setScreenColor(panel, temp).size(), temp.size());
                        break;
                    case "SETPENSIZE":
                        temp = temp.subList(LogoCommand.setPenSize(panel, temp).size(), temp.size());
                        break;
                    case "REPEAT":
                        temp = temp.subList(LogoCommand.repeat(panel, temp).size(), temp.size());
                        break;
                    default:
                        throw new SyntaxErrorException("The syntax of the repeat command is wrong");
                }
            }
            temp = new ArrayList<>(commands);
            n--;
        }
    }

    /**
     * Look for the end of the repeat command, the possibility that there is
     * a repeat within a repeat is taken into account.
     */
    private static int findEndBracket(List<String> list) {
        //make sure that the list is not null
        int i = 2;
        int openBrackets = 0;
        for (; i < list.size(); i++) {
            if (list.get(i).equals("["))
                openBrackets++;
            if (list.get(i).equals("]")) {
                openBrackets--;
                if (openBrackets == 0)
                    break;
            }
        }
        return i;
    }

    /**
     * Return true if a string is not an integer.
     */
    private static boolean notAnInteger(String s) {
        //make sure the string is not null and has a length of at least 1
        boolean b = s.charAt(0) == '-' || s.charAt(0) == '+';
        if (b && s.length() < 2)
            return true;
        int i = b ? 1 : 0;
        for (; i < s.length(); i++)
            if (Character.digit(s.charAt(i), 10) < 0)
                return true;
        return false;
    }

    /**
     * Creates a rgb color from a list.
     */
    private static RGBColor rgb(List<String> command) throws SyntaxErrorException {
        //make sure the list is not null and has a size of at least 4
        int r = Integer.parseInt(command.get(1));
        int g = Integer.parseInt(command.get(2));
        int b = Integer.parseInt(command.get(3));
        if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255)
            throw new SyntaxErrorException("Syntax error: incorrect rgb values");
        return new BasicRGBColor((byte) r, (byte) g, (byte) b);
    }

    /**
     * Checks that the panel and the commands are not null and also checks the name of the command
     * and the minimum length it must have.
     */
    private static <L, C extends Cursor<L>> void checkSizeAndName(Panel<L, C> panel, List<String> command, String name, int commandSize) throws SyntaxErrorException {
        Objects.requireNonNull(panel, "The panel must be not null");
        Objects.requireNonNull(command, "The command list must be not null");
        if (command.size() < commandSize || !command.get(0).equals(name))
            throw new SyntaxErrorException("The syntax of the " + name + " command is wrong");
    }

    /**
     * Checks that the number given as a string is positive.
     */
    private static int checkPositiveNumber(String name, String value) throws SyntaxErrorException {
        //make sure that the parameters are not null
        if (notAnInteger(value))
            throw new SyntaxErrorException("The syntax of the " + name + " command is wrong");
        int size = Integer.parseInt(value);
        if (size < 1)
            throw new SyntaxErrorException("The syntax of the " + name + " command is wrong");
        return size;
    }

    /**
     * Moves the cursor over the panel and adds a line if necessary.
     */
    private static <L, C extends Cursor<L>> void move(Panel<L, C> panel, List<String> command, String name) throws SyntaxErrorException {
        checkSizeAndName(panel, command, name, 2);
        int distance = checkPositiveNumber(name, command.get(1));
        if (distance == 0) return;
        L oldLocation = panel.getCursor().getPosition();
        L newLocation = panel.moveCursor(name.equals("BACKWARD") ? -distance : distance);
        if (panel.getCursor().getPlot()) {
            Line<L> line = new BasicLine<>(oldLocation, newLocation, panel.getCursor().getLineSize(), panel.getCursor().getLineColor());
            panel.addLine(line);
        }
    }

}
