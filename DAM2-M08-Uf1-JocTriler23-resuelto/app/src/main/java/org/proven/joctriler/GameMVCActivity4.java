package org.proven.joctriler;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GameMVCActivity4 extends Activity {

    static int SIZE = 4;
    List< ImageView > ivListCube;
    Button bStart;
    TextView tvInfo;
    View.OnClickListener listener;
    ModelGame modelGame;

    @Override
    public void onCreate( Bundle b ) {
        super.onCreate( b );
        setContentView( R.layout.joc_layout4 );

        // Init Elements (Inicialitzar variables,
        //                 Crear el Model de JOC)
        initElements();
        prepareListener();
        // Add Listener to Elements
        addListenerToElements();

    }

    private void addListenerToElements() {
        bStart.setOnClickListener( listener );
        for ( ImageView iv : ivListCube ) {
            iv.setOnClickListener( listener );
        }
    }

    private void prepareListener() {
        listener = new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                int clickedViewId = view.getId();

                switch ( clickedViewId ) {
                    case R.id.ivcube1:
                        handleCubeClick( 0 );
                        break;
                    case R.id.ivcube2:
                        handleCubeClick( 1 );
                        break;
                    case R.id.ivcube3:
                        handleCubeClick( 2 );
                        break;
                    case R.id.ivcube4:
                        handleCubeClick( 3 );
                        break;
                    case R.id.bstart:
                        handleStartButtonClick();
                        break;
                }
            }
        };
    }

    private void handleCubeClick( int cubeIndex ) {
        if ( modelGame.getStateGame() == ModelGame.STATE_GAME_USER ) {
            modelGame.setStateGame( ModelGame.STATE_GAME_MACHINE );
            checkResult( cubeIndex );
            Log.d( "EVENT", "ivcube" + ( cubeIndex + 1 ) );
        }
    }

    private void handleStartButtonClick() {
        if ( modelGame.getStateGame() == ModelGame.STATE_GAME_MACHINE ) {
            modelGame.setStateGame( ModelGame.STATE_GAME_USER );
            initGame();
            Log.d( "EVENT", "bstart" );
        }
    }

    private void checkResult( int positionUser ) {
        modelGame.setPositionUserBall( positionUser );
        if ( modelGame.checkResult() ) {
            tvInfo.setText( R.string.win );
            ivListCube.get( positionUser ).setImageResource( R.drawable.levantaok );
        } else {
            tvInfo.setText( R.string.lose );
            ivListCube.get( positionUser ).setImageResource( R.drawable.levantafail );
        }

    }

    private void initGame() {
        modelGame.initGame( SIZE );
        for ( ImageView iv : ivListCube ) {
            iv.setImageResource( R.drawable.cubilete );
        }
        //bStart.setVisibility(View.INVISIBLE);
        tvInfo.setText( ( R.string.playing ) );
    }

    private void initElements() {
        modelGame = new ModelGame();
        modelGame.setStateGame( ModelGame.STATE_GAME_MACHINE );

        int numCubes = SIZE; // Cambia esto al número total de ImageView que tienes

        //ivCube = new ImageView[numCubes];
        ivListCube = new ArrayList< ImageView >();
        for ( ImageView iv : ivListCube ) {
            int i = 0;
            i++;
            int cubeId = getResources().getIdentifier( "ivcube" + ( i + 1 ), "id", getPackageName() );
            ivListCube.add( findViewById( cubeId ) );
        }

        tvInfo = findViewById( R.id.tvinfo );
        bStart = findViewById( R.id.bstart );
        tvInfo.setText( R.string.start_game );
    }


    // Mètode Init Game
    // --> cridarem quan premi Start
    // bStart.setVisibility(View.INVISIBLE);
    // bStart.setVisibility(View.VISIBLE);


    public void readDinamicallyElementsLayout() {
        LinearLayout l = ( LinearLayout ) findViewById( R.id.layoutCubes );
        for ( int i = 0; i < l.getChildCount(); i++ ) {
            Object o = l.getChildAt( i );
            System.out.println( i + " GETCLASS: " + o.getClass().getSimpleName() );
            if ( o.getClass().getSimpleName() == "ImageView" ) {
                System.out.println( ( ( View ) o ).getTransitionName() );
            }


        }
    }
}

