package com.lolplanet.demo.api.riot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RiotApi {

    RestTemplate restTemplate = new RestTemplate();

    @Value("${riot.api.key}")
    String apiKey;

    public <T> T callApi(String delimitedUrl, Class<T> clazz) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Riot-Token", apiKey);

        HttpEntity reqEntity = new HttpEntity(headers);

        return restTemplate.exchange(delimitedUrl, HttpMethod.GET, reqEntity, clazz).getBody();
    }
}
