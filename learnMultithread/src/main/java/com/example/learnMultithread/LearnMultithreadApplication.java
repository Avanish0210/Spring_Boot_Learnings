package com.example.learnMultithread;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.Instant;
import java.util.concurrent.*;

@SpringBootApplication
@Slf4j
@EnableScheduling
@EnableAsync
public class LearnMultithreadApplication implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        taskScheduler.schedule(()->{
            log.info("Running after 2 sec");
        } , Instant.ofEpochSecond(2));
    }

    @Autowired
    private TaskScheduler taskScheduler;

	public static void main(String[] args) throws ExecutionException, InterruptedException {
        SpringApplication.run(LearnMultithreadApplication.class, args);


//      learnFuture();
//        learnCF2();
//        log.info("After the method call");
    }
//    static void learnThread() {
//        log.info("Before thread, Name of thread: {}, State: {}", Thread.currentThread().getName(), Thread.currentThread().getState());
//
//        Thread workerThread = new Thread(() -> {
//            log.info("Inside the thread Name of thread: {}, State: {}", Thread.currentThread().getName(), Thread.currentThread().getState());
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        );
//        workerThread.start();
//
////        workerThread.join(); // block the calling thread
//
//        log.info("After worker thread, State of worker: {}", workerThread.getState());
//
//        log.info("After thread");
//    }
//
//    static void learnFuture() throws ExecutionException, InterruptedException {
//        try (ExecutorService executorService = Executors.newFixedThreadPool(10)) {
//
//            Future<String> myNameFuture =  executorService.submit(() -> getName());
//
//            myNameFuture.get(); //block the calling thread
//            log.info("After name future : {}" , Thread.currentThread().getName());
//        }
//
//    }
//
//    static void learnCompletableFuture(){
//        CompletableFuture<String> myNameCF = CompletableFuture
//                .supplyAsync(()->getName())
//                        .thenApply(name -> name.toUpperCase())
//                                .thenApply(uperCaseName -> uperCaseName.length())
//                                        .thenApplyAsync(lengthOfName->{
//                                            log.info("Inside method with length");
//                                            if(true) throw new RuntimeException("Faking an error");
//                                            return "length was "+ lengthOfName;
//                                        })
//                                                .exceptionally((err)->{
//                                                    return "default value in case of failure";
//                                                });
//
//
//        myNameCF.thenAccept(name -> {
//            log.info("Got the name length: {}", name);
//        });
//        //this will not block the main thread
//    }
//
//    static void learnCF2(){
//        CompletableFuture<String> nameFuture = CompletableFuture.supplyAsync(()->getName());
//        CompletableFuture<String> addressFuture = CompletableFuture.supplyAsync(()->getAddress());
//
//        CompletableFuture.allOf(nameFuture,addressFuture);
//
//        log.info("Got the name: {} and adddress here: {}" , nameFuture.join(),addressFuture.join());
//    }
//
//    static String getName(){
//        try {
//            log.info("Inside name future : {}" , Thread.currentThread().getName());
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        return "Avanish";
//
//    }
//
//    static String getAddress(){
//        try {
//            log.info("Inside AddressFuture : {}" , Thread.currentThread().getName());
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        return "New Delhi";
//
//    }
}
