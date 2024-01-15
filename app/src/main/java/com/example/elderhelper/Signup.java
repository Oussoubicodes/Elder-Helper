package com.example.elderhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {
    EditText iFirstname, iLastname, iEmail, iNumber /*,iPassword*/;
    Button iNextButton, iRegisterButton, iLoginButton;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //Login Details
        iFirstname = findViewById(R.id.firstname);
        iLastname = findViewById(R.id.lastname);
        iEmail = findViewById(R.id.email);
        iNumber = findViewById(R.id.phone);

        //iPassword = findViewById(R.id.)


        //Buttons
        iNextButton = findViewById(R.id.registerButton);
        iRegisterButton = findViewById(R.id.registerButton);
        iLoginButton = findViewById(R.id.loginButton);

        fAuth = FirebaseAuth.getInstance(); //gets the current instance of database from firebase

        progressBar = findViewById(R.id.progressBar);

        iNextButton.setOnClickListener(new View.OnClickListener(){
            @Override
        });
    }
}