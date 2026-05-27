package com.example.learnMultithread;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MySchedular {

    @Scheduled(cron = "*/5 * * * * *") //this is blocking in nature this is concurrent
    @Async("jobExecutor")
    void logMe(){
        log.info("Schedular1 started...{}" , Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("Schedular1 ended...{}" , Thread.currentThread().getName());
    }

    @Scheduled(fixedRate = 1000) //this is blocking in nature this is concurrent
    void logYou(){
        log.info("Schedular2 started...{}" , Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("Schedular2 ended...{}" , Thread.currentThread().getName());
    }
}
