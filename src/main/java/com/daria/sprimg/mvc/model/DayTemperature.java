package com.daria.sprimg.mvc.model;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "day_temperatures")
public class DayTemperature {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

   @Column
   @Temporal(TemporalType.DATE)
    private java.util.Date day;

    private double celsius;

    private double fahrenheit;

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public double getCelsius() {
        return celsius;
    }

    public void setCelsius(double celsius) {
        this.celsius = celsius;
    }

    public double getFahrenheit() {
        return fahrenheit;
    }

    public void setFahrenheit(double fahrenheit) {
        this.fahrenheit = fahrenheit;
    }
}
