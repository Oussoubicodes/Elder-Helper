package com.example.elderhelper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.elderhelper.databinding.ActivityAddReminderBinding;
import com.example.elderhelper.databinding.ActivityMainBinding;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.util.Calendar;


public class AddReminder extends AppCompatActivity{

    private @NonNull ActivityAddReminderBinding binding;
    private MaterialTimePicker timePicker;
    private Calendar calendar;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    //GUI
    Button iBackButton, iSetReminder;
    EditText medicineName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddReminderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        createNotificationChannel();

        binding.setReminder.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                timePicker = new MaterialTimePicker.Builder()
                        .setTimeFormat(TimeFormat.CLOCK_12H)
                        .setHour(12)
                        .setMinute(0)
                        .setTitleText("Select the dosage time")
                        .build();

                timePicker.show(getSupportFragmentManager(), "elderhelper");
                timePicker.addOnPositiveButtonClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(timePicker.getHour() > 12){
                            binding.setReminder.setText(
                                    String.format("%02d",(timePicker.getHour()-12)) +":"
                                            +String.format("%02d", timePicker.getMinute())+"PM");
                        }else{
                            binding.setReminder.setText(timePicker.getHour()+":" + timePicker.getMinute()+ "AM");
                        }
                        calendar = Calendar.getInstance();
                        calendar.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
                        calendar.set(Calendar.MINUTE, timePicker.getMinute());
                        calendar.set(Calendar.SECOND, 0);
                        calendar.set(Calendar.MILLISECOND, 0);
                    }
                });
                binding.finishReminder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                        Intent intent = new Intent(AddReminder.this, AlarmReceiver.class);
                        pendingIntent = PendingIntent.getBroadcast(AddReminder.this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

                        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                                AlarmManager.INTERVAL_DAY, pendingIntent);
                        Toast.makeText(AddReminder.this, "Reminder set", Toast.LENGTH_SHORT).show();
                    }
                });

                binding.cancelReminder.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent intent= new Intent(AddReminder.this,AlarmReceiver.class);
                        pendingIntent = PendingIntent.getBroadcast(AddReminder.this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

                        if(alarmManager == null){
                            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                        }
                        alarmManager.cancel(pendingIntent);
                        Toast.makeText(AddReminder.this, "Reminder cancelled", Toast.LENGTH_SHORT).show();
                    }
                });

                binding.goBack.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Intent intent= new Intent(AddReminder.this, Reminder.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });
    }

    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "channel";
            String desc = "Channel for Alarm Manager";
            int important = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("elderhelper", name, important);
            channel.setDescription(desc);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


}