package com.example.anomia;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GestureDetectorCompat;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class gesture extends AppCompatActivity {
    SwipeListener swipeListener;
    ConstraintLayout cstr_layout;
    private Integer layout;
    private Class home;
    private Class destination;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //cstr_layout = findViewById(layout);
        //swipeListener = new SwipeListener(cstr_layout);
    }

    public gesture(int layout, Class home, Class destination){
        this.layout = layout;
        this.home = home;
        this.destination = destination;
    }

    public class SwipeListener implements View.OnTouchListener {
        GestureDetector gestureDetector;

        SwipeListener(View view){
            int threshold = 100;
            int velocity_threshold = 100;

            GestureDetector.SimpleOnGestureListener listener = new GestureDetector.SimpleOnGestureListener(){
                public boolean onDown(MotionEvent e){
                    return false;
                }

                @Override
                public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                    float xDiff = e2.getX() - e1.getX();
                    float yDiff = e2.getY() - e2.getY();
                    try{
                        if (Math.abs(xDiff) > threshold && Math.abs(velocityX) > velocity_threshold){
                            if (xDiff > 0){
                                Intent intent = new Intent(gesture.this, destination);
                                startActivity(intent);
                                Animatoo.animateSlideRight(gesture.this);
                            }
                            return true;
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    return false;
                }
            };
            Context context;
            gestureDetector = new GestureDetector(listener);
            view.setOnTouchListener(this);
        }

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return gestureDetector.onTouchEvent(motionEvent);
        }
    }
}
