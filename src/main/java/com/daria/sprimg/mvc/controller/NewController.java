package com.daria.sprimg.mvc.controller;

import com.daria.sprimg.mvc.model.DayTemperature;
import com.daria.sprimg.mvc.model.Temperature;
import com.daria.sprimg.mvc.service.TemperatureService;
import org.joda.time.DateTime;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@EnableScheduling
@RequestMapping("test")
public class NewController {

    @Autowired
    TemperatureService service;
    DayTemperature dayTemperature;


    //add city choosing
    public List<Temperature> getTemperatureList() throws IOException {
        String url = "https://www.gismeteo.ua/ua/weather-odessa-4982/";
        List<Temperature> temps = new ArrayList<>();

        try {
            Document doc = Jsoup.connect(url).get();

            Element table = doc.select("#tbwdaily1").get(0);
            Elements rows = table.select("tr");

            for (Element row : rows) {
                String head = row.select("th").text();
                Elements spans = row.select("span");
                String cels = spans.get(0).text();
                String fars = spans.get(1).text();
                temps.add(new Temperature(head, Integer.parseInt(cels), Integer.parseInt(fars)));
            }

            for (Temperature temp : temps) {
                System.out.println("Daytime = " + temp.getDayTime() + " C : " + temp.getCelsius() + " F: " + temp.getFahrenheit());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return temps;
    }

    public DayTemperature createDayTemperature() throws IOException {
        DayTemperature dayTemperature = new DayTemperature();
        //dayTemperature.setDay();
        List<Temperature> temps = getTemperatureList();
        double celsius = 0;
        double fahrenheit = 0;
        for (Temperature t : temps) {
            celsius += t.getCelsius();
            fahrenheit += t.getFahrenheit();
        }
        dayTemperature.setCelsius(celsius/temps.size());
        dayTemperature.setFahrenheit(fahrenheit/temps.size());
        return dayTemperature;
    }

    @RequestMapping(value = "/weather")
    public String testWeather(Model model){
        try {
            List<Temperature> temps = getTemperatureList();
            model.addAttribute("tempList", temps);
/*            DayTemperature dayTemperature = createDayTemperature();
            dayTemperature.setDay(new Date());
            service.addDayTemperature(dayTemperature);*/
            model.addAttribute("dayTemp", dayTemperature);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Weather";
    }

    @Scheduled(cron = "0 15 17 * * *")
    public void scheduledTask(){
        try {
            DayTemperature dayTemperature = createDayTemperature();
            dayTemperature.setDay(new Date());
            service.addDayTemperature(dayTemperature);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Task was executed!");
    }
}
