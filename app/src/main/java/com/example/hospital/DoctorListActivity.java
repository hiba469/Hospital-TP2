package com.example.hospital;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class DoctorListActivity extends AppCompatActivity {

    private Spinner doctorSpinner;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_list);

        doctorSpinner = findViewById(R.id.doctorSpinner);
        nextButton = findViewById(R.id.nextButton);

        String[] doctors = {
                "Dr Ahmed",
                "Dr Sara",
                "Dr Mohamed"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                doctors
        );

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        doctorSpinner.setAdapter(adapter);

        nextButton.setOnClickListener(v -> {
            Intent myLocalIntent = getIntent();
            Bundle myBundle = myLocalIntent.getExtras();

            if (myBundle == null) {
                myBundle = new Bundle();
            }

            String selectedDoctor =
                    doctorSpinner.getSelectedItem().toString();

            myBundle.putString("doctor", selectedDoctor);

            Intent myIntent = new Intent(
                    DoctorListActivity.this,
                    AppointmentActivity.class
            );

            myIntent.putExtras(myBundle);

            startActivityForResult(myIntent, 102);
        });
    }

    @Override
    protected void onActivityResult(
            int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 102
                && resultCode == Activity.RESULT_OK
                && data != null) {

            setResult(Activity.RESULT_OK, data);
            finish();
        }
    }
}