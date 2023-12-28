package com.ing.casestudy.LittleWeatherForecast.service;


import com.ing.casestudy.LittleWeatherForecast.model.IForecastData;
import com.ing.casestudy.LittleWeatherForecast.model.LittleForecastData;
import com.ing.casestudy.LittleWeatherForecast.model.MainWeatherData;
import com.ing.casestudy.LittleWeatherForecast.properties.LittleWeatherAppConfig;
import com.ing.casestudy.LittleWeatherForecast.properties.SpringConfiguration;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class WeatherForecastService extends MappingJackson2HttpMessageConverter {

    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";

    private static WeatherForecastService weatherForecastServiceInstance = new WeatherForecastService();

    public static WeatherForecastService getInstance() {
        return weatherForecastServiceInstance;
    }

    private WeatherForecastService() {
        setPrettyPrint(true);
    }

    public List<IForecastData> getForecastDataFromService(String city) throws ParseException {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        LittleWeatherAppConfig appConfig = applicationContext.getBean(LittleWeatherAppConfig.class);

        String endpoint = BASE_URL + "forecast?q=" + city + "&mode=json&appid=" + appConfig.getOpenWeatherApiKey() + "&units=metric";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(endpoint, String.class);

        List<IForecastData> weatherList = new ArrayList<>();
        JSONObject root = new JSONObject(result);

        JSONArray weatherData = root.getJSONArray("list");

        for (int i = 0; i < weatherData.length(); i++) {
            JSONObject arrayElement = weatherData.getJSONObject(i);
            JSONObject main = arrayElement.getJSONObject("main");


            int temp = main.getInt("temp");
            int feelsLike = main.getInt("feels_like");
            int pressure = main.getInt("pressure");
            int humidity = main.getInt("humidity");
            int temp_min = main.getInt("temp_min");
            int temp_max = main.getInt("temp_max");
            int temp_kf = main.getInt("temp_kf");
            int sea_level = main.getInt("sea_level");
            int grnd_level = main.getInt("grnd_level");
            String dateTxt = arrayElement.getString("dt_txt");

            SetMainWeatherData((double) temp, (double) feelsLike, (int) pressure, humidity, grnd_level, sea_level, temp_kf, temp_max, temp_min, dateTxt);

            LittleForecastData littleForecastData = getLittleForecastData(temp_max, (double) feelsLike, humidity, dateTxt);

            if (CheckWeatherDateInReportRange(dateTxt)) {
                weatherList.add(littleForecastData);
            }
        }
        return weatherList;
    }

    public static LittleForecastData getLittleForecastData(int temp_max, double feelsLike, int humidity, String dateTxt) {
        LittleForecastData littleForecastData = new LittleForecastData();
        littleForecastData.setTempMax(temp_max);
        littleForecastData.setFeelsLike(feelsLike);
        littleForecastData.setHumidity(humidity);
        littleForecastData.setDtTxt(dateTxt);
        return littleForecastData;
    }

    public static void SetMainWeatherData(double temp, double feelsLike, int pressure, int humidity, int grnd_level, int sea_level, int temp_kf, int temp_max, int temp_min, String dateTxt) {
        MainWeatherData mainWeatherData = new MainWeatherData();

        mainWeatherData.setTemp(temp);
        mainWeatherData.setFeelsLike(feelsLike);
        mainWeatherData.setPressure(pressure / 10);
        mainWeatherData.setHumidity(humidity);
        mainWeatherData.setGrndLevel(grnd_level);
        mainWeatherData.setSeaLevel(sea_level);
        mainWeatherData.setTempKf(temp_kf);
        mainWeatherData.setTempMax(temp_max);
        mainWeatherData.setTempMin(temp_min);
        mainWeatherData.setDtTxt(dateTxt);
    }

    public boolean CheckWeatherDateInReportRange(String forecastDate) {

        String dateStart = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        //HH converts hour in 24 hours format (0-23), day calculation
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date d1 = null;
        Date d2 = null;

        try {
            d1 = format.parse(dateStart);
            d2 = format.parse(forecastDate);

            if (d2.compareTo(d1) < 0)
            {
                /***
                 * the forecasting date can not be in past
                 */
                return false;
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        //in milliseconds
        long diff = d2.getTime() - d1.getTime();

        long diffHours = diff / (60 * 60 * 1000) ;

        return diffHours <= 48;
    }
}
