package com.ezetarg.samples;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ExchangeResponse {
    private Map<String, Double> rates;
    private String base;
    private String date;
}
