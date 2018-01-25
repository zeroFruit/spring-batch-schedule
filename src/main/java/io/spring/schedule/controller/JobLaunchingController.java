package io.spring.schedule.controller;

import io.spring.schedule.domain.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
public class JobLaunchingController {
    private static final Logger logger = LoggerFactory.getLogger(JobLaunchingController.class);
    @Autowired
    private JobOperator jobOperator;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value="/")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public long launch(@RequestParam("name") String name) throws Exception {
        return this.jobOperator.start("job", String.format("name=%s", name));
    }

    @GetMapping(value="/random")
    @ResponseStatus(HttpStatus.OK)
    public Quote getRandomQuote() {
        Quote quote = restTemplate.getForEntity(
                "https://gturnquist-quoters.cfapps.io/api/random", Quote.class
        ).getBody();
        logger.info(">> quote: {}", quote.toString());
        return quote;
    }

    @DeleteMapping(value="/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void stop(@PathVariable("id") Long id) throws Exception {
        this.jobOperator.stop(id);
    }

    @PatchMapping(value="/{id}")
    @ResponseStatus(HttpStatus.OK)
    public long restart(@PathVariable("id") Long id) throws Exception {
        return this.jobOperator.restart(id);
    }

}
