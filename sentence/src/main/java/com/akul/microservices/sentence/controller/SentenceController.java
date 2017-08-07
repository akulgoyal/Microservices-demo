package com.akul.microservices.sentence.controller;

import com.akul.microservices.sentence.service.SentenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SentenceController {


    private static Logger log = LoggerFactory.getLogger(SentenceController.class);
    /*@Autowired
    private DiscoveryClient client;*/

    /*@Autowired
    private LoadBalancerClient client;*/

    @Autowired
    private SentenceService sentenceService;

    @RequestMapping("/sentence")
    public @ResponseBody
    String getSentence() {
        /*return
                getWord("SUBJECT") + " "
                        + getWord("VERB") + " "
                        + getWord("ARTICLE") + " "
                        + getWord("ADJECTIVE") + " "
                        + getWord("NOUN") + "."
                ;*/

        log.info("Started to create sentences");
        long startTime = System.currentTimeMillis();

        String output = "<h3>Some Sentences</h3><br/>" +
                sentenceService.buildSentence() + "<br/><br/>" +
                sentenceService.buildSentence() + "<br/><br/>" +
                sentenceService.buildSentence() + "<br/><br/>" +
                sentenceService.buildSentence() + "<br/><br/>" +
                sentenceService.buildSentence() + "<br/><br/>"
                ;

        long endTime = System.currentTimeMillis();

        log.info("Sentence completed");
        return output + "Elapsed time (ms): " + (endTime-startTime);
    }

    /*public String getWord(String service) {
        *//*List<ServiceInstance> list = client.getInstances(service);
        if (list != null && list.size() > 0 ) {
            URI uri = list.get(0).getUri();
            if (uri !=null ) {
                return (new RestTemplate()).getForObject(uri,String.class);
            }
        }
        return null;*//*

        ServiceInstance serviceInstance = client.choose(service);
        URI uri = serviceInstance.getUri();
        if (uri !=null ) {
            return (new RestTemplate()).getForObject(uri,String.class);
        }
        return null;
    }*/
}