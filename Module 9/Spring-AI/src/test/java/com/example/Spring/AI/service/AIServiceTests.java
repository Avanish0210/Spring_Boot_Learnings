package com.example.Spring.AI.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AIServiceTests {
    @Autowired
    private AIService aiService;

    @Test
    public void testGetJoke(){
        var joke = aiService.getJoke("cat");
        System.out.println(joke);
    }
}
