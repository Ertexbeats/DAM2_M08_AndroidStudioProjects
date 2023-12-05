package org.proven.blackjack;


import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    View.OnClickListener listener;
    Button bGameActivity;
    Button bConfigActivity;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        instanciar();
        instantiateListener();
        setListeners();

    }

    public void instanciar() {
        bGameActivity = findViewById( R.id.bGameActivity );
        bConfigActivity = findViewById( R.id.bConfigActivity );
    }

    public void instantiateListener() {
        listener = v -> {
            if ( v.getId() == R.id.bGameActivity ) {
                onGameButtonClick();
            } else if ( v.getId() == R.id.bConfigActivity ) {
                onConfigButtonClick();
            }
        };
    }

    public void setListeners() {
        bGameActivity.setOnClickListener( listener );
        bConfigActivity.setOnClickListener( listener );
    }

    public void onGameButtonClick() {
        ObjectAnimator rotation = ObjectAnimator.ofFloat( findViewById( R.id.bGameActivity ), "rotation", 0f, 360f );
        rotation.setDuration( 1000 ); // Duración de la animación en milisegundos
        rotation.start();
        goToGameActivity();
    }

    public void onConfigButtonClick() {
        ObjectAnimator rotation = ObjectAnimator.ofFloat( findViewById( R.id.bConfigActivity ), "rotation", 0f, 360f );
        rotation.setDuration( 1000 ); // Duración de la animación en milisegundos
        rotation.start();
        goToConfigActivity();
    }

    // Creates the menu items
    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {
        super.onCreateOptionsMenu( menu );
        // Nivell del Menú, ItemId, Ordre dins del menú, Text
        menu.add( 0, Menu.FIRST, Menu.NONE, "Exit" );
        return true;
    }

    // Handles item selections
    @Override
    public boolean onOptionsItemSelected( MenuItem item ) {
        if ( item.getItemId() == Menu.FIRST ) {
            openDialog();
        }
        return true;
    }

    public void openDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder( this );
        builder.setTitle( "Exit" );
        builder.setMessage( "Are you sure you want to exit?" );
        builder.setPositiveButton( "Yes", ( dialog, id ) -> finishAffinity() );
        builder.setNegativeButton( "No", ( dialog, id ) -> dialog.cancel() );
        builder.show();
    }

    public void goToGameActivity() {
        startActivity( new Intent( this, GameActivity.class ) );
    }

    public void goToConfigActivity() {
        startActivity( new Intent( this, SettingsActivity.class ) );
    }
}
