package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView[] numtvs = new TextView[15];
    TextView[] weatherIcons = new TextView[15];
    TextView mainWeatherIcon;
    HorizontalScrollView fiveWeather;
    int[] numtvIds = {
            R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4, R.id.tv5,
            R.id.tv6, R.id.tv7, R.id.tv8, R.id.tv9, R.id.tv10,
            R.id.tv11, R.id.tv12, R.id.tv13, R.id.tv14, R.id.tv15
    };
    int[] weatherIconIds = {
            R.id.weatherIcon1,R.id.weatherIcon2,R.id.weatherIcon3,R.id.weatherIcon4,R.id.weatherIcon5,
            R.id.weatherIcon6,R.id.weatherIcon7,R.id.weatherIcon8,R.id.weatherIcon9,R.id.weatherIcon10,
            R.id.weatherIcon11,R.id.weatherIcon12,R.id.weatherIcon13,R.id.weatherIcon14,R.id.weatherIcon15,
    };
    EditText cityNameEditText;
    Button getWeatherButton;
    ImageButton fetchButton;
    TextView nowTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityNameEditText = findViewById(R.id.cityNameEditText);
        getWeatherButton = findViewById(R.id.getWeatherButton);
        fetchButton = findViewById(R.id.fetchButton);
        fiveWeather = findViewById(R.id.fiveWeather);

        for (int i = 0; i < numtvIds.length; i++) {
            numtvs[i] = findViewById(numtvIds[i]);
            weatherIcons[i] = findViewById(weatherIconIds[i]);
        }
        nowTextView = findViewById(R.id.nowTextView);
        mainWeatherIcon = findViewById(R.id.mainWeatherIcon);

        nowTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cityNameEditText.requestFocus();
            }
        });

        getWeatherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityName = cityNameEditText.getText().toString().toLowerCase().trim();
                if (!cityName.isEmpty()) {
                    String firstUpperCaseCityName = cityName.substring(0,1).toUpperCase()+cityName.substring(1);
                    nowTextView.setText(firstUpperCaseCityName);
                    fetchWeatherData(cityName);
                    fetchFiveDayForecast(cityName);
                } else if (cityName.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"날씨를 확인할 도시가 입력되지 않았습니다",Toast.LENGTH_SHORT).show();
                }
            }
        });
        fetchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cityName = cityNameEditText.getText().toString().toLowerCase().trim();
                String firstUpperCaseCityName = cityName.substring(0,1).toUpperCase()+cityName.substring(1);
                nowTextView.setText(firstUpperCaseCityName);
                fetchWeatherData(cityName);
                fetchFiveDayForecast(cityName);
            }
        });
    }


    //매핑우린모리더ㅣ멍ㄹㄷㅁ르으악ㅁㄴ
    private String getWeatherIcon(String description) {
        switch (description.toLowerCase()) {
            case "thunderstorm with light rain": return "\uf01d";
            case "thunderstorm with rain": return "\uf01d";
            case "thunderstorm with heavy rain": return "\uf01d";
            case "light thunderstorm": return "\uf01d";
            case "thunderstorm": return "\uf01e";
            case "heavy thunderstorm": return "\uf01e";
            case "ragged thunderstorm": return "\uf01e";
            case "thunderstorm with light drizzle": return "\uf01d";
            case "thunderstorm with drizzle": return "\uf01d";
            case "thunderstorm with heavy drizzle": return "\uf01d";
            case "light intensity drizzle": return "\uf01c";
            case "drizzle": return "\uf01c";
            case "heavy intensity drizzle": return "\uf01c";
            case "light intensity drizzle rain": return "\uf017";
            case "drizzle rain": return "\uf017";
            case "heavy intensity drizzle rain": return "\uf017";
            case "shower rain and drizzle": return "\uf017";
            case "heavy shower rain and drizzle": return "\uf017";
            case "shower drizzle": return "\uf017";
            case "light rain": return "\uf019";
            case "moderate rain": return "\uf019";
            case "heavy intensity rain": return "\uf019";
            case "very heavy rain": return "\uf019";
            case "extreme rain": return "\uf019";
            case "freezing rain": return "\uf01b";
            case "light intensity shower rain": return "\uf017";
            case "shower rain": return "\uf017";
            case "heavy intensity shower rain": return "\uf017";
            case "ragged shower rain": return "\uf017";
            case "light snow": return "\uf01b";
            case "snow": return "\uf01b";
            case "heavy snow": return "\uf01b";
            case "sleet": return "\uf0b5";
            case "light shower sleet": return "\uf0b5";
            case "shower sleet": return "\uf0b5";
            case "light rain and snow": return "\uf0b2";
            case "rain and snow": return "\uf0b2";
            case "light shower snow": return "\uf01b";
            case "shower snow": return "\uf01b";
            case "heavy shower snow": return "\uf01b";
            case "mist": return "\uf014";
            case "smoke": return "\uf062";
            case "haze": return "\uf0b6";
            case "sand/dust whirls": return "\uf063";
            case "fog": return "\uf014";
            case "sand": return "\uf063";
            case "dust": return "\uf063";
            case "volcanic ash": return "\uf0c8";
            case "squalls": return "\uf0c7";
            case "tornado": return "\uf056";
            case "clear sky": return "\uf00d";
            case "few clouds": return "\uf002";
            case "scattered clouds": return "\uf041";
            case "broken clouds": return "\uf013";
            case "overcast clouds": return "\uf013";
            case "tropical storm": return "\uf01d";
            case "hurricane": return "\uf073";
            case "cold": return "\uf076";
            case "hot": return "\uf072";
            case "windy": return "\uf050";
            case "hail": return "\uf015";

            default: return "\uf07b";
        }
    }


    // 현재 날씨
    private void fetchWeatherData(String cityName) {
        WeatherService weatherService = WeatherApiClient.getClient().create(WeatherService.class);
        Call<WeatherResponse> call = weatherService.getCurrentWeather(cityName, getString(R.string.api_key), "metric");

        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful()) {
                    WeatherResponse weatherResponse = response.body();
                    float temperature = weatherResponse.main.temp;
                    String description = weatherResponse.weather[0].description;
                    String weatherIcon = getWeatherIcon(description);
                    TextView tempTextView = findViewById(R.id.tempTextView);
                    TextView descTextView = findViewById(R.id.descTextView);
                    tempTextView.setText(temperature + "°C");
                    mainWeatherIcon.setVisibility(View.VISIBLE);
                    mainWeatherIcon.setText(weatherIcon);
                    fetchButton.setVisibility(View.VISIBLE);
                    fiveWeather.setVisibility(View.VISIBLE);
                    descTextView.setText(description);
                } else {
                    Toast.makeText(getApplicationContext(), "지역명을 찾을 수 없습니다.", Toast.LENGTH_SHORT).show();
                    nowTextView.setText("N/A");
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Log.e("Weather", t.getMessage());
            }
        });
    }

    // 5일간의 날씨
    private void fetchFiveDayForecast(String cityName) {
        WeatherService weatherService = WeatherApiClient.getClient().create(WeatherService.class);
        Call<WeatherForecastResponse> call = weatherService.getFiveDayForecast(cityName, getString(R.string.api_key), "metric");

        call.enqueue(new Callback<WeatherForecastResponse>() {
            @Override
            public void onResponse(Call<WeatherForecastResponse> call, Response<WeatherForecastResponse> response) {
                if (response.isSuccessful()) {
                    WeatherForecastResponse forecastResponse = response.body();

                    if (forecastResponse != null && forecastResponse.list != null) {
                        int count = Math.min(numtvs.length, forecastResponse.list.size());
                        for (int i = 0; i < count; i++) {
                            WeatherForecastResponse.Forecast forecast = forecastResponse.list.get(i);

                            String dateTime = forecast.dt_txt;
                            String date = dateTime.substring(8, 10);  // yyyy-MM-dd
                            String time = dateTime.substring(11, 13); // HH:mm
                            float temp = forecast.main.temp;
                            String description = forecast.weather.get(0).description;
                            String weatherIcon = getWeatherIcon(description);
                            numtvs[i].setText(String.format("%s일 %s시\n%.1f°C\n", date, time, temp));
                            weatherIcons[i].setText(weatherIcon);
                        }
                    } else {
                        Log.e("Weather", "ForecastResponse");
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "5일간의 날씨 정보를 찾을 수 없습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<WeatherForecastResponse> call, Throwable t) {
                Log.e("Weather", t.getMessage());
            }
        });
    }
}
