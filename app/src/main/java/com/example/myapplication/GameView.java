package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class GameView extends View {
    private final Bird bird;
    private final Runnable r;

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bird = new Bird();
        bird.setWidth(100*Constants.SCREEN_WIDTH/1080);
        bird.setHeight(100*Constants.SCREEN_WIDTH/1920);
        //Coordonnée de l'oiseau
        bird.setX(100*Constants.SCREEN_WIDTH/1080);
        bird.setY(Constants.SCREEN_HEIGHT/2-bird.getHeight()/2);
        //Création des propriétés de l'oiseau
        ArrayList<Bitmap> arrBms = new ArrayList<>();
        arrBms.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.bird0));
        arrBms.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.bird1));
        bird.setArrBms(arrBms);
        //android.os
        Handler handler = new Handler() {
            @Override
            public void publish(LogRecord record) {

            }

            @Override
            public void flush() {

            }

            @Override
            public void close() throws SecurityException {

            }
        };
        r = new Runnable() {
            @Override
            public void run() {
                invalidate();  //pour update the methode draw
            }
        };

    }


    public void draw(Canvas canvas)
    {
        super.draw(canvas);
        bird.draw(canvas); //On appele la methode qui dessine l'oiseau
        getHandler().postDelayed(r, 10);
    }


    // Création du piaff quand on touche l'écran

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            bird.setDrop(-15);
        }
        return true;
    }

}
