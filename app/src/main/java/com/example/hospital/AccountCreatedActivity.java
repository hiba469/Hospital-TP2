package com.example.hospital;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AccountCreatedActivity extends AppCompatActivity {

    private TextView accountMessage;
    private TextView accountUsername;
    private Button goToLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_account_created);

        accountMessage = findViewById(R.id.accountMessage);
        accountUsername = findViewById(R.id.accountUsername);
        goToLoginButton = findViewById(R.id.goToLoginButton);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {

            String message = bundle.getString("message");
            String username = bundle.getString("username");

            accountMessage.setText(message);

            accountUsername.setText(
                    "Username: " + username
            );
        }

        goToLoginButton.setOnClickListener(v -> {

            Bundle loginBundle = new Bundle();

            String username = bundle.getString("username");

            loginBundle.putString(
                    "username",
                    username
            );

            Intent intent = new Intent(
                    AccountCreatedActivity.this,
                    LoginActivity.class
            );

            intent.putExtras(loginBundle);

            startActivity(intent);

            finish();
        });
    }
}