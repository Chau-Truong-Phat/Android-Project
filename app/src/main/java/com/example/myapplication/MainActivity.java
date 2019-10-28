package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    TextView tv_Temp, tv_Status, tv_Name, tv_Country, tv_Humidity, tv_Cloud, tv_Wind, tv_Date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tv_Temp = (TextView)findViewById(R.id.textView_temp);
        tv_Status = (TextView)findViewById(R.id.textView_Status);
        tv_Name = (TextView)findViewById(R.id.textView_name);
        tv_Country = (TextView)findViewById(R.id.textView_Country);
        tv_Humidity = (TextView)findViewById(R.id.textView_humidity);
        tv_Cloud = (TextView)findViewById(R.id.textView_cloud);
        tv_Wind = (TextView)findViewById(R.id.textView_mill);
        tv_Date = (TextView)findViewById(R.id.textView_day);

        GetCurrentWeatherData("");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void GetCurrentWeatherData(String data){
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        String url = "https://api.openweathermap.org/data/2.5/forecast?q=" + data + "&APPID=2bde50bdcc809a6230f17e9ba8e7f951";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject jsonObjectCity = jsonObject.getJSONObject("city");
                            tv_Name.setText(jsonObjectCity.getString("name"));
                            tv_Country.setText(jsonObjectCity.getString("country"));

                            JSONArray jsonArrayWeatherInTime = jsonObject.getJSONArray("list");
                            JSONObject jsonObjectWeather = jsonArrayWeatherInTime.getJSONObject(0);

                            JSONArray jsonArrayWeather = jsonObjectWeather.getJSONArray("weather");
                            JSONObject jsonWeather = jsonArrayWeather.getJSONObject(0);

                            tv_Status.setText(jsonWeather.getString("description"));

                            String day = jsonObjectWeather.getString("dt");
                            long l = Long.valueOf(day);
                            Date date = new Date(l * 1000L);

                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE dd/MM");
                            String Day = simpleDateFormat.format(date);

                            tv_Humidity.setText(jsonObjectWeather.getJSONObject("main").getString("humidity"));
                            tv_Temp.setText(jsonObjectWeather.getJSONObject("main").getString("temp"));
                            tv_Wind.setText(jsonObjectWeather.getJSONObject("wind").getString("speed"));
                            tv_Date.setText(Day);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        requestQueue.add(stringRequest);
    }
}
