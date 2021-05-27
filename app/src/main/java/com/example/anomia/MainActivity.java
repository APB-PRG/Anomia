package com.example.anomia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class MainActivity extends AppCompatActivity {

    private ImageView btncadenas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initActivity();
    }

    @SuppressLint("WrongViewCast")
    private void initActivity(){
        //récupération des objets graphiques
        btncadenas = findViewById(R.id.btncadenas);

        //listening du bouton cadenas
        createOnclicbtncadenas();
    }

    private void createOnclicbtncadenas() {
        btncadenas.setOnClickListener(v -> openActivityconnexion());
    }

    //redirection vers connexion_page
    public void openActivityconnexion(){
        Intent intent = new Intent(MainActivity.this, page_connection.class);
        startActivity(intent);
        Animatoo.animateFade(this);
    }
}