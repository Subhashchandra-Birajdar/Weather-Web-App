<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Weather App</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa;
            padding: 20px;
        }
        h1, h2, h3 {
            margin-bottom: 20px;
        }
        .dependency, .api, .code-section {
            margin-bottom: 20px;
        }
        .card {
            margin-bottom: 20px;
        }
        .code-block {
            background-color: #e9ecef;
            padding: 10px;
            border-radius: 5px;
            font-family: monospace;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1 class="text-center">Weather App Project</h1>
        
        <div class="card">
            <div class="card-body">
                <h2 class="card-title">Project Overview</h2>
                <p>Project cloned in the specified path.</p>
                <h3>Tools and Technologies Used</h3>
                <ol>
                    <li>HTML</li>
                    <li>CSS</li>
                    <li>Bootstrap</li>
                    <li>Thymeleaf</li>
                    <li>Java</li>
                    <li>Spring Boot</li>
                </ol>
            </div>
        </div>
        
        <div class="card">
            <div class="card-body">
                <h2 class="card-title">Mainly Used Dependencies</h2>
                
                <div class="dependency">
                    <h3>httpClient</h3>
                    <p>Used to make HTTP requests over the internet.</p>
                </div>
                
                <div class="dependency">
                    <h3>Gson</h3>
                    <p>Library for JSON serialization and deserialization.</p>
                </div>
            </div>
        </div>
        
        <div class="card">
            <div class="card-body">
                <h2 class="card-title">Application Details</h2>
                <p>The application is working on <strong>localhost:8080</strong>.</p>
                <p>When you hit it, it will go to <strong>index.html</strong>.</p>
            </div>
        </div>
        
        <div class="card">
            <div class="card-body">
                <h2 class="card-title">API</h2>
                <p>API (Application Programming Interface) is a set of rules or protocols for building and interacting with software applications. It acts as a middleware layer that allows different software components to communicate with each other.</p>
                <p>OpenWeather API: <a href="http://openweathermap.org/api" target="_blank">http://openweathermap.org/api</a></p>
                <p>Sign up on this website and get the API key to use in your project.</p>
            </div>
        </div>

        <div class="card">
            <div class="card-body">
                <h2 class="card-title">Code Implementation</h2>
                <h3>Add Weatherclass class in model package</h3>
                <div class="code-block">
                    <pre>
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
                    </pre>
                </div>

                <h3>WeatherController Logic</h3>
                <div class="code-block">
                    <pre>
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
                    </pre>
                </div>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
