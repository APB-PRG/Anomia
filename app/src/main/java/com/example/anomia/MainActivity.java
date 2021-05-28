package com.example.anomia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
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
    private ImageView settings;
    private ImageView fleche;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        //changer fil actualité pour mainactivité!!
        setContentView(R.layout.activity_main);
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
    }

    //redirection vers connexion_page
    public void openActivityconnexion(){
        Intent intent = new Intent(MainActivity.this, page_connection.class);
        startActivity(intent);
        Animatoo.animateFade(this);
    }
}