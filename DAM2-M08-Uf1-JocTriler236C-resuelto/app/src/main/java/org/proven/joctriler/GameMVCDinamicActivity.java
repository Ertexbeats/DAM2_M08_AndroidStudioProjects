package org.proven.joctriler;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GameMVCDinamicActivity extends Activity {

    List<ImageView> ivListCube;
    ImageView ivAdd;
    Button bStart;
    TextView tvInfo;
    View.OnClickListener listener;
    ModelGame modelGame;
    static int SIZE_INIT = 3;
    int numberCubes;

    @Override
    public void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.joc_layout);

        // Init Elements (Inicialitzar variables,
        //                 Crear el Model de JOC)
        initElements();
        prepareListener();
        // Add Listener to Elements
        addListenerToElements();
    }

    private void addListenerToElements() {
        bStart.setOnClickListener(listener);
        for (ImageView iv: ivListCube  ) {
            iv.setOnClickListener(listener);
        }
    }

    private void prepareListener() {
        listener = new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(modelGame.getStateGame() == ModelGame.STATE_GAME_USER) {
                    ImageView iv;
                    for(int i=0; i< ivListCube.size(); i++) {
                        iv = ivListCube.get(i);
                        if(view.getId() == iv.getId()) {
                            checkResultActivity(i);
                        }

                    }
                }else  if(modelGame.getStateGame() == ModelGame.STATE_GAME_MACHINE) {
                    if (view.getId() == R.id.bstart) {
                        modelGame.setStateGame(ModelGame.STATE_GAME_USER);
                        //setContentView(R.layout.joc_layout);
                        //initElements();
                        initGame();
                        Log.d("EVENT", "bstart");
                    }
                }
            }
        };
    }

    private void checkResultActivity(int positionUser) {
        modelGame.setPositionUserBall(positionUser);
        if(positionUser==3) {
            System.out.println("ERROR per debugger");
        }
        if(modelGame.checkResult()) {
            if(getNumberCubes() == 6) {
                modelGame.setStateGame(ModelGame.STATE_GAME_MACHINE);
                tvInfo.setText(R.string.win);
                bStart.setVisibility(View.VISIBLE);
            }else {
                addCubeLayout();
                setNumberCubes(getNumberCubes() + 1);
                initGameAddCube(getNumberCubes());
                //tvInfo.setText(R.string.win);
                tvInfo.setText("Win - " + modelGame.getPositionMachineBall());
            }
            ivListCube.get(positionUser).setImageResource(R.drawable.levantaok);
        }else{
            modelGame.setStateGame(ModelGame.STATE_GAME_MACHINE);
            tvInfo.setText(R.string.lose);
            ivListCube.get(positionUser).setImageResource(R.drawable.levantafail);
            bStart.setVisibility(View.VISIBLE);
        }

    }

    private void initGame() {
        setNumberCubes(SIZE_INIT);
        modelGame.initGame(getNumberCubes());
        LinearLayout l = findViewById(R.id.layoutppal);
        l.removeAllViews();
        for( ImageView ivCube : ivListCube) {
            ivCube.setImageResource(R.drawable.cubilete);
        }
        bStart.setVisibility(View.INVISIBLE);
        tvInfo.setText(("Playing" + " - "  + modelGame.getPositionMachineBall()));
    }

    private void initGameAddCube(int numCubes) {
        modelGame.initGame(numCubes);
        for( ImageView ivCube : ivListCube) {
            ivCube.setImageResource(R.drawable.cubilete);
        }
        //bStart.setVisibility(View.INVISIBLE);
        tvInfo.setText((R.string.playing + " - "  + modelGame.getPositionMachineBall()));
    }

    private void initElements() {
        setNumberCubes(SIZE_INIT);
        ivListCube = new ArrayList<ImageView>();
        modelGame = new ModelGame();
        modelGame.setStateGame(ModelGame.STATE_GAME_MACHINE);

        ivListCube.add(findViewById(R.id.ivcube1)); // Cube 0
        ivListCube.add(findViewById(R.id.ivcube2)); // Cube 1
        ivListCube.add(findViewById(R.id.ivcube3)); // Cube 2

        tvInfo = (TextView) findViewById(R.id.tvinfo);
        bStart = (Button) findViewById(R.id.bstart);
        tvInfo.setText(R.string.start_game);
    }


    public void addCubeLayout() {
        ivAdd = new ImageView(this);
        ivAdd.setLayoutParams(ivListCube.get(0).getLayoutParams());
        ivAdd.setImageResource(R.drawable.cubilete);
        /******** IMPORTANT ******************/
        ivAdd.setId(getNumberCubes() + 1000 );  // Add UNIQUE IDENTIFIER
        LinearLayout l = findViewById(R.id.layoutppal);
        l.addView(ivAdd, ivAdd.getLayoutParams());

        ivListCube.add(ivAdd);
        //prepareListener();
        ivAdd.setOnClickListener(listener);
    }

    public int getNumberCubes() {
        return numberCubes;
    }

    public void setNumberCubes(int numberCubes) {
        this.numberCubes = numberCubes;
    }
}

