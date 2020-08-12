package com.amanaggarwal1.instafire;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText emailET;
    private EditText passwordET;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailET = findViewById(R.id.login_email);
        passwordET = findViewById(R.id.login_password);

        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() != null)
            goToPostActivity();

    }

    public void signInExistingUser(View view){
        String email = emailET.getText().toString();
        String password = passwordET.getText().toString();

        if(email.isEmpty()) {
            Toast.makeText(this, "Email field can't be empty", Toast.LENGTH_SHORT).show();
            return;
        }else if(password.isEmpty()) {
            Toast.makeText(this, "Password field can't be empty", Toast.LENGTH_SHORT).show();
            return;
        }


        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //Sign in successful
                            Toast.makeText(getApplicationContext(), "login", Toast.LENGTH_SHORT).show();
                            Log.i("LOGCAT", "login successful");
                            FirebaseUser user = mAuth.getCurrentUser();
                            goToPostActivity();
                        }else{
                            Log.d("LOGCAT", "login failed");
                            Toast.makeText(getApplicationContext(), "login unsuccessful", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void goToPostActivity() {
        Intent intent = new Intent(this, PostsActivity.class);
        startActivity(intent);
        finish();
    }

    public void registerNewUser(View view) {

    }
}