
package com.example.hospital;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PatientActivity extends AppCompatActivity {

    private TextView patientWelcome;
    private Button appointmentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient);

        patientWelcome = findViewById(R.id.patientWelcome);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            String username = bundle.getString("username");
            patientWelcome.setText("Welcome, " + username);
        }

        appointmentButton = findViewById(R.id.AppointmentButton);

        appointmentButton.setOnClickListener(v -> {
            Intent myIntent = new Intent(
                    PatientActivity.this,
                    DoctorListActivity.class
            );

            Bundle myBundle = getIntent().getExtras();

            if (myBundle != null) {
                myIntent.putExtras(myBundle);
            }

            startActivityForResult(myIntent, 101);
        });
    }

    @Override
    protected void onActivityResult(
            int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101
                && resultCode == Activity.RESULT_OK
                && data != null) {

            Bundle myBundle = data.getExtras();

            if (myBundle != null) {
                String doctor = myBundle.getString("doctor");
                String date = myBundle.getString("date");
                String time = myBundle.getString("time");

                LinearLayout appointmentList =
                        findViewById(R.id.appointmentList);

                TextView newAppointment = new TextView(this);

                newAppointment.setText(
                        doctor + " — " + date + " at " + time
                );

                newAppointment.setTextSize(16);
                newAppointment.setTextColor(
                        getResources().getColor(R.color.hospital_text)
                );
                newAppointment.setPadding(0, 0, 0, 16);

                appointmentList.addView(newAppointment);
            }
        }
    }
}
