package org.proven.joctriler;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GameMVCActivity extends Activity {

    ImageView ivCube[];
    Button bStart;
    TextView tvInfo;
    View.OnClickListener listener;
    ModelGame modelGame;
    static int SIZE = 3;


    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.joc_layout3);

        // Init Elements (Inicialitzar variables,
        //                 Crear el Model de JOC)
        initElements();
        prepareListener();
        // Add Listener to Elements
        addListenerToElements();

    }

    private void addListenerToElements() {
        bStart.setOnClickListener(listener);
        for (ImageView iv : ivCube) {
            iv.setOnClickListener(listener);
        }
    }

    private void prepareListener() {
        listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (modelGame.getStateGame() == ModelGame.STATE_GAME_USER) {
                    if (view.getId() == R.id.ivcube1) {
                        modelGame.setStateGame(ModelGame.STATE_GAME_MACHINE);
                        checkResult(0);
                        Log.d("EVENT", "ivcube1");
                    } else if (view.getId() == R.id.ivcube2) {
                        modelGame.setStateGame(ModelGame.STATE_GAME_MACHINE);
                        checkResult(1);
                        Log.d("EVENT", "ivcube2");
                    } else if (view.getId() == R.id.ivcube3) {
                        modelGame.setStateGame(ModelGame.STATE_GAME_MACHINE);
                        checkResult(2);
                        Log.d("EVENT", "ivcube3");
                    }
                } else if (modelGame.getStateGame() == ModelGame.STATE_GAME_MACHINE) {
                    if (view.getId() == R.id.bstart) {
                        modelGame.setStateGame(ModelGame.STATE_GAME_USER);
                        initGame();
                        Log.d("EVENT", "bstart");
                    }
                }
            }
        };
    }

    private void checkResult(int positionUser) {
        modelGame.setPositionUserBall(positionUser);
        if (modelGame.checkResult()) {
            tvInfo.setText(R.string.win);
            ivCube[positionUser].setImageResource(R.drawable.levantaok);
        } else {
            tvInfo.setText(R.string.lose);
            ivCube[positionUser].setImageResource(R.drawable.levantafail);
        }

    }

    private void initGame() {
        modelGame.initGame(SIZE);
        for (ImageView iv : ivCube) {
            iv.setImageResource(R.drawable.cubilete);
        }
        //bStart.setVisibility(View.INVISIBLE);
        tvInfo.setText((R.string.playing));
    }

    private void initElements() {
        modelGame = new ModelGame();
        modelGame.setStateGame(ModelGame.STATE_GAME_MACHINE);
        ivCube = new ImageView[3];
        ivCube[0] = findViewById(R.id.ivcube1);
        ivCube[1] = findViewById(R.id.ivcube2);
        ivCube[2] = findViewById(R.id.ivcube3);
        tvInfo = (TextView) findViewById(R.id.tvinfo);
        bStart = (Button) findViewById(R.id.bstart);
        tvInfo.setText(R.string.start_game);
    }

    // Mètode Init Game
    // --> cridarem quan premi Start
    // bStart.setVisibility(View.INVISIBLE);
    // bStart.setVisibility(View.VISIBLE);


    public void readDinamicallyElementsLayout() {
        LinearLayout l = (LinearLayout) findViewById(R.id.layoutCubes);
        for (int i = 0; i < l.getChildCount(); i++) {
            Object o = l.getChildAt(i);
            System.out.println(i + " GETCLASS: " + o.getClass().getSimpleName());
            if (o.getClass().getSimpleName() == "ImageView") {
                System.out.println(((View) o).getTransitionName());
            }


        }
    }
}

