package org.proven.gamecards;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ConfigActivity extends Activity {

    TextView tvName;
    EditText etName;
    RadioButton rbDifficult, rbMedium, rbEasy;
    RadioGroup rgLevel;
    RadioGroup.OnCheckedChangeListener rgLevelListener;
    CheckBox checkBox;
    LinearLayout llBackground;
    Spinner spinner;
    SeekBar seekBar;
    SeekBar.OnSeekBarChangeListener seekBarListener;
    Button bSend, bReturn;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.config_layout );

        instanciar();
        prepareListenerRadio();
        prepareListenerCheckBox();
        fillSpinnerSelect();
        prepareListenerSeekBar();

        prepareListenerSendButton();
        prepareListenerReturnButton();

    }

    public void instanciar() {
        tvName = findViewById( R.id.tvName );
        etName = findViewById( R.id.etName );
        rgLevel = findViewById( R.id.rgLevel );
        rbDifficult = findViewById( R.id.rbDifficult );
        rbMedium = findViewById( R.id.rbMedium );
        //.setChecked( true );
        rbEasy = findViewById( R.id.rbEasy );
        checkBox = findViewById( R.id.checkBox );
        spinner = findViewById( R.id.spinner );
        seekBar = findViewById( R.id.seekBar );
        bSend = findViewById( R.id.bSend );
        bReturn = findViewById( R.id.bReturn );
    }

    private void prepareListenerRadio() {
        rgLevelListener = new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged( RadioGroup group, int checkedId ) {
                switch ( checkedId ) {
                    case R.id.rbDifficult:
                        showMessage( "Difficult" );
                        break;
                    case R.id.rbMedium:
                        showMessage( "Medium" );
                        break;
                    case R.id.rbEasy:
                        showMessage( "Easy" );
                        break;
                }
            }
        };
        rgLevel.setOnCheckedChangeListener( rgLevelListener );
    }

    private void prepareListenerCheckBox() {
        checkBox.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                showMessage( "Change Background" );
                //llBackground.setBackgroundColor( getColor( R.color.green ));
            }
        } );
    }

    private void fillSpinnerSelect() {
        String[] spinneritems = new String[]{ "Spaguetti", "Hamburger", "Hot Dog" };
        ArrayAdapter adapter = new ArrayAdapter( this, android.R.layout.simple_spinner_item, spinneritems );
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        spinner.setAdapter( adapter );
    }

    private void prepareListenerSeekBar() {
        seekBarListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged( SeekBar seekBar, int progress, boolean fromUser ) {
                tvName.setText( "Progess changed: " + progress );
            }

            @Override
            public void onStartTrackingTouch( SeekBar seekBar ) {
                tvName.setText( "Progress START: " + seekBar.getProgress() );
            }

            @Override
            public void onStopTrackingTouch( SeekBar seekBar ) {
                tvName.setText( "Progress STOP: " + seekBar.getProgress() );
            }
        };
        seekBar.setOnSeekBarChangeListener( seekBarListener );
    }


    private void prepareListenerSendButton() {
        bSend.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                sendParamsGameActivity();
            }
        } );
    }

    private void sendParamsGameActivity() {
        Intent intent = new Intent( this, GameActivity.class );
        Bundle bundle = new Bundle();
        bundle.putString( "name", etName.getText().toString() );
        switch ( rgLevel.getCheckedRadioButtonId() ) {
            case R.id.rbDifficult:
                bundle.putString( "level", "Difficult" );
                break;
            case R.id.rbMedium:
                bundle.putString( "level", "Medium" );
                break;
            case R.id.rbEasy:
                bundle.putString( "level", "Easy" );
                break;
            default:
                bundle.putString( "level", "None" );
                break;
        }
        bundle.putBoolean( "background", checkBox.isChecked() );
        switch ( spinner.getSelectedItem().toString() ) {
            case "Spaguetti":
                bundle.putString( "food", "Spaguetti" );
                break;
            case "Hamburger":
                bundle.putString( "food", "Hamburger" );
                break;
            case "Hot Dog":
                bundle.putString( "food", "Hot Dog" );
                break;
            default:
                bundle.putString( "food", "None" );
                break;
        }

        bundle.putInt( "progress", seekBar.getProgress() );
        intent.putExtras( bundle );

        SharedPreferences sharedPreferences = this.getSharedPreferences( "dades", MODE_PRIVATE );
        SharedPreferences.Editor edit = sharedPreferences.edit();

        edit.putString( "spinner", spinner.getSelectedItem().toString() );
        edit.putString( "name", etName.getText().toString() );
        edit.commit();


        startActivity( intent );
    }

    private void prepareListenerReturnButton() {
        bReturn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                goToHomeActivity();
            }
        } );
    }

    private void prepareListenerGameButton() {
        bReturn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                goToGameActivity();
            }
        } );
    }

    public void goToHomeActivity() {
        startActivity( new Intent( this, HomeActivity.class ) );
    }

    public void goToGameActivity() {
        startActivity( new Intent( this, GameActivity.class ) );
    }


    private void showMessage( String msg ) {
        Toast.makeText( getApplicationContext(), msg, Toast.LENGTH_SHORT ).show();
    }
}