package com.example.hospital;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private Button CreateButton;
    private Button loginButton;
    private Spinner roleSpinner;
    private EditText username;
    private TextView welcomeMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login);

        CreateButton = findViewById(R.id.CreateButton);
        loginButton = findViewById(R.id.loginButton);
        roleSpinner = findViewById(R.id.list_view_role);
        username = findViewById(R.id.username);
        welcomeMessage = findViewById(R.id.welcomeMessage);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {

            String message = bundle.getString("message");
            String createdUsername = bundle.getString("username");

            if (message != null) {
                welcomeMessage.setText(message);
            }

            if (createdUsername != null) {
                username.setText(createdUsername);
            }
        }

        CreateButton.setOnClickListener(v -> {

            String user = username.getText().toString();

            Bundle createBundle = new Bundle();

            createBundle.putString(
                    "username",
                    user
            );

            Intent intent = new Intent(
                    LoginActivity.this,
                    CreateActivity.class
            );

            intent.putExtras(createBundle);

            startActivity(intent);
        });

        loginButton.setOnClickListener(v -> {

            if (roleSpinner.getSelectedItemPosition() == 0) {

                Toast.makeText(
                        LoginActivity.this,
                        "Select your role",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            String user = username.getText().toString();

            String role = roleSpinner
                    .getSelectedItem()
                    .toString();

            Bundle loginBundle = new Bundle();

            loginBundle.putString("username", user);
            loginBundle.putString("role", role);

            if (role.equals("Doctor")) {

                Intent intent = new Intent(
                        LoginActivity.this,
                        DoctorActivity.class
                );

                intent.putExtras(loginBundle);

                startActivity(intent);

            } else if (role.equals("Patient")) {

                Intent intent = new Intent(
                        LoginActivity.this,
                        PatientActivity.class
                );

                intent.putExtras(loginBundle);

                startActivity(intent);
            }
        });
    }
}