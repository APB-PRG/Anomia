package com.example.anomia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.firebase.auth.FirebaseAuth;

public class settings extends AppCompatActivity {

    private ImageView home;
    private ImageView profil;
    private ImageView deconnexion;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        initActivity();
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
}