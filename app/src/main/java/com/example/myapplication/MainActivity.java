package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    TextView tv_Temp, tv_Status, tv_Name, tv_Country, tv_Humidity, tv_Cloud, tv_Wind, tv_Date;
    TextView tv_hour0, tv_hour1, tv_hour2, tv_hour3, tv_hour4, tv_hour5;
    TextView tv_DoAm0, tv_DoAm1, tv_DoAm2, tv_DoAm3, tv_DoAm4, tv_DoAm5;
    ImageView image_Icon, image0, image1, image2, image3, image4, image5;
    SearchView searchView;

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
        image_Icon = (ImageView)findViewById(R.id.imageIcon);
        searchView = (SearchView) findViewById(R.id.app_bar_search);

        tv_hour0 = (TextView)findViewById( R.id.textView_hour0 );
        tv_hour1 = (TextView)findViewById( R.id.textView_hour1 );
        tv_hour2 = (TextView)findViewById( R.id.textView_hour2 );
        tv_hour3 = (TextView)findViewById( R.id.textView_hour3 );
        tv_hour4 = (TextView)findViewById( R.id.textView_hour4 );
        tv_hour5 = (TextView)findViewById( R.id.textView_hour5 );

        tv_DoAm0 = (TextView)findViewById( R.id.textView_hour0DoAm );
        tv_DoAm1 = (TextView)findViewById( R.id.textView_hour1DoAm );
        tv_DoAm2 = (TextView)findViewById( R.id.textView_hour2DoAm );
        tv_DoAm3 = (TextView)findViewById( R.id.textView_hour3DoAm );
        tv_DoAm4 = (TextView)findViewById( R.id.textView_hour4DoAm );
        tv_DoAm5 = (TextView)findViewById( R.id.textView_hour5DoAm );

        image0 = (ImageView)findViewById( R.id.imageView_hour0 );
        image1 = (ImageView)findViewById( R.id.imageView_hour1 );
        image2 = (ImageView)findViewById( R.id.imageView_hour2 );
        image3 = (ImageView)findViewById( R.id.imageView_hour3 );
        image4 = (ImageView)findViewById( R.id.imageView_hour4 );
        image5 = (ImageView)findViewById( R.id.imageView_hour5 );

        GetCurrentWeatherData("Hanoi");
        
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                GetCurrentWeatherData(s);
//                return true;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                GetCurrentWeatherData(s);
//                return false;
//            }
//        });
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
//                            Log.d(response);
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject jsonObjectCity = jsonObject.getJSONObject("city");
                            tv_Name.setText(jsonObjectCity.getString("name"));
                            tv_Country.setText(jsonObjectCity.getString("country"));

                            JSONArray jsonArrayWeatherInTime = jsonObject.getJSONArray("list");
                            JSONObject jsonObjectWeather = jsonArrayWeatherInTime.getJSONObject(0);


                            ArrayList<JSONObject> listJson5Hours = new ArrayList<JSONObject>();
                            listJson5Hours.add(jsonArrayWeatherInTime.getJSONObject(0));
                            listJson5Hours.add(jsonArrayWeatherInTime.getJSONObject(1));
                            listJson5Hours.add(jsonArrayWeatherInTime.getJSONObject(2));
                            listJson5Hours.add(jsonArrayWeatherInTime.getJSONObject(3));
                            listJson5Hours.add(jsonArrayWeatherInTime.getJSONObject(4));

                            JSONArray jsonArrayWeather = jsonObjectWeather.getJSONArray("weather");
                            JSONObject jsonWeather = jsonArrayWeather.getJSONObject(0);
                            String icon = jsonWeather.getString("icon");
                            String iconUrl = "http://openweathermap.org/img/w/" + icon + ".png";

                            Picasso.with(MainActivity.this).load(iconUrl).into(image_Icon);

                            tv_Status.setText(jsonWeather.getString("description"));

                            String day = jsonObjectWeather.getString("dt");
                            long l = Long.valueOf(day);
                            Date date = new Date(l * 1000L);

                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE dd/MM/yyyy");
                            String Day = simpleDateFormat.format(date);

                            tv_Humidity.setText(jsonObjectWeather.getJSONObject("main").getString("humidity") + " g/m³");
                            tv_Temp.setText(jsonObjectWeather.getJSONObject("main").getString("temp") + " °C");
                            tv_Wind.setText(jsonObjectWeather.getJSONObject("wind").getString("speed") + " knot");
                            tv_Cloud.setText(jsonObjectWeather.getJSONObject("clouds").getString("all") + " %");
                            tv_Date.setText(Day);

                            CustomWeatherHour(tv_hour0,tv_DoAm0,image0,jsonWeather);
                            Picasso.with(MainActivity.this).load("http://openweathermap.org/img/w/" + icon + ".png").into(image_Icon);
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

    public void CustomWeatherHour(TextView tvHour, TextView tvHumidity, ImageView img, JSONObject jsonObject){
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE HH:mm");
            String time = simpleDateFormat.format(jsonObject.getString("dt_txt"));
            tvHour.setText( time );
        } catch (JSONException e) {
            e.printStackTrace();
        }

//        tvHour.setText();
    }
}
