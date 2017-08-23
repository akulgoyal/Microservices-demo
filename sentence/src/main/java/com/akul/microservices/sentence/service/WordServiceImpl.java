package com.akul.microservices.sentence.service;

import com.akul.microservices.sentence.dao.*;
import com.akul.microservices.sentence.domain.Word;
import com.akul.microservices.sentence.domain.Word.Role;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.concurrent.Executor;

@Service
@CacheConfig(cacheNames = "words")
public class WordServiceImpl implements WordService {

    private SubjectClient subjectClient;
    private VerbClient verbClient;
    private ArticleClient articleClient;
    private AdjectiveClient adjectiveClient;
    private NounClient nounClient;
    private Executor executor;

    @Autowired
    public WordServiceImpl(SubjectClient subjectClient, VerbClient verbClient, ArticleClient articleClient,
                           AdjectiveClient adjectiveClient, NounClient nounClient, Executor executor) {
        this.subjectClient = subjectClient;
        this.verbClient = verbClient;
        this.articleClient = articleClient;
        this.adjectiveClient = adjectiveClient;
        this.nounClient = nounClient;
        this.executor = executor;
    }

    /*@Override
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
    }*/

    @Override
    @HystrixCommand(fallbackMethod="getFallbackSubject")
    //@Cacheable
    public Observable<Word> getSubject() {
        //	This 'reactive' observable is backed by a regular Java Callable, which can run in a different thread:
        return Observable.fromCallable(
                () ->  new Word (subjectClient.getWord().getWord(), Role.subject)
        ).subscribeOn(Schedulers.from(executor));
    }

    @Override
    @HystrixCommand(fallbackMethod="getFallbackVerb")
    public Observable<Word> getVerb() {
        return Observable.fromCallable(
                () ->  new Word (verbClient.getWord().getWord(), Role.verb)
        ).subscribeOn(Schedulers.from(executor));
    }

    @Override
    @HystrixCommand(fallbackMethod="getFallbackArticle")
    public Observable<Word> getArticle() {
        return Observable.fromCallable(
                () ->  new Word (articleClient.getWord().getWord(), Role.article)
        ).subscribeOn(Schedulers.from(executor));
    }

    @Override
    @HystrixCommand(fallbackMethod="getFallbackNoun")
    public Observable<Word> getNoun() {
        return Observable.fromCallable(
                () ->  new Word (nounClient.getWord().getWord(), Role.noun)
        ).subscribeOn(Schedulers.from(executor));
    }

    @Override
    @HystrixCommand(fallbackMethod="getFallbackAdjective")
    public Observable<Word> getAdjective() {
        return Observable.fromCallable(
                () ->  new Word (adjectiveClient.getWord().getWord(), Role.adjective)
        ).subscribeOn(Schedulers.from(executor));
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
