package com.daria.sprimg.mvc.model;

import javax.persistence.*;


public class Temperature {

    private String dayTime;
    private int celsius;
    private int fahrenheit;


    public Temperature(){}

    public Temperature (String dayTime, int celsius, int fahrenheit) {
        this.celsius = celsius;
        this.fahrenheit = fahrenheit;
        this.dayTime = dayTime;
    }

    public String getDayTime() {
        return dayTime;
    }

    public void setDayTime(String dayTime) {
        this.dayTime = dayTime;
    }

    public int getCelsius() {
        return celsius;
    }

    public void setCelsius(int celsius) {
        this.celsius = celsius;
    }

    public int getFahrenheit() {
        return fahrenheit;
    }

    public void setFahrenheit(int fahrenheit) {
        this.fahrenheit = fahrenheit;
    }
}
