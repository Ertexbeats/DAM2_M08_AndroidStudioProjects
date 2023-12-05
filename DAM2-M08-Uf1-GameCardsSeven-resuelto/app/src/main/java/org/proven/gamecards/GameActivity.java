package org.proven.gamecards;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class GameActivity extends Activity {

    static final int NUM_CARDS = 8;
    ImageView[] arrIvCards;
    TextView tvPoints, tvIntents, tvInfo;
    Button bstart, bReturn, bConfig;
    View.OnClickListener listener;
    Model modelGame;


    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.game_layout );

        // Instantiate Elements
        //instantiateElements();
        instantiateElementsDinamically();
        // prepare Listener
        prepareListener();
        // Add Elements to listener
        addToListenerElements();
        modelGame = new Model();
        restartGame();   //  --> Init model &
        modelGame.setStateGame( Model.PLAYING );

        readParamsFromConfigActivity();
        prepareListenerReturnButton();
    }

    private void readParamsFromConfigActivity() {
//        Bundle bundle = getIntent().getExtras();
//        if ( bundle != null ) {
//            String user = bundle.getString( "name", "" );
//            Log.d( "user", user );
//
//            String level = bundle.getString( "level", "" );
//            Log.d( "level", level );
//
//            Boolean background = bundle.getBoolean( "background", false );
//            Log.d( "background", String.valueOf( background ) );
//
//            String food = bundle.getString( "food", "" );
//            Log.d( "food", food );
//
//            int progress = bundle.getInt( "progress", 0 );
//            Log.d( "progress", String.valueOf( progress ) );
//
//
//            showParams( user, level, background, food, progress );
//        }


//        SharedPreferences sp = this.getSharedPreferences( "dades", MODE_PRIVATE );
//        String name = sp.getString( "name", "default value" );
//        String food = sp.getString( "spinner", "default value" );
//
//        tvInfo.setText( "User: " + name + "\nFood: " + food );


    }

    private void showParams( String user, String level, Boolean background, String food, int progress ) {
        tvInfo.setText( "User: " + user + "\nLevel: " + level + "\nBackground: " + background + "\nFood: " + food + "\nProgress: " + progress );
    }

    private void restartGame() {
        modelGame.restartGame( NUM_CARDS );
        modelGame.setStateGame( Model.PLAYING );
        // restart Game 8 cards
        drawScene();
    }

    private void writeInternalStorage() {
        try {
            String nameFile = "historic_points_game.txt";
            FileOutputStream fout = openFileOutput( nameFile, MODE_PRIVATE );
            String s = "Hello World";
            fout.write( s.getBytes() );
            fout.close();
        } catch ( Exception e ) {
            e.printStackTrace();
        }

    }

    private void readInternalStorage() {
        try {
            String nameFile = "historic_points_game.txt";
            FileInputStream fis = openFileInput( nameFile );
            BufferedReader br = new BufferedReader( new InputStreamReader( fis ) );
            String line = br.readLine();
            while ( line != null ) {
                System.out.println( line );
                line = br.readLine();
            }

            fis.close();
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    private void drawScene() {
        double sumCards = modelGame.getSumCards();
        if ( sumCards == 7.5 ) {
            tvPoints.setText( "YOU WIN: " + modelGame.getSumCards() );
        } else if ( sumCards < 7.5 ) {
            tvPoints.setText( "Points: " + modelGame.getSumCards() );
        } else if ( sumCards > 7.5 ) {
            tvPoints.setText( "You LOSE: " + modelGame.getSumCards() );
        }
        tvIntents.setText( "Intents:" + modelGame.getNumIntents() );
        for ( int i = 0; i < modelGame.getSizeCards(); i++ ) {
            Card c = modelGame.getCard( i );
            if ( !c.isVisible() ) {
                arrIvCards[ i ].setImageResource( R.drawable.back );
            } else {
                if ( c.getValue() == 0.5 ) {
                    arrIvCards[ i ].setImageResource( R.drawable.jk );
                } else if ( c.getValue() == 1 ) {
                    arrIvCards[ i ].setImageResource( R.drawable.c1 );
                } else if ( c.getValue() == 2 ) {
                    arrIvCards[ i ].setImageResource( R.drawable.c2 );
                } else if ( c.getValue() == 3 ) {
                    arrIvCards[ i ].setImageResource( R.drawable.c3 );
                } else if ( c.getValue() == 4 ) {
                    arrIvCards[ i ].setImageResource( R.drawable.c4 );
                } else if ( c.getValue() == 5 ) {
                    arrIvCards[ i ].setImageResource( R.drawable.c5 );
                } else if ( c.getValue() == 6 ) {
                    arrIvCards[ i ].setImageResource( R.drawable.c6 );
                } else if ( c.getValue() == 7 ) {
                    arrIvCards[ i ].setImageResource( R.drawable.c7 );
                }

            }
        }
    }

    private void addToListenerElements() {
        for ( int i = 0; i < arrIvCards.length; i++ ) {
            arrIvCards[ i ].setOnClickListener( listener );
        }
        bstart.setOnClickListener( listener );
    }

    private void prepareListener() {
        listener = new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                if ( modelGame.getStateGame() == Model.PLAYING ) {
                    //System.out.println(view.getId());
                    for ( int i = 0; i < arrIvCards.length; i++ ) {
                        if ( view.getId() == arrIvCards[ i ].getId() ) {
                            modelGame.swapCard( i );
                            modelGame.incrementIntent();
                            drawScene();
                        }
                    }
                    if ( modelGame.getSumCards() >= 7.5 ) {
                        modelGame.setStateGame( Model.WAITING_PLAY );
                    }
                } else {
                    if ( view.getId() == bstart.getId() ) {
                        restartGame();
                    }
                }
            }
        };
    }

    private void instantiateElements() {
        arrIvCards = new ImageView[ 8 ];
        tvPoints = findViewById( R.id.tvpoints );
        tvIntents = findViewById( R.id.tvintents );
        bstart = findViewById( R.id.bstart );
        arrIvCards[ 0 ] = findViewById( R.id.ivcard0 );
        arrIvCards[ 1 ] = findViewById( R.id.ivcard1 );
        arrIvCards[ 2 ] = findViewById( R.id.ivcard2 );
        arrIvCards[ 3 ] = findViewById( R.id.ivcard3 );
        arrIvCards[ 4 ] = findViewById( R.id.ivcard4 );
        arrIvCards[ 5 ] = findViewById( R.id.ivcard5 );
        arrIvCards[ 6 ] = findViewById( R.id.ivcard6 );
        arrIvCards[ 7 ] = findViewById( R.id.ivcard7 );

    }

    public void instantiateElementsDinamically() {
        tvInfo = findViewById( R.id.tvInfo );
        arrIvCards = new ImageView[ 8 ];
        tvPoints = findViewById( R.id.tvpoints );
        tvIntents = findViewById( R.id.tvintents );
        bstart = findViewById( R.id.bstart );
        bReturn = findViewById( R.id.bReturn );
        bConfig = findViewById( R.id.bConfig );
        LinearLayout layout0 = findViewById( R.id.layout0 );
        LinearLayout layout1 = findViewById( R.id.layout1 );
        for ( int i = 0; i < layout0.getChildCount(); i++ ) {
            Object o = layout0.getChildAt( i );
            //System.out.println(i + " GETCLASS: " + o.getClass().getSimpleName());
            if ( o.getClass().getSimpleName().equals( "ImageView" ) )
                arrIvCards[ i ] = ( ImageView ) o;
        }
        for ( int i = 0; i < layout1.getChildCount(); i++ ) {
            Object o = layout1.getChildAt( i );
            //System.out.println(i + " GETCLASS: " + o.getClass().getSimpleName());
            if ( o.getClass().getSimpleName().equals( "ImageView" ) )
                arrIvCards[ i + 4 ] = ( ImageView ) o;
        }
    }

    private void prepareListenerReturnButton() {
        listener = new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                // TODO Auto-generated method stub

                switch ( v.getId() ) {
                    case R.id.bReturn:
                        goToHomeActivity();
                        break;
                    case R.id.bConfig:

                        goToConfigActivity();
                        break;
                }
            }
        };
    }

    public void goToHomeActivity() {
        startActivity( new Intent( this, HomeActivity.class ) );
    }

    public void goToConfigActivity() {
        startActivity( new Intent( this, ConfigActivity.class ) );
    }
}