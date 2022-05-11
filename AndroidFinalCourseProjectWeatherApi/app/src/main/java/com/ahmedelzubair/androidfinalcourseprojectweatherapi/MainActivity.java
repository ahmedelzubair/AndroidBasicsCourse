package com.ahmedelzubair.androidfinalcourseprojectweatherapi;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ahmedelzubair.androidfinalcourseprojectweatherapi.api.ApiClient;
import com.ahmedelzubair.androidfinalcourseprojectweatherapi.model.ApiResponse;
import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    public static final String TAG = MainActivity.class.getSimpleName();
    private EditText etSearch;
    private Button btnSearch;
    private ImageView ivDayStatus;

    private TextView tvTemp, tvCountry, tvCity, tvLong, tvLat, tvCurrentTime, tvZoneId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setViewsListeners();
        loadWeatherData("Khartoum");

    }

    private void setViewsListeners() {
        btnSearch.setOnClickListener(view -> {
            String searchTerm = etSearch.getText().toString().trim();
            etSearch.setText("");
            loadWeatherData(searchTerm);
        });
    }

    private void loadWeatherData(String search) {

        ApiClient.getApiService()
                .getWeather(search).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

                Log.i(TAG, "onResponse: " + response);
                ApiResponse apiResponse = response.body();
                showData(apiResponse);

                Log.i(TAG, "onResponse: apiResponse = " + apiResponse.getRequest().getQuery());

            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

                Log.e(TAG, "onFailure: ", t);

            }
        });

    }

    private void showData(ApiResponse apiResponse) {

        String imageUrl = apiResponse.getCurrent().getWeatherIcons()
                .get(0).replace("https", "http");

        Glide.with(this)
                .load(imageUrl)
                .into(ivDayStatus);

        tvCity.setText("City : " + apiResponse.getLocation().getName());
        tvTemp.setText("Temp : " + apiResponse.getCurrent().getTemperature());
        tvCountry.setText("Country : " + apiResponse.getLocation().getCountry());
        tvLong.setText("Longitute : " + apiResponse.getLocation().getLon());
        tvLat.setText("Latitute : " + apiResponse.getLocation().getLat());
        tvCurrentTime.setText("Time : " + apiResponse.getLocation().getLocaltime());
        tvZoneId.setText("Zone ID : " + apiResponse.getLocation().getTimezoneId());

    }

    private void initViews() {
        etSearch = findViewById(R.id.etSearch);
        btnSearch = findViewById(R.id.btnSearch);
        ivDayStatus = findViewById(R.id.ivDayStatus);
        tvTemp = findViewById(R.id.tvTemp);
        tvCountry = findViewById(R.id.tvCountry);
        tvCity = findViewById(R.id.tvCity);
        tvLong = findViewById(R.id.tvLong);
        tvLat = findViewById(R.id.tvLat);
        tvCurrentTime = findViewById(R.id.tvCurrentTime);
        tvZoneId = findViewById(R.id.tvZoneId);
    }


}