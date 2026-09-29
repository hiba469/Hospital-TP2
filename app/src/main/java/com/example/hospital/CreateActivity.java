package com.example.hospital;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreateActivity extends AppCompatActivity {

    private EditText createUsername;
    private EditText createPassword;
    private EditText confirmPassword;
    private Button createAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create);

        createUsername = findViewById(R.id.createUsername);
        createPassword = findViewById(R.id.createPassword);
        confirmPassword = findViewById(R.id.confirmPassword);
        createAccountButton = findViewById(R.id.createAccountButton);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {

            String username = bundle.getString("username");

            if (username != null && !username.isEmpty()) {
                createUsername.setText(username);
            }
        }

        createAccountButton.setOnClickListener(v -> {

            String username = createUsername.getText().toString();
            String password = createPassword.getText().toString();
            String confirm = confirmPassword.getText().toString();

            if (username.isEmpty() ||
                    password.isEmpty() ||
                    confirm.isEmpty()) {

                Toast.makeText(
                        CreateActivity.this,
                        "Please fill in all fields",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            if (!password.equals(confirm)) {

                Toast.makeText(
                        CreateActivity.this,
                        "Passwords do not match",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            Bundle accountBundle = new Bundle();

            accountBundle.putString(
                    "username",
                    username
            );

            accountBundle.putString(
                    "message",
                    "Your account has been created successfully!"
            );

            Intent intent = new Intent(
                    CreateActivity.this,
                    AccountCreatedActivity.class
            );

            intent.putExtras(accountBundle);

            startActivity(intent);
        });
    }
}