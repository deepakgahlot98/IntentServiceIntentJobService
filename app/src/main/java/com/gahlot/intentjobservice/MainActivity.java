package com.gahlot.intentjobservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gahlot.intentjobservice.JobIntentService.JobIntentServiceActivity;
import com.gahlot.intentjobservice.intentService.IntentServiceActivity;

public class MainActivity extends AppCompatActivity {

    private Button IntentServiceBtn, JobIntentServiceBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentServiceBtn = findViewById(R.id.intent_service_button);
    }

    public void intentServiceClicked(View view) {
        Intent intent = new Intent(this, IntentServiceActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void jobIntentServiceClicked(View view) {
        Intent intent = new Intent(this, JobIntentServiceActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
