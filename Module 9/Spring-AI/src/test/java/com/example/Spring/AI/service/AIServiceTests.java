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

    @Test
    public void testSendMessage(){
        var embed = aiService.getEmbedding("This is a big text here");
        System.out.println(embed.length);
        for(float e: embed){
            System.out.println(e+" ");
        }

    }

    @Test
    public void testStoreData(){
        aiService.ingestDataToVectorStore("This is a big text here");
    }

    @Test
    public void testSimilarSearch(){
        var res = aiService.similaritySearch("This is a big text here");
        System.out.println(res);
    }
}
