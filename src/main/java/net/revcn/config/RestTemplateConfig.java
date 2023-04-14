package net.revcn.config;

import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    
    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
        return new RestTemplate(factory);
    }

    @Bean
    public HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory() {
        var factory = new HttpComponentsClientHttpRequestFactory();
        factory.setHttpClient(
            HttpClientBuilder.create().useSystemProperties().setRedirectStrategy(new LocationRedirectStrategy()).build()
        );
        factory.setConnectionRequestTimeout(5000);
        factory.setConnectTimeout(15000);
        return factory;
    }
}
