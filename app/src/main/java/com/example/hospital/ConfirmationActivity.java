package com.example.hospital;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ConfirmationActivity extends AppCompatActivity {

    private TextView confirmationDetails;
    private Button finishButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmation);

        confirmationDetails =
                findViewById(R.id.confirmationDetails);

        finishButton = findViewById(R.id.finishButton);

        Intent myLocalIntent = getIntent();
        Bundle myBundle = myLocalIntent.getExtras();

        if (myBundle != null) {
            String username = myBundle.getString("username");
            String doctor = myBundle.getString("doctor");
            String date = myBundle.getString("date");
            String time = myBundle.getString("time");

            confirmationDetails.setText(
                    "Patient: " + username
                            + "\nDoctor: " + doctor
                            + "\nDate: " + date
                            + "\nTime: " + time
            );
        }

        finishButton.setOnClickListener(v -> {
            Intent resultIntent = getIntent();

            setResult(Activity.RESULT_OK, resultIntent);

            finish();
        });
    }
}