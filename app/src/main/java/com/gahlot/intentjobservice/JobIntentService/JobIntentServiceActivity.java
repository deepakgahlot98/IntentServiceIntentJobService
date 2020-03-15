package com.gahlot.intentjobservice.JobIntentService;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gahlot.intentjobservice.R;
import com.gahlot.intentjobservice.intentService.MyResultReceiver;

import java.util.ArrayList;

public class JobIntentServiceActivity extends AppCompatActivity implements MyResultReceiver.GetResultInterface {

    private TextView cityName, cityTemp, cityRealFeel, cityMinTemp, CityMaxTemp, cityHumidity;
    private Button getInfoButton;
    private String[] cityNames = {"dehradun","New Delhi","bangalore","kolkata"};
    private ArrayList<String> list = new ArrayList<>();
    public MyResultReceiver myJobResultReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_intent_service);

        init();
        myJobResultReceiver = new MyResultReceiver(new Handler());
        myJobResultReceiver.setReceiver(this);
    }

    @Override
    public void getResult(int resultCode, Bundle resultData) {
        if(resultData!=null) {
            switch (resultCode) {
                case 100:
                    list = resultData.getStringArrayList("compValue");
                    cityName.setText(list.get(0));
                    cityTemp.setText("Temp C: " + list.get(1));
                    cityRealFeel.setText("Real Feel Temp C: " + list.get(2));
                    cityMinTemp.setText("Min Temp C: " + list.get(3));
                    CityMaxTemp.setText("Max Temp C: " + list.get(4));
                    cityHumidity.setText("Humidity: " + list.get(5));
                    break;
            }
        }
    }

    public void GetInfoButton(View view) {
        Intent mIntent = new Intent(this, MyJobIntentService.class);
        mIntent.putExtra("result",myJobResultReceiver);
        mIntent.putExtra("maxCountValue", cityNames);
        MyJobIntentService.enqueueWork(this, mIntent);

    }


    void init() {
        cityName = findViewById(R.id.city_name);
        cityTemp = findViewById(R.id.city_temp);
        cityRealFeel = findViewById(R.id.city_realFeel);
        cityMinTemp = findViewById(R.id.city_mintemp);
        CityMaxTemp = findViewById(R.id.city_maxtemp);
        cityHumidity = findViewById(R.id.city_humidity);
        getInfoButton = findViewById(R.id.get_weather_btn);
    }
}
