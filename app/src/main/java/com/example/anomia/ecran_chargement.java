package com.example.anomia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class ecran_chargement extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Context c = App.getContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecran_chargement);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(ecran_chargement.this, MainActivity.class);
                startActivity(intent);
                Animatoo.animateFade(c);
            }
        }, 2500);
    }
}