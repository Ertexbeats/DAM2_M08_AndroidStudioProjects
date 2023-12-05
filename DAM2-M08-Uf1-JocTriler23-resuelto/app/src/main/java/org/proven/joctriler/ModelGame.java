package org.proven.joctriler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ModelGame {
    public static int STATE_GAME_MACHINE = 0;
    public static int STATE_GAME_USER = 1;

    // Data structure
    // static int SIZE = 3;
    List<Boolean> listCube;
    int positionUserBall;
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
     *
     * @params numCubes -> number cubes of list
     */
    public void initGame(int numCubes) {
        // Generate List Cubes ( False )
        // Cubs a FALSE
        listCube.clear();
        for (int i = 0; i < numCubes; i++) {
            Boolean b = new Boolean(Boolean.FALSE);
            listCube.add(b);
        }
        // set Random position of ball
        setTiradaMachine(getRandomBall());
    }

    private int getRandomBall() {
        Random r = new Random();
        return r.nextInt(listCube.size());
    }

    /**
     * Puts true in arrayCube Cube user Ups
     *
     * @param position user Ups Cube
     */
    public void setTiradaMachine(int position) {
        if (position >= 0 && position < listCube.size()) {
            //arrCube[position] = true;
            Boolean b = new Boolean(Boolean.TRUE);
            listCube.set(position, b);
        }
    }

    /**
     * @return true if positioBall == Cube Ups true in arrCube
     */
    public boolean checkResult() {
        boolean bwin = false;
/*
        // if(arrCube[getPositionBall()]) {
        if (listCube == true) {
            bwin = true;
        }
        return bwin;*/
        if (getPositionUserBall() >= 0 && getPositionUserBall() < listCube.size()) {
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


    public static void main(String argv[]) {
        ModelGame m = new ModelGame();
        m.initGame(3);
        // Cub que aixeca
        m.setPositionUserBall(2);
        for (int i = 0; i < m.listCube.size(); i++) {
            System.out.println("Position Ball:" + m.listCube.get(i).booleanValue());
        }
        System.out.println(m.checkResult());
    }
}
