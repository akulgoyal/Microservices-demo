package com.akul.microservices.wordprovider.controller;

import com.akul.microservices.wordprovider.controller.domain.Word;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class WordController {

    @Value("${words}")
    private String words;

    @RequestMapping("/")
    public @ResponseBody
    Word getWord() {
        String[] wordArray = words.split(",");
        int i = (int)Math.round(Math.random() * (wordArray.length - 1));
        return new Word(wordArray[i]);
    }
}