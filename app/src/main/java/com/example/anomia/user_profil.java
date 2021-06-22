package com.example.anomia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.anomia.Model.Session;
import com.example.anomia.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class user_profil extends AppCompatActivity {

    private TextView username;
    private Session session;
    private ImageView settings;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profil);
        session = new Session(getApplicationContext());
        mAuth = FirebaseAuth.getInstance();

        initActivity();
    }

    private void initActivity() {
        if (mAuth.getCurrentUser() != null) {
            TextView username = (TextView)findViewById(R.id.username);
            username.setText(session.getusename().toUpperCase());
        }else{
            TextView username = (TextView)findViewById(R.id.username);
            username.setText("");
        }
        settings=findViewById(R.id.settings_profil);

        createonclicsettings();
    }

    private void createonclicsettings() {
        settings.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivitysettings();
            }
        });
    }

    private void openActivitysettings() {
        Intent intent = new Intent(user_profil.this, settings.class);
        startActivity(intent);
        Animatoo.animateSlideRight(this);
    }
}