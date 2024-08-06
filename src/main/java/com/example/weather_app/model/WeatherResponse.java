package com.example.weather_app.model;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class WeatherResponse {

    private String name;
    private Sys sys;
    private List<Weather> weather;
    private Main main;
    private Wind wind;

    @Getter
    @Setter
    public static class Sys{
        private String country;
    }

    @Getter
    @Setter
    public static class Weather{
        private int id;
        private String description;
    }

    @Getter
    @Setter
    public static class Main{
        private double temp;
        private String humidity;
    }

    @Getter
    @Setter
    public static class Wind{
        private double speed;
    }
}
