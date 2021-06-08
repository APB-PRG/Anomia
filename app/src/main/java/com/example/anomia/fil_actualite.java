package com.example.anomia;

import android.content.Intent;
import android.os.Bundle;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;

public class fil_actualite extends AppCompatActivity {

    private ImageView profil_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fil_actualite);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(getTitle());

        initActivity();
    }

    private void initActivity() {
        profil_user = findViewById(R.id.user_profil);

        createOnclicprofil_user();
    }

    private void createOnclicprofil_user() {
        profil_user.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityprofil_user();
            }
        });
    }

    private void openActivityprofil_user() {
        Intent intent = new Intent(fil_actualite.this, user_profil.class);
        startActivity(intent);
        Animatoo.animateFade(this);
    }
}