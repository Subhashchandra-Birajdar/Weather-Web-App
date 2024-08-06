package com.example.weather_app.controller;

import com.example.weather_app.model.WeatherResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class WeatherController {

    @Value("${api.key}")
    private String apiKey;

    @GetMapping("/")
    public String getIndex(){
        return "index";
    }

    @GetMapping("/weather")
    public String getWeather(@RequestParam("city")String city, Model model){
        // here we will write the logic to getback the data of the city
        // for that we are using weather api
        //first define the url here
        String url ="http://api.openweathermap.org/data/2.5/weather?q="+ city +"&appId="+ apiKey + "&units=metric";
        //create now restTemplate , to make http request to the web api
        RestTemplate restTemplate = new RestTemplate();
        //to store the response back from the api
        // we need to create Another class WeatherResponse
        WeatherResponse weatherResponse = restTemplate.getForObject(url, WeatherResponse.class);// here we call weatherapimap api

        if(weatherResponse!=null){
            model.addAttribute("city",weatherResponse.getName());
            model.addAttribute("country",weatherResponse.getSys().getCountry());
            model.addAttribute("weatherDescription",weatherResponse.getWeather().get(0).getDescription()); //weatherDescription
            model.addAttribute("temperature",weatherResponse.getMain().getTemp());
            model.addAttribute("humidity",weatherResponse.getMain().getHumidity());
            model.addAttribute("windSpeed",weatherResponse.getWind().getSpeed());
            String weatherIcon ="wi wi-owm-"+weatherResponse.getWeather().get(0).getId();
            model.addAttribute("weatherIcon",weatherIcon);
        }else{
            model.addAttribute("error","City not found.");
        }
        return "weather"; // weather.html return again
    }
}
