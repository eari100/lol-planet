package com.lolplanet.demo.api.riot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RiotApi {

    RestTemplate restTemplate = new RestTemplate();

    private final String baseUrl = "https://kr.api.riotgames.com/";

    @Value("${riot.api.key}")
    String apiKey;

    public Object callApi(String delimitedUrl) {
        String url = baseUrl + delimitedUrl + "?api_key=" + apiKey;
        return restTemplate.getForObject(url, Object.class);
    }
}
