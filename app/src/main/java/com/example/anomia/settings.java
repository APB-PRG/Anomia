package com.example.anomia;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.preference.PreferenceFragmentCompat;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.firebase.auth.FirebaseAuth;

public class settings extends AppCompatActivity {
    settings.SwipeListener swipeListener;
    ConstraintLayout cstr_layout;
    Switch simpleSwitch1;

    private ImageView home;
    private ImageView profil;
    private ImageView deconnexion;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        simpleSwitch1 = (Switch) findViewById(R.id.switch1);

        cstr_layout = findViewById(R.id.layout_settings);
        swipeListener = new settings.SwipeListener(cstr_layout);

        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.Theme_Anomia_night);
        }else{
            setTheme(R.style.Theme_Anomia);
        }

        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            simpleSwitch1.setChecked(true);
        }

        simpleSwitch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    reset();
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    reset();
                }
            }
        });


        initActivity();
    }

    private void reset() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void initActivity() {
        home = (ImageView) findViewById(R.id.home);
        profil = (ImageView) findViewById(R.id.profil_button);
        deconnexion = (ImageView) findViewById(R.id.deconnexion);

        createOnclicbtnhome();
        createOnclicbtndeco();
        createOnclicbtnprofil();
    }

    private void createOnclicbtnprofil() {
        profil.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityprofil();
            }
        });
    }

    private void createOnclicbtndeco() {
        deconnexion.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivitydeconnexion();
            }
        });
    }

    private void createOnclicbtnhome() {
        home.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityhome();
            }
        });
    }

    private void openActivityprofil() {
        Intent intent = new Intent(settings.this, user_profil.class);
        startActivity(intent);
        Animatoo.animateFade(this);
    }

    private void openActivitydeconnexion() {
        mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();

        Intent intent = new Intent(settings.this, page_connection.class);
        startActivity(intent);
        Animatoo.animateFade(this);
    }

    private void openActivityhome() {
        Intent intent = new Intent(settings.this,  MainActivity.class);
        startActivity(intent);
        Animatoo.animateSlideLeft(this);
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
                            if (xDiff < 0){
                                return_activity();
                            }
                            return true;
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

    private void return_activity() {
        finish();
        Animatoo.animateSlideLeft(this);
    }
}