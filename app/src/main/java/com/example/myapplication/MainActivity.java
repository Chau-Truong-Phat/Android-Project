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

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.CancellationException;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    TextView tv_Temp, tv_Status, tv_Name, tv_Country, tv_Humidity, tv_Cloud, tv_Wind, tv_Date;
    TextView tv_hour0, tv_hour1, tv_hour2, tv_hour3, tv_hour4, tv_hour5;
    TextView tv_DoAm0, tv_DoAm1, tv_DoAm2, tv_DoAm3, tv_DoAm4, tv_DoAm5;
    ImageView image_Icon, image0, image1, image2, image3, image4, image5;

    TextView tv_ngay0, tv_ngay1, tv_ngay2, tv_ngay3, tv_ngay4, tv_ngay5;
    TextView tv_temp0min, tv_temp1min, tv_temp2min, tv_temp3min, tv_temp4min, tv_temp5min;
    TextView tv_temp0max, tv_temp1max, tv_temp2max, tv_temp3max, tv_temp4max, tv_temp5max;
    ImageView image_ngay0, image_ngay1, image_ngay2, image_ngay3, image_ngay4, image_ngay5;

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

        tv_ngay0 = (TextView)findViewById( R.id.textView_ngay0 );
        tv_ngay1 = (TextView)findViewById( R.id.textView_ngay1 );
        tv_ngay2 = (TextView)findViewById( R.id.textView_ngay2 );
        tv_ngay3 = (TextView)findViewById( R.id.textView_ngay3 );
        tv_ngay4 = (TextView)findViewById( R.id.textView_ngay4 );
        tv_ngay5 = (TextView)findViewById( R.id.textView_ngay5 );

        tv_temp0min = (TextView)findViewById( R.id.textView_ngay0Min );
        tv_temp1min = (TextView)findViewById( R.id.textView_ngay1Min );
        tv_temp2min = (TextView)findViewById( R.id.textView_ngay2Min );
        tv_temp3min = (TextView)findViewById( R.id.textView_ngay3Min );
        tv_temp4min = (TextView)findViewById( R.id.textView_ngay4Min );
        tv_temp5min = (TextView)findViewById( R.id.textView_ngay5Min );

        tv_temp0max = (TextView)findViewById( R.id.textView_ngay0Max );
        tv_temp1max = (TextView)findViewById( R.id.textView_ngay1Max );
        tv_temp2max = (TextView)findViewById( R.id.textView_ngay2Max );
        tv_temp3max = (TextView)findViewById( R.id.textView_ngay3Max );
        tv_temp4max = (TextView)findViewById( R.id.textView_ngay4Max );
        tv_temp5max = (TextView)findViewById( R.id.textView_ngay5Max );

        image_ngay0 = (ImageView)findViewById( R.id.imageView_ngay0 );
        image_ngay1 = (ImageView)findViewById( R.id.imageView_ngay1 );
        image_ngay2 = (ImageView)findViewById( R.id.imageView_ngay2 );
        image_ngay3 = (ImageView)findViewById( R.id.imageView_ngay3 );
        image_ngay4 = (ImageView)findViewById( R.id.imageView_ngay4 );
        image_ngay5 = (ImageView)findViewById( R.id.imageView_ngay5 );

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
                            JSONObject jsonObjectWeatherDate = jsonArrayWeatherInTime.getJSONObject(0);

                            // Main
                            JSONArray jsonArrayWeather = jsonObjectWeatherDate.getJSONArray("weather");
                            JSONObject jsonWeather = jsonArrayWeather.getJSONObject(0);

                            String icon = jsonWeather.getString("icon");
                            String iconUrl = "http://openweathermap.org/img/w/" + icon + ".png";

                            Picasso.get().load(iconUrl).into(image_Icon);
//                            Picasso.get().load(iconUrl).into(image_Icon);
//                            Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(imageView);

                            tv_Status.setText(jsonWeather.getString("description"));
                            tv_Humidity.setText(jsonObjectWeatherDate.getJSONObject("main").getString("humidity") + " g/m³");
                            tv_Temp.setText(ChangedCTempToFTemp(jsonObjectWeatherDate.getJSONObject("main").getString("temp")) + " °C");
                            tv_Wind.setText(jsonObjectWeatherDate.getJSONObject("wind").getString("speed") + " knot");
                            tv_Cloud.setText(jsonObjectWeatherDate.getJSONObject("clouds").getString("all") + " %");
                            tv_Date.setText(DateFormatter(jsonObjectWeatherDate.getString("dt")));
