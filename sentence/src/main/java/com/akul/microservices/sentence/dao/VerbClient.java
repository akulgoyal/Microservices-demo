package com.akul.microservices.sentence.dao;

import com.akul.microservices.sentence.domain.Word;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("VERB")
public interface VerbClient {

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public Word getWord();
}
