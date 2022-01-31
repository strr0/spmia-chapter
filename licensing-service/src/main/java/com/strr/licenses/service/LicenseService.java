package com.strr.licenses.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.strr.licenses.client.OrganizationRestTemplateClient;
import com.strr.licenses.config.ServiceConfig;
import com.strr.licenses.model.License;
import com.strr.licenses.model.Organization;
import com.strr.licenses.repository.LicenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class LicenseService {
    private final LicenseRepository licenseRepository;
    private final ServiceConfig serviceConfig;
    private final OrganizationRestTemplateClient organizationRestTemplateClient;

    @Autowired
    public LicenseService(LicenseRepository licenseRepository,
                          ServiceConfig serviceConfig,
                          OrganizationRestTemplateClient organizationRestTemplateClient) {
        this.licenseRepository = licenseRepository;
        this.serviceConfig = serviceConfig;
        this.organizationRestTemplateClient = organizationRestTemplateClient;
    }

    private Organization getOrganization(String organizationId) {
        return organizationRestTemplateClient.getOrganization(organizationId);
    }

    public License getLicense(String organizationId, String licenseId) {
        License license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);
        Organization org = getOrganization(organizationId);
        return license
                .withOrganizationName( org.getName())
                .withContactName( org.getContactName())
                .withContactEmail( org.getContactEmail() )
                .withContactPhone( org.getContactPhone() )
                .withComment(serviceConfig.getExampleProperty());
    }

    private void randomlyRunLong() {
        Random random = new Random();
        int randomNum = random.nextInt(3);
        if (randomNum == 2) {
            throw BlockException.THROW_OUT_EXCEPTION;
        }
    }

    @SentinelResource(value = "getLicensesByOrg", fallback = "buildFallbackLicenseList")
    public List<License> getLicensesByOrg(String organizationId) {
        randomlyRunLong();
        return licenseRepository.findByOrganizationId(organizationId);
    }

    // 回退
    public List<License> buildFallbackLicenseList(String organizationId) {
        List<License> fallbackList = new ArrayList<>();
        License license = new License()
                .withId("0000000-00-00000")
                .withOrganizationId( organizationId )
                .withProductName("Sorry no licensing information currently available");

        fallbackList.add(license);
        return fallbackList;
    }

    public void saveLicense(License license) {
        license.withId( UUID.randomUUID().toString());
        licenseRepository.save(license);
    }

    public void updateLicense(License license) {
        licenseRepository.save(license);
    }

    public void deleteLicense(String licenseId) {
        licenseRepository.deleteById(licenseId);
    }
}
