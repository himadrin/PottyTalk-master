package com.andrewogren.pottytalk;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/* Reused from MyRuns Login */
public class LoginActivity extends AppCompatActivity implements EditText.OnClickListener {

    private EditText email;
    private EditText password;
    private Button logInButton;
    private TextView signUp;
    private FirebaseAuth firebaseAuth;

    private boolean isSigningUp = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (savedInstanceState != null) {
            isSigningUp = savedInstanceState.getBoolean("isSigningUp");
        }

        firebaseAuth = FirebaseAuth.getInstance();

        signUp = findViewById(R.id.signUpText);
        signUp.setOnClickListener(this);


        email = findViewById(R.id.emailField);
        password = findViewById(R.id.passwordField);

        logInButton = findViewById(R.id.loginButton);
        logInButton.setOnClickListener(this);

        setText();
    }

    private void setText() {
        if (isSigningUp) {
            logInButton.setText("SIGN UP");
            signUp.setText("Log In!");
        } else {
            logInButton.setText("LOGIN");
            signUp.setText("Sign Up!");
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("isSigningUp", isSigningUp);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.signUpText) {
            isSigningUp = !isSigningUp;
            setText();
        } else if (v.getId() == R.id.loginButton) {
            String emailStr = email.getText().toString();
            String passwordStr = password.getText().toString();

            emailStr = emailStr.trim();
            passwordStr = passwordStr.trim();
            logInOrSignUp(emailStr, passwordStr);

        }
    }


    private void logInOrSignUp(String email, String password) {

        if (email.isEmpty() || password.isEmpty()) {
            createErrorMessage();
        } else if (isSigningUp) {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            } else {
                                createSignInErrorMessage(task);
                            }
                        }
                    });
        } else {
            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>
                            () {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            } else {
                                createSignInErrorMessage(task);
                            }
                        }
                    });
        }
    }

    private void createSignInErrorMessage(Task<AuthResult> task) {
        AlertDialog.Builder builder = new AlertDialog.Builder
                (LoginActivity.this);
        builder.setMessage(task.getException().getMessage())
                .setTitle("Error")
                .setPositiveButton(android.R.string.ok, null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void createErrorMessage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setMessage("Both email and password must be filled out.")
                .setTitle("Error")
                .setPositiveButton(android.R.string.ok, null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
