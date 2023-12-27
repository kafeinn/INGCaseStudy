package com.ing.casestudy.LittleWeatherForecast.model;

import com.fasterxml.jackson.annotation.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "temp",
        "feels_like",
        "temp_min",
        "temp_max",
        "pressure",
        "sea_level",
        "grnd_level",
        "humidity",
        "temp_kf",
        "dt_txt"
})
public class MainWeatherData implements IForecastData {
    @JsonProperty("temp")
    private Double temp;
    @JsonProperty("feels_like")
    private Double feels_like;
    @JsonProperty("temp_min")
    private Double tempMin;
    @JsonProperty("temp_max")
    private Double tempMax;
    @JsonProperty("pressure")
    private Double pressure;
    @JsonProperty("sea_level")
    private Double seaLevel;
    @JsonProperty("grnd_level")
    private Double grndLevel;
    @JsonProperty("humidity")
    private Integer humidity;
    @JsonProperty("temp_kf")
    private Integer tempKf;
    @JsonProperty("dt_txt")
    private String dtTxt;


    @JsonProperty("temp")
    public Double getTemp() {
        return temp;
    }

    @JsonProperty("temp")
    public void setTemp(Double temp) {
        this.temp = temp;
    }
    @JsonProperty("feels_like")
    public Double getFeelsLike() { return feels_like; }

    @JsonProperty("feels_like")
    public void setFeelsLike(Double feels_like) { this.feels_like = feels_like; }

    @JsonProperty("temp_min")
    public Double getTempMin() {
        return tempMin;
    }

    @JsonProperty("temp_min")
    public void setTempMin(int temp_min) {
        this.tempMin = (double) temp_min;
    }

    @JsonProperty("temp_max")
    public Double getTempMax() {
        return tempMax;
    }

    @JsonProperty("temp_max")
    public void setTempMax(int temp_max) {
        this.tempMax = (double) temp_max;
    }

    @JsonProperty("pressure")
    public Double getPressure() {
        return pressure;
    }

    @JsonProperty("pressure")
    public void setPressure(int i) {
        this.pressure = (double) i;
    }

    @JsonProperty("sea_level")
    public Double getSeaLevel() {
        return seaLevel;
    }

    @JsonProperty("sea_level")
    public void setSeaLevel(int sea_level) {
        this.seaLevel = (double) sea_level;
    }

    @JsonProperty("grnd_level")
    public Double getGrndLevel() {
        return grndLevel;
    }

    @JsonProperty("grnd_level")
    public void setGrndLevel(int grnd_level) {
        this.grndLevel = (double) grnd_level;
    }

    @JsonProperty("humidity")
    public Integer getHumidity() {
        return humidity;
    }

    @JsonProperty("humidity")
    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    @JsonProperty("temp_kf")
    public Integer getTempKf() {
        return tempKf;
    }

    @JsonProperty("temp_kf")
    public void setTempKf(Integer tempKf) {
        this.tempKf = tempKf;
    }

    @JsonProperty("dt_txt")
    public String getDtTxt() { return dtTxt; }

    @JsonProperty("dt_txt")
    public void setDtTxt(String dtTxt) { this.dtTxt = dtTxt; }

}
