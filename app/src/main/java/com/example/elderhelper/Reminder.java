package com.example.elderhelper;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Reminder extends AppCompatActivity {

    //Buttons
    private Button makeReminder;
    private Button goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        makeReminder = (Button) findViewById(R.id.button2);
        goBack = (Button) findViewById(R.id.back_button);

        makeReminder.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent= new Intent(Reminder.this, AddReminder.class);
                startActivity(intent);
                finish();
            }
        });

        goBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent= new Intent(Reminder.this, HomeScreen.class);
                startActivity(intent);
                finish();
            }
        });

    }


    private Integer id;
    private String medicineName;
    private String frequency;
    private String time;

    public Reminder(){}

    public Reminder(Integer id, String medicineName, String time, String frequency) {
        this.id = id;
        this.medicineName = medicineName;
        this.time = time;
        this.frequency = frequency;
    }

    public int getId(){
        return id;
    }
    public String getName() {
        return medicineName;
    }

    public String getFrequency() {
        return frequency;
    }

    public String getTime() {
        return time;
    }


    public static final String TABLE_NAME = "reminder";
    public static final String ID = "_id";
    public static final String MEDICINE_ID = "medicine_id";
    public static final String TIME = "time";
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID + " integer primary key autoincrement, " +
                    MEDICINE_ID + " INTEGER, " +
                    TIME + " TEXT);";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        database.execSQL(TABLE_CREATE);
    }
}
