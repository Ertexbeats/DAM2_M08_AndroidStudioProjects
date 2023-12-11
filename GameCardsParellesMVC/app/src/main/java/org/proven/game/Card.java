package org.proven.game;

/**
 * ADT Abstract Data Type
 */
public class Card {
    int id;
    String image;
    boolean visible;

    public Card( int id, String image, boolean visible ) {
        this.id = id;
        this.image = image;
        this.visible = visible;
    }

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage( String image ) {
        this.image = image;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible( boolean visible ) {
        this.visible = visible;
    }
}
