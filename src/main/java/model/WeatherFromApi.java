package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;


public class WeatherFromApi {



    @JsonIgnore()
    @JsonProperty("coord")
    private Object coord;

    @JsonIgnore
    @JsonProperty("weather")
    private Object weather;

    @JsonProperty("base")
    private String base;

    @JsonProperty("visibility")
    private Integer visibility;
    @JsonProperty("wind")
    private Object wind;
    @JsonProperty("clouds")
    private Object clouds;
    @JsonProperty("dt")
    private Integer dt;
    @JsonProperty("sys")
    private Object sys;
    @JsonProperty("timezone")
    private Integer timezone;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("cod")
    private Integer cod;
    @JsonProperty("name")
    private String city;
    @JsonProperty("main")
    private HashMap<String,String> temperature;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public HashMap<String, String> getTemperature() {
        return temperature;
    }

    public void setTemperature(HashMap<String, String> temperature) {
        this.temperature = temperature;
    }
}
