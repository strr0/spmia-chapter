package com.strr.licenses.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiscoveryService {
    private final DiscoveryClient discoveryClient;

    @Autowired
    public DiscoveryService(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    public List<String> getEurekaServices() {
        List<String> services = new ArrayList<>();
        discoveryClient.getServices().forEach(service -> {
            discoveryClient.getInstances(service).forEach(instance -> {
                services.add(String.format("%s: %s", service, instance.getUri()));
            });
        });
        return services;
    }
}
