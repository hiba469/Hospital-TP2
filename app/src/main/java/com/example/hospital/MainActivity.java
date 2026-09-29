package com.example.hospital;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView progressText;

    private Handler handler = new Handler();

    private int progress = 0;

    private final Runnable progressRunnable = new Runnable() {
        @Override
        public void run() {

            if (progress < 100) {

                progress++;

                progressBar.setProgress(progress);

                progressText.setText(progress + "%");

                handler.postDelayed(this, 500);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        progressText = findViewById(R.id.progressText);

        View main = findViewById(R.id.main);

        main.setOnClickListener(v -> {

            Bundle bundle = new Bundle();
            bundle.putString(
                    "message",
                    "Welcome to the Hospital App"
            );

            Intent intent = new Intent(
                    MainActivity.this,
                    LoginActivity.class
            );

            intent.putExtras(bundle);

            startActivity(intent);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.i("LIFECYCLE", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.i("LIFECYCLE", "onResume");

        progressBar.setVisibility(View.VISIBLE);
        progressText.setVisibility(View.VISIBLE);

        handler.post(progressRunnable);
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.i("LIFECYCLE", "onPause");

        handler.removeCallbacks(progressRunnable);
    }
}