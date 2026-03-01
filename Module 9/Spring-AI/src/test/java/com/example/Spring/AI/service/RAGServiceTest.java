package com.example.Spring.AI.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RAGServiceTest {

    @Autowired
    private RAGService ragService;

    @Test
    public void testIngest() {
        ragService.injectPdfToVectorStore();
    }

    @Test
    public void testAskAI() {
        String res = ragService.askAI("Cant view the live class, what to do?");
        System.out.println(res);
    }
    @Test
    public void testAskAIWithAdvisor() {
        String res = ragService.askAIWithAdvisor("what are your view on mobile gaming" , "avanish0210");
        System.out.println(res);
    }


}
