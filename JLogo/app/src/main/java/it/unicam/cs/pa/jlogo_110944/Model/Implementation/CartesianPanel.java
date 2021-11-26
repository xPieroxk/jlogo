package it.unicam.cs.pa.jlogo_110944.Model.Implementation;

import it.unicam.cs.pa.jlogo_110944.Model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * An implementation of the Panel interface that use cartesian coordinates.
 */
public class CartesianPanel<C extends Cursor<CartesianLocation>> implements Panel<CartesianLocation, C> {

    private List<Line<CartesianLocation>> lines;
    private List<Area<CartesianLocation>> areas;
    private final C cursor;
    private RGBColor screenColor;
    private RGBColor areaColor;
    private final int width;
    private final int height;
    private final PanelUpdateSupport<CartesianLocation> notifier;
    private final AreaFinder<CartesianLocation> areaFinder;

    /**
     * Constructor method.
     *
     * @param width  the width of the panel
     * @param height the height of the panel
     * @param cursor the cursor of the panel
     * @throws NullPointerException     if the cursor is null
     * @throws IllegalArgumentException if the width or the height are negatives
     */
    public CartesianPanel(int width, int height, C cursor) {
        if (width < 0 || height < 0)
            throw new IllegalArgumentException("the height and the width must be positive");
        this.areas = new ArrayList<>();
        this.cursor = Objects.requireNonNull(cursor, "The cursor cannot be null");
        this.width = width;
        this.height = height;
        this.lines = new ArrayList<>();
        this.screenColor = new BasicRGBColor((byte) 255, (byte) 255, (byte) 255);
        this.areaColor = new BasicRGBColor((byte) 255, (byte) 255, (byte) 255);
        this.notifier = new PanelUpdateSupport<>();
        this.areaFinder = new GraphAreaFinder<>(this);
        cursor.setPosition(new CartesianLocation((double) width / 2, (double) height / 2));
    }

    @Override
    public void addLine(Line<CartesianLocation> line) {
        if (!this.lines.contains(Objects.requireNonNull(line, "The line cannot be null"))) {
            if (line.getStart().equals(line.getFinish()))
                return;
            this.lines.add(line);
            this.notifier.fireAddLine(line);
            this.areaFinder.findClosedArea().ifPresent(this::addArea);
        }
    }

    @Override
    public void addArea(Area<CartesianLocation> area) {
        this.areas.add(Objects.requireNonNull(area, "The area cannot be null"));
        this.notifier.fireAreaFound(area);
        this.lines.removeAll(area.getLines());
    }

    @Override
    public Line<CartesianLocation> getLine(CartesianLocation location1, CartesianLocation location2) {
        Objects.requireNonNull(location1, "Locations cannot be null");
        Objects.requireNonNull(location2, "Locations cannot be null");
        for (Line<CartesianLocation> line : this.lines)
            if (line.equals(new BasicLine<>(location1, location2)))
                return line;
        return null;
    }

    @Override
    public CartesianLocation moveCursor(int distance) {
        double angle = distance > 0 ? this.cursor.getDirection() : (this.cursor.getDirection() + 180) % 360;
        return moveCursor(distance, angle);
    }

    @Override
    public void rotateCursor(int degrees) {
        int newAngle = rotateCursor(value -> value > 0, degrees);
        this.cursor.setDirection(newAngle);
        this.notifier.fireAngleChanged(newAngle);
    }

    @Override
    public List<Line<CartesianLocation>> getLines() {
        return this.lines;
    }

    @Override
    public List<Area<CartesianLocation>> getAreas() {
        return this.areas;
    }

    @Override
    public void reset() {
        this.lines = new ArrayList<>();
        this.areas = new ArrayList<>();
        this.notifier.fireClearScreen();
    }

    @Override
    public CartesianLocation getHome() {
        return new CartesianLocation((double) this.width / 2, (double) this.height / 2);
    }

    @Override
    public void moveToHome() {
        this.cursor.setPosition(getHome());
        this.notifier.fireCursorMoved(getHome());
    }

    @Override
    public void setScreenColor(RGBColor color) {
        this.screenColor = Objects.requireNonNull(color, "The color cannot be null");
        this.notifier.fireSetScreenColor(color);
    }

    @Override
    public void setFillColor(RGBColor color) {
        this.areaColor = Objects.requireNonNull(color, "The color cannot be null");
    }

    @Override
    public RGBColor getScreenColor() {
        return this.screenColor;
    }

    @Override
    public RGBColor getFillColor() {
        return this.areaColor;
    }

    @Override
    public List<String> result() {
        List<String> resultString = new ArrayList<>();
        resultString.add("Screen color: " + (this.screenColor.getRed() & 0xFF) + " " + (this.screenColor.getGreen() & 0xFF) + " " + (this.screenColor.getBlue() & 0xFF));
        resultString.add("Fill color: " + (this.areaColor.getRed() & 0xFF) + " " + (this.areaColor.getGreen() & 0xFF) + " " + (this.areaColor.getBlue() & 0xFF));
        resultString.add("Pen color: " + (this.cursor.getLineColor().getRed() & 0xFF) + " " + (this.cursor.getLineColor().getGreen() & 0xFF) + " " + (this.cursor.getLineColor().getBlue() & 0xFF));
        resultString.add("Pen size: " + this.cursor.getLineSize());
        return result(resultString);
    }

