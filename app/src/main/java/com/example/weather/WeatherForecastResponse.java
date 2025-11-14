package com.example.weather;
import java.util.List;

public class WeatherForecastResponse {
    public List<Forecast> list;

    public class Forecast {
        public Main main;
        public List<Weather> weather;
        public String dt_txt;  // 날짜와 시간

        public class Main {
            public float temp;
        }

        public class Weather {
            public String description;
        }
    }
}
