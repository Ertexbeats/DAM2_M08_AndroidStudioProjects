package org.proven.menuexemple;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

public class MyOptionsMenu extends AppCompatActivity {

    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
    }

    // Creates the menu items
    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {
        super.onCreateOptionsMenu( menu );
        // Nivell del Menú, ItemId, Ordre dins del menú, Text
        menu.add( 0, Menu.FIRST, Menu.NONE, "Game" );
        menu.add( 0, Menu.FIRST + 1, Menu.NONE, "Settings" );
        menu.add( 0, Menu.FIRST + 2, Menu.NONE, "Quit" );
        return true;
    }

    // Handles item selections
    @Override
    public boolean onOptionsItemSelected( MenuItem item ) {
        if ( item.getItemId() == Menu.FIRST ) {
            goToMainActivity();
        } else if ( item.getItemId() == Menu.FIRST + 1 ) {

        } else if ( item.getItemId() == Menu.FIRST + 2 ) {
            // QUIT METHOD
            finishAffinity(); // close all activities
            finish(); // close the actual activity
        }
        return true;
    }

    private void goToMainActivity() {
        Intent intent = new Intent( this, MainActivity.class );
        startActivity( intent );
    }
}
