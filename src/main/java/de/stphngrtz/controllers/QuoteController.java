package de.stphngrtz.controllers;

import de.stphngrtz.models.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @see <a href="https://spring.io/guides/gs/consuming-rest/">Consuming a RESTful Web Service</a>
 */
@RestController
public class QuoteController {

    private static final Logger log = LoggerFactory.getLogger(QuoteController.class);

    @RequestMapping(value = "/quote", method = RequestMethod.GET)
    public Quote quote() {
        Quote quote = new RestTemplate().getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
        log.info("Quote: {}", quote);
        return quote;
    }
}
