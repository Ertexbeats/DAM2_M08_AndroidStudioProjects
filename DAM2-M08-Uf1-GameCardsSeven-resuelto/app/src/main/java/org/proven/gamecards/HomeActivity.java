package org.proven.gamecards;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends Activity {

    View.OnClickListener listener;
    Button bGameActivity, bConfigActivity;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.home_layout );

        instanciar();
        instantiateListener();
        setListeners();

    }

    public void instanciar() {
        bGameActivity = findViewById( R.id.bGameActivity );
        bConfigActivity = findViewById( R.id.bConfigActivity );
    }

    public void instantiateListener() {
        listener = new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                // TODO Auto-generated method stub

                switch ( v.getId() ) {
                    case R.id.bGameActivity:
                        goToGameActivity();
                        break;
                    case R.id.bConfigActivity:

                        goToConfigActivity();
                        break;
                }
            }
        };
    }

    public void setListeners() {
        bGameActivity.setOnClickListener( listener );
        bConfigActivity.setOnClickListener( listener );
    }

    public void goToGameActivity() {
        startActivity( new Intent( this, GameActivity.class ) );
    }

    public void goToConfigActivity() {
        startActivity( new Intent( this, ConfigActivity.class ) );
    }

}