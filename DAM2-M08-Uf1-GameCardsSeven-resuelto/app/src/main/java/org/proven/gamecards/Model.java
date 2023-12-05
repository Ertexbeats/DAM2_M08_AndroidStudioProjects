package org.proven.gamecards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Model {

    static int PLAYING = 1;
    static int WAITING_PLAY = 2;

    int numIntents;
    List<Card> listCards;

    int stateGame;

    public Model() {

        numIntents = 0;
        listCards = new ArrayList<Card>();
    }

    public int getStateGame() {
        return stateGame;
    }

    public void setStateGame(int stateGame) {
        this.stateGame = stateGame;
    }

    public int getNumIntents() {
        return numIntents;
    }

    public void setNumIntents(int numIntents) {
        this.numIntents = numIntents;
    }

    /**
     * Add cards in ArrayList with values
     * 0.5 1 2 3 4 5 6 7 0.5 1 2 3 4 5 6 7 ....
     * cards are not visible
     *
     * @param numCards number of cards added
     */
    public void initCards(int numCards) {
        double value;
        for(int i =0; i < numCards; i++) {
            int temp = i % 8;
            if(temp == 0) {
                value = 0.5;
            } else {
                value = temp;
            }
            Card card = new Card(value,false);
            listCards.add(card);
        }
        // Collections.shuffle(listCards);
    }

    /**
     * Initialize the game with cards
     *
     * @param numCards number of cards to Play
     */
    public void restartGame(int numCards) {
        setNumIntents(0);
        listCards.clear();
        initCards(numCards);
    }


    public void incrementIntent() {
        setNumIntents(getNumIntents() + 1);
    }

    /**
     * Calculate sum of cards visible == true
     *
     * @return Sum of visible cards
     */
    public double getSumCards() {
        double sum = 0;
        for(Card card: listCards) {
            if(card.isVisible()) {
                sum += card.getValue();
            }
        }
        return sum;
    }

    /**
     * Swap state (visible or not) of card by position
     *
     * @param position
     */
    public void swapCard(int position) {
        if(position >=0 && position < listCards.size()) {
            Card c = listCards.get(position);
            c.setVisible(! c.isVisible());
            listCards.set(position, c );
        }
    }

    public Card getCard(int pos) {
        Card c = null;
        if(pos >=0 && pos < listCards.size()) {
            c = listCards.get(pos);
        }
        return c;
    }

    /**
     *
     * @return num of cards in Array / Game
     */
    public int getSizeCards() {
        return listCards.size();
    }

    public static void main(String argv[]){
        Model m = new Model();
        m.restartGame(8);
        m.getCard(3).setVisible(true);
        System.out.println("Suma 1:" + m.getSumCards());
        m.getCard(4).setVisible(true);
        System.out.println("Suma 2:" + m.getSumCards());
        m.getCard(0).setVisible(true);
        System.out.println("Suma 3:" + m.getSumCards());
        m.getCard(4).setVisible(false);
        System.out.println("Suma 4:" + m.getSumCards());
    }




}
