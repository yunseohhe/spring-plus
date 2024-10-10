package org.example.expert.client.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class WeatherDto {

    private final String date;
    private final String weather;

    @JsonCreator
    public WeatherDto(@JsonProperty("date") String date, @JsonProperty("weather") String weather) {
        this.date = date;
        this.weather = weather;
    }
}
