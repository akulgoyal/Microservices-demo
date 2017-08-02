package com.akul.microservices.sentence.service;

import com.akul.microservices.sentence.domain.Word;
import rx.Observable;

public interface WordService {

    /*Word getSubject();

    Word getVerb();

    Word getArticle();

    Word getAdjective();

    Word getNoun();*/

    Observable<Word> getSubject();
    Observable<Word> getVerb();
    Observable<Word> getArticle();
    Observable<Word> getAdjective();
    Observable<Word> getNoun();
}
