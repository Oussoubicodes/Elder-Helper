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

public class Signup extends AppCompatActivity {
    EditText iFullname, iEmail, idobQ, iChildrenQ, iMomQ;
    Button iFinishButton, iRegisterButton, iLoginButton, iHome2;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //Login Details
        iFullname = findViewById(R.id.fullname);
        iEmail = findViewById(R.id.email);
        idobQ = findViewById(R.id.editTextNumberPassword);
        iChildrenQ = findViewById(R.id.editTextNumberPassword2);
        iMomQ = findViewById(R.id.editTextTextPassword2);

        //Buttons
        iFinishButton = findViewById(R.id.registerButton);
        iRegisterButton = findViewById(R.id.registerButton);
        iLoginButton = findViewById(R.id.loginButton);
        iHome2 = findViewById(R.id.button4);

        //Other
        fAuth = FirebaseAuth.getInstance(); //gets the current instance of database from firebase

        //Checks if user is already logged in
        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), HomeScreen.class));
            finish();
        }

        iFinishButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String email = iEmail.getText().toString().trim(); //Used trim() to format the data
                String dobQ = idobQ.getText().toString().trim();
                String childrenQ = iChildrenQ.getText().toString().trim();
                String momQ = iMomQ.getText().toString().trim();

                //Combining all the security questions into one password
                String password = idobQ.getText().toString().trim() +
                        iMomQ.getText().toString().trim() +
                        iMomQ.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    iEmail.setError("An email is required");
                    return;
                }

                if(TextUtils.isEmpty(dobQ)){
                    idobQ.setError("All security questions need to be answered");
                    return;
                }

                if(dobQ.length() != 8){
                    idobQ.setError("There should be precisely 8 characters in total");
                }

                if(TextUtils.isEmpty(childrenQ)){
                    iChildrenQ.setError("All security questions need to be answered");
                    return;
                }

                if(TextUtils.isEmpty(momQ)){
                    iMomQ.setError("All security questions need to be answered");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //Adds user to the database
                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    //Handles user creation
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Signup.this, "User Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), HomeScreen.class));
                        }else{
                            Toast.makeText(Signup.this, "Error !" +task.getException().getMessage() ,Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        iLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });

        iHome2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }
}