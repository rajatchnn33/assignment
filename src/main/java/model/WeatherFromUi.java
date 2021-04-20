package model;

import java.math.BigDecimal;

public class WeatherFromUi {
    private String city;
    private String condition;
    private String Wind;
    private BigDecimal humidityPercentage;
    private BigDecimal tempInDegrees;
    private BigDecimal tempInFahrenheit;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getWind() {
        return Wind;
    }

    public void setWind(String wind) {
        Wind = wind;
    }

    public BigDecimal getHumidityPercentage() {
        return humidityPercentage;
    }

    public void setHumidityPercentage(BigDecimal humidityPercentage) {
        this.humidityPercentage = humidityPercentage;
    }

    public BigDecimal getTempInDegrees() {
        return tempInDegrees;
    }

    public void setTempInDegrees(BigDecimal tempInDegrees) {
        this.tempInDegrees = tempInDegrees;
    }

    public BigDecimal getTempInFahrenheit() {
        return tempInFahrenheit;
    }

    public void setTempInFahrenheit(BigDecimal tempInFahrenheit) {
        this.tempInFahrenheit = tempInFahrenheit;
    }


}
