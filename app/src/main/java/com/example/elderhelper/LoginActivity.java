package com.example.elderhelper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText iEmail, iPassword;
    Button iLoginButton, iSignUp, iHome;
    //Textview iHint, iExample;
    FirebaseAuth fAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Credentials
        iEmail = findViewById(R.id.editTextText);
        iPassword = findViewById(R.id.editTextNumberPassword3);

        //Buttons
        iSignUp = findViewById(R.id.back_button);
        iLoginButton = findViewById(R.id.button);
        iHome = findViewById(R.id.button3);

        //Other
        progressBar = findViewById(R.id.progressBar);

        iLoginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String email = iEmail.getText().toString().trim(); //Used trim() to format the data
                String password = iPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    iEmail.setError("Please enter your email to Log in");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    iPassword.setError("Your password is required to Log in");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //authenticating the user
                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), HomeScreen.class));
                        }else{
                            Toast.makeText(LoginActivity.this, "Error !" +task.getException().getMessage() ,Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        iSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Signup.class));
            }
        });

        iHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }
}