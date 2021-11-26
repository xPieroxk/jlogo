package it.unicam.cs.pa.jlogo_110944.Model;

import java.util.LinkedList;
import java.util.List;

/**
 * A panel event notifier.
 *
 * @param <L> location type
 */
public final class PanelUpdateSupport<L> {

    private final List<PanelUpdateListener<L>> listeners;

    /**
     * Constructor method.
     */
    public PanelUpdateSupport() {
        this.listeners = new LinkedList<>();
    }

    /**
     * Adds a listener to the list of listeners.
     *
     * @param listener the listener to add
     */
    public synchronized void addListener(PanelUpdateListener<L> listener) {
        if (listener == null) return;
        this.listeners.add(listener);
    }

    /**
     * Removes a listener from the list of listeners.
     *
     * @param listener the listener to remove
     */
    public synchronized void removeListener(PanelUpdateListener<L> listener) {
        if (listener == null) return;
        this.listeners.remove(listener);
    }

    /**
     * Notifies all listeners that the angle of the cursor changed.
     *
     * @param angle the new angle of the cursor
     */
    public synchronized void fireAngleChanged(int angle) {
        listeners.forEach(l -> l.fireAngleChanged(angle));
    }

    /**
     * Notifies all listeners that a line has been added to the panel.
     *
     * @param line the generated line
     */
    public synchronized void fireAddLine(Line<L> line) {
        listeners.forEach(l -> l.fireAddLine(line));
    }

    /**
     * Notifies all listeners that the cursor moved to a new location
     *
     * @param location the new location of the cursor
     */
    public synchronized void fireCursorMoved(L location) {
        listeners.forEach(l -> l.fireCursorMoved(location));
    }


    /**
     * Notifies all listeners that a closed area has been found on the panel.
     *
     * @param area the found area
     */
    public synchronized void fireAreaFound(Area<L> area) {
        listeners.forEach(l -> l.fireAreaFound(area));
    }

    /**
     * Notifies all listeners that the panel screen has been cleared of lines and closed areas.
     */
    public synchronized void fireClearScreen() {
        listeners.forEach(PanelUpdateListener::fireClearScreen);
    }

    /**
     * Notifies all listeners that the color of the panel screen has been changed.
     *
     * @param color the new color of the screen
     */
    public synchronized void fireSetScreenColor(RGBColor color) {
        listeners.forEach(l -> l.fireSetScreenColor(color));
    }
}
