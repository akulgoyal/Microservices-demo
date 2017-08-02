package com.akul.microservices.sentence.service;

import com.akul.microservices.sentence.dao.*;
import com.akul.microservices.sentence.domain.Word;
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
    public Word getSubject() {
        return subjectClient.getWord();
    }

    @Override
    @HystrixCommand(fallbackMethod = "getFallbackVerb")
    public Word getVerb() {
        return verbClient.getWord();
    }

    @Override
    @HystrixCommand(fallbackMethod = "getFallbackArticle")
    public Word getArticle() {
        return articleClient.getWord();
    }

    @Override
    @HystrixCommand(fallbackMethod = "getFallbackAdjective")
    public Word getAdjective() {
        return adjectiveClient.getWord();
    }

    @Override
    @HystrixCommand(fallbackMethod = "getFallbackNoun")
    public Word getNoun() {
        return nounClient.getWord();
    }

    public Word getFallbackSubject() {
        return new Word("someone", Word.Role.subject);
    }

    public Word getFallbackAdjective() {
        return new Word("", Word.Role.adjective);
    }

    public Word getFallbackNoun() {
        return new Word("something", Word.Role.noun);
    }

    public Word getFallbackVerb() {
        return new Word("did", Word.Role.verb);
    }

    public Word getFallbackArticle() {
        return new Word("a", Word.Role.article);
    }
}
