package org.proven.blackjack;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.proven.blackjack.model.Model;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {
    Model modelGame;
    ImageView ivDCard1, ivDCard2, ivPCard1, ivPCard2;
    TextView tvDBorder, tvPBorder, tvValueMoney, tvValueBet, tvCenter, tvPlayer, tvTittle1, tvTittle2;
    Button bStand, bCard, bRestart;
    LinearLayout llBackground, DLlayout, PLlayout;
    View.OnClickListener listener;
    int MENU_SETTINGS = 0;
    int MENU_EXIT = 1;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.game_layout );
        // Game
        modelGame = new Model();
        instantiateElements();
        prepareListener();
        addElementsToListener();
        readParamsFrom();

        startGame();

    }

    private void restartGame() {
        modelGame.restartGame();
        readParamsFrom();
        drawSceneGame();
    }

    private void startGame() {
        modelGame.startGame();
        drawSceneGame();
    }


    private void drawSceneGame() {
        // Show Money
        tvValueMoney.setText( String.valueOf( modelGame.getMoney() ) );
        // Show Bet
        tvValueBet.setText( String.valueOf( modelGame.getBet() ) );
        // Lista card player
        // get player Cards
        ArrayList< Card > playerCards = modelGame.getPlayerCcards();
        //Vaciar el linear Layout
        PLlayout.removeAllViews();
        // Add Cards
        for ( Card c : playerCards ) {
            addCardPlayerLayout( c );
        }
        // Show Player Points
        int playerPoints = modelGame.sumPlayerPointsCards( playerCards );
        tvPBorder.setText( String.valueOf( playerPoints ) );

        // Lista card Dealer
        // Get Dealer Cards
        ArrayList< Card > dealerCards = modelGame.getDealerCards();
        // Vaciar el linear Layout
        DLlayout.removeAllViews();
        // Add Cards
        for ( Card c : dealerCards ) {
            addCardDealerLayout( c );
        }
        // Show Dealer Points
        int dealerPoints = modelGame.sumDealerPointsCards( dealerCards );
        tvDBorder.setText( String.valueOf( dealerPoints ) );
    }

    public void addCardPlayerLayout( Card c ) {
        ImageView iv = new ImageView( this );
        iv.setLayoutParams( ivPCard1.getLayoutParams() );    // get Params of another ImageView of Layout
        iv.setImageBitmap( generateCard( c.getCategory(), c.getValue() ) );
        PLlayout.addView( iv, iv.getLayoutParams() );
    }

    public void addCardDealerLayout( Card c ) {
        ImageView iv = new ImageView( this );
        iv.setLayoutParams( ivDCard2.getLayoutParams() );    // get Params of another ImageView of Layout
        iv.setImageBitmap( generateCard( c.getCategory(), c.getValue() ) );
        DLlayout.addView( iv, iv.getLayoutParams() );
    }


    private void instantiateElements() {
        // Instantiate LinearLayout
        llBackground = ( LinearLayout ) findViewById( R.id.llBackground );
        DLlayout = ( LinearLayout ) findViewById( R.id.DLlayout );
        PLlayout = ( LinearLayout ) findViewById( R.id.PLlayout );
        // Instantiate Cards
        ivDCard1 = ( ImageView ) findViewById( R.id.ivDCard1 );
        ivDCard2 = ( ImageView ) findViewById( R.id.ivDCard2 );
        ivPCard1 = ( ImageView ) findViewById( R.id.ivPCard1 );
        ivPCard2 = ( ImageView ) findViewById( R.id.ivPCard2 );
        // Instantiate Texts
        tvTittle1 = ( TextView ) findViewById( R.id.tvTittle1 );
        tvTittle2 = ( TextView ) findViewById( R.id.tvTittle2 );
        tvPlayer = ( TextView ) findViewById( R.id.tvPlayer );
        tvDBorder = ( TextView ) findViewById( R.id.tvDBorder );
        tvPBorder = ( TextView ) findViewById( R.id.tvPBorder );
        tvValueMoney = ( TextView ) findViewById( R.id.tvValueMoney );
        tvValueBet = ( TextView ) findViewById( R.id.tvValueBet );
        tvCenter = ( TextView ) findViewById( R.id.tvCenter );
        // Instantiate Buttons
        bStand = ( Button ) findViewById( R.id.bStand );
        bCard = ( Button ) findViewById( R.id.bCard );
        bRestart = ( Button ) findViewById( R.id.bRestart );
    }

    private void prepareListener() {
        listener = new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                if ( v.getId() == bCard.getId() ) {
                    if ( modelGame.getPlayerCcards().size() < 2 ) {
                        modelGame.startGame();
                    } else {
                        modelGame.addPlayerCard( new Card( modelGame.valueRamdon(), modelGame.categoryRamdon() ) );
                    }
                    drawSceneGame();
                } else if ( v.getId() == bRestart.getId() ) {
                    restartGame();
                    drawSceneGame();
                }
            }
        };
    }

    private void addElementsToListener() {
        bCard.setOnClickListener( listener );
        bStand.setOnClickListener( listener );
        bRestart.setOnClickListener( listener );
    }

    private void readParamsFrom() {
        SharedPreferences sp = this.getSharedPreferences( "dadespreferences", MODE_PRIVATE );

        String name = sp.getString( "name", "Player" );
        tvPlayer.setText( name );

        int money = sp.getInt( "money", 150 );
        modelGame.setMoney( money );


        int bet = sp.getInt( "bet", 10 );
        modelGame.setBet( bet );

        String background = sp.getString( "background", "green" );
        if ( background.equals( "green" ) ) {
            llBackground.setBackgroundResource( R.drawable.background );
        } else if ( background.equals( "brown" ) ) {
            llBackground.setBackgroundResource( R.drawable.background_brown );
        }

        boolean showLogo = sp.getBoolean( "showLogo", true );
        if ( showLogo ) {
            makeTittleVISIBLE();
        } else {
            makeTittleINVISIBLE();
        }
    }


    /**
     * @param bottomImage Background image
     * @param topImage    Front image merged in top
     * @param left        Position X of top
     * @param top         Position Y of top
     * @return Overlay    Merged Image
     */
    public Bitmap mergeBitmapImage( Bitmap bottomImage, Bitmap topImage, Bitmap numberValue, int left, int top ) {
        Bitmap overlay = Bitmap.createBitmap( bottomImage.getWidth(), bottomImage.getHeight(), bottomImage.getConfig() );
        Canvas canvas = new Canvas( overlay );
        canvas.drawBitmap( bottomImage, new Matrix(), null );
        canvas.drawBitmap( topImage, left, top, null );
        canvas.drawBitmap( numberValue, 160, 80, null );
        canvas.drawBitmap( numberValue, 800, 1250, null );
        return overlay;
    }

    private Bitmap generateCard( Category cat, int value ) {
        Bitmap bimaCFront = BitmapFactory.decodeResource( getResources(), R.drawable.front );
        Bitmap bimaCategory = getBitmapCategory( cat );
        Bitmap bimaValue = getBitmapvalue( value );
        int left = ( bimaCFront.getWidth() - bimaCategory.getWidth() ) / 2;
        int top = ( bimaCFront.getHeight() - bimaCategory.getHeight() ) / 2;
        Bitmap overlay = mergeBitmapImage( bimaCFront, bimaCategory, bimaValue, left, top );
        return overlay;
    }

    public Bitmap getBitmapCategory( Category category ) {
        Bitmap bm = null;
        switch ( category ) {
            case HEARTS:
                bm = BitmapFactory.decodeResource( getResources(), R.drawable.hearts );
                break;
            case DIAMONDS:
                bm = BitmapFactory.decodeResource( getResources(), R.drawable.diamond );
                break;
            case CLUBS:
                bm = BitmapFactory.decodeResource( getResources(), R.drawable.clubs );
                break;
            case SPADES:
                bm = BitmapFactory.decodeResource( getResources(), R.drawable.spades );
                break;
            default:
                bm = BitmapFactory.decodeResource( getResources(), R.drawable.front );
                break;
        }
        return bm;
    }

    public Bitmap getBitmapvalue( int value ) {
        Bitmap bm = null;
        switch ( value ) {
            case 0:
                bm = BitmapFactory.decodeResource( getResources(), R.drawable.a );
                break;
            case 1:
                bm = BitmapFactory.decodeResource( getResources(), R.drawable.c1 );
                break;
            case 2:
                bm = BitmapFactory.decodeResource( getResources(), R.drawable.c2 );
                break;
            case 3:
                bm = BitmapFactory.decodeResource( getResources(), R.drawable.c3 );
                break;
            case 4:
                bm = BitmapFactory.decodeResource( getResources(), R.drawable.c4 );
                break;
            case 5:
                bm = BitmapFactory.decodeResource( getResources(), R.drawable.c5 );
                break;
            case 6:
                bm = BitmapFactory.decodeResource( getResources(), R.drawable.c6 );
                break;
            case 7:
                bm = BitmapFactory.decodeResource( getResources(), R.drawable.c7 );
                break;
            case 8:
                bm = BitmapFactory.decodeResource( getResources(), R.drawable.c8 );
                break;
            case 9:
                bm = BitmapFactory.decodeResource( getResources(), R.drawable.c9 );
                break;
            case 10:
                bm = BitmapFactory.decodeResource( getResources(), R.drawable.c10 );
                break;
            case 11:
                bm = BitmapFactory.decodeResource( getResources(), R.drawable.j );
                break;
            case 12:
                bm = BitmapFactory.decodeResource( getResources(), R.drawable.q );
                break;
            case 13:
                bm = BitmapFactory.decodeResource( getResources(), R.drawable.k );
                break;
            default:
                bm = BitmapFactory.decodeResource( getResources(), R.drawable.front );
                break;
        }
        return bm;
    }

    private void makeTittleVISIBLE() {
        tvTittle1.setVisibility( View.VISIBLE );
        tvTittle2.setVisibility( View.VISIBLE );
    }

    private void makeTittleINVISIBLE() {
        tvTittle1.setVisibility( View.INVISIBLE );
        tvTittle2.setVisibility( View.INVISIBLE );
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {
        super.onCreateOptionsMenu( menu );
        // Nivell del Menú, ItemId, Ordre dins del menú, Text
        menu.add( 0, MENU_SETTINGS, Menu.NONE, "Settings" );
        menu.add( 0, MENU_EXIT, Menu.NONE, "Exit" );
        return true;
    }

    // Handles item selections
    @Override
    public boolean onOptionsItemSelected( MenuItem item ) {
        if ( item.getItemId() == MENU_SETTINGS ) {
            goToSettingsActivity();
        } else if ( item.getItemId() == MENU_EXIT ) {
            openDialog();
        }
        return true;
    }

    private void goToSettingsActivity() {
        Intent intent = new Intent( this, SettingsActivity.class );
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
