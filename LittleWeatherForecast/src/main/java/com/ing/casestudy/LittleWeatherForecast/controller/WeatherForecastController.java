package com.ing.casestudy.LittleWeatherForecast.controller;

import com.ing.casestudy.LittleWeatherForecast.model.IForecastData;
import com.ing.casestudy.LittleWeatherForecast.service.WeatherForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@RestController
public class WeatherForecastController {
    private final WeatherForecastService weatherForecastService;

    @Autowired
    public WeatherForecastController(WeatherForecastService weatherForecastService) {
        this.weatherForecastService = weatherForecastService;
    }

    @RequestMapping("/")
    String home() {
        return "Hello, this is the starting point of ING Weather Forecasting Services!";
    }

    @RequestMapping("forecast/{city}")
    public List<IForecastData> getForecastDataFromService(
            @PathVariable String city) throws ParseException {
        return this.weatherForecastService.getForecastDataFromService(city);
    }
}
