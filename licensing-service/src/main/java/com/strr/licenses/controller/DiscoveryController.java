package com.strr.licenses.controller;

import com.strr.licenses.service.DiscoveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/discovery")
public class DiscoveryController {
    private final DiscoveryService discoveryService;

    @Autowired
    public DiscoveryController(DiscoveryService discoveryService) {
        this.discoveryService = discoveryService;
    }

    @GetMapping("/services")
    public List<String> getEurekaServices() {
        return discoveryService.getEurekaServices();
    }
}
