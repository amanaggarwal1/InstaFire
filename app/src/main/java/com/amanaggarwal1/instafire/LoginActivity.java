package com.amanaggarwal1.instafire;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.amanaggarwal1.instafire.home.HomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

import static android.text.TextUtils.isEmpty;

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

        passwordET.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.integer.login || id == EditorInfo.IME_NULL) {
                    signInExistingUser(passwordET);
                    return true;
                }
                return false;
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //if(currentUser != null)
          //  goToPostActivity();
    }

    public void signInExistingUser(View view){

        emailET.setError(null);
        passwordET.setError(null);

        String email = emailET.getText().toString();
        String password = passwordET.getText().toString();

        if(isEmpty(email)){
            emailET.setError(getString(R.string.error_field_required));
            emailET.requestFocus();
            return;
        }else if(!isEmailValid(email)){
            emailET.setError(getString(R.string.error_invalid_email));
            emailET.requestFocus();
            return;
        }

        if(isEmpty(password)){
            passwordET.setError(getString(R.string.error_field_required));
            passwordET.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(getString(R.string.logcat), "signIn onComplete : " + task.isSuccessful());

                        if(task.isSuccessful()){
                            //Sign in successful
                            Toast.makeText(LoginActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            goToPostActivity();
                        }else{
                            String error = Objects.requireNonNull(task.getException()).getMessage();
                            showErrorDialog(error);
                            Log.d(getString(R.string.logcat), "Problem in signing in : " + error);
                        }
                    }
                });
    }

    private void showErrorDialog(String error) {
        new AlertDialog.Builder(this)
                .setTitle("Login failed")
                .setMessage(error)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }

    private boolean isEmailValid(String email) {
        // You can add more checking logic here.
        return email.contains("@");
    }

    private void goToPostActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    public void registerNewUser(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
        finish();
    }
}