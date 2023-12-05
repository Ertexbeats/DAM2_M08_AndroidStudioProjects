package org.proven.blackjack.model;

import android.util.Log;

import org.proven.blackjack.Card;
import org.proven.blackjack.Category;

import java.util.ArrayList;
import java.util.Scanner;

public class Model {
    Scanner sc = new Scanner( System.in );
    private int money, bet, pointsP, pointsD;
    // ATTRIBUTES
    private ArrayList< Card > playerCcards, dealerCards;

    // CONSTRUCTOR
    public Model() {
        pointsP = 0;
        pointsD = 0;
        bet = 0;
        money = 500;
        playerCcards = new ArrayList<>();
        dealerCards = new ArrayList<>();
    }

    public static void main( String[] args ) {
        Model m = new Model();
        m.startGame();  // add 2 cards player 2 cards dealer
        m.addPlayerCard( new Card( m.valueRamdon(), m.categoryRamdon() ) ); // add card player

        int pPoints = m.sumPlayerPointsCards( m.getPlayerCcards() );  //  get sum points player

        //pPoints = m.sumPlayerPointsCards( m.getPlayerCcards() );  //  get sum points player

        int dPoints = 0;
        while ( pPoints <= 17 ) {
            //m.addDealerCard();
            dPoints = m.sumDealerPointsCards( m.getDealerCards() );  //  get sum points dealer
        }
    }


    // GETTERS & SETTERS
    public int getPointsP() {
        return pointsP;
    }

    public void setPointsP( int pointsP ) {
        this.pointsP = pointsP;
    }

    public int getPointsD() {
        return pointsD;
    }

    public void setPointsD( int pointsD ) {
        this.pointsD = pointsD;
    }

    public int getBet() {
        return bet;
    }

    public void setBet( int bet ) {
        this.bet = bet;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney( int money ) {
        this.money = money;
    }

    public ArrayList< Card > getPlayerCcards() {
        return playerCcards;
    }

    public ArrayList< Card > getDealerCards() {
        return dealerCards;
    }

    public void restartGame() {
        setPointsP( 0 );
        setPointsD( 0 );
        setBet( 50 );
        setMoney( 1000 );
        dealerCards.clear();
        playerCcards.clear();
        startGame();
    }

    public void initCards() {
        Log.i( "initCards", "Generating Player Cards.." );
        addPlayerCard( new Card( valueRamdon(), categoryRamdon() ) );
        addPlayerCard( new Card( valueRamdon(), categoryRamdon() ) );

        Log.i( "initCards", "Generating Dealer Cards.." );
        addDealerCard( new Card( valueRamdon(), categoryRamdon() ) );
        addDealerCard( new Card( valueRamdon(), categoryRamdon(), false ) );
    }

    public void addPlayerCard( Card card ) {
        playerCcards.add( card );
    }

    public void addDealerCard( Card card ) {
        dealerCards.add( card );
    }

    public void startGame() {
        initCards();
    }

    public int sumPlayerPointsCards( ArrayList< Card > Cards ) {
        setPointsP( 0 );
        for ( Card card : Cards ) {
            if ( card.isVisible() == true ) {
                if ( card.getValue() > 10 ) {
                    pointsP += 10;
                } else {
                    pointsP += card.getValue();
                }

            } else if ( card.isVisible() == false ) {
                pointsP += 0;
            }
        }
        Log.i( "ValuePlayer", "Value: " + pointsP );
        return pointsP;
    }

    public int sumDealerPointsCards( ArrayList< Card > Cards ) {
        setPointsD( 0 );
        for ( Card card : Cards ) {
            if ( card.isVisible() == true ) {
                if ( card.getValue() > 10 ) {
                    pointsD += 10;
                } else {
                    pointsD += card.getValue();
                }
            }
        }
        Log.i( "ValueDealer", "Value: " + pointsD );
        return pointsD;
    }

    public Category categoryRamdon() {
        int num = ( int ) ( Math.random() * 4 ) + 1;
        switch ( num ) {
            case 1:
                return Category.HEARTS;
            case 2:
                return Category.DIAMONDS;
            case 3:
                return Category.SPADES;
            case 4:
                return Category.CLUBS;
        }
        return null;
    }

    public int valueRamdon() {
        int num = ( int ) ( Math.random() * 13 ) + 1;
        return num;
    }
}
