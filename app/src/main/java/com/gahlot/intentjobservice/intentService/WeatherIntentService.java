package com.gahlot.intentjobservice.intentService;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WeatherIntentService extends IntentService {

    private static final String TAG = "WeatherIntentService";
    private WeatherFetcher weatherFetcher = WeatherFetcher.getInstance();
    private ResultReceiver myResultReceiver;
    ArrayList<String> quote = new ArrayList<>();

    public WeatherIntentService() {
        super("weather_thread");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        try {
            if(intent!=null){
                myResultReceiver =  intent.getParcelableExtra("result");
            }

            quote = weatherFetcher.makeRequest();
            Log.i(TAG, "onHandleIntent: " + quote);
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("compValue", quote);
            myResultReceiver.send(100,bundle);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
