package com.example.anomia;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.anomia.Model.Session;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import com.example.anomia.Model.User;

public class page_connection extends AppCompatActivity {

    //bouton connexion, inscritption
    private TextView signup;
    private TextView signin;
    private ImageView btncadenas;

    private ImageView settings;

    //editable text pour input des informations du user
    private EditText usernameText, passwordText;
    private Session session;//sauvegarde des données utilisateurs (session)

    //authentifciation + création du user (voir classe)
    private FirebaseAuth mAuth;
    private static final String TAG = "loginActivity";
    private User user;

    //première chose exécuté lorsque l'utilisateur arrive sur la page
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_connection);
        session = new Session(getApplicationContext());
        initActivity();
    }

    private void initActivity(){
        //récupération des objets graphiques
        signup = (TextView) findViewById(R.id.sign_up);
        signin = (TextView) findViewById(R.id.sign_in);
        btncadenas = (ImageView) findViewById(R.id.btncadenas);

        //listen on different button
        createOnclicbtnsign_up();
        createOnclicbtnsign_in();
        createOnclicbtncadenas();

        settings=findViewById(R.id.settings_connexion);
        createonclicsettings();

        //création de la fonction login pour le user
        login();
    }

    private void login() {
        //association des entrées utilisateurs
        usernameText = findViewById(R.id.username);
        passwordText = findViewById(R.id.pswduser);

        //assignation de l'instance de firebaseAuth
        mAuth = FirebaseAuth.getInstance();
        //assoiciation du bouton login
        Button loginButton = (Button) findViewById(R.id.login);

        //action lorsque le bouton est pressé
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //conversion en string
                String username = usernameText.getText().toString();
                String password = passwordText.getText().toString();

                //vérification que le password et l'username ne sont pas vide
                if(TextUtils.isEmpty(password) || TextUtils.isEmpty(username)){
                    Toast.makeText(getApplicationContext(), "Please, enter all the informations to proceed inscription", Toast.LENGTH_LONG).show();
                    return;
                }

                //On renseigne l'user actuel avec le password et l'username associé + lancement de loginUser en param username & password
                user = new User(password, username);
                //permet d'avoir l'utilisateur connecté
                session.setusename(username);

                //regarde dans la base de données l'username correspondant à celui rentré par utilisteur
                FirebaseFirestore.getInstance().collection("users").whereEqualTo("username", user.getUsername()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            //récupération de l'email par query, get documents -> listes
                            String userEmail =  task.getResult().getDocuments().get(0).toObject(User.class).getEmail();
                            loginUser(userEmail, password);
                        }
                    }
                });
            }
        });
    }

    //connecte l'utilisateur actuel dans sa session
    private void loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(this,new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Log.d(TAG, "le client est authentifié");
                FirebaseUser user_fire = mAuth.getCurrentUser();

                updateUI(user_fire);
            }}).addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //toast
            }
        });
            //todo que faire si l'utilisateur rentre mal ses données?

    }

    //utiliser user pour transmettre les paramètres
    private void updateUI(FirebaseUser user_fire) {
        Intent profilIntent = new Intent(this, fil_actualite.class);
        //user_fire.startActivityForLinkWithProvider(profilIntent, )
        startActivity(profilIntent);
        Animatoo.animateFade(this);
    }

    private void createOnclicbtnsign_up() {
        signup.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivitycreation_compte();
            }
        });
    }

    private void createOnclicbtnsign_in() {
        signin.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityconnection_compte();
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        });
    }

    private void createOnclicbtncadenas() {
        btncadenas.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivitymain();
            }
        });
    }

    public void openActivitycreation_compte(){
        Intent intent = new Intent(page_connection.this, page_creation_compte.class);
        startActivity(intent);
        Animatoo.animateSlideLeft(this);
    }

    public void openActivityconnection_compte(){
        Intent intent = new Intent(page_connection.this, page_connection.class);
        startActivity(intent);
    }

    public void openActivitymain(){
        Intent intent = new Intent(page_connection.this, MainActivity.class);
        startActivity(intent);
        Animatoo.animateFade(this);
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
        Intent intent = new Intent(page_connection.this, settings.class);
        startActivity(intent);
        Animatoo.animateSlideRight(this);
    }
}