package org.proven.blackjack;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {
    EditText SetName, SetMoney;
    SeekBar SsbMinBet;
    TextView StvMinBetDisplay, tvTittle1, tvTittle2;
    RadioGroup SrgBackground;
    RadioButton SrbGreen, SrbBrown;
    CheckBox ScbShowLogo;
    Button SbCancel, SbOk;
    LinearLayout llBackground;
    SeekBar.OnSeekBarChangeListener SsbMinBetListener;
    RadioGroup.OnCheckedChangeListener SrgBackgroundListener;
    int MENU_GAME = 0;
    int MENU_EXIT = 1;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.settings_layout );

        instantiate();
        prepareListenerSeekBar();
        prepareListenerRadioGroup();
        prepareListenerCheckBox();
        prepareListenerButtons();
    }

    public void instantiate() {
        llBackground = findViewById( R.id.llBackground );
        tvTittle1 = findViewById( R.id.tvTittle1 );
        tvTittle2 = findViewById( R.id.tvTittle2 );
        SetName = findViewById( R.id.SetName );
        SetMoney = findViewById( R.id.SetMoney );
        SsbMinBet = findViewById( R.id.SsbMinBet );
        StvMinBetDisplay = findViewById( R.id.StvMinBetDisplay );
        SrgBackground = findViewById( R.id.SrgBackground );
        SrbGreen = findViewById( R.id.SrbGreen );
        SrbBrown = findViewById( R.id.SrbBrown );
        ScbShowLogo = findViewById( R.id.ScbShowLogo );
        SbCancel = findViewById( R.id.SbCancel );
        SbOk = findViewById( R.id.SbOk );
    }

    private void prepareListenerSeekBar() {
        SsbMinBetListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged( SeekBar seekBar, int progress, boolean fromUser ) {
                StvMinBetDisplay.setText( String.valueOf( seekBar.getProgress() ) );
            }

            @Override
            public void onStartTrackingTouch( SeekBar seekBar ) {
                StvMinBetDisplay.setText( String.valueOf( seekBar.getProgress() ) );
            }

            @Override
            public void onStopTrackingTouch( SeekBar seekBar ) {
                StvMinBetDisplay.setText( String.valueOf( seekBar.getProgress() ) );
            }
        };
        SsbMinBet.setOnSeekBarChangeListener( SsbMinBetListener );
    }

    private void prepareListenerRadioGroup() {
        SrgBackgroundListener = ( group, checkedId ) -> {
            if ( checkedId == R.id.SrbGreen ) {
                showMessage( "Background set to Green" );
                llBackground.setBackgroundResource( R.drawable.background );
            } else if ( checkedId == R.id.SrbBrown ) {
                showMessage( "Background set to Brown" );
                llBackground.setBackgroundResource( R.drawable.background_brown );
            }
        };
        SrgBackground.setOnCheckedChangeListener( SrgBackgroundListener );
    }

    private void prepareListenerCheckBox() {
        ScbShowLogo.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                if ( ScbShowLogo.isChecked() ) {
                    showMessage( "Show Logo" );
                    makeTittleVISIBLE();
                } else {
                    showMessage( "Hide Logo" );
                    makeTittleINVISIBLE();
                }
            }
        } );
    }


    private void prepareListenerButtons() {
        SbCancel.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                finish();
            }
        } );
        SbOk.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                sendParamsGameActivity();
            }
        } );
    }

    private void sendParamsGameActivity() {
        Intent intent = new Intent( this, GameActivity.class );
        Bundle bundle = new Bundle();
        bundle.putString( "name", SetName.getText().toString() );
        bundle.putString( "money", SetMoney.getText().toString() );

        bundle.putInt( "minBet", SsbMinBet.getProgress() );
        if ( SrbGreen.isChecked() ) {
            bundle.putString( "background", "green" );
        } else if ( SrbBrown.isChecked() ) {
            bundle.putString( "background", "brown" );
        }

        bundle.putBoolean( "showLogo", ScbShowLogo.isChecked() );
        intent.putExtras( bundle );

        SharedPreferences sharedPreferences = this.getSharedPreferences( "dadespreferences", MODE_PRIVATE );
        SharedPreferences.Editor edit = sharedPreferences.edit();

        edit.putString( "name", SetName.getText().toString() );
        edit.putInt( "money", Integer.parseInt( SetMoney.getText().toString() ) );
        edit.putInt( "minBet", SsbMinBet.getProgress() );
        edit.putString( "background", SrbGreen.isChecked() ? "green" : "brown" );
        edit.putBoolean( "showLogo", ScbShowLogo.isChecked() );
        edit.commit();

        startActivity( intent );
    }

    private void showMessage( String msg ) {
        Toast.makeText( getApplicationContext(), msg, Toast.LENGTH_SHORT ).show();
    }

    private void makeTittleVISIBLE() {
        tvTittle1.setVisibility( View.VISIBLE );
        tvTittle2.setVisibility( View.VISIBLE );
    }

    private void makeTittleINVISIBLE() {
        tvTittle1.setVisibility( View.INVISIBLE );
        tvTittle2.setVisibility( View.INVISIBLE );
    }

    // Creates the menu items
    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {
        super.onCreateOptionsMenu( menu );
        // Nivell del Menú, ItemId, Ordre dins del menú, Text
        menu.add( 0, MENU_GAME, Menu.NONE, "Game" );
        menu.add( 0, MENU_EXIT, Menu.NONE, "Exit" );
        return true;
    }

    // Handles item selections
    @Override
    public boolean onOptionsItemSelected( MenuItem item ) {
        if ( item.getItemId() == MENU_GAME ) {
            goToGameActivity();
        } else if ( item.getItemId() == MENU_EXIT ) {
            openDialog();
        }
        return true;
    }

    private void goToGameActivity() {
        Intent intent = new Intent( this, GameActivity.class );
        startActivity( intent );
    }

    public void openDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder( this );
        builder.setTitle( "Exit" );
        builder.setMessage( "Are you sure you want to exit?" );
        builder.setPositiveButton( "Yes", new DialogInterface.OnClickListener() {
            public void onClick( DialogInterface dialog, int id ) {
                finishAffinity();
            }
        } );
        builder.setNegativeButton( "No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick( DialogInterface dialog, int which ) {
                dialog.cancel();

            }
        } );
        builder.show();
    }
}
