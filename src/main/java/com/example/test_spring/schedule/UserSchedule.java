package com.example.test_spring.schedule;

import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class UserSchedule {
    // second minute hour day month year
    @Scheduled(cron = "0 * * * * *" , zone = "Asia/Bangkok")
    //every minute
    public void testEveryMinute(){
        log.info("Yooooo");
    }
    //every midnight
    public void testEveryMidnight(){

    }
}
