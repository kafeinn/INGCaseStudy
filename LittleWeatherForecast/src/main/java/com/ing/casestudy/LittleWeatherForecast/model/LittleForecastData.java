package com.ing.casestudy.LittleWeatherForecast.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "dt_txt",
        "feels_like",
        "temp_max",
        "humidity"
})
public class LittleForecastData implements IForecastData {
    @JsonProperty("feels_like")
    private Double feels_like;
    @JsonProperty("temp_max")
    private Double tempMax;
    @JsonProperty("humidity")
    private Integer humidity;
    @JsonProperty("dt_txt")
    private String dtTxt;


    @JsonProperty("feels_like")
    public Double getFeelsLike() { return feels_like; }

    @JsonProperty("feels_like")
    public void setFeelsLike(Double feels_like) { this.feels_like = feels_like; }

    @JsonProperty("temp_max")
    public Double getTempMax() {
        return tempMax;
    }

    @JsonProperty("temp_max")
    public void setTempMax(int temp_max) {
        this.tempMax = (double) temp_max;
    }
    @JsonProperty("humidity")
    public Integer getHumidity() {
        return humidity;
    }

    @JsonProperty("humidity")
    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    @JsonProperty("dt_txt")
    public String getDtTxt() { return dtTxt; }

    @JsonProperty("dt_txt")
    public void setDtTxt(String dtTxt) { this.dtTxt = dtTxt; }

}
