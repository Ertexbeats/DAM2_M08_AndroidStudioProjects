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

public class GameMVC6CubesActivity extends Activity {

    List<ImageView> ivListCube;
    Button bStart;
    TextView tvInfo;
    View.OnClickListener listener;
    ModelGame modelGame;
    static int SIZE = 6;


    @Override
    public void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.joc_6cubes_layout);

        // Init Elements (Inicialitzar variables,
        //                 Crear el Model de JOC)
        initElements();
        prepareListener();
        // Add Listener to Elements
        addListenerToElements();
    }

    private void addListenerToElements() {
        bStart.setOnClickListener(listener);
        for (ImageView iv: ivListCube) {
            iv.setOnClickListener(listener);
        }
    }

    private void prepareListener() {
        listener = new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(modelGame.getStateGame() == ModelGame.STATE_GAME_USER) {
                    for(int i=0; i < ivListCube.size();i++) {
                        ImageView iv = ivListCube.get(i);
                        if(view.getId() == iv.getId()) {
                            modelGame.setStateGame(ModelGame.STATE_GAME_MACHINE);
                            checkResultActivity(i);
                        }
                    }
                }else  if(modelGame.getStateGame() == ModelGame.STATE_GAME_MACHINE) {
                    if (view.getId() == R.id.bstart) {
                        modelGame.setStateGame(ModelGame.STATE_GAME_USER);
                        initGame();
                        Log.d("EVENT", "bstart");
                    }
                }
            }
        };
    }

    private void checkResultActivity(int positionUser) {
        modelGame.setPositionUserBall(positionUser);
        if(modelGame.checkResult()) {
            tvInfo.setText(R.string.win);
            ivListCube.get(positionUser).setImageResource(R.drawable.levantaok);
        }else{
            tvInfo.setText(R.string.lose);
            ivListCube.get(positionUser).setImageResource(R.drawable.levantafail);
        }

    }

    private void initGame() {
        modelGame.initGame(SIZE);
        for(ImageView iv : ivListCube) {
            iv.setImageResource(R.drawable.cubilete);
        }
        //bStart.setVisibility(View.INVISIBLE);
        tvInfo.setText((R.string.playing));
    }

    private void initElements() {
        modelGame = new ModelGame();
        modelGame.setStateGame(ModelGame.STATE_GAME_MACHINE);
        ivListCube = new ArrayList<ImageView>();
        ivListCube.add(findViewById(R.id.ivcube1));
        ivListCube.add(findViewById(R.id.ivcube2));
        ivListCube.add(findViewById(R.id.ivcube3));
        ivListCube.add(findViewById(R.id.ivcube4));
        ivListCube.add(findViewById(R.id.ivcube5));
        ivListCube.add(findViewById(R.id.ivcube6));
        tvInfo = (TextView) findViewById(R.id.tvinfo);
        bStart = (Button) findViewById(R.id.bstart);
        tvInfo.setText(R.string.start_game);
    }

    // Mètode Init Game
    // --> cridarem quan premi Start
    // bStart.setVisibility(View.INVISIBLE);
    // bStart.setVisibility(View.VISIBLE);


    public void readDinamicallyElementsLayout() {
        LinearLayout l = (LinearLayout)  findViewById(R.id.layoutCubes);
        for(int i=0; i < l.getChildCount(); i++) {
            Object o = l.getChildAt(i);
            System.out.println(i + " GETCLASS: " + o.getClass().getSimpleName());
            if(o.getClass().getSimpleName() == "ImageView") {
                System.out.println(((View) o).getTransitionName());
            }


        }
    }
}

