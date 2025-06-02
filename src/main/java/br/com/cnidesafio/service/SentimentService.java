package br.com.cnidesafio.service;

import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.Sentiment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SentimentService {

    private final LanguageServiceClient language;

    public String analisarSentimentoExterno(String texto) {
        try {
            log.info("[start] SentimentService - analisarSentimentoExterno");

            Document doc = Document.newBuilder()
                    .setContent(texto)
                    .setType(Document.Type.PLAIN_TEXT)
                    .setLanguage("pt")
                    .build();

            Sentiment sentiment = language.analyzeSentiment(doc).getDocumentSentiment();
            float score = sentiment.getScore();

            if (score >= 0.25) return "positivo";
            if (score <= -0.25) return "negativo";

            log.info("[finish] SentimentService - analisarSentimentoExterno");
            return "neutro";
        } catch (Exception e) {
            return "neutro";
        }
    }
}
