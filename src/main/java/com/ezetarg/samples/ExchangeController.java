package com.ezetarg.samples;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ExchangeController {
    private static final String ENDPOINT = "https://api.exchangeratesapi.io/latest";

    private final RestTemplate restTemplate;

    public ExchangeController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping(value = "/rates")
    public ExchangeResponse getRates() {
        return restTemplate.getForObject(ENDPOINT, ExchangeResponse.class);
    }

    @RequestMapping("test")
    public String test() {
        return "hola mundo! " + Math.random();
    }
}
