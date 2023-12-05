package org.proven.blackjack.model;

import org.proven.blackjack.Card;

import java.util.ArrayList;
import java.util.List;

public class ModelV2 {
    private int bet, money, pointsCard;
    private List< Card > listDealer, listPlayer;

    public ModelV2() {
        //pointsPlayer = new int[2];
        //pointsDealer = new int[2];
        pointsCard = 0;
        bet = 0;
        money = 500;
        listDealer = new ArrayList< Card >();
        listPlayer = new ArrayList< Card >();
    }

    public int getPointsCard() {
        return pointsCard;
    }

    public void setPointsCard( int pointsCard ) {
        this.pointsCard = pointsCard;
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

    public void restartGame() {
        setBet( 0 );
        //setMoney(getMoney());

        listDealer.clear();
        listPlayer.clear();
        startGame();
    }

    public void startGame() {
        listDealer.add( new Card() );
        listDealer.add( new Card() );
        listPlayer.add( new Card() );
        listPlayer.add( new Card() );

    }

    public List< Card > getListDealer() {
        pedirCarta( false );
        return listDealer;
    }

    public void setListDealer( List< Card > listDealer ) {
        this.listDealer = listDealer;
    }

    public List< Card > getListPlayer() {
        pedirCarta( true );
        return listPlayer;
    }

    public void setListPlayer( List< Card > listPlayer ) {
        this.listPlayer = listPlayer;
    }

    public void pedirCarta( boolean esJugador ) {
        if ( esJugador ) {
            listPlayer.add( new Card() );
        } else {
            listDealer.add( new Card() );
        }
    }

    public void incremetBet( int credito ) {
        setBet( getBet() + credito );
    }

    public void decremetMoney() {
        setMoney( getMoney() - getBet() );
    }

    public void winMoney() {
        setMoney( getMoney() + ( getBet() * 2 ) );
    }

    public int sumPlayerDealer( List< Card > listCards ) {
        pointsCard = 0;
        for ( Card card : listCards ) {
            if ( card.getValue() == 0 ) {
                pointsCard += 1;
            } else if ( card.getValue() > 10 ) {
                pointsCard += 10;
            } else {
                pointsCard += card.getValue();
            }
        }
        return pointsCard;
    }

    public int sumStart( int value1, int value2 ) {
        if ( value1 > 10 && value2 > 10 ) {
            return 20;
        } else if ( value1 == 0 && value2 == 0 ) {
            return 2;
        } else if ( value1 > 10 ) {
            return 10 + value2;
        } else if ( value2 > 10 ) {
            return value1 + 10;
        } else {
            return value1 + value2;
        }
    }
}