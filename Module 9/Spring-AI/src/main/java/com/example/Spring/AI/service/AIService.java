package com.example.Spring.AI.service;

import com.example.Spring.AI.dto.Joke;
import io.netty.channel.embedded.EmbeddedChannel;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AIService {
    private final ChatClient chatClient;
    public final EmbeddingModel embeddingModel;
    private final VectorStore vectorStore;

    public float[] getEmbedding(String text){
        return embeddingModel.embed(text);
    }

    // to store data you have to create in a document
//    public void ingestDataToVectorStore(String text){
//        Document document = new Document(text);
//        vectorStore.add(List.of(document));
//    }

    public void ingestDataToVectorStore() {
        List<Document> movies = List.of(
                new Document("A thief who steals corporate secrets through the use of dream-sharing technology.",
                        Map.of("title", "Inception", "genre", "Sci-Fi", "year", 2010)),

                new Document("A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival.",
                        Map.of("title", "Interstellar", "genre", "Sci-Fi", "year", 2014)),

                new Document("A poor yet passionate young man falls in love with a rich young woman, giving her a sense of freedom.",
                        Map.of("title", "The Notebook", "genre", "Romance", "year", 2004))
        );
        vectorStore.add(movies);
    }

    public List<Document> similaritySearch(String text){
        return vectorStore.similaritySearch(text);
    }

    public String getJoke(String topic){

        String systemPrompt = """
                You are a sarcastic joker, you make poetic jokes in 4 Lines.
                You dont make jokes about politics
                Give a joke on the topic: {topic}
                """;
        PromptTemplate promptTemplate = new PromptTemplate(systemPrompt);
        String renderText = promptTemplate.render(Map.of("topic", topic));
        var response =  chatClient.prompt()
                .user(renderText)
                .advisors(
                        new SimpleLoggerAdvisor()
                )
                .call()
                .entity(Joke.class);

        return response.text();
    }

}
