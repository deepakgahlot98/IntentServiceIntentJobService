package com.gahlot.intentjobservice.intentService;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.gahlot.intentjobservice.MainActivity;
import com.gahlot.intentjobservice.R;

import java.util.ArrayList;

public class IntentServiceActivity extends AppCompatActivity implements MyResultReceiver.GetResultInterface {

    private MyResultReceiver myResultReceiver;
    private TextView cityTemp, cityRealFeel, cityTempMin, cityTempMax, cityHumidity;
    private ArrayList<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_service);
        cityTemp = findViewById(R.id.textTemp);
        cityRealFeel = findViewById(R.id.textfeels);
        cityTempMin = findViewById(R.id.texttempmin);
        cityTempMax = findViewById(R.id.texttempmax);
        cityHumidity = findViewById(R.id.texthumadity);
        myResultReceiver = new MyResultReceiver(new Handler());
        myResultReceiver.setReceiver(this);
    }

    @Override
    public void getResult(int resultCode, Bundle resultData) {
        if(resultData!=null) {
            switch (resultCode) {
                case 100:
                    list = resultData.getStringArrayList("compValue");
                    cityTemp.setText("Temp C: " + list.get(0));
                    cityRealFeel.setText("Real Feel Temp C: " + list.get(1));
                    cityTempMin.setText("Min Temp C: " + list.get(2));
                    cityTempMax.setText("Max Temp C: " + list.get(3));
                    cityHumidity.setText("Humidity: " + list.get(4));
                    break;
            }
        }
    }

    public void getNumber(View view) {
        Intent intent = new Intent(IntentServiceActivity.this,WeatherIntentService.class);
        intent.putExtra("result",myResultReceiver);
        startService(intent);
    }
}
