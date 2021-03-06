package com.example.anomia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import com.example.anomia.Model.User;

public class page_creation_compte extends AppCompatActivity {
    page_creation_compte.SwipeListener swipeListener;
    ConstraintLayout cstr_layout;

    private TextView signin;
    private TextView signup;
    private ImageView btncadenas;
    private EditText usernameText, passwordText, repasswordText, emailText;
    private Button registerButton;
    private ImageView settings;
    //final EditText name_user = findViewById(R.id.name);

    private FirebaseDatabase database;
    //private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private static final String USER = "user";
    private static final String TAG = "RegisterActivity";
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_creation_compte);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myref = database.getReference("message");
        myref.setValue("hellow");
        cstr_layout = findViewById(R.id.creation_layout);
        swipeListener = new page_creation_compte.SwipeListener(cstr_layout);

        initActivity();
    }

    private void initActivity(){
        //r??cup??ration des objets graphiques
        signin = (TextView) findViewById(R.id.sign_in);
        signup = (TextView) findViewById(R.id.sign_up);
        btncadenas = (ImageView) findViewById(R.id.btncadenas);
        settings=findViewById(R.id.settings_creation);

        //lancement activit??
        createOnclicbtnsign_in();
        createOnclicbtnsign_up();
        createOnclicbtncadenas();
        registration();
        createonclicsettings();
    }

    //enregistrement d'un client
    private void registration() {
        usernameText = findViewById(R.id.name);
        passwordText = findViewById(R.id.password);
        repasswordText = findViewById(R.id.repassword);
        emailText = findViewById(R.id.email);

        //database = FirebaseDatabase.getInstance();
        //mDatabase = database.getReference(USER);
        mAuth = FirebaseAuth.getInstance();

        registerButton = (Button) findViewById(R.id.button_creation_compte);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailText.getText().toString();
                String password = passwordText.getText().toString();
                String username = usernameText.getText().toString();
                String repassword = repasswordText.getText().toString();
                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(username) || TextUtils.isEmpty(repassword)){
                    Toast.makeText(getApplicationContext(), "Please, enter all the informations to proceed inscription", Toast.LENGTH_LONG).show();
                    return;
                }

                //TODO faire en sorte de pouvoir v??rifier la correspondance en entre password et repassword
                if (TextUtils.equals(password, repassword)){
                    user = new User(email, password, username, repassword);
                    registerUser(password, email, username);
                }else{
                    Toast.makeText(getApplicationContext(), "Please, enter the same password", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        });
    }

    private void registerUser(String password, String email, String username) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //sign in success, update UI with the signed in user's informations
                    //mDatabase.child("user_ids").child(username).setValue(email);
                    Log.d(TAG, "createUserWithEmail: success");
                    FirebaseUser user_fire = mAuth.getCurrentUser();
                    user.setId_user(user_fire.getUid());
                    FirebaseFirestore.getInstance().collection("users").document(user_fire.getUid()).set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "set document: success");
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e(TAG, "set document: failed", e);
                        }
                    });
                    updateUI(user_fire);
                }else{
                    //sign in fails, display a message to the user
                    Log.d(TAG, "createUserWithEmail: failure", task.getException());
                    Toast.makeText(page_creation_compte.this, "Password should be at least 6 characters", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //redirection vers la page connectio
    private void updateUI(FirebaseUser user) {
        Intent loginIntent = new Intent(this, page_connection.class);
        startActivity(loginIntent);

        //animation en fondu avec Animatoo
        Animatoo.animateFade(this);
    }

    private void createOnclicbtnsign_up() {
        signup.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivitycreation_compte();
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        });
    }

    private void createOnclicbtnsign_in() {
        signin.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityconnection_compte();
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

    //redirection vers la page connection
    public void openActivityconnection_compte(){
        final EditText pssword_user = findViewById(R.id.password);
        Intent intent = new Intent(page_creation_compte.this, page_connection.class);
        //Pair<View, String> p1 = Pair.create((EditText) usernameText, "name");
        //Pair<View, String> p2 = Pair.create((EditText) passwordText, "password");
        //ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(page_creation_compte.this, name_user, ViewCompat.getTransitionName(name_user));
        //startActivity(intent, options.toBundle());
        startActivity(intent);
        Animatoo.animateSlideRight(this);
    }

    //redirection vers la meme page (le client reclic sur inscritption
    public void openActivitycreation_compte(){
        Intent intent = new Intent(page_creation_compte.this, page_creation_compte.class);
        startActivity(intent);
    }

    //redirection vers la page principale
    public void openActivitymain(){
        Intent intent = new Intent(page_creation_compte.this, MainActivity.class);
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
        Intent intent = new Intent(page_creation_compte.this, settings.class);
        startActivity(intent);
        Animatoo.animateSlideRight(this);
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
                            if (xDiff > 0){
                                openActivityconnection_compte();
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
}