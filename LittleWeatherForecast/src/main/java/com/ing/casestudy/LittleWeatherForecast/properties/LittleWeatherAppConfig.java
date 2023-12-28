package com.ing.casestudy.LittleWeatherForecast.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class LittleWeatherAppConfig {
    
    @Value("${openweather.apikey}")
    private String openWeatherApiKey;

    public String getOpenWeatherApiKey() {
        return openWeatherApiKey;
    }
}
