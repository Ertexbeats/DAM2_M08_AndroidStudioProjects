package org.proven.game;

import java.util.ArrayList;
import java.util.Collections;

public class ModelGame {
    int numCards;
    ArrayList< Card > arrayCards;
    int numTirades;

    public ModelGame() {
        arrayCards = new ArrayList< Card >();
        numCards = 0;
        numTirades = 0;
    }

    public void initGame() {
        arrayCards.clear(); /**********/
        for ( int i = 0; i < getNumCards() / 2; i++ ) {
            Card c1 = new Card( i, "", false );
            Card c2 = new Card( i, "", false );
            arrayCards.add( c1 );
            arrayCards.add( c2 );
        }
        Collections.shuffle( arrayCards );
    }

    public void validateCards() {
        for ( int i = 0; i < arrayCards.size(); i++ ) {
            Card c1 = arrayCards.get( i );
            boolean hasPair = false;
            if ( c1.isVisible() ) {
                for ( int j = 0; j < arrayCards.size(); j++ ) {
                    if ( j != i ) { // no es compara amb ella mateixa
                        Card c2 = arrayCards.get( j );
                        if ( c2.isVisible() ) {
                            if ( c1.getId() == c2.getId() ) {
                                hasPair = true;
                            }
                        }
                    }
                }
                if ( !hasPair ) c1.setVisible( false );
            }
        }
    }

    public void setStateCard( int index, boolean visible ) {
        if ( index < arrayCards.size() ) {
            Card c = arrayCards.get( index );
            c.setVisible( visible );
            arrayCards.set( index, c );
        }
    }

    public boolean getStateCard( int index ) {
        boolean b = false;
        if ( index < arrayCards.size() ) {
            b = arrayCards.get( index ).isVisible();
        }
        return b;
    }

    public Card getCard( int index ) {
        Card c = null;
        if ( index < arrayCards.size() ) {
            c = arrayCards.get( index );
        }
        return c;
    }

    public int getNumCards() {
        return numCards;
    }

    public void setNumCards( int numCards ) {
        this.numCards = numCards;
    }

    public int getNumTirades() {
        return numTirades;
    }

    public void setNumTirades( int numTirades ) {
        this.numTirades = numTirades;
    }

    public ArrayList getArrayCards() {
        return arrayCards;
    }

    public static void main( String argv[] ) {
        ModelGame modelGame = new ModelGame();
        modelGame.setNumCards( 6 );
        modelGame.initGame();
        System.out.println( "SIZE" + modelGame.getArrayCards().size() );
        for ( int i = 0; i < modelGame.getArrayCards().size(); i++ ) {
            System.out.println( "id:" + modelGame.getCard( i ).getId() );
        }

    }
}
