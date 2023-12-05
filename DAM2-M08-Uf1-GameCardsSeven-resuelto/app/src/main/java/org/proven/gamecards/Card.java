package org.proven.gamecards;

public class Card {
    double value;
    boolean visible;

    public Card(double value, boolean visible) {
        this.value = value;
        this.visible = visible;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
