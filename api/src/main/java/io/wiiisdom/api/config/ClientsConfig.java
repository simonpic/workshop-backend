package io.wiiisdom.api.config;

import org.keycloak.admin.client.Keycloak;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class ClientsConfig {

    @Value("${clients.ticket-service.base-url}")
    private String ticketMsBaseUrl;

    @Value("${clients.user-service.base-url}")
    private String userMsBaseUrl;

    private final KeycloakProperties keycloakProperties;

    @Autowired
    public ClientsConfig(KeycloakProperties keycloakProperties) {
        this.keycloakProperties = keycloakProperties;
    }

    @Bean(name = "ticketMsClient")
    public RestTemplate ticketMsClient() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(ticketMsBaseUrl));
        return restTemplate;
    }

    @Bean(name = "userMsClient")
    public RestTemplate userMsClient() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(userMsBaseUrl));
        return restTemplate;
    }

    @Bean
    public Keycloak keycloak() {
        return Keycloak.getInstance(keycloakProperties.getServerUrl(),
                keycloakProperties.getRealm(), keycloakProperties.getUsername(),
                keycloakProperties.getPassword(), keycloakProperties.getClientId());
    }

}
