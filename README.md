# Weather App Project

## Project Overview
Weather updates based on city
It utilizes the following technologies:

### Tools and Technologies Used
1. HTML
2. CSS
3. Bootstrap
4. Thymeleaf
5. Java
6. Spring Boot

## Mainly Used Dependencies

### httpClient
- Used to make HTTP requests over the internet.

### Gson
- Library for JSON serialization and deserialization.

## Application Details
- The application is running on **localhost:8080/**
- When you access it, it will go to **index.html**

## API
API (Application Programming Interface) is a set of rules or protocols for building and interacting with software applications. It acts as a middleware layer that allows different software components to communicate with each other.

- OpenWeather API: [http://openweathermap.org/api](http://openweathermap.org/api)
- Sign up on the website and get the API key to use in your project.

## Code Implementation

### Add WeatherResponse class in model package

```java
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
    public static class Sys {
        private String country;
    }

    @Getter
    @Setter
    public static class Weather {
        private int id;
        private String description;
    }

    @Getter
    @Setter
    public static class Main {
        private double temp;
        private String humidity;
    }

    @Getter
    @Setter
    public static class Wind {
        private double speed;
    }
}

### WeatherController Logic

```java
@GetMapping("/weather")
public String getWeather(@RequestParam("city") String city, Model model) {
    // Define the URL for the API request
    String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appId=" + apiKey + "&units=metric";
    // Create RestTemplate to make the HTTP request
    RestTemplate restTemplate = new RestTemplate();
    // Store the response from the API
    WeatherResponse weatherResponse = restTemplate.getForObject(url, WeatherResponse.class); // Call the OpenWeatherMap API

    if (weatherResponse != null) {
        model.addAttribute("city", weatherResponse.getName());
        model.addAttribute("country", weatherResponse.getSys().getCountry());
        model.addAttribute("weatherDescription", weatherResponse.getWeather().get(0).getDescription());
        model.addAttribute("temperature", weatherResponse.getMain().getTemp());
        model.addAttribute("humidity", weatherResponse.getMain().getHumidity());
        model.addAttribute("windSpeed", weatherResponse.getWind().getSpeed());
        String weatherIcon = "wi wi-owm-" + weatherResponse.getWeather().get(0).getId();
        model.addAttribute("weatherIcon", weatherIcon);
    } else {
        model.addAttribute("error", "City not found.");
    }
    return "weather"; // Return weather.html
}
