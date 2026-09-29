package com.example.hospital;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PatientActivity extends AppCompatActivity {

    private TextView patientWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.patient);

        patientWelcome = findViewById(R.id.patientWelcome);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {

            String username = bundle.getString("username");

            patientWelcome.setText(
                    "Welcome, " + username
            );
        }
    }
}