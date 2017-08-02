package com.akul.microservices.sentence.service;

import com.akul.microservices.sentence.domain.Word;

public interface WordService {

    Word getSubject();

    Word getVerb();

    Word getArticle();

    Word getAdjective();

    Word getNoun();
}
