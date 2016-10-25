package com.daria.sprimg.mvc.service;

import com.daria.sprimg.mvc.model.DayTemperature;
import com.daria.sprimg.mvc.model.Temperature;
import com.daria.sprimg.mvc.repository.TemperatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemperatureService {

    @Autowired
    private TemperatureRepository repository;

    public List<Temperature> getDayTemperature() {
        return repository.getDayTemperature();
    }

    public void addDayTemperature(DayTemperature dayTemperature) {
        repository.addDayTemperature(dayTemperature);
    }
}
