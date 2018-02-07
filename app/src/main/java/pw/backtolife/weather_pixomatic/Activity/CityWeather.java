package pw.backtolife.weather_pixomatic.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pw.backtolife.weather_pixomatic.Adapter.RecyclerViewForecastAdapter;
import pw.backtolife.weather_pixomatic.Network.RequestManager;
import pw.backtolife.weather_pixomatic.R;
import pw.backtolife.weather_pixomatic.WeatherInfo.Forecast;
import pw.backtolife.weather_pixomatic.WeatherInfo.WeatherModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CityWeather extends AppCompatActivity {

    private String city;
    private String cityShort;
    private AppCompatTextView cityTextView;
    private AppCompatTextView conditionText;
    private AppCompatTextView conditionTemp;
    private AppCompatTextView lastUupdate;
    private AppCompatTextView sunriseTime;
    private AppCompatTextView sunsetTime;
    private AppCompatTextView speed;
    private AppCompatTextView humidity;
    private AppCompatTextView visibility;
    private AppCompatTextView direction;
    private RecyclerView recyclerView;
    private RecyclerViewForecastAdapter forecastAdapter;
    private ProgressBar progressBar;
    private RelativeLayout relativeLayout;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_weather);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cityTextView = findViewById(R.id.tv_country);
        conditionText = findViewById(R.id.tv_condition_text);
        conditionTemp = findViewById(R.id.tv_condition_temp);
        lastUupdate = findViewById(R.id.tv_last_update);
        sunriseTime = findViewById(R.id.tv_sunrise_time);
        sunsetTime = findViewById(R.id.tv_sunset_time);
        speed = findViewById(R.id.tv_speed);
        humidity = findViewById(R.id.tv_humidity);
        visibility = findViewById(R.id.tv_visibility);
        direction = findViewById(R.id.tv_direction);

        relativeLayout = findViewById(R.id.rl_cw);
        linearLayout = findViewById(R.id.ll_cw);

        linearLayout.setVisibility(View.GONE);

        progressBar = new ProgressBar(this,null,android.R.attr.progressBarStyleLarge);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(100,100);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        relativeLayout.addView(progressBar,params);
        progressBar.setVisibility(View.VISIBLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);

        recyclerView = findViewById(R.id.rv_forecast);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        forecastAdapter = new RecyclerViewForecastAdapter(this);
        recyclerView.setAdapter(forecastAdapter);

        Intent intent = getIntent();
        city = intent.getStringExtra("city");
        cityShort = intent.getStringExtra("city_short");

        String s1 = "select%20*%20from%20weather.forecast%20where%20woeid%20in%20" +
                "(select%20woeid%20from%20geo.places(1)%20where%20text%3D\"";
        String s2 = "%2C%20";
        String s3 = "\")%20and%20u%3D%27c%27";
        String query = s1 + city + s2 + cityShort + s3;

        Map<String, String> oop = new HashMap<>();
        oop.put("q", query);
        oop.put("format", "json");
        oop.put("store", "%3A%2F%2Fdatatables.org%2Falltableswithkeys");

        RequestManager.Factory.getInstance(this).getWeather(oop)
                .enqueue(new Callback<WeatherModel>() {
                    @Override
                    public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            cityTextView.setText(city);
                            conditionText.setText(response.body().getQuery().getResults()
                                    .getChannel().getItem().getCondition().getText());
                            conditionTemp.setText(response.body().getQuery().getResults()
                                    .getChannel().getItem().getCondition().getTemp());
                            lastUupdate.setText(response.body().getQuery().getResults().getChannel()
                                    .getLastBuildDate());
                            sunriseTime.setText(response.body().getQuery().getResults().getChannel()
                                    .getAstronomy().getSunrise());
                            sunsetTime.setText(response.body().getQuery().getResults().getChannel()
                                    .getAstronomy().getSunset());
                            speed.setText(response.body().getQuery().getResults().getChannel()
                                    .getWind().getSpeed());
                            humidity.setText(response.body().getQuery().getResults().getChannel()
                                    .getAtmosphere().getHumidity());
                            visibility.setText(response.body().getQuery().getResults().getChannel()
                                    .getAtmosphere().getVisibility());
                            direction.setText(response.body().getQuery().getResults().getChannel()
                                    .getWind().getDirection());

                            forecastAdapter.setData(response.body().getQuery()
                                    .getResults().getChannel().getItem().getForecast());

                            progressBar.setVisibility(View.GONE);
                            linearLayout.setVisibility(View.VISIBLE);

                            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        }
                    }

                    @Override
                    public void onFailure(Call<WeatherModel> call, Throwable t) {

                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
