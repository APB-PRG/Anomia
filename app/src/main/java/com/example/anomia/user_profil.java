package com.example.anomia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anomia.Model.Session;
import com.example.anomia.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class user_profil extends AppCompatActivity {

    private TextView username;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profil);
        session = new Session(getApplicationContext());

        initActivity();
    }

    private void initActivity() {
        TextView username = (TextView)findViewById(R.id.username);

        username.setText(session.getusename().toUpperCase());
    }

}