package com.example.anomia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Script;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.anomia.Model.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class creation_community extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";
    private User user;

    private FirebaseAuth mAuth;

    private com.example.anomia.Model.Community community_user;

    private Button button;
    private String name;
    private String description;
    private String category1;
    private String category2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_community);
        button = (Button) findViewById(R.id.submit_community);
        
        initActivity();
    }

    private void initActivity() {
        Spinner dropdown = findViewById(R.id.spinner1);
        Spinner dropdown2 = findViewById(R.id.spinner2);
        String[] items = new String[]{"Humoristic", "Video games", "Technology", "Fun", "Informations"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items); //simple_spinner_dropdown_item
        dropdown.setAdapter(adapter);
        dropdown2.setAdapter(adapter);

        create_community();
    }

    private void create_community() {

        button = (Button) findViewById(R.id.submit_community);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText nameText = findViewById(R.id.name_comunity);
                EditText descriptionText = findViewById(R.id.description);
                Spinner category1Text = findViewById(R.id.spinner1);
                Spinner category2Text = findViewById(R.id.spinner2);

                name = nameText.getText().toString();
                description = descriptionText.getText().toString();
                category1 = category1Text.getSelectedItem().toString();
                category2 = category2Text.getSelectedItem().toString();

                community_user = new com.example.anomia.Model.Community(name, description, category1, category2);

                community_submit(name, description, category1, category2);
            }

            });
    }

    private void community_submit(String name, String description, String category1, String category2) {

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        user = new User();

        FirebaseFirestore.getInstance().collection("community").add(community_user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                community_user.setId(documentReference.getId());
                Log.d(TAG, "DocumentSnapshot written with ID: " + community_user.getId());
                FirebaseFirestore.getInstance().collection("community").document(documentReference.getId()).update("id", community_user.getId());

                //set communaute dans user actuel
                //com.example.anomia.Model.Community communaute = new com.example.anomia.Model.Community(community_user.getId() ,name, description, category1, category2);
                //user.setcomu(communaute);
                //FirebaseFirestore.getInstance().collection("users").document(currentUser.getUid()).update("list", user.getcomu());
            }
        });

        openActivitybutton();
    }

    //todo passer en parametre les valeurs de la communautés créé

    private void openActivitybutton() {
        Intent intent = new Intent(creation_community.this, Community.class);
        startActivity(intent);
        Animatoo.animateFade(this);
    }
}