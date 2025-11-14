package com.example.weather;

public class WeatherResponse {
    public Main main;
    public Weather[] weather;

    public class Main {
        public float temp;
    }

    public class Weather {
        public String description;
    }
}
