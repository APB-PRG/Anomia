package com.example.anomia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.FileProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class MainActivity extends AppCompatActivity {
    SwipeListener swipeListener;
    ConstraintLayout cstr_layout;

    private ImageView btncadenas;
    private ImageView settings;
    private ImageView fleche;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        //changer fil actualité pour mainactivité!!
        setContentView(R.layout.activity_main);
        cstr_layout = findViewById(R.id.constraint_layout);

        swipeListener = new SwipeListener(cstr_layout);
        initActivity();
    }

    @SuppressLint("WrongViewCast")
    private void initActivity(){
        //récupération des objets graphiques
        btncadenas = findViewById(R.id.btncadenas);
        settings=findViewById(R.id.settings);
        fleche = findViewById(R.id.fleche_fileactualite);

        //listening du bouton cadenas
        createOnclicbtncadenas();
        createonclicsettings();
        createOnclicfleche();
    }

    private void createOnclicfleche() {
        fleche.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityfile_actu();
            }
        });
    }

    private void createonclicsettings() {
        settings.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivitysettings();
            }
        });
    }

    private void createOnclicbtncadenas() {
        btncadenas.setOnClickListener(v -> openActivityconnexion());
    }

    private void openActivityfile_actu() {
        Intent intent = new Intent(MainActivity.this, fil_actualite.class);
        startActivity(intent);
    }

    private void openActivitysettings() {
        Intent intent = new Intent(MainActivity.this, settings.class);
        startActivity(intent);
        Animatoo.animateSlideRight(this);
    }

    //redirection vers connexion_page
    public void openActivityconnexion(){
        Intent intent = new Intent(MainActivity.this, page_connection.class);
        startActivity(intent);
        Animatoo.animateFade(this);
    }

    public class SwipeListener implements View.OnTouchListener {
        GestureDetector gestureDetector;

        SwipeListener(View view){
            int threshold = 100;
            int velocity_threshold = 100;

            GestureDetector.SimpleOnGestureListener listener = new GestureDetector.SimpleOnGestureListener(){
                public boolean onDown(MotionEvent e){
                    return true;
                }

                @Override
                public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                    float xDiff = e2.getX() - e1.getX();
                    float yDiff = e2.getY() - e1.getY();
                    try{
                        if (Math.abs(xDiff) > threshold && Math.abs(velocityX) > velocity_threshold){
                            if (xDiff > 0){
                                redirection_settings();
                            }
                            return true;
                        }else{
                            if (Math.abs(yDiff) > threshold && Math.abs(velocityY) > velocity_threshold){
                                if(yDiff>0){
                                    //nothingyet
                                }else{
                                    redirection_fil();
                                }
                                return true;
                            }
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    return false;
                }
            };
            gestureDetector = new GestureDetector(listener);
            view.setOnTouchListener(this);
        }

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return gestureDetector.onTouchEvent(motionEvent);
        }
    }

    public void redirection_settings(){
        Intent intent = new Intent(MainActivity.this, settings.class);
        startActivity(intent);
        Animatoo.animateSlideRight(this);
    }

    public void redirection_fil(){
        Intent intent = new Intent(MainActivity.this, fil_actualite.class);
        startActivity(intent);
        Animatoo.animateSlideUp(this);
    }
}