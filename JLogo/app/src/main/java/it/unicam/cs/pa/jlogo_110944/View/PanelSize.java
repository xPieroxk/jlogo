package it.unicam.cs.pa.jlogo_110944.View;

/**
 * Enum that contains the default panel sizes.
 */
public enum PanelSize {

    SMALL("Small (300x300)"),
    MEDIUM("Medium (500x500)"),
    LARGE("Large (650x650)");

    private final String description;

    PanelSize(String description) {
        this.description = description;
    }

    public int getWidth() {
        switch (this) {
            case SMALL:
                return 300;
            case MEDIUM:
                return 500;
            case LARGE:
                return 650;
        }
        return -1;
    }

    public int getHeight() {
        switch (this) {
            case SMALL:
                return 300;
            case MEDIUM:
                return 500;
            case LARGE:
                return 650;
        }
        return -1;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
