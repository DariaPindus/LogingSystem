package com.daria.sprimg.mvc.extra;

import org.springframework.scheduling.annotation.Scheduled;


public class Schedulers {

    private int i = 0;

    @Scheduled(cron = "0 05 17 * * *")
    public void scheduleTest() {
        i++;
        System.out.println("This is" + i + " test");
    }
}
