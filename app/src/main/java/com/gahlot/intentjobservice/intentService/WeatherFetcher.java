package com.gahlot.intentjobservice.intentService;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class WeatherFetcher {
    private static WeatherFetcher instance = null;

    private String link = "http://api.openweathermap.org/data/2.5/weather?q=bangalore&units=metric&appid=f2505a1aa5e4f87eb13d9a1c087208e1";
    private static final String TAG = "QuoteFetcher";

    private WeatherFetcher() {

    }

    public synchronized static WeatherFetcher getInstance() {
        if (instance == null) {
            instance = new WeatherFetcher();
        }
        return instance;
    }

    /**
     * Method to make json object request where json response starts wtih {
     * */
    public ArrayList<String> makeRequest() throws IOException {
        ArrayList<String> coord = new ArrayList<>();
        URL url = new URL(link);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("GET");
        con.setDoOutput(true);
        con.connect();

        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        br.close();
        String jsonString = sb.toString();
        JSONArray jsonArray = null;
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = new JSONObject(jsonString);
            JSONObject json = new JSONObject(jsonObject.getString("main"));
            //for (int i=0; i<jsonArray.length(); i++) {
                //jsonObject = jsonArray.getJSONObject(i);
                coord.add(json.getString("temp"));
                coord.add(json.getString("feels_like"));
                coord.add(json.getString("temp_min"));
                coord.add(json.getString("temp_max"));
                coord.add(json.getString("humidity"));
                Log.i(TAG, "makeRequest: " + coord);
            //}
            jsonObject = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return coord;
    }

    public ArrayList<String> makeCitySpecificRequest(String cityName) throws IOException {
        ArrayList<String> coord = new ArrayList<>();
        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&units=metric&appid=f2505a1aa5e4f87eb13d9a1c087208e1");
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("GET");
        con.setDoOutput(true);
        con.connect();

        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        br.close();
        String jsonString = sb.toString();
        JSONArray jsonArray = null;
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = new JSONObject(jsonString);
            JSONObject json = new JSONObject(jsonObject.getString("main"));
            //for (int i=0; i<jsonArray.length(); i++) {
            //jsonObject = jsonArray.getJSONObject(i);
            coord.add(cityName);
            coord.add(json.getString("temp"));
            coord.add(json.getString("feels_like"));
            coord.add(json.getString("temp_min"));
            coord.add(json.getString("temp_max"));
            coord.add(json.getString("humidity"));
            Log.i(TAG, "makeRequest: " + coord);
            //}
            jsonObject = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return coord;
    }
}
