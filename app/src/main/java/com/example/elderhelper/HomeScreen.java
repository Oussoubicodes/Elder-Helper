package com.example.elderhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.widget.Button;
import android.view.View;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;


public class HomeScreen extends AppCompatActivity {

    Button ireminders, istatistics, isteps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        //Buttons
        ireminders = findViewById(R.id.reminders_button);
        istatistics = findViewById(R.id.statistics_button);
        isteps = findViewById(R.id.activities_button);

        ireminders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Reminder.class));
            }
        });
    }

    public void logout(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }
}