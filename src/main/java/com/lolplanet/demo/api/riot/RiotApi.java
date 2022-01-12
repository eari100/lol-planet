package com.lolplanet.demo.api.riot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RiotApi {

    RestTemplate restTemplate = new RestTemplate();

    @Value("${riot.api.key}")
    String apiKey;

    public <T> T callApi(String delimitedUrl, Class<T> clazz) {
        String url = delimitedUrl + "?api_key=" + apiKey;
        return restTemplate.getForObject(url, clazz);
    }
}
