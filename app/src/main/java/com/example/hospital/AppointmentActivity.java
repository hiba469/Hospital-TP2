package com.example.hospital;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AppointmentActivity extends AppCompatActivity {

    private EditText appointmentDate;
    private EditText appointmentTime;
    private Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appointment);

        appointmentDate = findViewById(R.id.appointmentDate);
        appointmentTime = findViewById(R.id.appointmentTime);
        confirmButton = findViewById(R.id.confirmButton);

        confirmButton.setOnClickListener(v -> {
            String date = appointmentDate.getText().toString().trim();
            String time = appointmentTime.getText().toString().trim();

            if (date.isEmpty()) {
                appointmentDate.setError("Enter the date");
                return;
            }

            if (time.isEmpty()) {
                appointmentTime.setError("Enter the time");
                return;
            }

            Intent myLocalIntent = getIntent();
            Bundle myBundle = myLocalIntent.getExtras();

            if (myBundle == null) {
                myBundle = new Bundle();
            }

            myBundle.putString("date", date);
            myBundle.putString("time", time);

            Intent myIntent = new Intent(
                    AppointmentActivity.this,
                    ConfirmationActivity.class
            );

            myIntent.putExtras(myBundle);

            startActivityForResult(myIntent, 103);
        });
    }

    @Override
    protected void onActivityResult(
            int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 103
                && resultCode == Activity.RESULT_OK
                && data != null) {

            setResult(Activity.RESULT_OK, data);
            finish();
        }
    }
}