//                            tv_Temp.setText(icon);

                            CustomWeatherHour(tv_hour0, tv_DoAm0, image0,jsonArrayWeatherInTime.getJSONObject(0));
                            CustomWeatherHour(tv_hour1, tv_DoAm1, image1,jsonArrayWeatherInTime.getJSONObject(1));
                            CustomWeatherHour(tv_hour2, tv_DoAm2, image2,jsonArrayWeatherInTime.getJSONObject(2));
                            CustomWeatherHour(tv_hour3, tv_DoAm3, image3,jsonArrayWeatherInTime.getJSONObject(3));
                            CustomWeatherHour(tv_hour4, tv_DoAm4, image4,jsonArrayWeatherInTime.getJSONObject(4));
                            CustomWeatherHour(tv_hour5, tv_DoAm5, image5,jsonArrayWeatherInTime.getJSONObject(5));

                            CustomWeatherDate(tv_ngay0,tv_temp0min,tv_temp0max,image_ngay0,jsonArrayWeatherInTime.getJSONObject(0));
                            CustomWeatherDate(tv_ngay1,tv_temp1min,tv_temp1max,image_ngay1,jsonArrayWeatherInTime.getJSONObject(8));
                            CustomWeatherDate(tv_ngay2,tv_temp2min,tv_temp2max,image_ngay2,jsonArrayWeatherInTime.getJSONObject(16));
                            CustomWeatherDate(tv_ngay3,tv_temp3min,tv_temp3max,image_ngay3,jsonArrayWeatherInTime.getJSONObject(24));
                            CustomWeatherDate(tv_ngay4,tv_temp4min,tv_temp4max,image_ngay4,jsonArrayWeatherInTime.getJSONObject(32));

                            Picasso.get().load("http://openweathermap.org/img/wn/10d@2x.png").into(image_Icon);
                            //Picasso.with(MainActivity.this).load("http://openweathermap.org/img/w/" + "02d" + ".png").into(image_Icon);
                            //Picasso.with(MainActivity.this).load("http://openweathermap.org/img/w/"+icon+".png").into(image_Icon);
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

    public String hourFormatter(String day){
        long l = Long.valueOf(day);
        Date date = new Date(l * 1000L);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        return simpleDateFormat.format(date);
    }

    public void CustomWeatherHour(TextView tvHour, TextView tvHumidity, ImageView imgView,JSONObject jsonObject){
        try {
            String time = hourFormatter(jsonObject.getString("dt"));

            tvHumidity.setText(jsonObject.getJSONObject("main").getString("humidity") + " g/m³");
            tvHour.setText(time);

            JSONArray jsonArrayWeather = jsonObject.getJSONArray("weather");
            JSONObject jsonWeather = jsonArrayWeather.getJSONObject(0);

            String icon = jsonWeather.getString("icon");
            String iconUrl = "http://openweathermap.org/img/w/" + icon + ".png";
            Picasso.get().load(iconUrl).into(imgView);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void CustomWeatherDate(TextView tvDate,TextView tvTempMin, TextView tvTempMax, ImageView imgView,JSONObject jsonObject){
        try {
            long l = Long.valueOf(jsonObject.getString("dt"));
            Date date = new Date(l * 1000L);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM");
            String time = simpleDateFormat.format(date);
            tvDate.setText(time);

            tvTempMin.setText(ChangedCTempToFTemp(jsonObject.getJSONObject("main").getString("temp_min")) + " °C");
            tvTempMax.setText(ChangedCTempToFTemp(jsonObject.getJSONObject("main").getString("temp_max")) + " °C");

            JSONArray jsonArrayWeather = jsonObject.getJSONArray("weather");
            JSONObject jsonWeather = jsonArrayWeather.getJSONObject(0);

            String icon = jsonWeather.getString("icon");
            String iconUrl = "http://openweathermap.org/img/w/" + icon + ".png";
            Picasso.get().load(iconUrl).into(imgView);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String DateFormatter(String day){
        long l = Long.valueOf(day);
        Date date = new Date(l * 1000L);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE dd/MM/yyyy");
        return simpleDateFormat.format(date);
    }

    public String ChangedCTempToFTemp(String fTemp){
        DecimalFormat df = new DecimalFormat("#.00");
        Double x = Double.parseDouble(fTemp) - 273.15;
        return df.format(x);
    }
}
