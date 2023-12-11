package org.proven.game;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    /**
     * EXERCICI: fer un servei per a que validi les cartes automàticament al cap de 2 segons
     * https://developer.android.com/guide/components/services?hl=es-419
     * Implementar un  IntentService
     * Exemple d'implementació: https://www.geeksforgeeks.org/intent-service-in-android-with-example/
     */

    ImageView[] imageView = new ImageView[ 6 ];
    Button buttonValidate;
    TextView tvResult;
    View.OnClickListener listener;
    ModelGame modelGame;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_game );
        // instanciar elements
        instantiateElements();
        initGame();
        // Crear el listener
        createListener();
        // Afegir als elements el listener
        addElementsToListener();

    }

    private void initGame() {
        modelGame = new ModelGame();
        modelGame.setNumCards( 6 );
        modelGame.initGame();
        initScene();
    }

    private void initScene() {
        modelGame.setNumTirades( 0 );
        for ( int i = 0; i < imageView.length; i++ ) {
            imageView[ i ].setImageResource( R.drawable.back );
        }
    }

    private void addElementsToListener() {
        for ( int i = 0; i < imageView.length; i++ ) {
            imageView[ i ].setOnClickListener( listener );
        }
        buttonValidate.setOnClickListener( listener );
    }

    private void createListener() {
        listener = new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                if ( modelGame.getNumTirades() < 2 ) {
                    if ( view.getId() == imageView[ 0 ].getId() ) {
                        if ( !modelGame.getStateCard( 0 ) ) { // si és FALSE
                            modelGame.setStateCard( 0, true );
                            modelGame.setNumTirades( modelGame.getNumTirades() + 1 );
                            showCard( imageView[ 0 ], modelGame.getCard( 0 ) );
                        }
                    } else if ( view.getId() == imageView[ 1 ].getId() ) {
                        if ( !modelGame.getStateCard( 1 ) ) { // si és FALSE
                            modelGame.setStateCard( 1, true );
                            modelGame.setNumTirades( modelGame.getNumTirades() + 1 );
                            showCard( imageView[ 1 ], modelGame.getCard( 1 ) );
                        }
                    } else if ( view.getId() == imageView[ 2 ].getId() ) {
                        if ( !modelGame.getStateCard( 2 ) ) { // si és FALSE
                            modelGame.setStateCard( 2, true );
                            modelGame.setNumTirades( modelGame.getNumTirades() + 1 );
                            showCard( imageView[ 2 ], modelGame.getCard( 2 ) );
                        }
                    } else if ( view.getId() == imageView[ 3 ].getId() ) {
                        if ( !modelGame.getStateCard( 3 ) ) { // si és FALSE
                            modelGame.setStateCard( 3, true );
                            modelGame.setNumTirades( modelGame.getNumTirades() + 1 );
                            showCard( imageView[ 3 ], modelGame.getCard( 3 ) );
                        }
                    } else if ( view.getId() == imageView[ 4 ].getId() ) {
                        if ( !modelGame.getStateCard( 4 ) ) { // si és FALSE
                            modelGame.setStateCard( 4, true );
                            modelGame.setNumTirades( modelGame.getNumTirades() + 1 );
                            showCard( imageView[ 4 ], modelGame.getCard( 4 ) );
                        }
                    } else if ( view.getId() == imageView[ 5 ].getId() ) {
                        if ( !modelGame.getStateCard( 5 ) ) { // si és FALSE
                            modelGame.setStateCard( 5, true );
                            modelGame.setNumTirades( modelGame.getNumTirades() + 1 );
                            showCard( imageView[ 5 ], modelGame.getCard( 5 ) );
                        }
                    }
                }
                if ( modelGame.getNumTirades() == 2 ) {
                    if ( view.getId() == buttonValidate.getId() ) {
                        modelGame.validateCards();
                        modelGame.setNumTirades( 0 );
                        drawScene();
                    }
                }
            }
        };
    }

    private void drawScene() {
        for ( int i = 0; i < imageView.length; i++ ) {
            if ( !modelGame.getStateCard( i ) ) {
                imageView[ i ].setImageResource( R.drawable.back );
            }
        }
    }

    private void showCard( ImageView imageView, Card card ) {
        switch ( card.getId() ) {
            case 0:
                imageView.setImageResource( R.drawable.eugenio );
                break;
            case 1:
                imageView.setImageResource( R.drawable.faemino );
                break;
            case 2:
                imageView.setImageResource( R.drawable.chiquito );
                break;
            default:
                imageView.setImageResource( R.drawable.back );
                break;
        }
    }

    private void instantiateElements() {
        imageView[ 0 ] = findViewById( R.id.im0 );
        imageView[ 1 ] = findViewById( R.id.im1 );
        imageView[ 2 ] = findViewById( R.id.im2 );
        imageView[ 3 ] = findViewById( R.id.im3 );
        imageView[ 4 ] = findViewById( R.id.im4 );
        imageView[ 5 ] = findViewById( R.id.im5 );
        buttonValidate = findViewById( R.id.bstart );
        tvResult = findViewById( R.id.tvresult );
    }
}