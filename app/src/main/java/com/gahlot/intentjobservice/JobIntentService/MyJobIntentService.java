package com.gahlot.intentjobservice.JobIntentService;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

import com.gahlot.intentjobservice.intentService.WeatherFetcher;

import java.io.IOException;
import java.util.ArrayList;

public class MyJobIntentService extends JobIntentService {

    private WeatherFetcher weatherFetcher = WeatherFetcher.getInstance();
    private ResultReceiver myJobResultReceiver;
    ArrayList<String> quote = new ArrayList<>();

    /**
     * Unique job ID for this service.
     */
    private static final int JOB_ID = 9;

    public static void enqueueWork(Context context, Intent intent) {
        enqueueWork(context, MyJobIntentService.class, JOB_ID, intent);
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        String[] cityNames = intent.getStringArrayExtra("maxCountValue");

        if(intent!=null){
            myJobResultReceiver =  intent.getParcelableExtra("result");
        }

        try {
            for (int i=0; i< cityNames.length; i++) {
                quote = weatherFetcher.makeCitySpecificRequest(cityNames[i]);
                System.out.println(quote);
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("compValue", quote);
                myJobResultReceiver.send(100, bundle);
                Thread.sleep(5000);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
