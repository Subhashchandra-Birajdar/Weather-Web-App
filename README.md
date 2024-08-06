# Weather App Project

## Project Overview
This project has been cloned into the specified path. It utilizes the following technologies:

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
- The application is running on **localhost:8080**.
- When you access it, it will go to **index.html**.

## API
API (Application Programming Interface) is a set of rules or protocols for building and interacting with software applications. It acts as a middleware layer that allows different software components to communicate with each other.

- OpenWeather API: [http://openweathermap.org/api](http://openweathermap.org/api)
- Sign up on the website and get the API key to use in your project.

## Code Implementation

### Add this Class

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
