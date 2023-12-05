package org.proven.joctriler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ModelGame {
    public static int STATE_GAME_MACHINE = 0;
    public static int STATE_GAME_USER = 1;

    // Data structure
    //static int SIZE = 3;
    List<Boolean> listCube;
    int positionUserBall;  // Cube Selects User
    int stateGame;

    public ModelGame() {
        listCube = new ArrayList<Boolean>();
        positionUserBall = -1;
        setStateGame(STATE_GAME_MACHINE);
    }

    public int getStateGame() {
        return stateGame;
    }

    public void setStateGame(int stateGame) {
        this.stateGame = stateGame;
    }

    /**
     * Create List of Cubes with 1 Ball (True)
     * @param numCubes  -> Number cubes of List
     */
    public void initGame(int numCubes) {
        // Generate List Cubes  ( False)
        listCube.clear();
        for (int i = 0; i < numCubes; i++) {
            Boolean b = new Boolean(Boolean.FALSE);
            listCube.add(b);

        }
        // set Random position of ball
        setTiradaMachine( getRandomBall() );
    }

    private int getRandomBall() {
        Random r = new Random();
        return r.nextInt(listCube.size());
    }

    /**
     * Puts true in arrayCube Cube user Ups
     * @param position user Ups Cube
     */
    public void setTiradaMachine(int position) {
        if(position >=0 && position < listCube.size()) {
            Boolean b = new Boolean(Boolean.TRUE);
            listCube.set(position,b);
        }
    }

    /**
     *  Check if User Wins
     * @return true if positioBall == Cube Ups true in arrCube
     */
    public boolean checkResult() {
        boolean bwin = false;
        if(getPositionUserBall() >=0
                && getPositionUserBall() < listCube.size()) {
            bwin = listCube.get(getPositionUserBall()).booleanValue();
        }
        return bwin;
    }

    public int getPositionUserBall() {
        return positionUserBall;
    }

    public void setPositionUserBall(int positionUserBall) {
        this.positionUserBall = positionUserBall;
    }

    public List<Boolean> getListCube() {
        return listCube;
    }

    public int getPositionMachineBall() {
        int pos  = -1;
        for(int i=0; i< getListCube().size(); i++) {
            if( getListCube().get(i).booleanValue() == true) {
                pos = i;
            }
        }
        return pos;
    }

    public static void main(String argv[]) {
        ModelGame m = new ModelGame();

        m.setStateGame(ModelGame.STATE_GAME_MACHINE);
        m.initGame(5);
        m.setStateGame(STATE_GAME_USER);
        m.setPositionUserBall(2);   // Cub que aixeca
        for(int i=0;i< m.listCube.size(); i++) {
            System.out.println("Position Ball:" +
                    m.listCube.get(i).booleanValue());
        }
        System.out.println(m.checkResult());
    }
}
