package com.systemdesign.distributedcache.loadbalancer.util;

import org.apache.http.client.config.*;
import org.apache.http.conn.ssl.*;
import org.apache.http.impl.client.*;
import org.apache.tomcat.util.net.*;
import org.springframework.boot.web.client.*;
import org.springframework.http.*;
import org.springframework.http.client.*;
import org.springframework.stereotype.*;
import org.springframework.util.*;
import org.springframework.web.client.*;

import javax.annotation.*;

@Component
public class RestTemplateUtil {

    private RestTemplate restTemplate;

    public RestTemplateUtil() {

    }

    private HttpComponentsClientHttpRequestFactory getSocketFactory() {

        RequestConfig requestConfig = RequestConfig.custom().setRedirectsEnabled(true).build();

        HttpClientBuilder httpClientBuilder = HttpClients.custom();
        httpClientBuilder.setDefaultRequestConfig(requestConfig);

        CloseableHttpClient httpClient = httpClientBuilder.build();
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
        return factory;
    }

    public String constructURL(String url, String... placeHolders) {
        StringBuilder replacedURL = new StringBuilder(url);
        for (String placeHolder : placeHolders) {
            int indexFirst = replacedURL.toString().indexOf('{');
            int indexSecond = replacedURL.toString().indexOf('}');
            replacedURL.replace(indexFirst, indexSecond + 1, placeHolder);
        }
        return replacedURL.toString();
    }

    public <Response> Response postObjectFromAPI(String url, Object request, Class<Response> responseType,
            String... placeHolders) throws RestClientException {
        String replacedURL = constructURL(url, placeHolders);
        HttpEntity<Object> requestEntity = new HttpEntity<>(request);
        return restTemplate.postForObject(replacedURL, requestEntity, responseType);
    }

    public void putObjectFromAPI(String url, String authPayload, Object request) throws RestClientException {
        String replacedURL = url.substring(0, url.lastIndexOf('/'));
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Authorization", authPayload);
        headers.add("content-type", "application/json");
        HttpEntity<Object> requestEntity = new HttpEntity<>(request, headers);
        restTemplate.put(replacedURL, requestEntity);
    }

    public void putObjectFromAPI(String url, Object request, String... placeHolders) throws RestClientException {
        String replacedURL = constructURL(url, placeHolders);
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("content-type", "application/json");
        HttpEntity<Object> requestEntity = new HttpEntity<>(request, headers);
        restTemplate.put(replacedURL, requestEntity);
    }

    public <Response> Response postObjectFromAPI(String url, String contentType, Object request,
            Class<Response> responseType, String... placeHolders) throws RestClientException {
        String replacedURL = constructURL(url, placeHolders);
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("content-type", contentType);
        HttpEntity<Object> requestEntity = new HttpEntity<>(request, headers);
        return restTemplate.postForObject(replacedURL, requestEntity, responseType);
    }

    public <Response> Response postObjectFromAPI(String url, String contentType, String authPayload, Object request,
            Class<Response> responseType) throws RestClientException {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("content-type", contentType);
        headers.add("Authorization", authPayload);
        HttpEntity<Object> requestEntity = new HttpEntity<>(request, headers);
        return restTemplate.postForObject(url, requestEntity, responseType);
    }

    public <S> S getObjectFromAPI(String url, Class<S> destinationType, String... placeHolders)
            throws RestClientException {
        String replacedURL = constructURL(url, placeHolders);
        return restTemplate.getForObject(replacedURL, destinationType);
    }

    public <S> S getObjectFromAPI(String url, String authPayload, Class<S> destinationType, String... placeHolders)
            throws RestClientException {
        String replacedURL = constructURL(url, placeHolders);
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Authorization", authPayload);
        return restTemplate.exchange(replacedURL, HttpMethod.GET, new HttpEntity<>(null, headers), destinationType)
                .getBody();
    }

    @PostConstruct public void init() {
        this.restTemplate = getRestTemplateBuilder().build();
    }

    public RestTemplateBuilder getRestTemplateBuilder() {
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        return restTemplateBuilder.requestFactory(() -> getSocketFactory());
    }

}