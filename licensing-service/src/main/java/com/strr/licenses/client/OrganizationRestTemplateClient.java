package com.strr.licenses.client;

import com.strr.licenses.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OrganizationRestTemplateClient {
    private final RestTemplate restTemplate;

    @Autowired
    public OrganizationRestTemplateClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Organization getOrganization(String organizationId) {
        ResponseEntity<Organization> exchange = restTemplate.exchange("http://organizationservice/v1/organizations/{organizationId}",
                HttpMethod.GET,
                null,
                Organization.class,
                organizationId);
        return exchange.getBody();
    }
}
