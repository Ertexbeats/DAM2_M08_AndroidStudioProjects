package org.proven.joctriler;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends Activity {
    ImageView ivCube1, ivCube2, ivCube3;
    Button bStart;
    TextView tvInfo;
    View.OnClickListener listener;

    int positionBall = 0;
    boolean playing = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joc_layout);
        // Instantiate Elements
        instantiateElements();
        // init game
        initGame();
        // Prepare Listener
        prepareListener();
        // Add Listener to Elements
        addListenerToElements();

    }
    @SuppressLint("SetTextI18n")
    private void initGame() {
        // init state Cubes
        ivCube1.setImageResource(R.drawable.cubilete);
        ivCube2.setImageResource(R.drawable.cubilete);
        ivCube3.setImageResource(R.drawable.cubilete);
        // set Position Ball 0, 1, 2
        Random rand = new Random();
        positionBall = rand.nextInt(3);
        Log.d("POSITION BALL: ", positionBall + "");
        // positionBall = rand.nextInt() % 3;
        // bStart.setVisibility(View.INVISIBLE);
        playing = true;
        bStart.setVisibility(View.INVISIBLE);
        tvInfo.setText(R.string.playing);
    }

    private void instantiateElements() {
        tvInfo = (TextView) findViewById(R.id.tvinfo);
        bStart = (Button) findViewById(R.id.bstart);
        ivCube1 = (ImageView) findViewById(R.id.ivcube1);
        ivCube2 = (ImageView) findViewById(R.id.ivcube2);
        ivCube3 = (ImageView) findViewById(R.id.ivcube3);
    }

    private void prepareListener() {
        listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // CUBE 1
                if (view.getId() == R.id.ivcube1) {
                    if (playing) {
                        playing = false;
                        bStart.setVisibility(View.VISIBLE);
                        if (positionBall == 0) {
                            // ha encertat
                            tvInfo.setText(R.string.win);
                            // Mostrar Coco amb pilota
                            ivCube1.setImageResource(R.drawable.levantaok);
                        } else {
                            // No ha encertat
                            tvInfo.setText(R.string.lose);
                            // Coco sense pilota
                            ivCube1.setImageResource(R.drawable.levantafail);
                        }
                    }
                } // CUBE 2
                 else if (view.getId() == R.id.ivcube2) {
                    if (playing) {
                        playing = false;
                        bStart.setVisibility(View.VISIBLE);
                        if (positionBall == 1) {
                            // ha encertat
                            tvInfo.setText(R.string.win);
                            // Mostrar Coco amb pilota
                            ivCube2.setImageResource(R.drawable.levantaok);
                        } else {
                            // No ha encertat
                            tvInfo.setText(R.string.lose);
                            // Coco sense pilota
                            ivCube2.setImageResource(R.drawable.levantafail);
                        }
                    }
                } // CUBE 3
                  else if (view.getId() == R.id.ivcube3) {
                    if (playing) {
                        playing = false;
                        bStart.setVisibility(View.VISIBLE);
                        if (positionBall == 2) {
                            // ha encertat
                            tvInfo.setText(R.string.winplay);
                            // Mostrar Coco amb pilota
                            ivCube3.setImageResource(R.drawable.levantaok);
                        } else {
                            // No ha encertat
                            tvInfo.setText(R.string.lose);
                            // Coco sense pilota
                            ivCube3.setImageResource(R.drawable.levantafail);
                        }
                    }
                } // PLAY
                  else if (view.getId() == R.id.bstart) {
                    if(!playing) {
                        initGame();

                    }
                }
            }
        };
    }

    private void addListenerToElements() {
        ivCube1.setOnClickListener(listener);
        ivCube2.setOnClickListener(listener);
        ivCube3.setOnClickListener(listener);
        bStart.setOnClickListener(listener);
    }


    public void addCubeLayout() {
        ImageView iv = new ImageView(this);
        iv.setLayoutParams(ivCube1.getLayoutParams());
        iv.setImageResource(R.drawable.cubilete);
        LinearLayout l = findViewById(R.id.layoutppal);
        l.addView(iv, iv.getLayoutParams());
    }
}