package org.proven.blackjack;

public class Card {
    private int value;
    private Category category;
    private boolean visible = true;

    public Card( int value, Category category ) {
        this.value = value;
        this.category = category;
    }

    public Card() {
    }

    public Card( int value, Category category, boolean visible ) {
        this.value = value;
        this.category = category;
        this.visible = visible;
    }

    public int getValue() {
        return value;
    }

    public void setValue( int value ) {
        this.value = value;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory( Category category ) {
        this.category = category;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible( boolean visible ) {
        this.visible = visible;
    }

    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder( "Card { " );
        sb.append( "value = " ).append( value );
        sb.append( ", category = " ).append( category );
        sb.append( ", visible = " ).append( visible );
        sb.append( " }" );
        return sb.toString();
    }
}