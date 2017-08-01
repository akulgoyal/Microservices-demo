package com.akul.microservices.sentence.service;

import com.akul.microservices.sentence.dao.*;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WordServiceImpl implements WordService {

    private SubjectClient subjectClient;
    private VerbClient verbClient;
    private ArticleClient articleClient;
    private AdjectiveClient adjectiveClient;
    private NounClient nounClient;

    @Autowired
    public WordServiceImpl(SubjectClient subjectClient, VerbClient verbClient, ArticleClient articleClient,
                           AdjectiveClient adjectiveClient, NounClient nounClient) {
        this.subjectClient = subjectClient;
        this.verbClient = verbClient;
        this.articleClient = articleClient;
        this.adjectiveClient = adjectiveClient;
        this.nounClient = nounClient;
    }

    @Override
    @HystrixCommand(fallbackMethod = "getFallbackSubject")
    public String getSubject() {
        return subjectClient.getWord();
    }

    @Override
    @HystrixCommand(fallbackMethod = "getFallbackVerb")
    public String getVerb() {
        return verbClient.getWord();
    }

    @Override
    @HystrixCommand(fallbackMethod = "getFallbackArticle")
    public String getArticle() {
        return articleClient.getWord();
    }

    @Override
    @HystrixCommand(fallbackMethod = "getFallbackAdjective")
    public String getAdjective() {
        return adjectiveClient.getWord();
    }

    @Override
    @HystrixCommand(fallbackMethod = "getFallbackNoun")
    public String getNoun() {
        return nounClient.getWord();
    }

    public String getFallbackSubject() {
        return "someone";
    }

    public String getFallbackAdjective() {
        return "";
    }

    public String getFallbackNoun() {
        return "something";
    }

    public String getFallbackVerb() {
        return "did";
    }

    public String getFallbackArticle() {
        return "a";
    }
}
