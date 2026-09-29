package com.example.hospital;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DoctorActivity extends AppCompatActivity {

    private TextView doctorWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.doctor);

        doctorWelcome = findViewById(R.id.doctorWelcome);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {

            String username = bundle.getString("username");

            doctorWelcome.setText(
                    "Welcome, Doctor " + username
            );
        }
    }
}