    @Override
    public C getCursor() {
        return this.cursor;
    }

    @Override
    public void addPanelUpdateListener(PanelUpdateListener<CartesianLocation> listener) {
        this.notifier.addListener(listener);
    }

    @Override
    public void removePanelUpdateListener(PanelUpdateListener<CartesianLocation> listener) {
        this.notifier.removeListener(listener);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartesianPanel<?> panel = (CartesianPanel<?>) o;
        return width == panel.width && height == panel.height && lines.equals(panel.lines) && areas.equals(panel.areas) && cursor.equals(panel.cursor) && screenColor.equals(panel.screenColor) && areaColor.equals(panel.areaColor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lines, areas, cursor, screenColor, areaColor, width, height);
    }

    /**
     * Fills the result list with the found areas.
     */
    private List<String> result(List<String> result) {
        int i = 0;
        result.add("Areas found: " + this.areas.size());
        for (Area<CartesianLocation> area : this.areas) {
            result.add("Area: " + i++);
            result.add("\tColor: " + (area.getColor().getRed() & 0xFF) + " " + (area.getColor().getGreen() & 0xFF) + " " + (area.getColor().getBlue() & 0xFF));
            area.getLines().stream()
                    .sequential()
                    .map(Line::getStart)
                    .collect(Collectors.toList())
                    .forEach(c -> result.add("\tvertex " + c.toString()));
        }
        return result;
    }

    /**
     * Calculates the arrival location of the cursor.
     */
    private CartesianLocation moveCursor(int distance, double angle) {
        CartesianLocation start = this.cursor.getPosition();
        CartesianLocation arrive = new CartesianLocation(Math.round((start.getX() + (Math.abs(distance) * Math.cos(Math.toRadians(angle)))) * 100000d) / 100000d,
                Math.round((start.getY() + (Math.abs(distance) * Math.sin(Math.toRadians(angle)))) * 100000d) / 100000d);
        return moveCursor(start, arrive);
    }

    /**
     * Determines whether the target location of the cursor is outside the boundaries of the panel.
     */
    private CartesianLocation moveCursor(CartesianLocation start, CartesianLocation arrive) {
        Line<CartesianLocation> bottom = new BasicLine<>(new CartesianLocation(0, 0), new CartesianLocation(this.width, 0)),
                right = new BasicLine<>(new CartesianLocation(this.width, 0), new CartesianLocation(this.width, this.height)),
                top = new BasicLine<>(new CartesianLocation(this.width, this.height), new CartesianLocation(0, this.height)),
                left = new BasicLine<>(new CartesianLocation(0, this.height), new CartesianLocation(0, 0)),
                line = new BasicLine<>(start, arrive);
        Optional<CartesianLocation> intersection = Optional.empty();
        if (arrive.getX() > this.width)
            intersection = intersect(line, right);
        if (arrive.getX() < 0 && intersection.isEmpty())
            intersection = intersect(line, left);
        if (arrive.getY() > this.height && intersection.isEmpty())
            intersection = intersect(line, top);
        if (arrive.getY() < 0 && intersection.isEmpty())
            intersection = intersect(line, bottom);
        this.cursor.setPosition(intersection.orElse(arrive));
        this.notifier.fireCursorMoved(intersection.orElse(arrive));
        return intersection.orElse(arrive);
    }

    /**
     * Determines if two lines intersect.
     */
    private Optional<CartesianLocation> intersect(Line<CartesianLocation> line1, Line<CartesianLocation> line2) {
        double x1 = line1.getStart().getX(), y1 = line1.getStart().getY(),
                x2 = line1.getFinish().getX(), y2 = line1.getFinish().getY(),
                x3 = line2.getStart().getX(), y3 = line2.getStart().getY(),
                x4 = line2.getFinish().getX(), y4 = line2.getFinish().getY();
        double d = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
        if (d == 0)
            return Optional.empty();
        double x = ((x3 - x4) * (x1 * y2 - y1 * x2) - (x1 - x2) * (x3 * y4 - y3 * x4)) / d;
        double y = ((y3 - y4) * (x1 * y2 - y1 * x2) - (y1 - y2) * (x3 * y4 - y3 * x4)) / d;
        x = x == -0.0 ? 0.0 : x;
        y = y == -0.0 ? 0.0 : y;
        return Optional.of(new CartesianLocation(x, y));
    }

    /**
     * Rotates the cursor to the right or left according to the predicate.
     */
    private int rotateCursor(Predicate<Integer> p, int degrees) {
        if (p.test(degrees))
            degrees = (this.cursor.getDirection() + degrees) % 360;
        else {
            degrees = (this.cursor.getDirection() + degrees) % 360;
            degrees = degrees < 0 ? 360 + degrees : degrees;
        }
        return degrees;
    }
}
