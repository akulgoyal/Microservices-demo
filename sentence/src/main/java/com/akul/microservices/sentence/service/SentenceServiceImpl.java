package com.akul.microservices.sentence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SentenceServiceImpl implements SentenceService {

    private WordService wordService;

    @Autowired
    public SentenceServiceImpl(WordService wordService) {
        this.wordService = wordService;
    }

    @Override
    public String buildSentence() {
        return String.format("%s %s %s %s %s",
                wordService.getSubject(),
                wordService.getVerb(),
                wordService.getArticle(),
                wordService.getAdjective(),
                wordService.getNoun());
    }
}
